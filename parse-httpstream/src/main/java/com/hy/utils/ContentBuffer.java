package com.hy.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Description: 缓冲区类来累积所有接收到的HttpContent片段
 *
 * @author: yhong
 * Date: 2024/9/26
 */
public class ContentBuffer {
    private ByteBuf buffer;

    public ContentBuffer() {
        this.buffer = Unpooled.buffer();
    }

    public void addContent(ByteBuf content) {
        buffer.writeBytes(content);
    }

    public ByteBuf getContent() {
        return buffer;
    }

    public void release() {
        if (buffer != null) {
            buffer.release();
            buffer = null;
        }
    }
}
