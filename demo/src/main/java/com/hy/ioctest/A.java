package com.hy.ioctest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author: yhong
 * Date: 2024/5/10
 */
@Component("a")
public class A {
    @Value("我是AAA")
    private String name;

    @Autowired
    private B b;
}
