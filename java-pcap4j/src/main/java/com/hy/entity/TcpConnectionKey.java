package com.hy.entity;

import lombok.AllArgsConstructor;

import java.net.InetAddress;
import java.util.Objects;

/**
 * Description: TCP 连接四元组
 * Author: yhong
 * Date: 2024/4/9
 */
@AllArgsConstructor
public class TcpConnectionKey {
    private final InetAddress sourceAddress;
    private final short sourcePort;
    private final InetAddress destinationAddress;
    private final short destinationPort;

    // 重写equals, 确保复合键的唯一
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TcpConnectionKey that = (TcpConnectionKey) o;
        return sourcePort == that.sourcePort &&
                destinationPort == that.destinationPort &&
                Objects.equals(sourceAddress, that.sourceAddress) &&
                Objects.equals(destinationAddress, that.destinationAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceAddress, sourcePort, destinationAddress, destinationPort);
    }
}
