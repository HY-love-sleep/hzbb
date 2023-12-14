package com.hy.controller;

import com.hy.dynamic_datasource.DataSourceContextHolder;
import com.hy.entity.TestUser;
import com.hy.mapper.TestUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 测试动态数据源
 * Author: yhong
 * Date: 2023/12/12
 */
@RestController
@Slf4j
public class DataSourceController {
    @Autowired
    private TestUserMapper userMapper;
    @GetMapping("/getData")
    public String getMasterData(@RequestParam(name = "datasourceName") String dataSourceName) {
        DataSourceContextHolder.setDatasource(dataSourceName);
        log.info(DataSourceContextHolder.getDataSource());
        TestUser testUser = userMapper.findByUserName(dataSourceName);
//        TestUser select = userMapper.select();
        DataSourceContextHolder.removeDataSource();
        return testUser.getUserName();
    }
}
