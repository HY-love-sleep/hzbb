package com.hy.service.impl;

import com.hy.entity.Person;
import com.hy.mapper.PersonMapper;
import com.hy.service.PersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hongy
 * @since 2024-05-06
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

}
