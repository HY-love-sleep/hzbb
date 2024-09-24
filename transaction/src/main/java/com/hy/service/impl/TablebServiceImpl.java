package com.hy.service.impl;

import com.hy.entity.Tableb;
import com.hy.mapper.TablebMapper;
import com.hy.service.TablebService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yhong
 * @since 2024-08-19
 */
@Service
public class TablebServiceImpl extends ServiceImpl<TablebMapper, Tableb> implements TablebService {

    @Transactional
    @Override
    public void methodB(Tableb tableb) {
        System.out.println("Method B");
        this.save(tableb);
        throw new RuntimeException("exception B!");
    }
}
