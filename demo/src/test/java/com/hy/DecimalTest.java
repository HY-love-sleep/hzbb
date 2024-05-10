package com.hy;

import java.math.BigDecimal;

/**
 * Description:
 *
 * @Author: yhong
 * Date: 2024/5/7
 */
public class DecimalTest {
    public static void main(String[] args) {
        BigDecimal bigDecimal1 = BigDecimal.valueOf(2.36);
        BigDecimal bigDecimal2 = BigDecimal.valueOf(3.5);
        BigDecimal resDecimal = bigDecimal1.add(bigDecimal2);
        System.out.println(resDecimal);
    }
}
