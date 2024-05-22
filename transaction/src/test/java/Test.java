import com.hy.Application;
import com.hy.entity.Order;
import com.hy.service.OrderService;
import com.hy.service.ProductService;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Description:
 *
 * @author: yhong
 * Date: 2024/5/22
 */
@SpringBootTest(classes = Application.class)
public class Test {
    final int threadCount = 100;
    final CountDownLatch startLatch = new CountDownLatch(1);
    final CountDownLatch doneLatch = new CountDownLatch(threadCount);
    @Resource
    private ProductService productService;
    @Resource
    private OrderService orderService;
    @org.junit.jupiter.api.Test
    public void test() {
        final int threadCount = 100;
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch doneLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    // 等待所有线程就绪
                    startLatch.await();
                    // 执行目标方法
                    productService.sellProducts(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    // 线程执行完毕
                    doneLatch.countDown();
                }
            }).start();
        }

        try {
            // 所有线程已就绪，启动所有线程
            System.out.println("All threads ready, starting concurrent execution...");
            startLatch.countDown();

            // 等待所有线程执行完毕
            doneLatch.await();
            System.out.println("All threads finished execution.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
