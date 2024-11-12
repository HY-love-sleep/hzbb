package com.hy.event.publisher;

import com.hy.event.entity.ExceptionLogEntity;
import com.hy.event.entity.SimpExceptionLog;
import com.hy.event.enums.LogTypeEnum;
import com.hy.event.event.ExceptionLogEvent;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author: yhong
 * Date: 2024/11/11
 */
@Service
@RequiredArgsConstructor
public class SimpExceptionLogPublisher implements LogEventPublisher<SimpExceptionLog> {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(SimpExceptionLog logEvent) {
        ExceptionLogEntity exceptionLogEntity = MODEL_MAPPER.map(logEvent, ExceptionLogEntity.class);
        exceptionLogEntity.setDeptId(1L)
                .setLogLevel("1")
                .setUserId(1L)
                .setUserName("hongy")
                .setTraceId(1)
                .setTenantId(1);
        ExceptionLogEvent exceptionLogEvent = new ExceptionLogEvent(exceptionLogEntity, LogTypeEnum.EXCEPTION);
        applicationEventPublisher.publishEvent(exceptionLogEvent);
    }
}
