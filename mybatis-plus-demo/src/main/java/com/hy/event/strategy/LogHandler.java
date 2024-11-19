package com.hy.event.strategy;


import com.hy.event.dto.ExceptionLogDTO;
import com.hy.event.enums.LogHandlerType;

/**
 * Description: 日志处理策略接口
 *
 * @author: yhong
 * Date: 2024/11/15
 */
public interface LogHandler {
    void handle(ExceptionLogDTO log);
    boolean support(LogHandlerType type);
}
