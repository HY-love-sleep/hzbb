package com.hy.kelihua;

import java.util.function.Function;

/**
 * Description: 柯里化
 *
 * @author: yhong
 * Date: 2024/12/25
 */
public class Test {
    // 未柯理化
    public static int f(int x, int y) {
        return x + y;
    }

    // 柯理化
    public static Function<Integer, Function<Integer, Integer>> curriedF = x -> y -> x + y;

    public static void main(String[] args) {
        System.out.println(f(2, 3));
        Function<Integer, Function<Integer, Integer>> curriedF = x -> y -> x + y;
        Function<Integer, Integer> apply = curriedF.apply(2);
        System.out.println(apply.apply(3));
    }
}
