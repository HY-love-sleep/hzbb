package com.hy.service.impl;

import com.hy.entity.Post;
import com.hy.mapper.PostMapper;
import com.hy.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hongy
 * @since 2024-02-27
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

}
