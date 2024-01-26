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
@ConfigurationProperties(prefix = "spring.kafka.consumer")
@Component
@Getter
@Setter
@ToString
public class KafkaConsumerProperties {
    private String  groupId;
    private String autoOffsetReset;
    private Integer maxPollRecords;
    private Boolean enableAutoCommit;
    private Integer autoCommitInterval;
    private Batch batch;
}
