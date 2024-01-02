// package com.hy.config;
//
// import com.hy.entity.Student;
// import org.apache.kafka.clients.producer.ProducerConfig;
// import org.apache.kafka.clients.consumer.ConsumerConfig;
// import org.apache.kafka.common.serialization.StringSerializer;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
// import org.springframework.kafka.core.*;
// import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
// import org.springframework.kafka.support.serializer.JsonDeserializer;
// import org.springframework.kafka.support.serializer.JsonSerializer;
//
// import java.util.HashMap;
// import java.util.Map;
//
// @Configuration
// public class KafkaConfig {
//     @Bean
//     public ProducerFactory<String, Student> producerFactory() {
//         Map<String, Object> configProps = new HashMap<>();
//         configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.174.132:9092");
//         configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//         configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//         return new DefaultKafkaProducerFactory<>(configProps);
//     }
//
//     @Bean
//     public KafkaTemplate<String, Student> kafkaTemplate() {
//         return new KafkaTemplate<>(producerFactory());
//     }
//
//     @Bean
//     public ConsumerFactory<String, Student> consumerFactory() {
//         Map<String, Object> configProps = new HashMap<>();
//         configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.174.132:9092");
//         configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//         configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//         configProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
//         configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Student.class.getName());
//         return new DefaultKafkaConsumerFactory<>(configProps);
//     }
//
//     @Bean
//     public ConcurrentKafkaListenerContainerFactory<String, Student> kafkaListenerContainerFactory() {
//         ConcurrentKafkaListenerContainerFactory<String, Student> factory =
//             new ConcurrentKafkaListenerContainerFactory<>();
//         factory.setConsumerFactory(consumerFactory());
//         return factory;
//     }
// }
