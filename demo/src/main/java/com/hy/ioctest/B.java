package com.hy.ioctest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author: yhong
 * Date: 2024/5/10
 */
@Component("b")
public class B {
    @Value("我是BBB")
    private String name;
}
