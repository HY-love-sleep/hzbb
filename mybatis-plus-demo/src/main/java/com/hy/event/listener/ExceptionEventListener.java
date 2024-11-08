package com.hy.event.listener;

import com.hy.event.event.ExceptionLogEvent;
import com.hy.event.event.OperationLogEvent;
import org.springframework.stereotype.Component;

/**
 * Description: 异常日志监听器
 *
 * @author: yhong
 * Date: 2024/11/7
 */
@Component
public class ExceptionEventListener implements LogEventListener<ExceptionLogEvent>{

    @Override
    public void onEvent(ExceptionLogEvent event) {

    }
}
