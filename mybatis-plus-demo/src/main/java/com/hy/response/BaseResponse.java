package com.hy.response;

import java.io.Serializable;

/**
 * 基础的请求响应接口定义
 *
 * @author 王一飞
 * @since 2021/10/21
 */
public interface BaseResponse extends Serializable {

    Integer getCode();

    String getMsg();

    default <T> T getData() {
        throw new RuntimeException("no implements");
    }
}
