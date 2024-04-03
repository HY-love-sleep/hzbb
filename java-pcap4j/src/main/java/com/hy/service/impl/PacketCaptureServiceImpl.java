package com.hy.service.impl;

import com.hy.service.PacketCaptureService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.jnetpcap.packet.PcapPacket;
import org.pcap4j.core.*;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

/**
 * Description:
 * Author: yhong
 * Date: 2024/4/2
 */
@Service
@Slf4j
public class PacketCaptureServiceImpl implements PacketCaptureService {
    private static final Logger PACKET_LOGGER_BASE64 = LoggerFactory.getLogger("PACKET_LOGGER_BASE64");
    private static final Logger PACKET_LOGGER = LoggerFactory.getLogger("PACKET_LOGGER");

    @Override
    public void startPacketCapture(PcapNetworkInterface networkInterface) {
        int snapLen = 65536; // 数据包捕获的最大长度 [太大会造成内存消耗过大， 太小可能导致数据包丢失]
        int timeout = 10; // 超时时间，单位为秒

        try (PcapHandle handle = networkInterface.openLive(snapLen, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, timeout)){
            // 添加过滤器
            handle.setFilter("tcp", BpfProgram.BpfCompileMode.OPTIMIZE);

            // 定义数据包监听器
            PacketListener listener = this::handlePacket;
            handle.loop(-1, listener); // -1表示持续捕获数据包，直到手动停止

        } catch (NotOpenException | InterruptedException | PcapNativeException e) {
            log.error("抓包异常=================>", e);
        }
    }

    /**
     * 处理抓取到的数据包Packet
     * @param packet
     */
    private void handlePacket(Packet packet) {

        if (!(packet instanceof EthernetPacket)) {
            log.debug("Packet is not an instance of EthernetPacket");
            return;
        }

        EthernetPacket ethernetPacket = (EthernetPacket) packet;
        if (!(ethernetPacket.getPayload() instanceof IpV4Packet)) {
            log.debug("Payload is not an instance of IpV4Packet");
            return;
        }

        IpV4Packet ipV4Packet = (IpV4Packet) ethernetPacket.getPayload();
        log.info("ipV4 Header:{}", ipV4Packet.getHeader());

        if (!(ipV4Packet.getPayload() instanceof TcpPacket)) {
            log.debug("Payload is not an instance of TcpPacket");
            return;
        }

        TcpPacket tcpPacket = (TcpPacket) ipV4Packet.getPayload();
        if (tcpPacket.getPayload() == null) {
            log.debug("Tcp payload is empty");
            return;
        }

        byte[] payloadBytes = tcpPacket.getPayload().getRawData();
        processPayload(payloadBytes);
    }

    /**
     * 处理传入的负载数据。
     * @param payloadBytes 负载数据的字节数组。
     * 如果字节数组满足特定的HTTPS报头格式，则将其作为HTTPS数据处理；
     * 否则，将字节数组转为Base64字符串格式，并计算其SHA-256散列值，进行记录。
     */
    private void processPayload(byte[] payloadBytes) {
        boolean isHttps = payloadBytes.length >= 5 && payloadBytes[0] == 0x16 && payloadBytes[1] == 0x03;
        if (isHttps) {
            // TODO: HTTPS 解析
        } else {
            // base64编码后存入日志文件
            String base64Payload = Base64.getEncoder().encodeToString(payloadBytes);
            PACKET_LOGGER_BASE64.info("TCP payload(base64): {}", base64Payload);

            // 解析payloadBytes获得详细信息
            String payloadString = new String(payloadBytes, StandardCharsets.UTF_8);
            PACKET_LOGGER.info("TCP payload: {}", payloadString);

            // 打印负载长度和hash摘要
            int payloadLength = payloadBytes.length;
            String payloadHash = computeSHA256(payloadBytes);
            log.info("TCP payload (len={} hash={})", payloadLength, payloadHash);
        }
    }

    /**
     * 计算给定字节数组的SHA-256散列值。
     *
     * @param payloadBytes 待计算散列值的字节数组。
     * @return 计算得到的SHA-256散列值的16进制字符串。如果在计算过程中发生异常，则返回null。
     */
    private String computeSHA256(byte[] payloadBytes) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(payloadBytes);
            byte[] hashBytes = messageDigest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("无法解析hash, payloadBytes:{}", payloadBytes, e);
        }
        return null;
    }

}
