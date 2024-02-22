package com.hy.config;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * Description: 自定义监听器
 * Author: yhong
 * Date: 2024/2/22
 */
public class MyStartingApplicationListener implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        System.out.println("自定义Starting 监听器");
    }
}
