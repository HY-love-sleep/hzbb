package com.hy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2024-08-28
 */
@TableName("mysql.user_system_roles")
public class UserSystemRoles implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 系统ID
     */
    private Long thirdSystemId;

    /**
     * 系统名称
     */
    private String thirdSystemName;

    /**
     * 角色ID
     */
    private Long thirdRoleId;

    /**
     * 角色名称
     */
    private String thirdRoleName;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getThirdSystemId() {
        return thirdSystemId;
    }

    public void setThirdSystemId(Long thirdSystemId) {
        this.thirdSystemId = thirdSystemId;
    }

    public String getThirdSystemName() {
        return thirdSystemName;
    }

    public void setThirdSystemName(String thirdSystemName) {
        this.thirdSystemName = thirdSystemName;
    }

    public Long getThirdRoleId() {
        return thirdRoleId;
    }

    public void setThirdRoleId(Long thirdRoleId) {
        this.thirdRoleId = thirdRoleId;
    }

    public String getThirdRoleName() {
        return thirdRoleName;
    }

    public void setThirdRoleName(String thirdRoleName) {
        this.thirdRoleName = thirdRoleName;
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

    @Override
    public String toString() {
        return "UserSystemRoles{" +
        ", id = " + id +
        ", userId = " + userId +
        ", userName = " + userName +
        ", thirdSystemId = " + thirdSystemId +
        ", thirdSystemName = " + thirdSystemName +
        ", thirdRoleId = " + thirdRoleId +
        ", thirdRoleName = " + thirdRoleName +
        ", createBy = " + createBy +
        ", updateBy = " + updateBy +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        "}";
    }
}
