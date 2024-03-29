package com.hy.service.impl;

import com.hy.config.ExecutorConfig;
import com.hy.config.SqlContext;
import com.hy.entity.Employee;
import com.hy.exception.BizException;
import com.hy.mapper.EmployeeMapper;
import com.hy.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.hy.utils.CommonUtils.averageAssign;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hy
 * @since 2024-03-29
 */
@Service
@Slf4j
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Resource
    private SqlContext sqlContext;
    // 使用@Transactional注解， 在多线程情况下事务回滚失效
    @Transactional
    @Override
    public void saveThread(List<Employee> employeeList) {
        try {
            // 先做删除操作， 如果子线程出现异常， 该操作不会回滚
            this.getBaseMapper().delete(null);
            ExecutorService threadPool = ExecutorConfig.getThreadPool();
            List<List<Employee>> lists = averageAssign(employeeList, 5);
            // 执行的线程
            Thread[] threadArray = new Thread[lists.size()];
            // 监控子线程执行完毕再执行主线程， 不然会导致主线程关闭， 子线程也会随之关闭；
            CountDownLatch latch = new CountDownLatch(lists.size());
            AtomicBoolean atomicBoolean = new AtomicBoolean(true);
            for (int i = 0; i < lists.size(); i++) {
                if (i == lists.size() - 1) {
                    atomicBoolean.set(false);
                }
                List<Employee> employees = lists.get(i);
                threadArray[i] = new Thread(() -> {
                    try {
                        if (!atomicBoolean.get()) {
                            // 最后一个线程抛出异常
                            throw new BizException("101", "最后一个线程抛出异常");
                        }
                        this.saveBatch(employees);
                    } finally {
                        latch.countDown();
                    }
                });
            }
            for (int i = 0; i < lists.size(); i++) {
                threadPool.execute(threadArray[i]);
            }
            latch.await();
            log.info("添加完毕");
        } catch (InterruptedException e) {
            throw new BizException("102", "主线程抛出异常");
        }
    }

    // 手动提交事务， 事务回滚不失效
    @Override
    public void saveThread2(List<Employee> employeeList) throws SQLException {
        SqlSession sqlSession = sqlContext.getSqlSession();
        Connection connection = sqlSession.getConnection();
        try {
            connection.setAutoCommit(false);
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            // 先删除
            mapper.delete(null);
            ExecutorService threadPool = ExecutorConfig.getThreadPool();
            List<Callable<Boolean>> callableList = new ArrayList<>();
            List<List<Employee>> lists=averageAssign(employeeList, 5);
            AtomicBoolean atomicBoolean = new AtomicBoolean(true);
            for (int i = 0; i < lists.size(); i++) {
                if (i == lists.size() - 1) {
                    atomicBoolean.set(false);
                }
                List<Employee> employees = lists.get(i);
                Callable<Boolean> callable = () -> {
                    if (!atomicBoolean.get()) {
                        throw new BizException("101", "最后一个线程抛出异常");
                    }
                    return mapper.saveBatch(employees);
                };
                callableList.add(callable);
            }
            // 执行子线程
            List<Future<Boolean>> futures = threadPool.invokeAll(callableList);
            for(Future<Boolean> future : futures) {
                if (!future.get()) {
                    // 只要有一个不成功就全部回滚
                    connection.rollback();
                    return;
                }
            }
            connection.commit();
            log.info("添加成功");
        } catch (SQLException | InterruptedException | ExecutionException e) {
            connection.rollback();
            log.error("回滚， 出现异常：{}", e.getMessage());
            throw new BizException("102", "主线程异常");
        } finally {
            connection.close();
        }
    }
}
