package com.hy.handler;

import com.hy.entity.Student;
import com.lmax.disruptor.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;


/**
 * Description: 判断分数并打标签
 * Author: yhong
 * Date: 2024/1/2
 */
@Component
public class CheckScoreHandler implements EventHandler<Student> {

    @Autowired
    ThreadPoolExecutor threadPoolExecutor;

    private static final byte PASS_FLAG = 1;
    private static final byte FAIL_FLAG = 0;

    @Override
    public void onEvent(Student student, long l, boolean b) throws Exception {
        threadPoolExecutor.submit(() -> {
            if (student.getScore() >= 80) {
                student.setFlag(PASS_FLAG);
            } else {
                student.setFlag(FAIL_FLAG);
            }
        });
    }
}
