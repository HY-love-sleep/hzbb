package com.hy.template;

import com.hy.Utils.TaskProcessUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * Description: 子任务模板
 *
 * @Author: yhong
 * Date: 2024/4/24
 */
@Slf4j
public abstract class ChildTaskTemplate<T> {
    private final int POOL_SIZE = 3; // 线程池大小
    private final int SPLIT_SIZE = 4; // 数据拆分大小
    private String taskName;
    protected volatile boolean terminal = false; // 接收jvm关闭信号，实现优雅停机

    public ChildTaskTemplate(String taskName) {
        this.taskName = taskName;
    }

    // 流程定义
    public void doExecute() {
        int i = 0;
        while(true) {
            log.info("{} :Cycle-{} - Begin", taskName, i);
            // 获取数据
            List<T> tasks = queryData();
            // 处理数据
            taskExecute(tasks);
            log.info("{} :Cycle-{} - End", taskName, i);
            if (terminal) {
                // 只有应用关闭，才会走到这里，用于实现优雅的下线
                break;
            }
            i++;
        }
        // 回收线程池资源
        TaskProcessUtil.releaseExecutors(taskName);
    }

    // 处理单个任务数据
    private void taskExecute(List<T> sourceDatas) {
        if (CollectionUtils.isEmpty(sourceDatas)) {
            return;
        }
        // 将数据拆成4份
        List<List<T>> splitDatas = Lists.partition(sourceDatas, SPLIT_SIZE);
        final CountDownLatch latch = new CountDownLatch(splitDatas.size());

        // 并发处理拆分的数据，共用一个线程池
        for (final List<T> datas : splitDatas) {
            ExecutorService executorService = TaskProcessUtil.getOrInitExecutors(taskName, POOL_SIZE);
            executorService.submit(() -> doProcessData(datas, latch));
        }
        try {
            latch.await();
        } catch (Exception e) {
            log.error("任务处理报错", e);
        }
    }

    // 优雅停机
    public void terminal() {
        // 关机
        terminal = true;
        log.info("{} shut down", taskName);
    }

    /**
     * 获取数据
     * @return
     */
    public abstract List<T> queryData();

    /**
     * 处理数据
     * @param tasks
     * @param latch
     */
    public abstract void doProcessData(List<T> tasks, CountDownLatch latch);



}
