package com.hy.event.listener;

import com.hy.entity.ExposExceptionLog;
import com.hy.event.entity.ExceptionLogEntity;
import com.hy.event.event.ExceptionLogEvent;
import com.hy.event.event.OperationLogEvent;
import com.hy.event.handler.ExceptionLogEventProcessor;
import com.hy.service.ExposExceptionLogService;
import com.hy.utils.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
            // exposExceptionLogService.save(exceptionLogEntity);
            // 上报到API接口
            try {
                OkHttpUtils.postJson("http://127.0.0.1:8080/expos-exception-log/create", exceptionLogEntity);
            } catch (IOException e) {
                log.error("发送异常日志失败:{}", e.getMessage(), e);
            }
        });
    }
}
