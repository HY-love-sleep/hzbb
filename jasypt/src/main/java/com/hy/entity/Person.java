package com.hy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author hongy
 * @since 2024-05-06
 */
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(String lockFlag) {
        this.lockFlag = lockFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public String getMiniOpenid() {
        return miniOpenid;
    }

    public void setMiniOpenid(String miniOpenid) {
        this.miniOpenid = miniOpenid;
    }

    public String getQqOpenid() {
        return qqOpenid;
    }

    public void setQqOpenid(String qqOpenid) {
        this.qqOpenid = qqOpenid;
    }

    public String getGiteeLogin() {
        return giteeLogin;
    }

    public void setGiteeLogin(String giteeLogin) {
        this.giteeLogin = giteeLogin;
    }

    public String getOscId() {
        return oscId;
    }

    public void setOscId(String oscId) {
        this.oscId = oscId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "Person{" +
        ", userId = " + userId +
        ", username = " + username +
        ", password = " + password +
        ", salt = " + salt +
        ", phone = " + phone +
        ", avatar = " + avatar +
        ", nickname = " + nickname +
        ", name = " + name +
        ", email = " + email +
        ", deptId = " + deptId +
        ", createBy = " + createBy +
        ", updateBy = " + updateBy +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        ", lockFlag = " + lockFlag +
        ", delFlag = " + delFlag +
        ", wxOpenid = " + wxOpenid +
        ", miniOpenid = " + miniOpenid +
        ", qqOpenid = " + qqOpenid +
        ", giteeLogin = " + giteeLogin +
        ", oscId = " + oscId +
        ", tenantId = " + tenantId +
        "}";
    }
}
