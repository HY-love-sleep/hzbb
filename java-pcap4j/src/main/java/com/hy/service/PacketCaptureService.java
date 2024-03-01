package com.hy.service;

import lombok.extern.slf4j.Slf4j;
import org.pcap4j.core.*;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.util.MacAddress;
import org.pcap4j.util.NifSelector;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Description: 抓包并解析
 * Author: yhong
 * Date: 2024/3/1
 */
@Service
@Slf4j
public class PacketCaptureService {

    public void startPacketCapture(String networkInterfaceName) {
        try {
            // 查找网络接口
            PcapNetworkInterface networkInterface = selectNetworkInterface(networkInterfaceName);

            // 打开网络接口
            int snapLen = 65536; // 数据包捕获的最大长度
            int timeout = 10; // 超时时间，单位为秒
            PcapHandle handle = networkInterface.openLive(snapLen, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, timeout);

            // 添加过滤器，捕获TCP协议的数据包
            handle.setFilter("tcp", BpfProgram.BpfCompileMode.OPTIMIZE);

            // 定义数据包监听器
            PacketListener listener = packet -> {
                if (packet instanceof EthernetPacket) {  // 判断是否为以太网帧
                    EthernetPacket ethernetPacket = (EthernetPacket) packet;
                    if (ethernetPacket.getPayload() instanceof IpV4Packet) {  // 判断是否为IPV4数据包
                        IpV4Packet ipV4Packet = (IpV4Packet) ethernetPacket.getPayload();
                        // log.info("ipV4 Header:{}", ipV4Packet.getHeader());
                        if (ipV4Packet.getPayload() instanceof TcpPacket) {  // 判断是否为TCP 数据包
                            TcpPacket tcpPacket = (TcpPacket) ipV4Packet.getPayload();
                            if (tcpPacket.getPayload() != null) {
                                byte[] payloadBytes = tcpPacket.getPayload().getRawData();
                                // 如果出现乱码， 需要指定payloadBytes的编码格式，例如UTF-8、ISO-8859-1、GBK 等；
                                String payloadString = new String(payloadBytes, StandardCharsets.US_ASCII);
                                log.info("TCP payload: {}", payloadString);
                            }
                        }
                    }
                }
            };

            handle.loop(-1, listener); // -1表示持续捕获数据包，直到手动停止

        } catch (PcapNativeException | NotOpenException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static PcapNetworkInterface selectNetworkInterface(String name) {
        List<PcapNetworkInterface> allDevs = null;
        try {
            // 获取所有的网络接口
            allDevs = Pcaps.findAllDevs();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (allDevs == null) {
            log.error("未发现可用的网络接口");
            return null;
        }

        // 根据描述信息匹配网络接口
        for (PcapNetworkInterface dev : allDevs) {
            if (dev.getName().equals(name)) {
                return dev;
            }
        }

        log.error("[{}], 未找到该名称对应的网络接口", name);
        return null;
    }
}

