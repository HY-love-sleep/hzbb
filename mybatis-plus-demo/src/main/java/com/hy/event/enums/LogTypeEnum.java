package com.hy.event.enums;

/**
 * Description: 日志类型枚举
 *
 * @author: yhong
 * Date: 2024/11/7
 */
public enum LogTypeEnum {
    OPERATE("操作日志", "OPERATE"),
    LOGIN("登录日志", "LOGIN"),
    EXCEPTION("异常日志", "EXCEPTION");

    private final String description;
    private final String code;

    LogTypeEnum(String description, String code) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public static LogTypeEnum fromCode(String code) {
        for (LogTypeEnum type : LogTypeEnum.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid log type code: " + code);
    }
}

