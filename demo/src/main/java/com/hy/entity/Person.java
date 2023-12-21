package com.hy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author hy
 * @since 2023-12-21
 */
@Data
@Accessors(chain = true)
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 姓名
     */
    private String name;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 所属部门ID
     */
    private Long deptId;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 锁定标记，0未锁定，9已锁定
     */
    private String lockFlag;

    /**
     * 删除标记，0未删除，1已删除
     */
    private String delFlag;

    /**
     * 微信登录openId
     */
    private String wxOpenid;

    /**
     * 小程序openId
     */
    private String miniOpenid;

    /**
     * QQ openId
     */
    private String qqOpenid;

    /**
     * 码云标识
     */
    private String giteeLogin;

    /**
     * 开源中国标识
     */
    private String oscId;

    /**
     * 所属租户ID
     */
    private Long tenantId;
}
