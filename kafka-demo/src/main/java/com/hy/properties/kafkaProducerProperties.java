package com.hy.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Author: yhong
 * Date: 2024/1/26
 */
@ConfigurationProperties(prefix = "spring.kafka.producer")
@Component
@Getter
@Setter
@ToString
public class kafkaProducerProperties {
    private Integer retries;
    private Integer batchSize;
    private Integer bufferMemory;
}
