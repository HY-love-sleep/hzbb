import com.hy.Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
    @Test
    public void test() {

    }
}
