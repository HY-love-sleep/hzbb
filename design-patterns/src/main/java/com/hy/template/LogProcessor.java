package com.hy.template;

/**
 * Description: 日志处理模版类
 *
 * @author: yhong
 * Date: 2024/1/16
 */
public abstract class LogProcessor {
    protected abstract void preProcessor();
    protected abstract void postProcessor();

    public final void execute(String logMessage, LogHandler logHandler) {
        preProcessor();
        logHandler.handleLog(logMessage);
        postProcessor();
    }

}
