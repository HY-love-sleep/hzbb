package com.hy.event.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Description: 异常日志
 *
 * @author: yhong
 * Date: 2024/11/15
 */
@Getter
@Setter
public class ExceptionLogDTO implements Serializable {
    private static final long serialVersionUID = 1L;

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
