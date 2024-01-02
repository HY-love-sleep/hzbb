package com.hy.handler;

import com.hy.entity.ParkingInfo;
import com.hy.event.InParkingDataEvent;
import com.hy.service.KafkaProducerService;
import com.lmax.disruptor.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Description: 停车信息发送kafka
 * Author: yhong
 * Date: 2023/12/29
 */
@Component
public class ParkingDataToKafkaHandler implements EventHandler<InParkingDataEvent>{
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Value("${spring.kafka.topic}")
    private String topic;

    @Override
    public void onEvent(InParkingDataEvent inParkingDataEvent, long l, boolean b) throws Exception {
        int threadId = (int) Thread.currentThread().getId();
        String carLicense = inParkingDataEvent.getCarLicense();
        ParkingInfo info = new ParkingInfo(threadId, carLicense);
        // 增加实际发送kafka消息
        kafkaProducerService.sendMessage(topic,info.toString());
        System.out.printf("Thread Id %s send %s in plaza message to kafka...%n",threadId,carLicense);
    }

}
