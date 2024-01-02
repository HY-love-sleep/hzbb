package com.hy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hy.entity.Student;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Description: 发送Student到kafka中
 * Author: yhong
 * Date: 2024/1/2
 */
@Service
public class StudentProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public StudentProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value("${spring.kafka.student_topic}")
    private String topic;

    public void sendStudentToKafka(String student) {
        kafkaTemplate.send(topic, student);
    }

    public void sendStudentsToKafka(List<Student> students) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        for (Student student : students) {
            String jsonStudent = objectMapper.writeValueAsString(student);
            kafkaTemplate.send(topic, jsonStudent);
        }
    }

    // 异步发送
    public void AsyncSendStudentsToKafka(List<Student> students) {
        CompletableFuture[] futures = new CompletableFuture[students.size()];
        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);

            futures[i] = CompletableFuture.runAsync(() -> {
                try {
                    kafkaTemplate.send(topic, objectMapper.writeValueAsString(student));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures);
        try {
            allOf.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
