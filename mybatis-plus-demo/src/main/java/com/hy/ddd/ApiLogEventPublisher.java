package com.hy.ddd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Description: 具体日志事件发布器
 * Author: yhong
 * Date: 2024/10/31
 */
@Slf4j
@Service
public class ApiLogEventPublisher extends AbstractLoggingEventPublisher<String> {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public ApiLogEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    protected void publishEvent(ApiLogEvent apiLogEvent) {
        applicationEventPublisher.publishEvent(apiLogEvent);
    }
}
