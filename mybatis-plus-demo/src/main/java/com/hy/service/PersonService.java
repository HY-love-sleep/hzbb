package com.hy.service;

import com.hy.annotation.EncryptSensitiveFields;
import com.hy.entity.Person;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.query.PersonQuery;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author hy
 * @since 2023-12-21
 */
public interface PersonService extends IService<Person> {
    List<Person> findAll();
    Person getPersonByName(String userName);
    Person getPersonByCondition(PersonQuery query);
}
