package com.hy.event.listener;

import com.hy.event.dto.ExceptionLogDTO;
import com.hy.event.entity.ExceptionLog;
import com.hy.event.enums.LogHandlerType;
import com.hy.event.event.ExceptionLogEvent;
import com.hy.event.handler.ExceptionLogEventProcessor;
import com.hy.event.strategy.LogHandler;
import com.hy.event.strategy.LogHandlerFactory;
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
    private LogHandlerFactory logHandlerFactory;
    @Autowired
    private ExceptionLogEventProcessor exceptionLogEventProcessor;

    @Override
    @EventListener
    public void onEvent(ExceptionLogEvent event) {
        LogHandler logHandler = logHandlerFactory.getLogHandler(LogHandlerType.MYSQL);
        log.info("进入ExceptionLogEvent监听器");
        exceptionLogEventProcessor.process(event, handle -> {
            ExceptionLog exceptionLog = event.getLog();
            ExceptionLogDTO dto = MODEL_MAPPER.map(exceptionLog, ExceptionLogDTO.class);
            logHandler.handle(dto);
        });
    }
}
