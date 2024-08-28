package com.hy.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.hy.service.UserSystemRolesService;
import com.hy.entity.UserSystemRoles;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2024-08-28
 */
@Controller
@RequestMapping("/user-system-roles")
public class UserSystemRolesController {


    @Autowired
    private UserSystemRolesService userSystemRolesService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<UserSystemRoles>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<UserSystemRoles> aPage = userSystemRolesService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserSystemRoles> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(userSystemRolesService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody UserSystemRoles params) {
        userSystemRolesService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        userSystemRolesService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody UserSystemRoles params) {
        userSystemRolesService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
