package DP;


/*
 * https://leetcode.com/problems/unique-binary-search-trees/description/
 * <=1 -> 1
 * 2 -> 2
 * 3 -> 5
 * 4 -> 14
 * res[n] = res[i] * res[n - i - 1]
 */
public class UniqueBSTWithNKeys {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                dp[i] += (dp[j] * dp[i - j - 1]);
            }
        }
        return dp[n];
    }
}
