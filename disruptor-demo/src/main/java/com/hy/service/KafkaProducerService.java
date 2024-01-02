package com.hy.service;

import com.hy.entity.ParkingInfo;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String value) {
        // 或者使用KafkaTemplate发送消息
        kafkaTemplate.send(topic, value);
    }
}
