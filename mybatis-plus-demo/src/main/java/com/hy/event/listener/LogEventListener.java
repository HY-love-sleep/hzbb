package com.hy.event.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;

/**
 * Description: 监听器接口
 *
 * @author: yhong
 * Date: 2024/11/7
 */
public interface LogEventListener<T extends ApplicationEvent> {
    @EventListener
    void onEvent(T event);
}

