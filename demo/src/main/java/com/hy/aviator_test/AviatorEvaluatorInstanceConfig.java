package com.hy.aviator_test;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 注册自定义函数
 * Author: yhong
 * Date: 2024/1/8
 */
@Configuration
public class AviatorEvaluatorInstanceConfig {
    @Bean
    public AviatorEvaluatorInstance aviatorEvaluatorInstance() {
        AviatorEvaluatorInstance instance = AviatorEvaluator.getInstance();
        // 开启缓存
        instance.setCachedExpressionByDefault(true);
        // 使用LRU缓存， 最大值为100个
        instance.useLRUExpressionCache(100);
        // 注册内置函数， 版本比较函数
        instance.addFunction(new VersionFunction());

        return instance;
    }
}
