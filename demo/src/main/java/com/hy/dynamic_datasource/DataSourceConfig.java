package com.hy.dynamic_datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 数据源配置
 * Author: yhong
 * Date: 2023/12/12
 */
@Configuration
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    public DataSource slaveDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource createDynamicDataSource() {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        DataSource defaultDataSource = masterDataSource();
        dataSourceMap.put("master", defaultDataSource);
        dataSourceMap.put("slave", slaveDataSource());
        return new DynamicDataSource(defaultDataSource, dataSourceMap);
    }
}
