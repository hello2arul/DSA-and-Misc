package DP;

/*
 * https://leetcode.com/problems/wildcard-matching/description/ 
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

 

Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 */
class WildcardMatching {
    Boolean[][] memo;
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                if (j == n) {
                    dp[i][j] = i == m;
                } else if (i == m) {
                    dp[i][j] = j < p.length() && p.charAt(j) == '*' && dp[i][j + 1];
                } else if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                    dp[i][j] = dp[i + 1][j + 1];
                } else if (p.charAt(j) == '*') {
                    dp[i][j] = dp[i + 1][j] || dp[i][j + 1];
                }
            }
        }


        return dp[0][0];
    }
    
    public boolean doesMatch(String s, String p, int i, int j) {
        if (j == p.length()) {
            return i == s.length();
        }

        if (memo[i][j] != null) {
            return memo[i][j];
        }


        if (i == s.length()) {
            boolean result = j < p.length() && p.charAt(j) == '*' && doesMatch(s, p, i, j + 1);
            memo[i][j] = result;
            return result;
        }

        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
            boolean result = doesMatch(s, p, i + 1, j + 1);
            memo[i][j] = result;
            return result;
        }

        if (p.charAt(j) == '*') {
            boolean result = doesMatch(s, p, i + 1, j) || doesMatch(s, p, i, j + 1);
            memo[i][j] = result;
            return result;
        }


        memo[i][j] = false;
        return false;
    }
}