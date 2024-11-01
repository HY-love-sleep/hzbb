package com.hy.service;

import com.hy.ddd.ApiLogEvent;
import com.hy.entity.OperationLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yhong
 * @since 2024-10-31
 */
public interface OperationLogService extends IService<OperationLog> {
    void saveLog(ApiLogEvent logEvent);

}
