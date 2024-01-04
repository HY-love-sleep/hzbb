package com.hy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hy.entity.Student;
import com.lmax.disruptor.RingBuffer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

/**
 * Description: 拉模式下的Kafka消费端程序
 * Author: yhong
 * Date: 2024/1/4
 */
@Service
public class KafkaPollConsumerService implements Runnable {

    @Autowired
    private StudentDisruptor disruptor;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final KafkaConsumer<String, String> consumer;

    public KafkaPollConsumerService() {
        Properties props = new Properties();
        // 配置Kafka连接属性
        props.put("bootstrap.servers", "192.168.174.132:9092");
        props.put("group.id", "student-info-poll-consumer-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("student-info"));
    }

    @Override
    public void run() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                try {
                    Student event = objectMapper.readValue(record.value(), Student.class);
                    RingBuffer<Student> ringBuffer = disruptor.getDisruptor().getRingBuffer();
                    long sequence = ringBuffer.next();
                    try {
                        Student student = ringBuffer.get(sequence);
                        student.setName(event.getName());
                        student.setScore(event.getScore());
                    } finally {
                        ringBuffer.publish(sequence);
                    }
                } catch (JsonProcessingException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}

