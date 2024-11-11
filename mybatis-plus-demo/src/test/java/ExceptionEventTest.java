import com.hy.Application;
import com.hy.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description:
 *
 * @author: yhong
 * Date: 2024/11/7
 */
@SpringBootTest(classes = Application.class)
public class ExceptionEventTest {
    @Autowired
    private PersonService personService;
    @Test
    public void test() throws Exception {
        throw new Exception("mock exception");
        // personService.mockException();
    }
}
