package com.hy.event.enums;

/**
 * Description: 日志处理类型枚举
 *
 * @author: yhong
 * Date: 2024/11/15
 */
public enum LogHandlerType {
    API("api"),
    MYSQL("mysql"),
    KAFKA("kafka");

    private final String type;

    LogHandlerType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static LogHandlerType fromType(String type) {
        for (LogHandlerType handlerType : values()) {
            if (handlerType.getType().equals(type)) {
                return handlerType;
            }
        }
        throw new IllegalArgumentException("Unsupported log handler type: " + type);
    }
}
