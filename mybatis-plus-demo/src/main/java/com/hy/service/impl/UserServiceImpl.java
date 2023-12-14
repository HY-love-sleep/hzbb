package com.hy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.entity.User;
import com.hy.mapper.UserMapper;
import com.hy.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Description: 用户service实现类
 * Author: yhong
 * Date: 2023/12/14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
