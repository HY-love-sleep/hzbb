package com.hy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description: 用户Mapper
 * Author: yhong
 * Date: 2023/12/14
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
