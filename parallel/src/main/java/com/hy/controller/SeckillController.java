package com.hy.controller;

import com.hy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeckillController {

    private final ProductService productService;

    @Autowired
    public SeckillController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/seckill/{productId}")
    public String seckill(@PathVariable Long productId) {
        if (productService.decrementSeckillStock(productId)) {
            return "秒杀成功";
        } else {
            return "秒杀失败，库存不足";
        }
    }
}
