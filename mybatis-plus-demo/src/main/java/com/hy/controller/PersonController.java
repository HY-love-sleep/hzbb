package com.hy.controller;

import com.cubigdata.expos.framework.core.exception.BusinessException;
import com.cubigdata.expos.framework.core.response.BaseResult;
import com.cubigdata.expos.framework.mybatis.crypto.service.DataMigrationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.hy.service.PersonService;
import com.hy.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author hy
 * @since 2023-12-21
 */
@Controller
@RequestMapping("/person")
public class PersonController {
    private final DataMigrationService dataMigrationService;
    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService, DataMigrationService dataMigrationService) {
        this.personService = personService;
        this.dataMigrationService = dataMigrationService;
    }

    @PostMapping("/refreshUserInfo")
    public BaseResult refreshUserInfo(
            @RequestParam String jdbcUrl,
            @RequestParam String username,
            @RequestParam String password) {
        try {
            dataMigrationService.refreshUserInfo(jdbcUrl, username, password);
            return BaseResult.success("User information refreshed and encrypted successfully.");
        } catch (Exception e) {
            throw new BusinessException("Failed to refresh user information: " + e.getMessage());
        }
    }

    @GetMapping(value = "/")
    public ResponseEntity<Page<Person>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Person> aPage = personService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Person> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(personService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Person params) {
        personService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        personService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Person params) {
        personService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
