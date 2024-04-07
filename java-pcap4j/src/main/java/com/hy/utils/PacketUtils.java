package com.hy.utils;

import com.hy.entity.RawPacket;
import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.TcpPacket;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

/**
 * Description: 数据包分析工具类
 * Author: yhong
 * Date: 2024/4/7
 */
@Slf4j
public class PacketUtils {
    /**
     * 将 Base64 编码的字符串解码为字节数组
     * @param base64String Base64 编码的字符串
     * @return 解码后的字节数组
     */
    public static byte[] decodeBase64(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }

    /**
     * 将 Base64 字节数组编码为字符串
     * @param bytes byte[] 字节数组
     * @return 编码后的字符串
     */
    public static String encodeBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 解码负载数据。
     * 尝试将字节数组解码为字符串。首先，检查是否指定了编码格式；如果指定，则直接使用指定的编码格式解码。
     * 如果没有指定编码格式，则使用自动编码检测来确定解码所使用的编码格式。
     *
     * @param base64String 待解码的字节数组的base64编码。
     * @param specifiedEncoding 指定的编码格式，如果为null，则自动检测编码格式[影响性能]。
     * @return 解码后的字符串。如果输入为空或解码后字符串为空，则返回null。
     */
    public static String decodePayload(String base64String, Charset specifiedEncoding) {
        byte[] bytes = decodeBase64(base64String);
        if (bytes == null || bytes.length == 0) {
            log.warn("传入的字节数组为空");
            return null;
        }

        String decodedPayload;
        Charset detectedCharset = null;
        if (null != specifiedEncoding) {
            decodedPayload = new String(bytes, specifiedEncoding);
        } else {
            CharsetDetector detector = new CharsetDetector();
            detector.setText(bytes);
            // 进行编码格式检测
            CharsetMatch match = detector.detect();
            detectedCharset = Charset.forName(match.getName());

            // 使用检测到的编码格式将字节数组解码为字符串
            decodedPayload = new String(bytes, detectedCharset);
        }

        if (StringUtils.isEmpty(decodedPayload)) {
            if (null != specifiedEncoding) {
                log.warn("使用指定编码格式 {} 解码后，字符串为空。bytes的Base64编码为：{}", specifiedEncoding, encodeBase64(bytes));
            } else {
                log.warn("使用自动检测的编码格式 {} 解码后，字符串为空。bytes的Base64编码为：{}", detectedCharset.displayName(), encodeBase64(bytes));
            }
        }
        return decodedPayload;
    }

    /**
     * 从IPV4和TCP包中获取原始包信息。
     * @param ipV4Packet IPV4包，用于获取源IP和目的IP。
     * @param tcpPacket TCP包，用于获取源端口和目的端口以及负载字节数组。
     * @return 返回填充了源IP、目的IP、源端口和目的端口信息的RawPacket对象。
     */
    public static RawPacket getRawPacketInfo(IpV4Packet ipV4Packet, TcpPacket tcpPacket) {
        RawPacket rawPacket = new RawPacket();
        rawPacket.setSourceIp(ipV4Packet.getHeader().getSrcAddr().toString());
        rawPacket.setDestinationIp(ipV4Packet.getHeader().getDstAddr().toString());
        rawPacket.setSourcePort(tcpPacket.getHeader().getSrcPort().value());
        rawPacket.setDestinationPort(tcpPacket.getHeader().getDstPort().value());
        rawPacket.setTcpPayload(encodeBase64(tcpPacket.getPayload().getRawData()));
        return rawPacket;
    }


}
