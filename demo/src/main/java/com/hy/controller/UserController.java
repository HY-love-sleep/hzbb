package com.hy.controller;

import com.hy.entity.User;
import com.hy.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 用户控制层
 * Author: yhong
 * Date: 2023/12/6
 */
@RestController
@RequestMapping(value = "/api")
public class UserController {
    @PostMapping(value = "/user")
    public boolean insert(@RequestBody User user) {
        if (StringUtils.isBlank(user.getName())) {
            throw new BizException("-1", "用户名不能为空!");
        }
        return true;
    }

    @PutMapping("/user")
    public boolean update(@RequestBody User user) {
        String str = null;
        str.equals("1122");
        return true;
    }

    @DeleteMapping("/user")
    public boolean delete(@RequestBody User user) {
        Integer.parseInt("abs12");
        return true;
    }

    @GetMapping("/user")
    public List<User> findByUser(User user) {
        List<User> userList = new ArrayList<>();
        User user1 = new User(1, "hzbb", 27);
        userList.add(user1);
        return userList;
    }
}
