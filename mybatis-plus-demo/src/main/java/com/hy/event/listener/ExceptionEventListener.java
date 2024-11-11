package com.hy.event.listener;

import com.hy.entity.ExposExceptionLog;
import com.hy.event.entity.ExceptionLogEntity;
import com.hy.event.event.ExceptionLogEvent;
import com.hy.event.event.OperationLogEvent;
import com.hy.event.handler.ExceptionLogEventProcessor;
import com.hy.service.ExposExceptionLogService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Description: 异常日志监听器
 *
 * @author: yhong
 * Date: 2024/11/7
 */
@Component
@Slf4j
public class ExceptionEventListener implements LogEventListener<ExceptionLogEvent>{
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    @Autowired
    private ExposExceptionLogService exposExceptionLogService;
    @Autowired
    private ExceptionLogEventProcessor exceptionLogEventProcessor;

    @Override
    @EventListener
    public void onEvent(ExceptionLogEvent event) {
        log.info("进入ExceptionLogEvent监听器");
        exceptionLogEventProcessor.process(event, handle -> {
            ExceptionLogEntity exceptionLog = event.getLog();
            ExposExceptionLog exceptionLogEntity = MODEL_MAPPER.map(exceptionLog, ExposExceptionLog.class);
            // 入库
            exposExceptionLogService.save(exceptionLogEntity);
        });
    }
}
