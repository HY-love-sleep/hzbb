package com.hy.aviator_test;

/**
 * Description: 比较版本大小
 * 通过计算每一个区间段内两个版本号的和大小来判断是否一致， 通过和计算能够覆盖到“前导零的问题”
 * Author: yhong
 * Date: 2024/1/8
 */
public class VersionUtil {
    public static Integer compareVersion(String v1, String v2) {
        int m = v1.length();
        int n = v2.length();
        int p = 0, q = 0;
        while (p < m || q < n) {
            int x = 0;
            while (p < m && v1.charAt(p) != '.') {
                x += x * 10 + (v1.charAt(p) - '0');
                p++;
            }

            int y = 0;
            while (q < n && v2. charAt(q) != '.') {
                y += y * 10 + (v2.charAt(q) - '0');
                q++;
            }
            if (x > y) {
                return 1;
            } else if (x < y) {
                return -1;
            }
            p++;
            q++;
        }
        return 0;
    }
}
