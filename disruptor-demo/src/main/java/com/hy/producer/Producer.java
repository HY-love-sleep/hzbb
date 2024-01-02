package com.hy.producer;

import com.hy.entity.Order;
import com.lmax.disruptor.RingBuffer;

/**
 * Description: 生产者
 * Author: yhong
 * Date: 2024/1/2
 */
public class Producer {
    private RingBuffer<Order> ringBuffer;

    public Producer(RingBuffer<Order> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void sendData(String uuid) {
        long sequence = ringBuffer.next();
        Order order = ringBuffer.get(sequence);
        order.setId(uuid);
        ringBuffer.publish(sequence);
    }
}
