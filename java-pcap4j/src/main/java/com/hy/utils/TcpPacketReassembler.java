package com.hy.utils;

import com.hy.entity.TcpConnectionKey;
import org.pcap4j.packet.TcpPacket;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: Tcp报文重组模块
 * Author: yhong
 * Date: 2024/4/9
 */
@Service
public class TcpPacketReassembler {
    private final ConcurrentHashMap<TcpConnectionKey, TcpStream> streams = new ConcurrentHashMap<>();

    /**
     * 将接收到的TCP报文添加到重组模块中。
     *
     * @param sourceAddress 源地址
     * @param sourcePort 源端口
     * @param destinationAddress 目的地址
     * @param destinationPort 目的端口
     * @param tcpPacket TCP报文
     */
    public void addPacket(InetAddress sourceAddress, short sourcePort, InetAddress destinationAddress, short destinationPort, TcpPacket tcpPacket) {
        TcpConnectionKey key = new TcpConnectionKey(sourceAddress, sourcePort, destinationAddress, destinationPort);
        streams.computeIfAbsent(key, TcpStream::new).addPacket(tcpPacket);
    }

    /**
     * 获取已重组完成的TCP数据流。
     *
     * @return 已重组完成的TCP数据流列表
     */
    public List<byte[]> getCompletedStreams() {
        List<byte[]> completedStreams = new ArrayList<>();
        streams.forEach((key, stream) -> {
            byte[] completedStream = stream.getCompletedStream();
            if (completedStream != null) {
                completedStreams.add(completedStream);
            }
        });
        return completedStreams;
    }

    /**
     * 清理已完成重组或已过期的TCP连接。
     */
    public void cleanup() {
        Iterator<Map.Entry<TcpConnectionKey, TcpStream>> iterator = streams.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<TcpConnectionKey, TcpStream> entry = iterator.next();
            if (entry.getValue().isCompletedOrExpired()) {
                iterator.remove();
            }
        }
    }

    /**
     * 内部类，表示一个TCP连接的数据流。
     */
    private static class TcpStream {
        private final AtomicInteger expectedSeqNumber = new AtomicInteger();
        private final Deque<TcpSegment> packetQueue = new ConcurrentLinkedDeque<TcpSegment>();

        public TcpStream(TcpConnectionKey tcpConnectionKey) {

        }

        /**
         * 添加TCP报文到数据流中。
         *
         * @param tcpPacket TCP报文
         */
        public void addPacket(TcpPacket tcpPacket) {
            int seqNumber = tcpPacket.getHeader().getSequenceNumber();
            if (seqNumber == expectedSeqNumber.get()) {
                byte[] data = tcpPacket.getPayload().getRawData();
                packetQueue.offerLast(new TcpSegment(seqNumber, data));
                expectedSeqNumber.addAndGet(data.length);
            } else if (seqNumber > expectedSeqNumber.get()) {
                // 如果收到的序列号大于期望序列号，说明中间有数据包缺失，暂时保留该数据包，等待后续补发或超时重传
                packetQueue.offerLast(new TcpSegment(seqNumber, tcpPacket.getPayload().getRawData()));
            }
        }

        /**
         * 获取已完成重组的TCP数据流。
         *
         * @return 完成重组的TCP数据流，若未完成则返回null
         */
        public byte[] getCompletedStream() {
            if (!packetQueue.isEmpty() && packetQueue.peekFirst().getSeqNumber() == 0) {
                // 确保数据流从序列号为0开始
                byte[] completedStream = new byte[packetQueue.getLast().getEndIndex()];
                TcpSegment currentSegment;
                int offset = 0;
                while ((currentSegment = packetQueue.pollFirst()) != null) {
                    System.arraycopy(currentSegment.getData(), 0, completedStream, offset, currentSegment.getData().length);
                    offset += currentSegment.getData().length;
                }
                return completedStream;
            }
            return null;
        }

        /**
         * 判断TCP连接是否已完成重组或已过期。
         *
         * @return 若已完成重组或已过期，则返回true，否则返回false
         */
        public boolean isCompletedOrExpired() {
            // 考虑超时重传：检查队列中最早未被确认的报文是否已超过最大重传次数或超时时间
            TcpSegment firstUnacknowledgedSegment = packetQueue.peekFirst();
            if (firstUnacknowledgedSegment != null) {
                long currentTime = System.currentTimeMillis(); // 当前时间
                long elapsedTimeSinceTransmission = currentTime - firstUnacknowledgedSegment.getTransmissionTimestamp(); // 报文发送至今的时间差
                int maxRetransmitCount = 5; // 最大重传次数（可根据实际情况设置）
                long retransmissionTimeout = 3000L; // 重传超时时间（单位：毫秒，可根据实际情况设置）

                // 检查是否已达到最大重传次数或已超时
                if (firstUnacknowledgedSegment.getRetransmitCount() >= maxRetransmitCount ||
                        elapsedTimeSinceTransmission > retransmissionTimeout) {
                    return true; // 视为过期，需要清理
                }
            }

            // 考虑连接关闭：检查是否有收到FIN标志位的报文
            TcpSegment lastSegment = packetQueue.peekLast();
            if (lastSegment != null && lastSegment.isFinReceived()) {
                return true; // 视为已完成，需要清理
            }

            // 若以上条件均不满足，认为连接尚未完成重组或过期
            return false;
        }
    }

    /**
     * 内部类，表示TCP数据流中的一个片段。
     */
    private static class TcpSegment {
        private final int seqNumber;
        private final byte[] data;
        private long transmissionTimestamp; // 报文发送时间戳
        private int retransmitCount; // 报文已重传次数
        private boolean finReceived; // 是否收到FIN标志位

        public TcpSegment(int seqNumber, byte[] data) {
            this.seqNumber = seqNumber;
            this.data = data;
            this.transmissionTimestamp = System.currentTimeMillis(); // 初始化发送时间戳
            this.retransmitCount = 0; // 初始重传次数为0
            this.finReceived = false; // 初始状态未收到FIN标志位
        }

        public int getSeqNumber() {
            return seqNumber;
        }

        public int getEndIndex() {
            return seqNumber + data.length;
        }

        public byte[] getData() {
            return data;
        }

        public long getTransmissionTimestamp() {
            return transmissionTimestamp;
        }

        public void setTransmissionTimestamp(long transmissionTimestamp) {
            this.transmissionTimestamp = transmissionTimestamp;
        }

        public int getRetransmitCount() {
            return retransmitCount;
        }

        public void setRetransmitCount(int retransmitCount) {
            this.retransmitCount = retransmitCount;
        }

        public boolean isFinReceived() {
            return finReceived;
        }

        public void setFinReceived(boolean finReceived) {
            this.finReceived = finReceived;
        }
    }

}

