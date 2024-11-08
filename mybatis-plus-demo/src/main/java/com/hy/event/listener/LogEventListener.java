package com.hy.event.listener;

import org.springframework.context.ApplicationEvent;

/**
 * Description: 监听器接口
 *
 * @author: yhong
 * Date: 2024/11/7
 */
public interface LogEventListener<T extends ApplicationEvent> {
    void onEvent(T event);
}

