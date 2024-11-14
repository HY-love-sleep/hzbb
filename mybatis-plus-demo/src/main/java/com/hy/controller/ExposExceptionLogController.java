package com.hy.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.hy.service.ExposExceptionLogService;
import com.hy.entity.ExposExceptionLog;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2024-11-14
 */
@Controller
@RequestMapping("/expos-exception-log")
public class ExposExceptionLogController {


    @Autowired
    private ExposExceptionLogService exposExceptionLogService;

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody ExposExceptionLog params) {
        exposExceptionLogService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }


}
