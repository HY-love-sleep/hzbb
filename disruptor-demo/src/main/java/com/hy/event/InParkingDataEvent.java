package com.hy.event;

/**
 * Description: 停车事件
 * Author: yhong
 * Date: 2023/12/29
 */
public class InParkingDataEvent {
    private String carLicense = "";


    public String getCarLicense() {
        return carLicense;
    }

    public void setCarLicense(String carLicense) {
        this.carLicense = carLicense;
    }
}
