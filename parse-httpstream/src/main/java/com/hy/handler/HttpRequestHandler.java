package com.hy.handler;

import com.hy.utils.FileUtils;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Description: 处理http请求
 *
 * @author: yhong
 * Date: 2024/9/26
 */
@Component
@Slf4j
public class HttpRequestHandler implements HttpStreamHandler<HttpRequest>{

    /**
     * 处理post类型的form表单提交
     * todo: 扩展put提交以及二进制流文件类型等；
     * @param request
     * @param outputDir
     * @throws IOException
     */
    @Override
    public void handleHttpStream(HttpRequest request, String outputDir) throws IOException {
        if (request.method() == HttpMethod.POST) {
            FullHttpRequest fullRequest = (FullHttpRequest) request;
            String contentType = request.headers().get(HttpHeaderNames.CONTENT_TYPE);
            if (contentType != null && contentType.startsWith("multipart/form-data")) {
                String boundary = FileUtils.extractBoundary(contentType);
                parseMultipartFormData(fullRequest, boundary, outputDir);
            }
        }
    }

    /**
     * 提取表单提交的文件并保存
     * @param request
     * @param boundary
     * @param outputDir
     * @throws IOException
     */
    private void parseMultipartFormData(FullHttpRequest request, String boundary, String outputDir) throws IOException {
        HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(true), request);
        try {
            while (decoder.hasNext()) {
                InterfaceHttpData data = decoder.next();
                if (data != null) {
                    if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.FileUpload) {
                       FileUtils.saveToFile(data, outputDir);
                    }
                }
            }
        } finally {
            decoder.destroy();
        }
    }

}
