package com.hy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hy.entity.Student;
import com.lmax.disruptor.RingBuffer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * Description: 拉模式的kafka消费者
 * Author: yhong
 * Date: 2024/1/10
 */
@Slf4j
@Component
public class KafkaPollConsumer {
    private KafkaConsumer<String, String> consumer;

    @Autowired
    private StudentDisruptor disruptor;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaPollConsumer() {
        this.consumer = null;
    }

    @PostConstruct
    public void init() {
        initializeConsumer();
        startConsuming();
    }


    public void initializeConsumer() {
        Properties props = new Properties();
        // 配置Kafka连接属性
        props.put("bootstrap.servers", "192.168.174.132:9092");
        props.put("group.id", "student-info-poll-consumer-group");
        props.put("max.poll.records", "100"); // 设置每次拉取的最大记录数为100
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        this.consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("student-info"));
    }


    @Async("asyncExecutor")
    public void startConsuming() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(2000));
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
