package com.hy.handler;

import com.hy.event.InParkingDataEvent;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import org.springframework.stereotype.Component;

/**
 * Description: 停车信息发送kafka
 * Author: yhong
 * Date: 2023/12/29
 */
@Component
public class ParkingDataToKafkaHandler implements EventHandler<InParkingDataEvent>{

    @Override
    public void onEvent(InParkingDataEvent inParkingDataEvent, long l, boolean b) throws Exception {
        long threadId = Thread.currentThread().getId();
        String carLicense = inParkingDataEvent.getCarLicense();
        System.out.printf("Thread Id %s send %s in plaza message to kafka...%n",threadId,carLicense);
    }

}
