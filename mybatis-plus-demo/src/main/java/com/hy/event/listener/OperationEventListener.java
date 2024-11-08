package com.hy.event.listener;

import com.hy.event.event.OperationLogEvent;
import org.springframework.stereotype.Component;

/**
 * Description: 操作日志监听器
 *
 * @author: yhong
 * Date: 2024/11/7
 */
@Component
public class OperationEventListener implements LogEventListener<OperationLogEvent> {

    @Override
    public void onEvent(OperationLogEvent event) {

    }
}
