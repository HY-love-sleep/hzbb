package com.hy.producer;

import com.hy.event.InParkingDataEvent;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.CountDownLatch;

/**
 * Description: 发布事件类
 * Author: yhong
 * Date: 2023/12/29
 */
public class InParkingDataEventPublisher implements Runnable{
    Disruptor<InParkingDataEvent> disruptor;
    private CountDownLatch latch;
    // 模拟十辆车入场；
    private static int LOOP = 1000;

    public InParkingDataEventPublisher(Disruptor<InParkingDataEvent> disruptor, CountDownLatch latch) {
        this.disruptor = disruptor;
        this.latch = latch;
    }

    @Override
    public void run() {
        InParkingDataEventTranslator translator = new InParkingDataEventTranslator();
        for (int i = 0; i < LOOP; i++) {
            disruptor.publishEvent(translator);
            // try {
            //     Thread.sleep(1000L);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
        }
        latch.countDown();
        System.out.println("生产者写完" + LOOP + "个消息");
    }
}
