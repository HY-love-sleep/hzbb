package com.hy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hy
 * @since 2024-01-02
 */
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer score;
    private Byte flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Student{" +
        ", id = " + id +
        ", name = " + name +
        ", score = " + score +
        ", flag = " + flag +
        "}";
    }
}
