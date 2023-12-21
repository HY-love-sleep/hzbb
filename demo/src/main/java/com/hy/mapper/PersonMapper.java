package com.hy.mapper;

import com.hy.entity.Person;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

import com.hy.dto.SelectUsersByphoneResultDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author hy
 * @since 2023-12-21
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person> {

    List<SelectUsersByphoneResultDto> selectUsersByphone(@Param("phoneVal") String phoneVal);
}
