package com.hy.event.strategy;

import com.hy.entity.ExposExceptionLog;
import com.hy.event.dto.ExceptionLogDTO;
import com.hy.event.enums.LogHandlerType;
import com.hy.service.ExposExceptionLogService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description: 将日志保存到 MySQL 数据库
 *
 * @author: yhong
 * Date: 2024/11/15
 */
@Service
@Slf4j
public class MysqlLogHandler implements LogHandler {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    @Autowired
    private ExposExceptionLogService exposExceptionLogService;
    @Override
    @Transactional
    public void handle(ExceptionLogDTO exceptionLogDTO) {
        // 具体逻辑：将日志保存到 MySQL 数据库
        log.info("=================将异常日志保存到数据库======================");
        ExposExceptionLog logEntity = MODEL_MAPPER.map(exceptionLogDTO, ExposExceptionLog.class);
        exposExceptionLogService.save(logEntity);
    }

    @Override
    public boolean support(LogHandlerType type) {
        return type == LogHandlerType.MYSQL;
    }
}
