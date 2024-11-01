package com.hy.ddd;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description: 日志切面
 * Author: yhong
 * Date: 2024/10/31
 */
@Aspect
@Component
public class ApiLogAspect {
    @Autowired
    private ApiLogEventPublisher apiLogEventPublisher;

    @Before("execution(* com.hy.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        // 获取方法参数
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();

        StringBuilder parameters = new StringBuilder();
        for (Object arg : args) {
            parameters.append(arg).append(",");
        }

        // 发布日志事件
        apiLogEventPublisher.onCreated(parameters.toString());
    }

    @After("execution(* com.hy.controller.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        // 获取方法参数
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();

        StringBuilder parameters = new StringBuilder();
        for (Object arg : args) {
            parameters.append(arg).append(",");
        }

        // 发布日志事件
        apiLogEventPublisher.onUpdated(parameters.toString(), "");
    }
}
