package com.hy.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.hy.service.TableaService;
import com.hy.entity.Tablea;
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
@RequestMapping("/tablea")
public class TableaController {


    @Autowired
    private TableaService tableaService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<Tablea>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Tablea> aPage = tableaService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Tablea> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(tableaService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Tablea params) {
        tableaService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        tableaService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Tablea params) {
        tableaService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
