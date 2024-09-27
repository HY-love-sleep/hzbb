package com.hy.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ChunkedTransferDecoder {

    public static void main(String[] args) throws IOException {
        byte[] input = FileUtils.readFile("C:\\My_Work\\IdeaProjects\\MyGitProject\\hzbb\\parse-httpstream\\src\\main\\resources\\bin_file\\excel.bin");

        try {
            byte[] decodedData = decodeChunked(input);
            System.out.println(new String(decodedData, StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] decodeChunked(byte[] data) throws Exception {
        List<Byte> result = new ArrayList<>();
        int pos = skipHeaders(data); // 跳过HTTP头部

        while (pos < data.length) {
            int endOfLine = findEndOfLine(data, pos);
            if (endOfLine == -1) break;
            String lengthStr = new String(data, pos, endOfLine - pos, StandardCharsets.US_ASCII).trim();
            int length = Integer.parseInt(lengthStr, 16);
            pos = endOfLine + 2; // 跳过CRLF

            for (int i = 0; i < length; i++) {
                result.add(data[pos++]);
            }

            pos += 2; // 跳过CRLF

            if (length == 0) break; // 最后一个块
        }

        byte[] resultArray = new byte[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }
        return resultArray;
    }

    private static int skipHeaders(byte[] data) {
        int pos = 0;
        while (pos < data.length) {
            if (data[pos] == '\r' && data[pos + 1] == '\n' &&
                data[pos + 2] == '\r' && data[pos + 3] == '\n') {
                return pos + 4; // 跳过空行
            }
            pos++;
        }
        throw new IllegalArgumentException("No headers found");
    }

    private static int findEndOfLine(byte[] data, int start) {
        for (int i = start; i < data.length - 1; i++) {
            if (data[i] == '\r' && data[i + 1] == '\n') {
                return i;
            }
        }
        return -1;
    }
}