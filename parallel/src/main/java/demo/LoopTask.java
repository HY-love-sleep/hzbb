package demo;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 任务入口
 *
 * @Author: yhong
 * Date: 2024/4/24
 */
public class LoopTask {
    private List<ChildTask> childTasks;
    public void initLoopTask() {
        childTasks = new ArrayList();
        childTasks.add(new ChildTask("childTask1"));
        childTasks.add(new ChildTask("childTask2"));
        for (final ChildTask childTask : childTasks) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    childTask.doExecute();
                }
            }).start();
        }
    }
    public void shutdownLoopTask() {
        if (!CollectionUtils.isEmpty(childTasks)) {
            for (ChildTask childTask : childTasks) {
                childTask.terminal();
            }
        }
    }
    public static void main(String args[]) throws Exception{
        LoopTask loopTask = new LoopTask();
        loopTask.initLoopTask();
        Thread.sleep(5000L);
        loopTask.shutdownLoopTask();
    }
}