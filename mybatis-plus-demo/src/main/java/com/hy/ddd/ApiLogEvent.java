package com.hy.ddd;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Description: 日志事件类
 * Author: yhong
 * Date: 2024/10/31
 */
@Getter
public class ApiLogEvent extends BaseEvent {
    private final String operationType;
    private final String parameters;
    private final LocalDateTime timestamp;
    private final String apiPath;

    public ApiLogEvent(String operationType, String parameters, String apiPath) {
        this.operationType = operationType;
        this.parameters = parameters;
        this.timestamp = LocalDateTime.now();
        this.apiPath = apiPath;
    }
}
