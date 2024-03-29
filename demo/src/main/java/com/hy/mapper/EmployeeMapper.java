package com.hy.mapper;

import com.hy.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hy
 * @since 2024-03-29
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    Boolean saveBatch(List<Employee> lists);
}
