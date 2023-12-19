import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.hy.Application;
import com.hy.entity.User;
import com.hy.entity.dto.UserDTO;
import com.hy.mapper.UserMapper;
import com.hy.query.UserQuery;
import com.hy.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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

    /**
     * 随机插入一百条记录
     */
    @Test
    public void addRandomUsers() {
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            User new_user = User.builder()
                    .id(null)
                    .userNo("User" + random.nextInt(1000))
                    .nickname("User" + i)
                    .email("user" + i + "@example.com")
                    .phone("159" + (10000000 + random.nextInt(90000000)))
                    .birthday(new Date())
                    .build();

            userMapper.insert(new_user);
        }
    }

    /**
     * 修改性别
     */
    @Test
    public void updateGender() {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        // 使用SQL的LIKE操作符和MOD函数来匹配奇数编号的用户
        updateWrapper.likeRight(User::getNickname, "User")
                .and(wrapper -> wrapper.apply("MOD(CAST(SUBSTRING_INDEX(nickname, 'User', -1) AS UNSIGNED), 2) = 1"));

        // 设置更新的字段
        updateWrapper.set(User::getGender, 1);

        // 执行更新
        userMapper.update(null, updateWrapper);
    }

    @Test
    public void getList() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        UserQuery userQuery = UserQuery.builder()
                .id(1L)
                .userNo("001")
                .nickname("hzbb")
                .phone("15902744580")
                .gender(1)
                .build();
        if (userQuery.getGender() != null) {
            queryWrapper.eq(User::getGender, userQuery.getGender()).gt(User::getId, 50L);
        }
        List<UserDTO> userDTOList = userMapper.selectList(queryWrapper).stream()
                .map(this::toUserDTO)
                .limit(10)
                .collect(Collectors.toList());
        System.out.println(userDTOList);
    }

    private UserDTO toUserDTO(User user) {
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

}
