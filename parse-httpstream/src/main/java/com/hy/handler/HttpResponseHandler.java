package com.hy.handler;

import com.hy.utils.FileUtils;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Description: 处理http 响应
 *
 * @author: yhong
 * Date: 2024/9/26
 */
@Slf4j
@Component
public class HttpResponseHandler implements HttpStreamHandler<HttpResponse>{

    @Override
    public void handleHttpStream(HttpResponse response, String outputDir) throws IOException {
        if (response instanceof FullHttpResponse) {
            FullHttpResponse fullResponse = (FullHttpResponse) response;
            // 获取 Content-Disposition 头部信息
            String contentDisposition = fullResponse.headers().get(HttpHeaderNames.CONTENT_DISPOSITION);
            String contentType = fullResponse.headers().get(HttpHeaderNames.CONTENT_TYPE);
            // 解析文件名
            String filename = FileUtils.extractFilename(contentDisposition);
            if (filename != null && contentType != null) {
                try {
                    FileUtils.saveToFile(fullResponse, filename, outputDir);
                } catch (IOException e) {
                    log.error("Save file failed, file name:{}", filename);
                }
            } else {
                log.info("No valid filename or content type found in response.");
            }
        }
    }
}
