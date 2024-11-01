package com.hy.ddd;

import com.hy.service.OperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description: 抽象日志发布器
 * Author: yhong
 * Date: 2024/10/31
 */
@Slf4j
public abstract class AbstractLoggingEventPublisher<T> implements DataChangedEventPublisher<T> {
    @Autowired
    private OperationLogService logService;

    protected AbstractLoggingEventPublisher() {}

    @Override
    public void publish(ApiLogEvent apiLogEvent) {
        logEvent(apiLogEvent);
        publishEvent(apiLogEvent);
    }

    private void logEvent(ApiLogEvent apiLogEvent) {
        logService.saveLog(apiLogEvent);
    }

    protected abstract void publishEvent(ApiLogEvent apiLogEvent);
}
