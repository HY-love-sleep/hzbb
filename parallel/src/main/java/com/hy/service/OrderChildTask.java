package com.hy.service;

import com.hy.Utils.OrdersGenerator;
import com.hy.entity.Orders;
import com.hy.template.ChildTaskTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Description: 订单子任务
 *
 * @Author: yhong
 * Date: 2024/4/25
 */
@Slf4j
public class OrderChildTask extends ChildTaskTemplate {
    private final OrdersService ordersService;

    public OrderChildTask(String taskName, OrdersService ordersService) {
        super(taskName);
        this.ordersService = ordersService;
    }

    @Override
    public List queryData() {
        List<Orders> orders = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orders.add(OrdersGenerator.generateRandomOrder());
        }
        return orders;
    }

    @Override
    public void doProcessData(List tasks, CountDownLatch latch) {
        try {
            ordersService.saveBatch(tasks);
        } catch (Exception e) {
            log.error("订单入库异常", e);
        } finally {
            if (null != latch) {
                latch.countDown();
            }
        }
    }

}
