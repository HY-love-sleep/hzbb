package com.hy.service.impl;

import com.hy.service.PacketCaptureService;
import com.hy.utils.PacketUtils;
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
    private static final Logger PACKET_LOGGER = LoggerFactory.getLogger("PACKET_LOGGER");
    private static final Logger IP_PACKET_LOGGER = LoggerFactory.getLogger("IP_PACKET_LOGGER");
    private static final Logger TCP_PACKET_LOGGER = LoggerFactory.getLogger("TCP_PACKET_LOGGER");

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
     * ip包中提供了更底层的网络信息，例如源 IP 和目标 IP 地址
     * tcp数据包，包括源端口、目标端口、序列号、确认号等信息
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
        // log.info("ipV4 Header: {}", ipV4Packet.getHeader());
        // log.info("ipV4 PayLoad: {}", ipV4Packet.getPayload().getRawData());

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
        // log.info("tcp header: {}", tcpPacket.getHeader());
        // log.info("tcp payload raw data: {}", tcpPacket.getPayload().getRawData());
        String decodedPayload = PacketUtils.decodePayload(PacketUtils.encodeBase64(tcpPacket.getPayload().getRawData()), null);
        log.info("tcp decodedPayload: {}", decodedPayload);

        PACKET_LOGGER.info("raw packet info: {}", PacketUtils.getRawPacketInfo(ipV4Packet, tcpPacket));
    }
}
