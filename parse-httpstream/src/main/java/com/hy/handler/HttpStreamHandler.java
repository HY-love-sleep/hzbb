package com.hy.handler;

import com.hy.utils.ContentBuffer;
import com.hy.utils.FileUtils;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.LastHttpContent;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Description: 处理http stream
 *
 * @author: yhong
 * Date: 2024/9/26
 */
public interface HttpStreamHandler<T> {
    /**
     * 解析httpStream中的文件并保存到指定目录下
     * @param message
     * @param outputDir
     */
    void handleHttpStream(T message, String outputDir) throws IOException;

    default void handlerHttpContent(HttpContent httpContent, ContentBuffer contentBuffer) {
        // 处理 HTTP 内容片段
        ByteBuf content = httpContent.content();
        if (content.isReadable()) {
            contentBuffer.addContent(content);
        }
        content.release();
    }

    default void handleLastHttpContent(LastHttpContent lastHttpContent, ContentBuffer contentBuffer, String outputDir, String currentFilename) throws IOException {
        // 将最后一个内容片段添加到缓冲区
        ByteBuf content = lastHttpContent.content();
        if (content.isReadable()) {
            contentBuffer.addContent(content);
        }

        // 释放最后一个内容片段
        content.release();

        // 如果有文件名，则保存文件
        if (currentFilename != null) {
            ByteBuf fullContent = contentBuffer.getContent();
            FileUtils.saveToFile(fullContent, currentFilename, outputDir);
        }

        // 释放缓冲区
        contentBuffer.release();
    }
}

