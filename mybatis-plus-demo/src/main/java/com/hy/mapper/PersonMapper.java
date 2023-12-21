package com.hy.mapper;

import com.hy.entity.Person;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;
import com.hy.dto.SelectUserResultDto;
import com.hy.dto.SelectUserByNickNameResultDto;
import com.hy.dto.SelectUserByNickNameParamDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.hy.dto.SelectUserMoreToIdResultDto;

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

    List<SelectUserResultDto> selectUser(Map<String, Object> params);

    List<SelectUserByNickNameResultDto> selectUserByNickName(SelectUserByNickNameParamDto params);

    Page<SelectUserMoreToIdResultDto> selectUserMoreToId(@Param("id") Long id);
}
