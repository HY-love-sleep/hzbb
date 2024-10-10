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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    /**
     * 将图片文件转换为字节数组。
     *
     * @param filePath 图片文件的路径
     * @return 图片的字节数组
     * @throws IOException 如果读取文件时发生错误
     */
    public static byte[] imageToByteArray(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist: " + filePath);
        }

        // 获取文件长度
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            throw new IOException("File size exceeds the maximum allowed size: " + fileSize);
        }

        // 创建字节数组
        byte[] bytes = new byte[(int) fileSize];

        // 读取文件内容到字节数组
        try (FileInputStream fis = new FileInputStream(file)) {
            int bytesRead = fis.read(bytes);
            if (bytesRead != bytes.length) {
                throw new IOException("Failed to read the entire file: " + filePath);
            }
        }

        return bytes;
    }

    /**
     * 计算文件的MD5值
     * @param filePath 文件路径
     * @return MD5字符串
     * @throws NoSuchAlgorithmException 如果没有这种算法
     * @throws IOException 如果读取文件出错
     */
    public static String calculateFileMD5(String filePath) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] dataBytes = new byte[4096000];
            int nread = 0;
            while ((nread = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }
        }
        byte[] mdbytes = md.digest();

        // convert the byte to hex format method 1
        StringBuilder sb = new StringBuilder();
        for (byte mdbyte : mdbytes) {
            sb.append(Integer.toString((mdbyte & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        // try {
        //     String imagePath = "C:\\Users\\洪岩\\Downloads\\test.jpg";
        //     byte[] imageBytes = imageToByteArray(imagePath);
        //     System.out.println("Image byte array length: " + imageBytes.length + " bytes"); //2972614 bytes
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        try {
            String filePath = "C:\\My_Work\\IdeaProjects\\MyGitProject\\hzbb\\parse-httpstream\\src\\main\\resources\\bin_file\\dsmm-request-jpg.bin"; // 替换为实际文件路径
            String md5 = calculateFileMD5(filePath);
            System.out.println("文件的MD5值: " + md5);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }


}
