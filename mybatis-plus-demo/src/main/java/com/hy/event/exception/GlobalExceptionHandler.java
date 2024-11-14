package com.hy.event.exception;

import com.hy.annotation.LogException;
import com.hy.event.entity.ExceptionLogEntity;
import com.hy.event.entity.SimpExceptionLog;
import com.hy.event.enums.BaseResultCodeEnum;
import com.hy.event.publisher.ExceptionLogPublisher;
import com.hy.event.publisher.SimpExceptionLogPublisher;
import com.hy.response.BaseResponse;
import com.hy.response.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;

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
    @Autowired
    private SimpExceptionLogPublisher simpExceptionLogPublisher;
    // @ExceptionHandler(value = Exception.class)
    // public void exceptionHandler(Exception e) {
    //     log.error(e.getStackTrace()[0].getClassName(), e);
    //     // mock
    //     ExceptionLogEntity entity = new ExceptionLogEntity();
    //     entity.setAppName("用户中心");
    //     entity.setDeptId(1L);
    //     entity.setLogType("ex");
    //     entity.setLogLevel("1");
    //     entity.setLogContent(e.getMessage());
    //     entity.setUserId(1L);
    //     entity.setUserName("hongy25");
    //     entity.setTenantId(1);
    //     entity.setTraceId(1);
    //
    //     // exceptionLogPublisher.publish(entity);
    //     simpExceptionLogPublisher.publish(new SimpExceptionLog("分类分级", "business", "测试异常"));
    // }



    // @ExceptionHandler(CustomException.class)
    // @LogException
    // public void handleCustomException(CustomException ex) {
    //     log.info("GlobalExceptionHandler 处理异常： {}", ex.getMessage(), ex);
    // }

    @ExceptionHandler(CustomException.class)
    @LogException
    public ResponseEntity<BaseResult> handleCustomException(CustomException ex) {
        BaseResult errorResponse = new BaseResult(ex.getStatusCode(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatusCode()));
    }
}
