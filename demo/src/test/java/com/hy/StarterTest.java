package com.hy;

import com.hy.test.SayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author: yhong
 * Date: 2024/7/9
 */
@SpringBootTest(classes = DemoApplication.class)
@Slf4j
public class StarterTest {
    @Resource
    private SayService service;

    @Test
    public void test() {
        service.say("Wangy");
    }
}
