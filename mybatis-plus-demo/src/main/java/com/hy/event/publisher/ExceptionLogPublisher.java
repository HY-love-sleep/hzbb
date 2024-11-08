package com.hy.event.publisher;

import com.hy.event.entity.ExceptionLogEntity;
import com.hy.event.enums.LogTypeEnum;
import com.hy.event.event.ExceptionLogEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Description: 异常日志事件发布类
 *
 * @author: yhong
 * Date: 2024/11/7
 */
@Service
@RequiredArgsConstructor
public class ExceptionLogPublisher implements LogEventPublisher<ExceptionLogEntity> {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(ExceptionLogEntity exceptionLogEntity) {
        ExceptionLogEvent exceptionLogEvent = new ExceptionLogEvent(exceptionLogEntity, LogTypeEnum.EXCEPTION);
        applicationEventPublisher.publishEvent(exceptionLogEvent);
    }
}
