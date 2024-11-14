package com.hy.event.enums;

import com.hy.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 定义标准的响应码
 *
 * @author 王一飞
 * @since 2021/10/21
 */
@AllArgsConstructor
@Getter
public enum BaseResultCodeEnum implements BaseResponse {

    /*通用成功*/
    SUCCESS(200, "ok"),

    /*通用系统异常*/
    ERROR(1000, "系统出现异常"),

    /*通用参数错误*/
    ERROR_PARAM(2000, "参数错误"),
    ERROR_DATA(3000, "数据错误"),
    ERROR_CONFIG(4000, "配置错误"),
    ERROR_INNER(5000, "内部错误"),
//
//    /*请求异常*/
    ERROR_REQUEST_PARAMS(6000, "请求参数异常"),
    ERROR_REQUEST_ARGUMENT_TYPE(7000, "参数类型错误"),
    ERROR_REQUEST_URI(8000, "请求路径不正确"),

    ;

    /**
     * 结果编码
     */
    private final int code;
    /**
     * 结果消息
     */
    private final String msg;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
