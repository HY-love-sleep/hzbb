package com.hy.service;

import com.hy.demo.ChildTask;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @Author: yhong
 * Date: 2024/4/25
 */
@Component
public class StartTask {
    private List<OrderChildTask> childTasks;
    private final OrdersService ordersService;

    public StartTask(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    public void init() {
        childTasks = new ArrayList<>(5);
        childTasks.add(new OrderChildTask("orderChildTask1", ordersService));
        childTasks.add(new OrderChildTask("orderChildTask2", ordersService));
        childTasks.add(new OrderChildTask("orderChildTask3", ordersService));
        childTasks.forEach(childTask -> {
            new Thread(() -> {
                childTask.doExecute();
            }).start();
        });
    }

    public void shutdownLoopTask() {
        if (!CollectionUtils.isEmpty(childTasks)) {
            for (OrderChildTask childTask : childTasks) {
                childTask.terminal();
            }
        }
    }

}
