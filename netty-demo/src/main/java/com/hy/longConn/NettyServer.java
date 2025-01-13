package com.hy.longConn;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;

import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Description: netty实现webSocket demo
 *
 * @author: yhong
 * Date: 2024/11/14
 */
public class NettyServer {
    private static final CopyOnWriteArrayList<Channel> channels = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 创建 bootstrap
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(
                                    new HttpServerCodec(),
                                    new HttpObjectAggregator(65536),
                                    new WebSocketServerProtocolHandler("/ws"), // 支持ws
                                    new WebSocketFrameHandler() // 自定义ws页面消息处理器
                            );
                        }
                    });

            // 绑定端口 8080
            ChannelFuture future = serverBootstrap.bind(8080).sync();
            System.out.println("Server started on port 8080...");

            // 处理控制台输入并广播给页面连接
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String msg = scanner.nextLine();
                if (msg.equalsIgnoreCase("exit")) {
                    break;
                }
                broadcastToClients(msg);
            }

            // 等待server socket关闭
            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    // 向所有连接的客户端广播消息
    private static void broadcastToClients(String message) {
        if (message.isEmpty()) return;
        for (Channel channel : channels) {
            if (channel.isActive()) {
                channel.writeAndFlush(new TextWebSocketFrame(message));
            }
        }
    }

    // 处理输入的Webocket帧（来自客户端的消息）
    public static class WebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
            if (frame instanceof TextWebSocketFrame) {
                String message = ((TextWebSocketFrame) frame).text();
                System.out.println("Received from client: " + message);
            }
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            channels.add(ctx.channel());
            System.out.println("New client connected: " + ctx.channel().remoteAddress());
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            channels.remove(ctx.channel());
            System.out.println("Client disconnected: " + ctx.channel().remoteAddress());
        }
    }
}
