package com.hy.service;

import com.hy.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yhong
 * @since 2024-08-09
 */
public interface UserService extends IService<User> {
    Boolean register(User user);
}
