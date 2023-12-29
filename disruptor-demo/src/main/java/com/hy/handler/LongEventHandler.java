package com.hy.handler;

import com.hy.event.LongEvent;
import com.hy.factory.LongEventFactory;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;

/**
 * Description: 消费者，用于处理事件
 * Author: yhong
 * Date: 2023/12/29
 */
public class LongEventHandler implements EventHandler<LongEvent> {


    @Override
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("currentThread:" + Thread.currentThread().getName() + " Event: " + longEvent);
    }
}
