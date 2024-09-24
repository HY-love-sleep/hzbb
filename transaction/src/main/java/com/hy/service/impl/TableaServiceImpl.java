package com.hy.service.impl;

import com.hy.entity.Tablea;
import com.hy.entity.Tableb;
import com.hy.mapper.TableaMapper;
import com.hy.service.TableaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.service.TablebService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor
@Slf4j
public class TableaServiceImpl extends ServiceImpl<TableaMapper, Tablea> implements TableaService {
    private final TablebService tablebService;

    @Transactional
    @Override
    public void methodA(Tablea tablea) {
        System.out.println("Method A");
        this.save(tablea);
        try {
            tablebService.methodB(convertA2B(tablea));
        } catch (RuntimeException e) {
            log.error("Method B error", e);
        }

    }

    private Tableb convertA2B(Tablea tablea) {
        Tableb tableb = new Tableb();
        tableb.setName(tablea.getName());
        return tableb;
    }
}
