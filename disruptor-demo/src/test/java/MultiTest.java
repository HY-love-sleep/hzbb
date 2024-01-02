import com.hy.entity.Order;
import com.hy.handler.Consumer;
import com.hy.handler.EventExceptionHandler;
import com.hy.producer.Producer;
import com.lmax.disruptor.*;

import com.lmax.disruptor.dsl.ProducerType;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Description: 多生产者-消费者
 * Author: yhong
 * Date: 2024/1/2
 */

public class MultiTest {
    @Test
    public void test() throws InterruptedException {
        // 1、创建ringBuffer
        RingBuffer<Order> ringBuffer = RingBuffer.create(
                ProducerType.MULTI,
                Order::new,
                1024 * 1024,
                new YieldingWaitStrategy()
        );

        // 2、通过RingBuffer创建一个屏障
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        // 3、创建十个消费者数组
        Consumer[] consumers = new Consumer[10];
        for (int i = 0; i < 10; i++) {
            consumers[i] = new Consumer("c" + i);
        }

        // 4、构建多消费者工作池
        WorkerPool<Order> orderWorkerPool = new WorkerPool<Order>(ringBuffer,
                sequenceBarrier,
                new EventExceptionHandler(),
                consumers);

        //5、设置多个消费者的sequence序号，用于统计单独的消费进度，并存储到ringBuffer中
        ringBuffer.addGatingSequences(orderWorkerPool.getWorkerSequences());

        //6、启动workPool
        orderWorkerPool.start(Executors.newFixedThreadPool(10));

        // 生产数据
        final CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            // 创建100个producer
            final Producer producer = new Producer(ringBuffer);
            new Thread(() -> {
                try {
                    latch.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 每个producer发送100条数据
                for (int j = 0; j < 100; j++) {
                    producer.sendData(UUID.randomUUID().toString());
                }
            }).start();
        }
        Thread.sleep(2000);
        System.err.println("----------线程创建完毕，开始生产数据----------");
        latch.countDown();
        Thread.sleep(10000);
        System.err.println("任务总数:" + consumers[2].getCount());
    }
}
