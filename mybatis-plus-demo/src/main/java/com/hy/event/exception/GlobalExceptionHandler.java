package com.hy.event.exception;

import com.hy.event.entity.ExceptionLogEntity;
import com.hy.event.publisher.ExceptionLogPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Description: 全局异常处理
 *
 * @author: yhong
 * Date: 2024/11/11
 */
@RestControllerAdvice(basePackages = {"com.hy"})
@Slf4j
public class GlobalExceptionHandler {
    @Autowired
    private ExceptionLogPublisher exceptionLogPublisher;
    @ExceptionHandler(value = Exception.class)
    public void exceptionHandler(Exception e) {
        log.error(e.getStackTrace()[0].getClassName(), e);
        // mock
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

        exceptionLogPublisher.publish(entity);
    }
}
