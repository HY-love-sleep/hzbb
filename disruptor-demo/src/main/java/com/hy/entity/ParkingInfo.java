package com.hy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hy
 * @since 2023-12-29
 */
@TableName("demo.parking_info")
@Accessors
public class ParkingInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 线程id
     */
    private Integer threadId;

    /**
     * 停车信息
     */
    private String carLicense;

    public ParkingInfo(Integer threadId, String carLicense) {
        this.threadId = threadId;
        this.carLicense = carLicense;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getThreadId() {
        return threadId;
    }

    public void setThreadId(Integer threadId) {
        this.threadId = threadId;
    }

    public String getCarLicense() {
        return carLicense;
    }

    public void setCarLicense(String carLicense) {
        this.carLicense = carLicense;
    }

    @Override
    public String toString() {
        return "ParkingInfo{" +
        ", id = " + id +
        ", threadId = " + threadId +
        ", carLicense = " + carLicense +
        "}";
    }
}
