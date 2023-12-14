import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hy.Application;
import com.hy.entity.User;
import com.hy.mapper.UserMapper;
import com.hy.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * Description: 用户表测试类
 * Author: yhong
 * Date: 2023/12/14
 */
@SpringBootTest(classes = Application.class)
public class UserTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Test
    public void add_test() {
        User new_user = User.builder()
                .id(1L)
                .userNo("001")
                .nickname("hzbb")
                .email("1122.com")
                .phone("15902744580")
                .birthday(new Date())
                .build();
        userMapper.insert(new_user);
    }

    @Test
    public void select_test() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }
}
