package com.hy.event.handler;

import com.hy.event.event.ExceptionLogEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Description: 异常日志处理器
 *
 * @author: yhong
 * Date: 2024/11/8
 */
@Component
public class ExceptionLogEventProcessor {
    public void process(ExceptionLogEvent event, LogEventHandler<ExceptionLogEvent> handler) {
        handler.handle(event);
    }
}
