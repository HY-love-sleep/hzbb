package com.hy.handler;

import com.hy.entity.Order;
import com.lmax.disruptor.ExceptionHandler;

/**
 * Description: 异常处理类
 * Author: yhong
 * Date: 2024/1/2
 */
public class EventExceptionHandler implements ExceptionHandler<Order> {

    @Override
    public void handleEventException(Throwable throwable, long l, Order order) {

    }

    @Override
    public void handleOnStartException(Throwable throwable) {

    }

    @Override
    public void handleOnShutdownException(Throwable throwable) {

    }
}
