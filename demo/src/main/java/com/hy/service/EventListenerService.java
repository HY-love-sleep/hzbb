package com.hy.service;

import com.hy.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Description: 事件监听器
 * Author: yhong
 * Date: 2024/1/31
 */
@Slf4j
@Component
public class EventListenerService {
    @EventListener
    public void handlePersonEvent(BaseEvent<Person> personEvent) {
        log.info("监听到Person事件：{}", personEvent);
    }

    @EventListener
    public void handlerOrderEvent(BaseEvent<Order> orderEvent) {
        log.info("监听到Order事件：{}", orderEvent);
    }
}
