package com.hy.Utils;

import com.hy.entity.Orders;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class OrdersGenerator {

    private static final Random RANDOM = new Random();

    // 预定义订单状态列表
    private static final String[] ORDER_STATUSES = {"PENDING", "PROCESSING", "SHIPPED", "DELIVERED", "CANCELLED"};

    public static Orders generateRandomOrder() {
        // 随机生成订单属性
        Integer orderId = null; // 假设订单ID范围在[0, 100_000)
        Integer customerId = RANDOM.nextInt(5_000); // 假设客户ID范围在[0, 5_000)
        Integer productId = RANDOM.nextInt(1_000); // 假设产品ID范围在[0, 1_000)
        Integer quantity = 1 + RANDOM.nextInt(10); // 假设数量范围在[1, 10]
        BigDecimal unitPrice = BigDecimal.valueOf(RANDOM.nextDouble() * 1_000).setScale(2, RoundingMode.HALF_UP); // 价格范围在[0, 1_000)，保留两位小数
        BigDecimal totalPrice = null; // 计算总价

        LocalDate startDate = LocalDate.of(2026, 1, 1);
        LocalDate endDate = LocalDate.of(2027, 12, 31);
        LocalDate orderDate = startDate.plusDays(RANDOM.nextInt((int) ChronoUnit.DAYS.between(startDate, endDate)));

        String orderStatus = ORDER_STATUSES[RANDOM.nextInt(ORDER_STATUSES.length)];

        // 构建并返回Orders对象
        return new Orders(
                orderId,
                customerId,
                productId,
                quantity,
                unitPrice,
                totalPrice,
                Date.valueOf(orderDate),
                orderStatus
        );
    }
}