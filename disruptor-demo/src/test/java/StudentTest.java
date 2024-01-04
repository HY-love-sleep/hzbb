import com.hy.Application;
import com.hy.service.KafkaPollConsumerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description: 测试kafka拉模式下的disruptor
 * Author: yhong
 * Date: 2024/1/4
 */
@SpringBootTest(classes = Application.class)
public class StudentTest {

    @Autowired
    private KafkaPollConsumerService kafkaPollConsumerService;
    @Test
    public void test() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                2,
                1000,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(100),
                new ThreadPoolExecutor.AbortPolicy()
        );

        threadPool.submit(kafkaPollConsumerService);
    }
}
