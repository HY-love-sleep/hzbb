package com.hy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hy.entity.Student;
import com.lmax.disruptor.RingBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Description: kafka消费者， 拉取kafka消息
 * Author: yhong
 * Date: 2024/1/3
 */

@Service
public class KafkaPushConsumer {

    @Autowired
    private StudentDisruptor disruptor;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 推模式被动接受数据， 数据量大容易将服务打死
     * @param message
     * @throws JsonProcessingException
     */

    // @KafkaListener(topics = "student-info", groupId = "student-info-consumer-group")
    public void listen(String message) throws JsonProcessingException {
        Student event = objectMapper.readValue(message, Student.class);
        RingBuffer<Student> ringBuffer = disruptor.getDisruptor().getRingBuffer();
        long sequence = ringBuffer.next();
        try {
            Student student = ringBuffer.get(sequence);
            student.setName(event.getName());
            student.setScore(event.getScore());
        } finally {
            ringBuffer.publish(sequence);
        }
    }

}

