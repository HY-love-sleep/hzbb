package hot100;

import com.sun.org.apache.bcel.internal.generic.FSUB;

/**
 * Description: LongestPalindrome
 * Author: yhong
 * Date: 2023/12/19
 */
public class Solution5 {
    private String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        int begin = 0;
        int maxLen = 1;
        for (int L = 2; L <= len; L++) {
            for (int i = 0; i < len; i++) {
                // j - i + 1 = L    j = l + i - 1
                int j = L + i - 1;
                if (j >= len) break;
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (j - i <= 2) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }

            }
        }
        return s.substring(begin, begin + maxLen);
    }

    private String longestPalindrome2(String s) {
        if (s.length() <= 1) {
            return s;
        }
        int begin = 0, maxLen = 1;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expand(s, i, i);
            int len2 = expand(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                begin = i - (maxLen - 1) / 2;
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    private int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        Solution5 s5 = new Solution5();
        String s1 = "babad";
        String s2 = "abba";
        String s3 = "ac";
        System.out.println(s5.longestPalindrome2(s1));
    }
}
