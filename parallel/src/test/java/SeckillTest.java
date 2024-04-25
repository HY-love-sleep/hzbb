import com.hy.Application;
import com.hy.entity.Product;
import com.hy.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Description:
 *
 * @Author: yhong
 * Date: 2024/4/25
 */
@SpringBootTest(classes = Application.class)
public class SeckillTest {
    @Autowired
    private ProductService productService;
    @Test
    public void initTest() {
        Product product = new Product(1L, "cz", 100, 200);
        productService.initProductStock(product);
    }

    @Test
    public void seckillTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        List<Future<Boolean>> results = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Callable<Boolean> seckillTask = () -> productService.decrementSeckillStock(1L);
            Future<Boolean> result = executorService.submit(seckillTask);
            results.add(result);
        }
        for (Future<Boolean> result : results) {
            System.out.println("Seckill result: " + result.get());
        }

        // 关闭线程池
        executorService.shutdown();

    }

}

