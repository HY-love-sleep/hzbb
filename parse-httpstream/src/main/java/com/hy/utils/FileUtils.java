package com.hy.utils;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: 文件处理工具类【保存、转发等等】
 *
 * @author: yhong
 * Date: 2024/9/26
 */
@Slf4j
public class FileUtils {
    public static byte[] readFile(String filePath) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             FileChannel fileChannel = fileInputStream.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate((int) fileChannel.size());
            while (fileChannel.read(buffer) > 0) {
                buffer.flip();
            }
            return buffer.array();
        }
    }

    public static void saveToFile(byte[] data, String outputPath, String filename) throws IOException {
        File outputFile = new File(outputPath, filename);
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(data);
            fos.flush();
        }
        log.info("File saved to: {}", outputFile.getAbsolutePath());
    }

    public static void saveToFile(ByteBuf data, String outputPath, String filename) throws IOException {
        File outputFile = new File(outputPath, filename);
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            byte[] bytes = new byte[data.readableBytes()];
            data.readBytes(bytes);
            fos.write(bytes);
            fos.flush();
        }
        log.info("File saved to: {}", outputFile.getAbsolutePath());
    }

    public static void saveToFile(HttpObject message, String filename, String outputDir) throws IOException {
        File outputFile = new File(outputDir, filename);

        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            ByteBuf content = ((FullHttpMessage) message).content();

            // 使用循环读取 ByteBuf 的内容并写入 FileOutputStream
            byte[] bytes = new byte[content.readableBytes()];
            content.readBytes(bytes);
            fos.write(bytes);
            fos.flush();
            content.release();
        }
        log.info("File saved to: {}", outputFile.getAbsolutePath());
    }

    public static String extractBoundary(String contentType) {
        int start = contentType.indexOf("boundary=");
        if (start != -1) {
            return contentType.substring(start + 9).trim();
        }
        return null;
    }

    public static void saveToFile(InterfaceHttpData data, String outputDir) throws IOException {
        if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.FileUpload) {
            FileUpload fileUpload = (FileUpload) data;
            if (fileUpload.isCompleted()) {
                String fileName = fileUpload.getFilename();
                File outputFile = new File(outputDir, fileName);

                try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                    ByteBuf content = fileUpload.content();

                    // 使用循环读取 ByteBuf 的内容并写入 FileOutputStream
                    byte[] bytes = new byte[content.readableBytes()];
                    content.readBytes(bytes);
                    fos.write(bytes);
                    fos.flush();

                    content.release();
                }
                log.info("File saved to: {}", outputFile.getAbsolutePath());
            }
        }
    }

    public static String extractFilename(String contentDisposition) {
        if (contentDisposition == null) {
            return null;
        }

        Pattern pattern = Pattern.compile("filename=\"?([^\"]+)\"?");
        Matcher matcher = pattern.matcher(contentDisposition);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }
}
