package com.hy.event.handler;

import com.hy.event.event.BaseLogEvent;

/**
 * Description: 日志事件处理器函数式接口, 通过泛型灵活处理不同的日志类型事件
 *
 * @author: yhong
 * Date: 2024/11/7
 */
@FunctionalInterface
public interface LogEventHandler<T extends BaseLogEvent<?>> {
    void handle(T logEvent);
}

