package com.hy.template;

/**
 * Description: 日志处理器【函数式接口】
 *
 * @author: yhong
 * Date: 2024/10/16
 */
@FunctionalInterface
public interface LogHandler {
    void handleLog(String logMessage);
}
