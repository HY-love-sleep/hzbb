package com.hy.service.impl;

import com.hy.service.PacketCaptureService;
import lombok.extern.slf4j.Slf4j;
import org.jnetpcap.packet.PcapPacket;
import org.pcap4j.core.*;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Description:
 * Author: yhong
 * Date: 2024/4/2
 */
@Service
@Slf4j
public class PacketCaptureServiceImpl implements PacketCaptureService {
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

    private void handlePacket(Packet packet) {

        if (packet instanceof EthernetPacket) {  // 判断是否为以太网帧
            EthernetPacket ethernetPacket = (EthernetPacket) packet;
            if (ethernetPacket.getPayload() instanceof IpV4Packet) {  // 判断是否为IPV4数据包
                IpV4Packet ipV4Packet = (IpV4Packet) ethernetPacket.getPayload();
                log.info("ipV4 Header:{}", ipV4Packet.getHeader());
                if (ipV4Packet.getPayload() instanceof TcpPacket) {  // 判断是否为TCP 数据包
                    TcpPacket tcpPacket = (TcpPacket) ipV4Packet.getPayload();
                    if (tcpPacket.getPayload() != null) {
                        byte[] payloadBytes = tcpPacket.getPayload().getRawData();
                        String base64Payload = Base64.getEncoder().encodeToString(payloadBytes);
                        //todo:暂存到日志文件中， 可发往消息队列mq
                        PACKET_LOGGER.info("TCP payload(base64): {}", base64Payload);
                        // 仅记录数据包长度和哈希摘要
                        int payloadLength = payloadBytes.length;
                        String payloadHash = computeSHA256(payloadBytes);
                        log.info("TCP payload (len={} hash={})", payloadLength, payloadHash);
                    }
                }
            }
        }
    }

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
