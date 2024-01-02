import com.hy.Application;
import com.hy.entity.Student;
import com.hy.handler.CheckScoreHandler;
import com.hy.handler.StudentFlagToDBHandler;
import com.hy.producer.InParkingDataEventPublisher;
import com.hy.producer.StudentPublisher;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: 测试这样一种场景：
 * 利用disruptor拉取kafka里的数据落库到mysql中；
 * 其中启动两个消费者， C1根据score对student对象进行打标， >80分为合格；
 * C2负责将打完分的student对象入库；
 * Author: yhong
 * Date: 2024/1/2
 */

@SpringBootTest(classes = Application.class)
public class StudentTest {
    @Autowired
    private CheckScoreHandler checkScoreHandler;

    @Autowired
    private StudentFlagToDBHandler toDBHandler;

    @Test
    public void test() {
        int bufferSize = 1024 * 1024;
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Disruptor<Student> disruptor = new Disruptor<>(
                Student::new,
                bufferSize,
                executorService,
                ProducerType.MULTI,
                new BlockingWaitStrategy());

        EventHandlerGroup<Student> studentEventHandlerGroup = disruptor.handleEventsWith(checkScoreHandler);
        studentEventHandlerGroup.then(toDBHandler);
        disruptor.start();

        executorService.submit(new StudentPublisher(disruptor));

        disruptor.shutdown();
        executorService.shutdown();

    }
}
