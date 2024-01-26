package com.hy.comsumer;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: student主题消费者
 * Author: yhong
 * Date: 2024/1/26
 */
@Slf4j
@Component
public class StudentConsumer {
    @KafkaListener(id = "operation", topics = {"student-info"}, containerFactory = "batchFactory", errorHandler = "consumerAwareErrorHandler")
    public void batchConsumer(List<ConsumerRecord<?, ?>> consumerRecords, Acknowledgment ack) {
        long start = System.currentTimeMillis();

        for (ConsumerRecord<?, ?> consumerRecord : consumerRecords) {
            log.info("消费的每条数据为：{}", consumerRecord.value());
        }
        ack.acknowledge();
        log.info("收到bigData推送的数据，拉取数据量：{}，消费时间：{}ms", consumerRecords.size(), (System.currentTimeMillis() - start));
    }
}
