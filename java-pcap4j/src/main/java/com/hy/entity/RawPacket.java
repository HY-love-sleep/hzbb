package com.hy.entity;

import lombok.Data;

/**
 * Description: 数据包基本信息
 * Author: yhong
 * Date: 2024/4/7
 */
@Data
public class RawPacket {
    private String sourceIp;
    private Short sourcePort;
    private String destinationIp;
    private Short destinationPort;
    /**
     * tcpPacket负载的base64编码
     */
    private String tcpPayload;
}
