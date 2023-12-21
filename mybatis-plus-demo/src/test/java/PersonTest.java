import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.Application;
import com.hy.dto.SelectUserByNickNameParamDto;
import com.hy.dto.SelectUserByNickNameResultDto;
import com.hy.dto.SelectUserResultDto;
import com.hy.entity.Person;
import com.hy.mapper.PersonMapper;
import com.hy.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * Description:
 * Author: yhong
 * Date: 2023/12/20
 */
@SpringBootTest(classes = Application.class)
public class PersonTest {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonMapper personMapper;

    @Test
    public void test() {
        Person person = personService.getById(1L);
        System.out.println(person);
    }

    @Test
    public void selectByNameAndPhoneTest() {
        Map<String, Object> query = new HashMap<>();
        query.put("userName", "yhong");
        query.put("phone", "13260474020");
        List<SelectUserResultDto> selectUserResultDtos = personMapper.selectUser(query);
        System.out.println(selectUserResultDtos);
    }

    @Test
    public void selectByNameAndNickNameTest() {
        SelectUserByNickNameParamDto queryDto = new SelectUserByNickNameParamDto().setUserName("yhong").setNickName("hzbb");
        List<SelectUserByNickNameResultDto> selectUserByNickNameResultDtos = personMapper.selectUserByNickName(queryDto);
        System.out.println(selectUserByNickNameResultDtos);
    }

    @Test
    public void insertRandomUserTest() {
        for (int i = 0; i < 1000; i++) {
            // 生成随机的测试数据
            Person person = new Person();
            person.setUsername(generateRandomString(8)); // 生成随机用户名
            person.setPassword(generateRandomString(10)); // 生成随机密码
            person.setSalt(generateRandomString(6)); // 生成随机盐值
            person.setPhone(generateRandomPhoneNumber()); // 生成随机电话号码
            person.setAvatar("default.jpg"); // 默认头像
            person.setNickname("User" + i); // 根据循环生成昵称
            person.setName("User Name" + i); // 根据循环生成姓名
            person.setEmail("user" + i + "@example.com"); // 根据循环生成邮箱
            person.setDeptId(1L); // 部门ID，根据实际情况设置
            person.setCreateBy("Admin"); // 创建人，根据实际情况设置
            person.setUpdateBy("Admin"); // 修改人，根据实际情况设置
            person.setCreateTime(new Date()); // 创建时间，根据实际情况设置
            person.setUpdateTime(new Date()); // 修改时间，根据实际情况设置
            person.setLockFlag("0"); // 锁定标记，默认未锁定
            person.setDelFlag("0"); // 删除标记，默认未删除
            person.setWxOpenid(generateRandomString(20)); // 生成随机微信OpenID
            person.setMiniOpenid(generateRandomString(20)); // 生成随机小程序OpenID
            person.setQqOpenid(generateRandomString(20)); // 生成随机QQ OpenID
            person.setGiteeLogin(generateRandomString(20)); // 生成随机码云标识
            person.setOscId(generateRandomString(20)); // 生成随机开源中国标识
            person.setTenantId(1L); // 租户ID，根据实际情况设置

            // 插入数据到数据库
            personMapper.insert(person);
        }
    }

    @Test
    public void pageTest() {
        LambdaQueryWrapper<Person> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(Person::getUserId, 500);
        Page<Person> page = personService.page(new Page<>(1, 10), queryWrapper);
        System.out.println(page.getRecords());
    }

    private String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        return randomString.toString();
    }

    private String generateRandomPhoneNumber() {
        Random random = new Random();
        // 生成随机的11位手机号码
        StringBuilder phoneNumber = new StringBuilder("1");
        for (int i = 0; i < 10; i++) {
            phoneNumber.append(random.nextInt(10));
        }
        return phoneNumber.toString();
    }


}
