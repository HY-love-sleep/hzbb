package com.hy.exception;

import lombok.NoArgsConstructor;

/**
 * Description: 自定义异常
 * Author: yhong
 * Date: 2023/12/6
 */
public class BizException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    protected String errorCode;
    protected String errorMsg;

    public BizException() {
        super();
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public BizException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    public BizException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode,cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
