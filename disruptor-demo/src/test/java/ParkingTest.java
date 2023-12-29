import com.hy.Application;
import com.hy.entity.ParkingInfo;
import com.hy.event.InParkingDataEvent;
import com.hy.handler.ParkingDataInDbHandler;
import com.hy.handler.ParkingDataSmsHandler;
import com.hy.handler.ParkingDataToKafkaHandler;
import com.hy.producer.InParkingDataEventPublisher;
import com.hy.service.ParkingInfoService;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: 停车测试类
 * Author: yhong
 * Date: 2023/12/29
 */
@SpringBootTest(classes = Application.class)
public class ParkingTest {
    @Autowired
    private ParkingInfoService service;

    @Autowired
    private ParkingDataInDbHandler parkingDataInDbHandler;

    @Autowired
    private ParkingDataToKafkaHandler parkingDataToKafkaHandler;

    @Test
    public void testWithDisruptor() throws InterruptedException {
        long begin = System.currentTimeMillis();
        int bufferSize = 1024;
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Disruptor<InParkingDataEvent> disruptor = new Disruptor<InParkingDataEvent>(
                (EventFactory<InParkingDataEvent>) InParkingDataEvent::new,
                bufferSize,
                executorService,
                ProducerType.SINGLE,
                new BlockingWaitStrategy()
        );
        // 使用disruptor创建消费者C1C2
        EventHandlerGroup<InParkingDataEvent> handlerGroup = disruptor.handleEventsWith(parkingDataInDbHandler, parkingDataToKafkaHandler);
        ParkingDataSmsHandler parkingDataSmsHandler = new ParkingDataSmsHandler();
        // 声明C1 C2完事以后才走到C3
        handlerGroup.then(parkingDataSmsHandler);
        disruptor.start();
        CountDownLatch latch = new CountDownLatch(1);
        executorService.submit(new InParkingDataEventPublisher(disruptor, latch));
        latch.await();

        disruptor.shutdown();
        executorService.shutdown();

        System.out.println("总耗时:" + (System.currentTimeMillis() - begin)); //20995
    }

    @Test
    public void testWithoutDisruptor() {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            int num = (int) (Math.random() * 8000);
            num = num + 1000;
            int threadId = (int) Thread.currentThread().getId();
            String carLicense = "京Z" + num;
            ParkingInfo info = new ParkingInfo(threadId, carLicense);
            // 存入数据库
            service.save(info);
            // 发送kafka
            System.out.printf("Thread Id %s send %s in plaza message to kafka...%n",threadId,carLicense);
            // 发短信通知
            System.out.printf("Thread Id %s send %s in plaza sms to user%n",threadId,carLicense);
        }
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("总耗时:" + (System.currentTimeMillis() - begin));  //23799
    }
}
