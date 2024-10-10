package com.hy.service;

import com.hy.handler.HttpContentHandler;
import com.hy.handler.HttpRequestHandler;
import com.hy.handler.HttpResponseHandler;
import com.hy.utils.ContentBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.CombinedChannelDuplexHandler;
import io.netty.channel.DefaultChannelPipeline;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * Description: 解析HTTP消息
 *
 * @author: yhong
 * Date: 2024/9/26
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class HttpMessageService {
    private final HttpRequestHandler httpRequestHandler;
    private final HttpResponseHandler httpResponseHandler;
    private final HttpContentHandler httpContentHandler;

    public void parseHttpMessages(byte[] rawData, String outputDir) throws IOException {
        CombinedChannelDuplexHandler<?, ?> codec;
        if (rawData[0] == 'H' || rawData[0] == 'h') {
            codec = new HttpClientCodec(131072, 131072, 10 * 1024 * 1024);
        } else {
            codec = new HttpServerCodec(131072, 131072, 10 * 1024 * 1024);
        }
        EmbeddedChannel channel = new EmbeddedChannel(
                codec,
                // 最大消息大小【需要动态调整】
                new HttpObjectAggregator(10 * 1024 * 1024),
                new ChunkedWriteHandler()
        );
        ByteBuf byteBuf = Unpooled.wrappedBuffer(rawData);
        boolean success = channel.writeInbound(byteBuf);
        if (!success) {
            log.error("Failed to write data to channel.");
            return;
        }
        ContentBuffer contentBuffer = new ContentBuffer();
        while (true) {
            // todo :解析 chunked 编码的部分。size 应该是一个十六进制数字，但在这个例子中，size 实际上是二进制数据的一部分，导致 int(size, 16) 抛出异常
            Object msg = channel.readInbound();
            if (msg == null) {
                break;
            }
            log.info("Received message of type: {}", msg.getClass().getSimpleName());
            if (msg instanceof HttpRequest) {
                httpRequestHandler.handleHttpStream((HttpRequest) msg, outputDir);
            } else if (msg instanceof HttpResponse) {
                httpResponseHandler.handleHttpStream((HttpResponse) msg, outputDir);
            } else if (msg instanceof HttpContent) {
                httpContentHandler.handlerHttpContent((HttpContent) msg, contentBuffer);
            } else if (msg instanceof LastHttpContent) {
                httpContentHandler.handleLastHttpContent((LastHttpContent) msg, contentBuffer, outputDir, "defaultName");
                break;
            }
        }
        channel.close();
    }
}