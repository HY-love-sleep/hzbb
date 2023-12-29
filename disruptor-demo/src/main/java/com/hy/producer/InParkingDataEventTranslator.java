package com.hy.producer;

import com.hy.event.InParkingDataEvent;
import com.lmax.disruptor.EventTranslator;

public class InParkingDataEventTranslator implements EventTranslator<InParkingDataEvent> {

	@Override
	public void translateTo(InParkingDataEvent event, long sequence) {
		this.generateTradeTransaction(event);
	}

	private InParkingDataEvent generateTradeTransaction(InParkingDataEvent event) {
		int num = (int) (Math.random() * 8000);
		num = num + 1000;
		event.setCarLicense("京Z" + num);
		System.out.println("Thread Id " + Thread.currentThread().getId() + " 写完一个event");
		return event;
	}
}