package com.hy.solution;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Description: twoSum
 * Author: yhong
 * Date: 2023/12/13
 */
public class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int gap = target - nums[i];
            if (map.containsKey(gap)) {
                return new int[] {i, map.get(gap)};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }
}


