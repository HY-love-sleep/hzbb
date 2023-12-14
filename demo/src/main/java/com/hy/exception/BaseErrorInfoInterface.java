package com.hy.exception;

/**
 * Description: 错误信息接口
 * Author: yhong
 * Date: 2023/12/6
 */
public interface BaseErrorInfoInterface {
    /**
     * 获取错误码
     * @return
     */
    String getResultCode();

    /**
     * 获取错误信息
     * @return
     */
    String getResultMsg();
}
