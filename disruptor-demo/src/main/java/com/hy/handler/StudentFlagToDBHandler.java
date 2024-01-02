package com.hy.handler;

import com.hy.entity.Student;
import com.hy.service.StudentService;
import com.lmax.disruptor.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description: 打完标签的学生信息入库
 * Author: yhong
 * Date: 2024/1/2
 */
@Component
public class StudentFlagToDBHandler implements EventHandler<Student> {
    @Autowired
    private StudentService service;

    @Override
    public void onEvent(Student student, long l, boolean b) throws Exception {
        try {
            service.save(student);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
