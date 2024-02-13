package DP;

/*
 * https://leetcode.com/problems/edit-distance/description/
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        return minEdits(word1, word2, word1.length(), word2.length());
    }
    public int minEdits(String s1, String s2, int i, int j) {
        if(i == 0)
            return j;
        if(j == 0)
            return i;
        if(s1.charAt(i - 1) == s2.charAt(j - 1))
            return minEdits(s1, s2, i - 1, j - 1);
        return 1 + Math.min(
            Math.min(
                minEdits(s1, s2, i - 1, j),
                minEdits(s1, s2, i, j - 1)
            ),
            minEdits(s1, s2, i - 1, j - 1)
        );
    }

    public int minEditsMemo(String s1, String s2, int i, int j, int[][] memo) {
        if(i == 0)
            return j;
        if(j == 0)
            return i;
        if(memo[i][j] != Integer.MAX_VALUE) {
            return memo[i][j];
        }
        if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
            memo[i][j] =  minEdits(s1, s2, i - 1, j - 1, memo);
            return memo[i][j];
        }
        memo[i][j] = 1 + Math.min(
            Math.min(
                minEdits(s1, s2, i - 1, j, memo),
                minEdits(s1, s2, i, j - 1, memo)
            ),
            minEdits(s1, s2, i - 1, j - 1, memo)
        );
        return memo[i][j];
    }

    public int minDistanceTabulation(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++) {
            for(int j = 0; j <= n; j++) {
                if(i == 0) {
                    dp[i][j] = j;
                } else if(j == 0) {
                    dp[i][j] = i;
                } else if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; 
                } else {
                    dp[i][j] = 1 + Math.min(
                        Math.min(dp[i - 1][j], dp[i][j - 1]),
                        dp[i - 1][j - 1]
                    );
                }
            }
        }

        return dp[m][n];
    }
}
