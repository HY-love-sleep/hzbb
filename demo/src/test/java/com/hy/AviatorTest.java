package com.hy;

import com.hy.aviator_test.TxtToStringUtil;
import com.hy.aviator_test.VersionUtil;
import com.hy.service.AviatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * Description:
 * Author: yhong
 * Date: 2024/1/8
 */
@SpringBootTest(classes = DemoApplication.class)
public class AviatorTest {
    @Autowired
    private AviatorService service;
    @Test
    public void test() {
        String rule = TxtToStringUtil.txtToString("C:\\My_Work\\IdeaProjects\\MyGitProject\\hzbb\\demo\\src\\main\\java\\com\\hy\\aviator_test\\rule.txt");
        boolean isMatch = service.filter("Android", "1.38.1", rule);
        System.out.println(isMatch);
    }
}
