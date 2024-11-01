package com.hy.service.impl;

import com.hy.ddd.ApiLogEvent;
import com.hy.entity.OperationLog;
import com.hy.mapper.OperationLogMapper;
import com.hy.service.OperationLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yhong
 * @since 2024-10-31
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {

    @Override
    public void saveLog(ApiLogEvent logEvent) {
        OperationLog log = new OperationLog();
        log.setOperationType(logEvent.getOperationType());
        log.setParameters(logEvent.getParameters());
        log.setApiPath(logEvent.getApiPath());
        log.setTimestamp(logEvent.getTimestamp());
        this.save(log);
    }
}
