package com.hy.event.listener;

import com.hy.entity.ExposExceptionLog;
import com.hy.event.entity.OperationLogEntity;
import com.hy.event.event.OperationLogEvent;
import com.hy.event.handler.OperationLogEventProcessor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description: 操作日志监听器
 *
 * @author: yhong
 * Date: 2024/11/7
 */
@Component
public class OperationEventListener implements LogEventListener<OperationLogEvent> {
    private static final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private OperationLogEventProcessor operationLogEventProcessor;

    @Override
    public void onEvent(OperationLogEvent event) {
        operationLogEventProcessor.process(event, operationLogEvent  -> {
            // 处理操作日志逻辑
            OperationLogEntity log = event.getLog();
            modelMapper.map(log, ExposExceptionLog.class);

        });
    }
}
