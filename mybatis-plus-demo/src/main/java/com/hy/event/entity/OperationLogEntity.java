package com.hy.event.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@TableName("expos_operation_log")
@Setter
@Getter
public class OperationLogEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String servName; // 服务名称
    
    private String menu; // 菜单
    private String type; // 操作类型
    private String target; // 目标
    
    private String ip; // user ip
    private Long userId;
    private String userName;
    
    private String userDept;
    private Long duration;
    private String module; // 修改设置的字段
    private String operationDesc;
    private String params;
    private String result;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date requestTime;
    private Long tenantId;
    
    public OperationLogEntity() {
    }

}