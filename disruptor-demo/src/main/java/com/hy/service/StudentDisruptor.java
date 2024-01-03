package com.hy.service;

import com.hy.entity.Student;
import com.hy.factory.MyThreadFactory;
import com.hy.factory.StudentFactory;
import com.hy.handler.CheckScoreHandler;
import com.hy.handler.StudentFlagToDBHandler;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Description: 全局唯一的disruptor
 * Author: yhong
 * Date: 2024/1/3
 */
@Component
public class StudentDisruptor {
    private Disruptor<Student> disruptor;

    @Autowired
    private CheckScoreHandler checkScoreHandler;

    @Autowired
    private StudentFlagToDBHandler toDBHandler;

    public StudentDisruptor() {
        this.disruptor = null;
    }

    @PostConstruct
    public void init() {
        this.disruptor = new Disruptor<Student>(
                new StudentFactory(),
                1024 * 1024,
                new MyThreadFactory("consumer"),
                ProducerType.SINGLE,
                new YieldingWaitStrategy()
        );
        EventHandlerGroup<Student> studentEventHandlerGroup = this.disruptor.handleEventsWith(checkScoreHandler);
        studentEventHandlerGroup.then(toDBHandler);
        this.disruptor.start();
        System.out.println("------------------------disruptor初始化完成，启动成功---------------------------");
    }

    public Disruptor<Student> getDisruptor() {
        return this.disruptor;
    }
}
