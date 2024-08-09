package com.hy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.hy.entity.User;
import com.hy.mapper.UserMapper;
import com.hy.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yhong
 * @since 2024-08-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static final ReentrantLock LOCK = new ReentrantLock();

    @Override
    public Boolean register(User user) {
        try {
            LOCK.lock();
            return getaBoolean(user);
        } finally {
            LOCK.unlock();
        }
    }

    @Transactional
    private Boolean getaBoolean(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", user.getName());
        User oldUser = this.getOne(queryWrapper);
        if (null != oldUser) {
            throw new RuntimeException("用户已存在");
        }
        this.save(user);
        return Boolean.TRUE;
    }
}
