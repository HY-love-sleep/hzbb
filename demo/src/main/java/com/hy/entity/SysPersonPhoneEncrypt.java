package com.hy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 人员的手机号码分词密文映射表
 * </p>
 *
 * @author hy
 * @since 2023-12-21
 */
@TableName("demo.sys_person_phone_encrypt")
public class SysPersonPhoneEncrypt implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联人员信息表主键
     */
    private Integer personId;

    /**
     * 手机号码分词密文
     */
    private String phoneKey;

    public SysPersonPhoneEncrypt(Integer personId, String phoneKey) {
        this.personId = personId;
        this.phoneKey = phoneKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPhoneKey() {
        return phoneKey;
    }

    public void setPhoneKey(String phoneKey) {
        this.phoneKey = phoneKey;
    }

    @Override
    public String toString() {
        return "SysPersonPhoneEncrypt{" +
        ", id = " + id +
        ", personId = " + personId +
        ", phoneKey = " + phoneKey +
        "}";
    }
}
