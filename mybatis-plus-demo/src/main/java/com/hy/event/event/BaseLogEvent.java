package com.hy.event.event;

import com.hy.event.enums.LogTypeEnum;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Description: 日志事件基类
 *
 * @author: yhong
 * Date: 2024/11/7
 */
@Getter
public class BaseLogEvent<T> extends ApplicationEvent{
    private final T log;
    private final LogTypeEnum logType;

    public BaseLogEvent(T logMessage, LogTypeEnum logType) {
        super(logMessage);
        this.log = logMessage;
        this.logType = logType;
    }

}
