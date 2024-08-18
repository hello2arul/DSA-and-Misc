package DP;

/*
 * https://leetcode.com/problems/longest-palindromic-subsequence/description/
 */
public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length()-1];
    }
    
    public int lps(String s, int i, int j) {
        if(i > j)
            return 0;
        if(i == j)
            return 1;
        if(s.charAt(i) == s.charAt(j))
            return 2 + lps(s, i + 1, j - 1);
        return Math.max(lps(s, i + 1, j), lps(s, i, j - 1));
    }
}
