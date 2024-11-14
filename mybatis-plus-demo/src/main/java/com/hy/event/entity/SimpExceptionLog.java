package com.hy.event.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Description: 各模块提供异常日志信息
 *
 * @author: yhong
 * Date: 2024/11/11
 */
@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class SimpExceptionLog {
    private String appName;
    private String logType;
    private String logContent;

    public SimpExceptionLog(String appName, String logType, String logContent) {
        this.appName = appName;
        this.logType = logType;
        this.logContent = logContent;
    }
}
