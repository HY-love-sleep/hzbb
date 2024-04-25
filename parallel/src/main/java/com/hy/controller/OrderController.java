package com.hy.controller;

import com.hy.service.OrdersService;
import com.hy.service.StartTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @Author: yhong
 * Date: 2024/4/25
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    private final OrdersService ordersService;
    private final StartTask startTask;

    public OrderController(OrdersService ordersService, StartTask startTask) {
        this.ordersService = ordersService;
        this.startTask = startTask;
    }

    @GetMapping("/start")
    public void startProcessTask() {
        startTask.init();
    }

    @GetMapping("/stop")
    public void stopProcessTask() {
        startTask.shutdownLoopTask();
    }

}
