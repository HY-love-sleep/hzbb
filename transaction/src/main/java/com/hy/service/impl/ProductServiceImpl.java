package com.hy.service.impl;

import com.hy.entity.Order;
import com.hy.entity.Product;
import com.hy.mapper.ProductMapper;
import com.hy.service.OrderService;
import com.hy.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author hongy
 * @since 2024-05-22
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    private final ReentrantLock lock = new ReentrantLock();
    @Resource
    private OrderService orderService;
    @Transactional
    @Override
    public void sellProducts(Integer id) {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "进入方法");
            Product product = this.getById(id);
            Integer quantity = product.getQuantity();
            System.out.println(Thread.currentThread().getName() + "当前库存" + quantity);
            if (quantity > 0) {
                product.setQuantity(product.getQuantity() - 1);
                this.updateById(product);
                Order order = new Order();
                order.setBuyerName(Thread.currentThread().getName());
                order.setProductName(product.getName());
                orderService.save(order);
                System.out.println(Thread.currentThread().getName() + "减库存完毕， 创建订单");
            } else {
                System.out.println(Thread.currentThread().getName() + "没有库存啦");
            }
            System.out.println(Thread.currentThread().getName() + "释放锁");
        } finally {
            lock.unlock();
        }
    }
}
