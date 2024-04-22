package com.hy.service.impl;

import com.hy.annotation.IgnoreTenant;
import com.hy.entity.Person;
import com.hy.mapper.PersonMapper;
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
    @IgnoreTenant(value = false)
    public List<Person> findAll() {
        return personMapper.selectList(null);
    }
}
