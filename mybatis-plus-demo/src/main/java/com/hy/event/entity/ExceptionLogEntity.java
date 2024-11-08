package com.hy.event.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * Description: 异常日志实体类
 *
 * @author: yhong
 * Date: 2024/11/7
 */
@TableName("expos_exception_log")
@Setter
@Getter
public class ExceptionLogEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String appName;
    private Long deptId;
    private String logType;
    private String logLevel;
    private String logContent;
    private Long userId;
    private String userName;
    private Integer traceId;
    private Integer tenantId;
}
