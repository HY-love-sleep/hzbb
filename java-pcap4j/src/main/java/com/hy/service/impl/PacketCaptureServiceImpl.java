package com.hy.service.impl;

import com.hy.service.PacketCaptureService;
import com.hy.utils.PacketUtils;
import com.hy.utils.TcpPacketReassembler;
import lombok.AllArgsConstructor;
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

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Description:
 * Author: yhong
 * Date: 2024/4/2
 */
@Service
@Slf4j
// @AllArgsConstructor
public class PacketCaptureServiceImpl implements PacketCaptureService {
    private static final Logger PACKET_LOGGER = LoggerFactory.getLogger("PACKET_LOGGER");
    private static final Logger IP_PACKET_LOGGER = LoggerFactory.getLogger("IP_PACKET_LOGGER");
    private static final Logger TCP_PACKET_LOGGER = LoggerFactory.getLogger("TCP_PACKET_LOGGER");
    private ConcurrentHashMap<InetSocketAddress, List<TcpPacket>> incompletePackets = new ConcurrentHashMap<>();
    // private final TcpPacketReassembler tcpPacketReassembler;

    @Override
    public void startPacketCapture(PcapNetworkInterface networkInterface) {
        int snapLen = 65536; // 数据包捕获的最大长度 [太大会造成内存消耗过大， 太小可能导致数据包丢失]
        int timeout = 10; // 超时时间，单位为秒

        try (PcapHandle handle = networkInterface.openLive(snapLen, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, timeout)) {
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
     * ip包中提供了更底层的网络信息，例如源 IP 和目标 IP 地址
     * tcp数据包，包括源端口、目标端口、序列号、确认号等信息
     *
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
        IP_PACKET_LOGGER.info("ipV4 packet: {}", ipV4Packet);

        if (!(ipV4Packet.getPayload() instanceof TcpPacket)) {
            log.debug("Payload is not an instance of TcpPacket");
            return;
        }

        TcpPacket tcpPacket = (TcpPacket) ipV4Packet.getPayload();

        if (tcpPacket.getPayload() == null) {
            log.debug("Tcp payload is empty");
            return;
        }
        TCP_PACKET_LOGGER.info("tcp packet: {}", tcpPacket);
        String decodedPayload = PacketUtils.decodePayload(PacketUtils.encodeBase64(tcpPacket.getPayload().getRawData()), null);
        log.info("tcp decodedPayload: {}", decodedPayload);

        PACKET_LOGGER.info("raw packet info: {}", PacketUtils.getRawPacketInfo(ipV4Packet, tcpPacket));
    }

    /**
     * 处理抓取到的数据包Packet
     * 通过TCP报文中的序列号（Sequence Number）和数据偏移量（Data Offset）字段
     * 判断包是否连续并进行重组
     * @param packet
     */
    private void handlePacket2(Packet packet) {
        if (!(packet instanceof EthernetPacket)) {
            log.debug("数据包不是一个以太网数据包");
            return;
        }

        EthernetPacket ethernetPacket = (EthernetPacket) packet;
        if (!(ethernetPacket.getPayload() instanceof IpV4Packet)) {
            log.debug("数据包载荷不是IPV4数据包");
            return;
        }

        IpV4Packet ipV4Packet = (IpV4Packet) ethernetPacket.getPayload();

        if (!(ipV4Packet.getPayload() instanceof TcpPacket)) {
            log.debug("数据包载荷不是TCP数据包");
            return;
        }

        TcpPacket tcpPacket = (TcpPacket) ipV4Packet.getPayload();

        // 构建sourceSocketAddress和destinationSocketAddress
        InetAddress destinationAddress = ipV4Packet.getHeader().getDstAddr();
        int destinationPort = tcpPacket.getHeader().getDstPort().value();
        InetAddress sourceAddress = ipV4Packet.getHeader().getSrcAddr();
        int sourcePort = ((int) tcpPacket.getHeader().getSrcPort().value()) & 0xFFFF;
        InetSocketAddress sourceSocketAddress = new InetSocketAddress(sourceAddress, sourcePort);
        InetSocketAddress destinationSocketAddress = new InetSocketAddress(destinationAddress, destinationPort);

        List<TcpPacket> packetList = incompletePackets.getOrDefault(sourceSocketAddress, new ArrayList<>());

        // 添加当前 TCP 数据包到列表中
        packetList.add(tcpPacket);

        // 检查是否有完整的 TCP 报文
        List<TcpPacket> completePackets = new ArrayList<>();
        // AtomicBoolean isComplete = new AtomicBoolean(false);

        // 从packetList中获取第一个tcp包，将其头部的序列号作为expectedSeqNumber的初始值
        AtomicInteger expectedSeqNumber = new AtomicInteger(Optional.of(
                packetList.stream()
                        .findFirst().map(t -> t.getHeader().getSequenceNumber())
                        .orElse(-1)).orElse(-1));

        packetList.stream()
                .filter(pkt -> pkt.getHeader().getSequenceNumber() == expectedSeqNumber.get())
                .forEach(pkt -> {
                    completePackets.add(pkt);
                    expectedSeqNumber.addAndGet(pkt.getHeader().getDataOffsetAsInt());
                });

        // if (isComplete.get()) {

        // 从列表中移除已经处理的完整数据包
        packetList.removeAll(completePackets);
        // 处理完整的 TCP 报文
        processCompletePackets(completePackets);
        // 更新未完成的数据包列表
        incompletePackets.put(sourceSocketAddress, packetList);

    }

    // private void handlePacket3(Packet packet) {
    //     if (!(packet instanceof EthernetPacket)) {
    //         log.debug("数据包不是一个以太网数据包");
    //         return;
    //     }
    //
    //     EthernetPacket ethernetPacket = (EthernetPacket) packet;
    //     if (!(ethernetPacket.getPayload() instanceof IpV4Packet)) {
    //         log.debug("数据包载荷不是IPV4数据包");
    //         return;
    //     }
    //
    //     IpV4Packet ipV4Packet = (IpV4Packet) ethernetPacket.getPayload();
    //
    //     if (!(ipV4Packet.getPayload() instanceof TcpPacket)) {
    //         log.debug("数据包载荷不是TCP数据包");
    //         return;
    //     }
    //
    //     TcpPacket tcpPacket = (TcpPacket) ipV4Packet.getPayload();
    //     InetAddress sourceAddress = ipV4Packet.getHeader().getSrcAddr();
    //     short sourcePort = tcpPacket.getHeader().getSrcPort().value();
    //     InetAddress destinationAddress = ipV4Packet.getHeader().getDstAddr();
    //     short destinationPort = tcpPacket.getHeader().getDstPort().value();
    //
    //     tcpPacketReassembler.addPacket(sourceAddress, sourcePort, destinationAddress, destinationPort, tcpPacket);
    // }

    private void processCompletePackets(List<TcpPacket> completePackets) {
        StringBuilder tcpPayloadBuilder = new StringBuilder();

        for (TcpPacket packet : completePackets) {
            // 获取 TCP 数据包的有效载荷（payload）的原始字节数组
            byte[] payloadBytes = packet.getPayload().getRawData();

            // 将有效载荷的字节数组转换为字符串，并追加到 StringBuilder 中
            tcpPayloadBuilder.append(new String(payloadBytes, StandardCharsets.UTF_8));
        }

        // 获取完整的 TCP 报文字符串
        String completeTcpPacket = tcpPayloadBuilder.toString();
        // TCP_PACKET_LOGGER.info("tcpPacket: {}", completeTcpPacket);

        // 解析请求头和请求体
        String[] parts = completeTcpPacket.split("\r\n\r\n", 2);
        String requestHeader = parts[0]; // 请求头部分
        String requestBody = (parts.length > 1) ? parts[1] : ""; // 请求体部分（如果存在）

        TCP_PACKET_LOGGER.info("Request Header: {}", requestHeader);
        TCP_PACKET_LOGGER.info("Request Body: {}", requestBody);
    }


}
