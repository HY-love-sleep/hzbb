package com.hy.producer;

import com.hy.entity.Student;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;

public class StudentPublisher implements Runnable {
    private final Disruptor<Student> disruptor;
    private final KafkaConsumer kafkaConsumer; // 需要创建一个Kafka消费者，并实现pollMessage方法

    public StudentPublisher(Disruptor<Student> disruptor, KafkaConsumer kafkaConsumer) {
        this.disruptor = disruptor;
        this.kafkaConsumer = kafkaConsumer;
    }

    @Override
    public void run() {
        RingBuffer<Student> ringBuffer = disruptor.getRingBuffer();

        // 在这里编写Kafka消息的拉取逻辑，例如使用@KafkaListener或Kafka原生消费者API
        while (true) {
            // 拉取Kafka消息
            String kafkaMessage = kafkaConsumer.pollMessage(); // 需要实现这个方法

            // 将Kafka消息转换为Student对象
            Student student = convertKafkaMessageToStudent(kafkaMessage);

            // 发布到Disruptor的RingBuffer中
            long sequence = ringBuffer.next();
            try {
                Student event = ringBuffer.get(sequence);
                // 将Student的数据设置到event对象中
                event.setId(student.getId());
                event.setName(student.getName());
                event.setScore(student.getScore());
                // 可以设置其他字段...
            } finally {
                ringBuffer.publish(sequence);
            }
        }
    }

    private Student convertKafkaMessageToStudent(String kafkaMessage) {
        // 实现将Kafka消息转换为Student对象的逻辑
        // 你需要解析Kafka消息，并创建一个Student对象
        // 返回Student对象
    }
}
