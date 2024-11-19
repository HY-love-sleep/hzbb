package com.hy.event.strategy;

import com.hy.event.dto.ExceptionLogDTO;
import com.hy.event.enums.LogHandlerType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Description: 将日志发送到 Kafka
 *
 * @author: yhong
 * Date: 2024/11/15
 */
@Service
@Slf4j
public class KafkaLogHandler implements LogHandler {
    @Override
    public void handle(ExceptionLogDTO log) {
        // 具体逻辑：将日志发送到 Kafka
    }

    @Override
    public boolean support(LogHandlerType type) {
        return type == LogHandlerType.KAFKA;
    }
}
