package com.hy.ddd;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Description: 事件基类
 * Author: yhong
 * Date: 2024/10/31
 */
@Data
public abstract class BaseEvent implements Serializable {
    private final String eventId;
    private final LocalDateTime eventTime;

    public BaseEvent() {
        this.eventId = UUID.randomUUID().toString();
        this.eventTime = LocalDateTime.now();
    }
}