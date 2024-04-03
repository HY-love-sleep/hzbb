package com.hy.service;

import org.pcap4j.core.PcapNetworkInterface;

/**
 * Description:
 * Author: yhong
 * Date: 2024/4/2
 */
public interface PacketCaptureService {
    void startPacketCapture(PcapNetworkInterface networkInterface);
}
