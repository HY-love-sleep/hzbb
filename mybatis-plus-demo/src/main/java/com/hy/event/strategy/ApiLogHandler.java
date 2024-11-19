package com.hy.event.strategy;

import com.hy.event.dto.ExceptionLogDTO;
import com.hy.event.enums.LogHandlerType;
import com.hy.utils.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Description: 将日志发送到 API 接口
 *
 * @author: yhong
 * Date: 2024/11/15
 */
@Service
@Slf4j
public class ApiLogHandler implements LogHandler {
    private final static String API_URL = "ip:port/dgpc/api/error/log/add";
    @Override
    public void handle(ExceptionLogDTO exceptionLogDTO) {
        try {
            log.info("==========================异常日志上报========================");
            OkHttpUtils.postJson(API_URL, exceptionLogDTO);
        } catch (Exception e) {
            // todo: 这个异常可能被捕获然后上报， 形成套娃， 所以这个异常不应该进入切面
            log.error("异常上报失败, url:{}", API_URL, e);
        }

    }

    @Override
    public boolean support(LogHandlerType type) {
        return type == LogHandlerType.API;
    }
}
