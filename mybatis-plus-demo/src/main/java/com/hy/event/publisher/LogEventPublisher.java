package com.hy.event.publisher;

import com.hy.event.event.BaseLogEvent;

/**
 * Description: 日志事件发布接口
 *
 * @author: yhong
 * Date: 2024/11/7
 */
public interface LogEventPublisher<T> {
    void publish(T logEvent);
}

