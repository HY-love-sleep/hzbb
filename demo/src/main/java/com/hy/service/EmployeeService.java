package com.hy.service;

import com.hy.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hy
 * @since 2024-03-29
 */
public interface EmployeeService extends IService<Employee> {
    void saveThread(List<Employee> employeeList);

    void saveThread2(List<Employee> employeeList) throws SQLException;
}
