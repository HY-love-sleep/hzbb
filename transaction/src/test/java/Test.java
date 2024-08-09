import com.hy.Application;
import com.hy.entity.Order;
import com.hy.entity.User;
import com.hy.service.OrderService;
import com.hy.service.ProductService;
import com.hy.service.UserService;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.UUID;
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
    @Resource
    private UserService userService;
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

    @org.junit.jupiter.api.Test
    public void userTest() {
        CountDownLatch doneLatch = new CountDownLatch(100);
        CountDownLatch startLatch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    startLatch.await();
                    User user = new User("hongy", String.valueOf(UUID.randomUUID()));
                    userService.register(user);
                    System.out.println("插入成功！" + Thread.currentThread().getName() + ":  " + "register user :" + user);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    doneLatch.countDown();
                }
            }).start();
        }

        try {
            System.out.println("所有线程就绪， 开始注册！");
            startLatch.countDown();

            doneLatch.await();
            System.out.println("所有线程执行完毕， 注册结束！");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    public void singleTest() {
        User user = new User("zhangchang", String.valueOf(UUID.randomUUID()));
        userService.register(user);
    }


}
