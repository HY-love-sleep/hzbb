package com.hy.event.event;

import com.hy.event.entity.ExceptionLog;
import com.hy.event.enums.LogTypeEnum;

/**
 * Description: 异常日志事件
 *
 * @author: yhong
 * Date: 2024/11/7
 */
public class ExceptionLogEvent extends BaseLogEvent<ExceptionLog> {

    public ExceptionLogEvent(ExceptionLog logMessage, LogTypeEnum logType) {
        super(logMessage, logType);
    }
}
