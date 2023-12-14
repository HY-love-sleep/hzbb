package com.hy.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 全局异常处理类
 * Author: yhong
 * Date: 2023/12/6
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 业务异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public ResultBody bizExceptionHandler(HttpServletRequest request, BizException e) {
        log.error("业务异常， 原因：{}", e.getErrorMsg());
        return ResultBody.error(e.getErrorCode(), e.getErrorMsg());
    }

    @ExceptionHandler
    @ResponseBody
    public ResultBody nullPointExceptionHandler(HttpServletRequest request, NullPointerException e) {
        log.error("空指针异常， 原因:{}", e.getMessage());
        return ResultBody.error(CommonEnum.BODY_NOT_MATCH);
    }

    @ExceptionHandler
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest request, Exception e) {
        log.error("未知异常， 原因：{}", e.getMessage());
        return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR);
    }

}
