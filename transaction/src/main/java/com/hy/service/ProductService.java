package com.hy.service;

import com.hy.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品信息表 服务类
 * </p>
 *
 * @author hongy
 * @since 2024-05-22
 */
public interface ProductService extends IService<Product> {
    void sellProducts(Integer id);
}
