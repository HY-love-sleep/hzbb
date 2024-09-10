package com.hy.processor;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 * Author: yhong
 * Date: 2024/4/2
 */
@Slf4j
public class HttpStreamParser {

    public static void main(String[] args) {
        String filePath = "C:\\My_Work\\IdeaProjects\\MyGitProject\\hzbb\\java-pcap4j\\src\\main\\resources\\binFiles\\nacos-request.bin"; // 替换为你的文件路径
        String outputDir = "C:\\My_Work\\IdeaProjects\\MyGitProject\\hzbb\\java-pcap4j\\src\\main\\resources\\output"; // 替换为输出目录路径
        try {
            byte[] rawData = readFile(filePath);
            parseHttpMessages(rawData, outputDir);
        } catch (IOException e) {
            log.error("解析流量失败", e);
        }
    }

    /**
     * 读取http stream 原始数据bin文件
     * @param filePath
     * @return
     * @throws IOException
     */
    private static byte[] readFile(String filePath) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             FileChannel fileChannel = fileInputStream.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate((int) fileChannel.size());
            while (fileChannel.read(buffer) > 0) {
                buffer.flip();
            }
            return buffer.array();
        }
    }

    /**
     *
     * @param rawData
     * @param outputDir
     */
    private static void parseHttpMessages(byte[] rawData, String outputDir) {
        // 使用 HttpClientCodec 来解析服务器响应
        EmbeddedChannel channel = new EmbeddedChannel(
                new HttpClientCodec(),
                new HttpObjectAggregator(65536) // 最大消息大小
        );

        ByteBuf byteBuf = Unpooled.wrappedBuffer(rawData);
        channel.writeInbound(byteBuf);

        while (channel.finish()) {
            Object msg = channel.readInbound();
            if (msg instanceof HttpRequest) {
                handleHttpRequest((HttpRequest) msg, outputDir);
            } else if (msg instanceof HttpResponse) {
                handleHttpResponse((HttpResponse) msg, outputDir);
            } else if (msg instanceof HttpContent) {
                handleHttpContent((HttpContent) msg);
            } else if (msg instanceof LastHttpContent) {
                // todo: 处理完所有内容
                break;
            }
        }

        channel.close();
    }

    private static void handleHttpRequest(HttpRequest request, String outputDir) {
        log.info("Received HTTP Request:");
        log.info("URI: {}", request.uri());
        log.info("Method: {}", request.method());
        log.info("Headers: {}", request.headers());

        if (request instanceof FullHttpRequest) {
            FullHttpRequest fullRequest = (FullHttpRequest) request;
            // 获取 Content-Disposition 头部信息
            String contentDisposition = fullRequest.headers().get(HttpHeaderNames.CONTENT_DISPOSITION);
            String contentType = fullRequest.headers().get(HttpHeaderNames.CONTENT_TYPE);

            // 解析文件名
            String filename = extractFilename(contentDisposition);

            if (filename != null && contentType != null) {
                try {
                    saveToFile(fullRequest, filename, outputDir);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                log.info("No valid filename or content type found in request.");
            }
        }
    }

    private static void handleHttpResponse(HttpResponse response, String outputDir) {
        log.info("Received HTTP Response:");
        log.info("Status: {}", response.status());
        log.info("Headers: {}", response.headers());

        if (response instanceof FullHttpResponse) {
            FullHttpResponse fullResponse = (FullHttpResponse) response;
            // 获取 Content-Disposition 头部信息
            String contentDisposition = fullResponse.headers().get(HttpHeaderNames.CONTENT_DISPOSITION);
            String contentType = fullResponse.headers().get(HttpHeaderNames.CONTENT_TYPE);

            // 解析文件名
            String filename = extractFilename(contentDisposition);

            if (filename != null && contentType != null) {
                try {
                    saveToFile(fullResponse, filename, outputDir);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                log.info("No valid filename or content type found in response.");
            }
        }
    }

    private static void handleHttpContent(HttpContent httpContent) {
        // 处理 HTTP 内容片段
        ByteBuf content = httpContent.content();
        if (content.isReadable()) {
            // 打印或处理内容片段
            log.warn("Received HTTP Content Fragment: " + content.toString(io.netty.util.CharsetUtil.UTF_8));
        }
    }

    private static String extractFilename(String contentDisposition) {
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

    private static void saveToFile(HttpObject message, String filename, String outputDir) throws IOException {
        File outputFile = new File(outputDir, filename);
        
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            ByteBuf content = ((FullHttpMessage) message).content();
            
            // 使用循环读取 ByteBuf 的内容并写入 FileOutputStream
            byte[] bytes = new byte[content.readableBytes()];
            content.readBytes(bytes);
            fos.write(bytes);
            
            // 确保所有数据都被写入文件
            fos.flush();
        }

        log.info("File saved to: {}", outputFile.getAbsolutePath());
    }
}
