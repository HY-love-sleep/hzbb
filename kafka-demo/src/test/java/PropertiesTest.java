import com.hy.Application;
import com.hy.properties.KafkaConsumerProperties;
import com.hy.properties.kafkaProducerProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description:
 * Author: yhong
 * Date: 2024/1/26
 */
@SpringBootTest(classes = Application.class)
@EnableConfigurationProperties(KafkaConsumerProperties.class)
public class PropertiesTest {
    @Autowired
    private KafkaConsumerProperties kafkaConsumerProperties;
    @Autowired
    private kafkaProducerProperties kafkaProducerProperties;

    @Test
    public void test() {
        System.out.println(kafkaConsumerProperties);
        System.out.println(kafkaProducerProperties);
    }
}
