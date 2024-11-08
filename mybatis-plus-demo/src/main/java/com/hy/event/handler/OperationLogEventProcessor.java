package com.hy.event.handler;

import com.hy.event.event.OperationLogEvent;
import org.springframework.stereotype.Component;

/**
 * Description: 操作日志事件处理
 *
 * @author: yhong
 * Date: 2024/11/8
 */
@Component
public class OperationLogEventProcessor {
    public void process(OperationLogEvent event, LogEventHandler<OperationLogEvent> handler){
        handler.handle(event);
    }
}
