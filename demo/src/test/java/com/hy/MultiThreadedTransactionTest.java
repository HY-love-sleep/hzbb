package com.hy;

import com.hy.entity.Employee;
import com.hy.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Description: 多线程事务测试
 * Author: yhong
 * Date: 2024/3/29
 */
@SpringBootTest(classes = DemoApplication.class)
@Slf4j
public class MultiThreadedTransactionTest {
    @Autowired
    private EmployeeService employeeService;
    @Test
    public void test1() {
        int size = 10;
        ArrayList<Employee> employees = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Employee employeeDO = new Employee();
            employeeDO.setEmployeeName("lol"+i);
            employeeDO.setAge(18);
            employeeDO.setGender((byte) 1);
            employeeDO.setIdNumber(i);
            employeeDO.setCreateTime(Calendar.getInstance().getTime());
            employees.add(employeeDO);
        }
        try {
            employeeService.saveThread(employees);
            log.info("添加成功");
        } catch (Exception e) {
            log.error("Test异常：{}", e.getMessage());
        }
    }

    @Test
    public void test2() {
        int size = 10;
        ArrayList<Employee> employees = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Employee employeeDO = new Employee();
            employeeDO.setEmployeeName("lol"+i);
            employeeDO.setAge(18);
            employeeDO.setGender((byte) 1);
            employeeDO.setIdNumber(i);
            employeeDO.setCreateTime(Calendar.getInstance().getTime());
            employees.add(employeeDO);
        }
        try {
            employeeService.saveThread2(employees);
            log.info("添加成功");
        } catch (Exception e) {
            log.error("Test异常：{}", e.getMessage());
        }
    }
}
