package com.hy.processor;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.CombinedChannelDuplexHandler;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import lombok.extern.slf4j.Slf4j;

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
public class HttpStreamProcessor {

    public static void main(String[] args) {
        // String filePath = "C:\\My_Work\\IdeaProjects\\MyGitProject\\hzbb\\java-pcap4j\\src\\main\\resources\\binFiles\\nacos-request-post.bin";
        // String filePath = "C:\\My_Work\\IdeaProjects\\MyGitProject\\hzbb\\java-pcap4j\\src\\main\\resources\\binFiles\\nacos-response.bin";
        String filePath = "C:\\My_Work\\IdeaProjects\\MyGitProject\\hzbb\\java-pcap4j\\src\\main\\resources\\binFiles\\execel-response.bin";
        String outputDir = "C:\\My_Work\\IdeaProjects\\MyGitProject\\hzbb\\java-pcap4j\\src\\main\\resources\\output";
        try {
            byte[] rawData = readFile(filePath);
            parseHttpMessages(rawData, outputDir);
        } catch (IOException e) {
            log.error("解析流量失败!!", e);
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
     * 通过EmbeddedChannel解析字节数组为msg
     * @param rawData
     * @param outputDir
     */
    private static void parseHttpMessages(byte[] rawData, String outputDir) throws IOException {
        // 使用 HttpClientCodec or  HttpServerCodec  来解析服务器响应
        CombinedChannelDuplexHandler<?, ?> codec;
        if (rawData[0] == 'H' || rawData[0] == 'h') {
            codec = new HttpClientCodec();
        } else {
            codec = new HttpServerCodec();
        }
        EmbeddedChannel channel = new EmbeddedChannel(
                codec,
                new HttpObjectAggregator(1048576) // 最大消息大小【需要动态调整】
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
                break;
            }
        }
        channel.close();
    }

    /**
     * 处理request
     * todo: 目前支持POST提交的multipart/form-data类型文件解析
     * 考虑扩展PUT或者PATCH类型的请求以及application/octet-stream等类型的文件提交
     * @param request
     * @param outputDir
     */
    private static void handleHttpRequest(HttpRequest request, String outputDir) throws IOException {
        if (request.method() == HttpMethod.POST) {
            FullHttpRequest fullRequest = (FullHttpRequest) request;
            String contentType = request.headers().get(HttpHeaderNames.CONTENT_TYPE);
            if (contentType != null && contentType.startsWith("multipart/form-data")) {
                String boundary = extractBoundary(contentType);
                parseMultipartFormData(fullRequest, boundary, outputDir);
            }
        }
    }
    private static String extractBoundary(String contentType) {
        int start = contentType.indexOf("boundary=");
        if (start != -1) {
            return contentType.substring(start + 9).trim();
        }
        return null;
    }

    /**
     * 提取表单提交的文件并保存
     * @param request
     * @param boundary
     * @param outputDir
     * @throws IOException
     */
    private static void parseMultipartFormData(FullHttpRequest request, String boundary, String outputDir) throws IOException {
        HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(true), request);
        try {
            while (decoder.hasNext()) {
                InterfaceHttpData data = decoder.next();
                if (data != null) {
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
                            log.info("File saved to : {}", outputFile.getAbsolutePath());
                        }
                    }
                }
            }
        } finally {
            decoder.destroy();
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
                    log.error("Save file failed, file name:{}", filename);
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
        content.release();
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
            content.release();
        }

        log.info("File saved to: {}", outputFile.getAbsolutePath());
    }
}
