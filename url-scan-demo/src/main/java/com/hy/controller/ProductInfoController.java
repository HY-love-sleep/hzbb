package com.hy.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.hy.service.ProductInfoService;
import com.hy.entity.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 商品信息表 前端控制器
 * </p>
 *
 * @author 
 * @since 2024-08-28
 */
@Controller
@RequestMapping("/product-info")
public class ProductInfoController {


    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<ProductInfo>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<ProductInfo> aPage = productInfoService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductInfo> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(productInfoService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody ProductInfo params) {
        productInfoService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        productInfoService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody ProductInfo params) {
        productInfoService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
