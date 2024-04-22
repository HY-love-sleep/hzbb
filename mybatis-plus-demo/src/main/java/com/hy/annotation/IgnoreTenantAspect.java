package com.hy.annotation;

import com.hy.config.ApiContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description: IgnoreTenantAspect切面
 * Author: yhong
 * Date: 2024/4/19
 */
@Aspect
@Component
@Slf4j
public class IgnoreTenantAspect {
    @Autowired
    private ApiContext apiContext;
    public IgnoreTenantAspect() {
    }
    @Around("@annotation(ignoreTenant)")
    public Object doAround(ProceedingJoinPoint joinPoint, IgnoreTenant ignoreTenant) throws Throwable {
        if (ignoreTenant.value()) {
            apiContext.setCurrentTenantId(-1L);
        }
        return joinPoint.proceed();
    }
}
