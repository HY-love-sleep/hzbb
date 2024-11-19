package com.hy.event.strategy;

import com.hy.event.enums.LogHandlerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description: 日志处理策略工厂
 *
 * @author: yhong
 * Date: 2024/11/15
 */
@Component
public class LogHandlerFactory {

    private final Map<String, LogHandler> handlers;

    @Autowired
    public LogHandlerFactory(ApplicationContext context) {
        this.handlers = context.getBeansOfType(LogHandler.class);
    }

    public LogHandler getLogHandler(LogHandlerType type) {
        for (LogHandler handler : handlers.values()) {
            if (handler.support(type)) {
                return handler;
            }
        }
        throw new IllegalArgumentException("Unsupported log handler type: " + type);
    }
}
