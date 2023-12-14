package com.hy;

import com.hy.dynamic_datasource.DataSourceContextHolder;
import com.hy.entity.TestUser;
import com.hy.mapper.TestUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class DemoApplicationTests {
	@Autowired
	private TestUserMapper userMapper;


	@Test
	void contextLoads() {
		log.info("Before setting data source: {}", DataSourceContextHolder.getDataSource());
		DataSourceContextHolder.setDatasource("slave");
		log.info("After setting data source: {}", DataSourceContextHolder.getDataSource());
		TestUser testUser = userMapper.findByUserName("slave");
		System.out.println(testUser);
	}

}
