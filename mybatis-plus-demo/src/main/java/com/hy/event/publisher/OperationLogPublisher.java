package com.hy.event.publisher;

import com.hy.event.entity.OperationLog;
import com.hy.event.enums.LogTypeEnum;
import com.hy.event.event.OperationLogEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Description: 操作日志事件发布类
 *
 * @author: yhong
 * Date: 2024/11/7
 */
@Service
@RequiredArgsConstructor
public class OperationLogPublisher implements LogEventPublisher<OperationLog> {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(OperationLog operationLog) {
        OperationLogEvent operationLogEvent = new OperationLogEvent(operationLog, LogTypeEnum.OPERATE);
        applicationEventPublisher.publishEvent(operationLogEvent);
    }
}
