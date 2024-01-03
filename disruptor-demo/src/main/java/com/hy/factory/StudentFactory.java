package com.hy.factory;

import com.hy.entity.Student;
import com.lmax.disruptor.EventFactory;

/**
 * Description:
 * Author: yhong
 * Date: 2024/1/3
 */
public class StudentFactory implements EventFactory<Student> {

    @Override
    public Student newInstance() {
        return new Student();
    }
}
