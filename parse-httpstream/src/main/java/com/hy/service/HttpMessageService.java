package com.hy.service;

import com.hy.handler.HttpContentHandler;
import com.hy.handler.HttpRequestHandler;
import com.hy.handler.HttpResponseHandler;
import com.hy.utils.ContentBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.CombinedChannelDuplexHandler;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.*;
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
            codec = new HttpClientCodec();
        } else {
            codec = new HttpServerCodec();
        }
        EmbeddedChannel channel = new EmbeddedChannel(
                codec,
                // 最大消息大小【需要动态调整】
                new HttpObjectAggregator(1048576)
        );
        ByteBuf byteBuf = Unpooled.wrappedBuffer(rawData);
        channel.writeInbound(byteBuf);
        ContentBuffer contentBuffer = new ContentBuffer();
        while (channel.finish()) {
            Object msg = channel.readInbound();
            if (msg == null) {
                break;
            }
            if (msg instanceof HttpRequest) {
                httpRequestHandler.handleHttpStream((HttpRequest) msg, outputDir);
            } else if (msg instanceof HttpResponse) {
                httpResponseHandler.handleHttpStream((HttpResponse) msg, outputDir);
            } else if (msg instanceof HttpContent) {
                httpContentHandler.handlerHttpContent((HttpContent) msg, contentBuffer);
            } else if (msg instanceof LastHttpContent) {
                httpContentHandler.handleLastHttpContent((LastHttpContent) msg, contentBuffer, outputDir, "defaultName");
            }
        }
        channel.close();
    }
}