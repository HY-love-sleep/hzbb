package com.hy.event.exception;

import com.hy.event.publisher.ExceptionLogPublisher;
import com.hy.event.publisher.SimpExceptionLogPublisher;
import com.hy.response.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseResult> handleCustomException(CustomException ex) {
        BaseResult errorResponse = new BaseResult(ex.getStatusCode(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatusCode()));
    }
}
