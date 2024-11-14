package com.hy.aspect;

import com.hy.event.entity.SimpExceptionLog;
import com.hy.event.publisher.SimpExceptionLogPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ExceptionHandlingAspect {
    private final Environment environment;
    private final SimpExceptionLogPublisher simpExceptionLogPublisher;

    @Around("execution(* com.hy..*(..))")
    public Object aroundServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // 执行方法
            return joinPoint.proceed();
        } catch (Exception ex) {
            // 处理服务层异常
            log.info("Service layer exception caught in aspect: {}", ex.getMessage());

            SimpExceptionLog event = new SimpExceptionLog();
            event.setAppName(environment.getProperty("server.servlet.context-path"))
                    .setLogType(ex.getClass().getName())
                    .setLogContent(ex.getMessage());
            simpExceptionLogPublisher.publish(event);
            // 重新抛出异常，让全局异常处理器处理
            throw ex;
        }
    }
}