package DP;

/*
 * https://leetcode.com/problems/maximal-square
 
Given an m x n binary matrix filled with 0's and 1's, 
find the largest square containing only 1's and return its area.
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int max = 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j] - '0';
                } else if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]),
                            dp[i - 1][j - 1]) + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;
    }
}
