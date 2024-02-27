package com.hy.service;

import com.hy.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hongy
 * @since 2024-02-27
 */
public interface PostService extends IService<Post> {
    Post findByTitle(String title);
}
