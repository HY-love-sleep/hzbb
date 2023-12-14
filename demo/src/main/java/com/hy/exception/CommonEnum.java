package com.hy.exception;

/**
 * Description: 错误枚举类
 * Author: yhong
 * Date: 2023/12/6
 */
public enum CommonEnum implements BaseErrorInfoInterface{
    SUCCESS("200", "成功"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误"),
    SERVER_BUSY("503", "服务器正忙"),
    BODY_NOT_MATCH("510", "空指针！")
    ;

    private final String resultCode;
    private final String resultMsg;

    CommonEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }


    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
