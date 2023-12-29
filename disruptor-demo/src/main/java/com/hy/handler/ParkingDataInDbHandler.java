package com.hy.handler;

import com.hy.entity.ParkingInfo;
import com.hy.event.InParkingDataEvent;
import com.hy.service.ParkingInfoService;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Description: 停车信息入库
 * Author: yhong
 * Date: 2023/12/29
 */
@Component
public class ParkingDataInDbHandler implements EventHandler<InParkingDataEvent>, WorkHandler<InParkingDataEvent> {
    @Autowired
    private ParkingInfoService parkingInfoService;

    @Override
    public void onEvent(InParkingDataEvent inParkingDataEvent, long l, boolean b) throws Exception {
        this.onEvent(inParkingDataEvent);
    }

    @Override
    public void onEvent(InParkingDataEvent inParkingDataEvent) throws Exception {
        int threadId = (int) Thread.currentThread().getId();
        String carLicense = inParkingDataEvent.getCarLicense();
        ParkingInfo info = new ParkingInfo(threadId, carLicense);
        if (parkingInfoService.save(info)) {
            System.out.printf("Thread Id %s save %s into db ....%n",threadId,carLicense);
        }
    }
}
