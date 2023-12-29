package com.hy.handler;

import com.hy.event.InParkingDataEvent;
import com.lmax.disruptor.EventHandler;
import org.springframework.stereotype.Component;

/**
 * Description: 停车信息发送短信
 * Author: yhong
 * Date: 2023/12/29
 */
@Component
public class ParkingDataSmsHandler implements EventHandler<InParkingDataEvent> {

    @Override
    public void onEvent(InParkingDataEvent inParkingDataEvent, long l, boolean b) throws Exception {
        long threadId = Thread.currentThread().getId();
        String carLicense = inParkingDataEvent.getCarLicense();
        System.out.printf("Thread Id %s send %s in plaza sms to user%n",threadId,carLicense);

    }
}
