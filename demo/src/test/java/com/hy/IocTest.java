package com.hy;

import com.hy.ioctest.A;
import com.hy.ioctest.MyAutoConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description: 自动装配测试类
 *
 * @author: yhong
 * Date: 2024/5/10
 */
public class IocTest {
    @Test
    public void test() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MyAutoConfig.class);
        A aaa = ac.getBean("a", A.class);
        System.out.println("======================" + aaa);
    }
}
