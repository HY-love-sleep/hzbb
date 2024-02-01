package com.hy.controller;

import com.hy.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 事件发布器
 * Author: yhong
 * Date: 2024/1/31
 */
@Slf4j
@RestController
@RequestMapping("/person")
public class EventPublishController {
    private final ApplicationContext applicationContext;

    @Autowired
    public EventPublishController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @GetMapping("/publishEvent")
    public void publish() {
        Person person = new Person();
        person.setName("hy");
        applicationContext.publishEvent(new PersonEvent(person, "add"));
        applicationContext.publishEvent((new OrderEvent(new Order("1122"), "update")));
    }

    @GetMapping("/TPublishEvent")
    public void publishT() {
        Person person = new Person();
        person.setName("hy");
        applicationContext.publishEvent(new BaseEvent<>(person, "add"));
        applicationContext.publishEvent((new BaseEvent<>(new Order("1122"), "update")));
    }
}
