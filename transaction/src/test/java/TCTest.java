import com.hy.Application;
import com.hy.entity.Tablea;
import com.hy.service.TableaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description:
 * Author: yhong
 * Date: 2024/8/19
 */
@SpringBootTest(classes = Application.class)
public class TCTest {
    @Autowired
    private TableaService tableaService;

    @Test
    public void test() {
        Tablea tablea = new Tablea();
        tablea.setName("tablea");
        tableaService.methodA(tablea);
    }
}
