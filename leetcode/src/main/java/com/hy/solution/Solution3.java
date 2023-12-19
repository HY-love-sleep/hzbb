package com.hy.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: lengthOfLongestSubstring
 * pwwkew 3
 * abba 2
 * Author: yhong
 * Date: 2023/12/15
 */
public class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        int left = 0;
        Map<Character, Integer> dict = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (dict.containsKey(s.charAt(i))) {
                left = Math.max(dict.get(s.charAt(i)) + 1, left);
            }
            res = Math.max(res, i - left + 1);
            dict.put(s.charAt(i), i);
        }
        return res;
    }
}
