package com.hy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hy.entity.Post;
import com.hy.mapper.PostMapper;
import com.hy.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hongy
 * @since 2024-02-27
 */
@Service
@RequiredArgsConstructor
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
    private final PostMapper postMapper;
    @Override
    public Post findByTitle(String title) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        return postMapper.selectOne(queryWrapper);
    }
}
