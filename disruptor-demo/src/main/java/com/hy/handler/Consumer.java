package com.hy.handler;

import com.hy.entity.Order;
import com.lmax.disruptor.WorkHandler;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: 消费者
 * 实现WorkHandler和EventHandler接口的区别在于：
 * 1、实现WorkHandler是多个处理器共同处理event，每个event只会被一个handler处理，用于任务一致的条件下， 比如只有一个任务就是消息入库；
 * 2、实现EventHandler，每个event会被多个handler 处理，例如一个event需要被入库、发送kafka消息...
 * Author: yhong
 * Date: 2024/1/2
 */
public class Consumer implements WorkHandler<Order> {

    private String consumerId;

    private static AtomicInteger count = new AtomicInteger(0);

    private final Random random = new Random(5);

    public Consumer(String consumerId) {
        this.consumerId = consumerId;
    }

    @Override
    public void onEvent(Order order) throws Exception {
        // Thread.sleep(random.nextInt(5));
        System.err.println("当前消费者: " + this.consumerId + ", 消费信息ID: " + order.getId());
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}
