package com.hy.aspect;

import com.hy.event.entity.ExceptionLogEntity;
import com.hy.event.entity.SimpExceptionLog;
import com.hy.event.publisher.ExceptionLogPublisher;
import com.hy.event.publisher.SimpExceptionLogPublisher;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExceptionHandlingAspect {

    @Autowired
    private ExceptionLogPublisher exceptionLogPublisher;
    @Autowired
    private SimpExceptionLogPublisher simpExceptionLogPublisher;

    @AfterThrowing(pointcut = "execution(* com.hy.service..*(..))", throwing = "e")
    public void handleException(Exception e) {
        log.error("发生异常: ", e);
        // exceptionLogPublisher.publish(createExceptionLogEntity(e)); // 发布异常日志
        simpExceptionLogPublisher.publish(new SimpExceptionLog("分类分级", "business", "测试异常"));
    }

    private ExceptionLogEntity createExceptionLogEntity(Exception e) {
        ExceptionLogEntity entity = new ExceptionLogEntity();
        entity.setAppName("用户中心");
        entity.setDeptId(1L);
        entity.setLogType("ex");
        entity.setLogLevel("1");
        entity.setLogContent(e.getMessage());
        entity.setUserId(1L);
        entity.setUserName("hongy25");
        entity.setTenantId(1);
        entity.setTraceId(1);
        return entity;
    }
}
