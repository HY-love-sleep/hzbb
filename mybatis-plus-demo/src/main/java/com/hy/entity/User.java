package com.hy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "tb_user")
@Builder
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String userNo;
    private String nickname;
    private String email;
    private String phone;
    private Integer gender;
    private Date birthday;
    private Integer isDelete;
    private Date createTime;
    private Date updateTime;
}