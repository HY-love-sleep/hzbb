package com.hy.event.event;

import com.hy.event.entity.OperationLogEntity;
import com.hy.event.enums.LogTypeEnum;

/**
 * Description: 操作日志事件
 *
 * @author: yhong
 * Date: 2024/11/7
 */
public class OperationLogEvent extends BaseLogEvent<OperationLogEntity>{

    public OperationLogEvent(OperationLogEntity logMessage, LogTypeEnum logType) {
        super(logMessage, logType);
    }
}
