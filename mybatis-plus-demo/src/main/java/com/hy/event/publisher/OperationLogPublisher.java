package com.hy.event.publisher;

import com.hy.event.entity.OperationLogEntity;
import com.hy.event.enums.LogTypeEnum;
import com.hy.event.event.BaseLogEvent;
import com.hy.event.event.ExceptionLogEvent;
import com.hy.event.event.OperationLogEvent;
import lombok.AllArgsConstructor;
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
public class OperationLogPublisher implements LogEventPublisher<OperationLogEntity> {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(OperationLogEntity operationLogEntity) {
        OperationLogEvent operationLogEvent = new OperationLogEvent(operationLogEntity, LogTypeEnum.OPERATE);
        applicationEventPublisher.publishEvent(operationLogEvent);
    }
}
