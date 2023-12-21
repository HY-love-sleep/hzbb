package com.hy;

import com.hy.dto.SelectUsersByphoneResultDto;
import com.hy.entity.Person;
import com.hy.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Description: 模糊查询测试类
 * Author: yhong
 * Date: 2023/12/21
 */
@SpringBootTest(classes = DemoApplication.class)
public class FuzzyQueryTest {
    @Autowired
    private PersonService personService;
    @Test
    public void registTest() {
        Person p = new Person().setUsername("hzbb1221").setPhone("15902744580");
        personService.regist(p);
    }

    @Test
    public void fuzzyQueryTest() {
        List<SelectUsersByphoneResultDto> personList = personService.getPersonList("4580");
        personList.forEach(System.out::println);
    }


}
