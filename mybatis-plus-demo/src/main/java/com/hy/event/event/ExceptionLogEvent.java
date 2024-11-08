package com.hy.event.event;

import com.hy.event.entity.ExceptionLogEntity;
import com.hy.event.enums.LogTypeEnum;

/**
 * Description: 异常日志事件
 *
 * @author: yhong
 * Date: 2024/11/7
 */
public class ExceptionLogEvent extends BaseLogEvent<ExceptionLogEntity> {

    public ExceptionLogEvent(ExceptionLogEntity logMessage, LogTypeEnum logType) {
        super(logMessage, logType);
    }
}
