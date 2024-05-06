import com.hy.Application;
import com.hy.entity.Person;
import com.hy.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Description:
 *
 * @Author: yhong
 * Date: 2024/5/6
 */
@SpringBootTest(classes = Application.class)
public class JasyptTest {
    @Autowired
    private PersonService personService;
    @Test
    public void test() {
        List<Person> list = personService.list(null);
        list.stream()
                .map(Person::getUsername)
                .forEach(System.out::println);
    }
}
