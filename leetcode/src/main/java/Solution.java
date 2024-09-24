import java.util.HashMap;
import java.util.Map;

class Solution {
    public static int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int res = Integer.MIN_VALUE;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            res = Math.max(res, i - left + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "tmmzuxt";
        System.out.println(lengthOfLongestSubstring(s));
    }
}