package com.hy.service;

import com.hy.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final StringRedisTemplate redisTemplate;

    @Autowired
    public ProductService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 初始化商品库存到Redis
     */
    public void initProductStock(Product product) {
        String key = "product:" + product.getId();
        redisTemplate.opsForValue().set(key, String.valueOf(product.getSeckillStock()));
    }

    /**
     * 秒杀减库存
     *
     * @return true - 秒杀成功，false - 秒杀失败（库存不足）
     */
    public boolean decrementSeckillStock(Long productId) {
        String key = "product:" + productId;
        String s = redisTemplate.opsForValue().get(key);
        Long stock = Long.valueOf(s);

        if (stock > 0) {
            System.out.println("stock:" + stock);
            Long currentStock = redisTemplate.opsForValue().decrement(key);
            return currentStock >= 0;
        } else {
            return false;
        }
    }
}
