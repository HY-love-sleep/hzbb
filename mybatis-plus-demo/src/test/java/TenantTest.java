import com.hy.Application;
import com.hy.annotation.IgnoreTenant;
import com.hy.config.ApiContext;
import com.hy.entity.Person;
import com.hy.entity.Tenant;
import com.hy.mapper.TenantMapper;
import com.hy.service.PersonService;
import com.hy.service.TenantService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Description: 测试多租户
 * Author: yhong
 * Date: 2024/4/18
 */
@SpringBootTest(classes = Application.class)
public class TenantTest {
    @Autowired
    private TenantService tenantService;
    @Autowired
    private TenantMapper tenantMapper;
    @Autowired
    private ApiContext apiContext;
    @Autowired
    private PersonService personService;
    @BeforeEach
    public void setTenantId() {
        apiContext.setCurrentTenantId(0L);
    }
    @Test
    public void MapperTest() {
        List<Tenant> tenants = tenantMapper.selectList(null);
        tenants.forEach(System.out::println);
    }

    @Test
    public void ServiceTest() {
        List<Tenant> list = tenantService.list(null);
        list.forEach(System.out::println);
    }

    @Test
    public void personServiceTest() {
        List<Person> list = personService.list(null);
        list.stream()
                .map(Person::getUsername)
                .forEach(System.out::println);
    }

    @Test
    public void tenantTest() {
        List<Person> all = personService.findAll();
        System.out.println(all.size());
        // all.forEach(System.out::println);
    }

}
