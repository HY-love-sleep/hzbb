package com.hy.test;

import com.hy.factory.SayFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author: yhong
 * Date: 2024/7/9
 */
@Component
@RequiredArgsConstructor
public class SayService {
    private final SayFactory sayFactory;
    public void say(String name) {
        com.hy.service.SayService sayService = sayFactory.getSayService(name);
        sayService.say();
    }
}
