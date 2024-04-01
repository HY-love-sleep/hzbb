package com.hy.solution;

/**
 * Description: 01背包
 * F(Cap, N) = max(F(Cap, N-1), F(Cap - Wn, N - 1) + Vn)
 * Author: yhong
 * Date: 2024/4/1
 */
public class BackBag {
    static int maxValue(int capacity, int[] weights, int[] values) {
        int n = weights.length;
        int[][] dp = new int[n][capacity + 1];
        for (int i = weights[0]; i <= capacity; i++) {
            dp[0][i] = values[0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (j < weights[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i]);
                }
            }
        }
        return dp[n - 1][capacity];
    }

    public static void main(String[] args) {
        int[] values = {3, 4, 6};
        int[] weights = {1, 3, 4};
        System.out.println(maxValue(4, weights, values));
    }
}
