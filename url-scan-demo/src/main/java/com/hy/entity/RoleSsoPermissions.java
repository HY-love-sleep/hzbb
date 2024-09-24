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
@TableName("mysql.role_sso_permissions")
public class RoleSsoPermissions implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 子系统ID
     */
    private Integer thirdSystemId;

    /**
     * 子系统名称
     */
    private String thirdSystemName;

    /**
     * 子系统角色ID
     */
    private Integer thirdSystemRoleId;

    /**
     * 子系统角色名称
     */
    private String thirdSystemRoleName;

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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getThirdSystemId() {
        return thirdSystemId;
    }

    public void setThirdSystemId(Integer thirdSystemId) {
        this.thirdSystemId = thirdSystemId;
    }

    public String getThirdSystemName() {
        return thirdSystemName;
    }

    public void setThirdSystemName(String thirdSystemName) {
        this.thirdSystemName = thirdSystemName;
    }

    public Integer getThirdSystemRoleId() {
        return thirdSystemRoleId;
    }

    public void setThirdSystemRoleId(Integer thirdSystemRoleId) {
        this.thirdSystemRoleId = thirdSystemRoleId;
    }

    public String getThirdSystemRoleName() {
        return thirdSystemRoleName;
    }

    public void setThirdSystemRoleName(String thirdSystemRoleName) {
        this.thirdSystemRoleName = thirdSystemRoleName;
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
        return "RoleSsoPermissions{" +
        ", id = " + id +
        ", roleId = " + roleId +
        ", roleName = " + roleName +
        ", thirdSystemId = " + thirdSystemId +
        ", thirdSystemName = " + thirdSystemName +
        ", thirdSystemRoleId = " + thirdSystemRoleId +
        ", thirdSystemRoleName = " + thirdSystemRoleName +
        ", createBy = " + createBy +
        ", updateBy = " + updateBy +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        "}";
    }
}
