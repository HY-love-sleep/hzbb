package com.hy.service;

import com.hy.dto.SelectUsersByphoneResultDto;
import com.hy.entity.Person;
import com.baomidou.mybatisplus.extension.service.IService;

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
    public Person regist(Person person);

    public List<SelectUsersByphoneResultDto> getPersonList(String phoneVal);
}
