package com.hy.multithreading;

import com.hy.solution.Solution1;

import java.util.Arrays;

/**
 * Description: 测试类
 * Author: yhong
 * Date: 2023/12/14
 */
public class MainTest {
    public static void main(String[] args) {
        Solution1 s = new Solution1();
        int[] nums = {2, 7, 11, 15};
        System.out.println(Arrays.toString(s.twoSum(nums, 9)));
    }
}
