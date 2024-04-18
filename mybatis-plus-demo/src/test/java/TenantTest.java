import com.hy.Application;
import com.hy.config.ApiContext;
import com.hy.entity.Tenant;
import com.hy.mapper.TenantMapper;
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
    @BeforeEach
    public void setTenantId() {
        apiContext.setCurrentTenantId(1L);
    }
    @Test
    public void test() {
        List<Tenant> tenants = tenantMapper.selectList(null);
        tenants.forEach(System.out::println);
    }


}
