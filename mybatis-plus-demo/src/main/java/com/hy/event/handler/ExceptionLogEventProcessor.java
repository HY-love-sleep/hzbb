package com.hy.event.handler;

import com.hy.event.event.ExceptionLogEvent;
import com.hy.event.event.OperationLogEvent;
import org.springframework.stereotype.Component;

/**
 * Description: 异常日志处理器
 *
 * @author: yhong
 * Date: 2024/11/8
 */
@Component
public class ExceptionLogEventProcessor {
    public void process(ExceptionLogEvent event, LogEventHandler<ExceptionLogEvent> handler){
        handler.handle(event);
    }
}
