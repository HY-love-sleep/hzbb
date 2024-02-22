package com.hy.config;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * Description: 自定义监听器
 * Author: yhong
 * Date: 2024/2/22
 */
public class MyStartedApplicationListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("自定义Started 监听器");
    }
}
