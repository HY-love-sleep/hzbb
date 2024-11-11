package com.hy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hy.entity.Person;
import com.hy.mapper.PersonMapper;
import com.hy.query.PersonQuery;
import com.hy.service.PersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hy
 * @since 2023-12-21
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {
    @Autowired
    private PersonMapper personMapper;

    @Override
    public List<Person> findAll() {
        return personMapper.selectList(null);
    }

    @Override
    public Person getPersonByName(String userName) {
        LambdaQueryWrapper<Person> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Person::getUsername, userName);
        return personMapper.selectOne(queryWrapper);
    }

    @Override
    public Person getPersonByCondition(PersonQuery query) {
        assert query!= null;
        LambdaQueryWrapper<Person> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(query.getUsername() != null, Person::getUsername, query.getUsername());
        queryWrapper.eq(query.getPhone() != null, Person::getPhone, query.getPhone());
        queryWrapper.eq(query.getEmail() != null, Person::getEmail, query.getEmail());
        return personMapper.selectOne(queryWrapper);
    }

    @Override
    public void mockException() {
        int a = 0;
        // try{
        //     a = 1 / 0;
        // } catch (Exception e) {
        //     log.error("mockException:{}", e);
        // }

        a = 1 / 0;
        System.out.println(a);
    }
}
