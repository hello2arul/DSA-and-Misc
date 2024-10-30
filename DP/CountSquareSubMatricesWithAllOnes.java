package DP;

/*
 * https://leetcode.com/problems/count-square-submatrices-with-all-ones/
 Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.

 */
public class CountSquareSubMatricesWithAllOnes {
    public int countSquares(int[][] matrix) {
        int max = 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j];
                } else if(matrix[i][j] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i][j-1],dp[i-1][j]),
                                        dp[i-1][j-1]) + 1;
                }
                max += dp[i][j];
            }
   }
   return max;
}
}
