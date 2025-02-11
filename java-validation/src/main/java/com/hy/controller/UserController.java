package com.hy.controller;

import com.hy.common.CommonResult;
import com.hy.entity.PostDTO;
import com.hy.entity.UserAddDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * Description: 用户接口类
 * Author: yhong
 * Date: 2024/2/27
 */
@RestController
@RequestMapping("/user")
@Validated
@Slf4j
public class UserController {
    @GetMapping("/get")
    public UserAddDTO get(@RequestParam("id") @Min(value = 1L, message = "编号必须大于 0") Integer id) {
        log.info("[get][id: {}]", id);
        UserAddDTO userAddDTO = new UserAddDTO();
        userAddDTO.setUsername("张三");
        userAddDTO.setPassword("123456");
        return userAddDTO;
    }

    @PostMapping("/add")
    public void add(@Valid @RequestBody UserAddDTO addDTO) {
        log.info("[add][addDTO: {}]", addDTO);
    }

    @PostMapping("/post")
    public CommonResult getTitle(@Valid @RequestBody PostDTO postDTO) {
        log.info("postDTO: {}", postDTO);
        return CommonResult.success();
    }
}
