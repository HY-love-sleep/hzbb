package com.hy.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Description: 自定义初始化器
 * Author: yhong
 * Date: 2024/2/22
 */
public class MyApplicationContextInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("自定义ApplicationContextInitializer");
    }
}
