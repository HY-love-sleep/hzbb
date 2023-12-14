package com.hy.mapper;

import com.hy.entity.TestUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TestUserMapper {

    @Select("SELECT * FROM test_user WHERE user_name = #{userName}")
    TestUser findByUserName(@Param("userName") String userName);
    // todo : 无法进行数据库查询， 待替换pom文件
    @Select("SELECT * FROM test_user limit 1")
    TestUser select();
}
