package DP;

import java.util.Map;

/*
 * https://leetcode.com/problems/longest-common-subsequence/description/
 * not necessarily contiguous unlike substring
 */
public class LongestCommonSubsequence {
    //O(2^n) since recursive tree doubles
    private int lcsRecursive(String s1, String s2, int i, int j) {
        if(i >= s1.length() || j >= s2.length()) {
            return 0;
        }
        if(s1.charAt(i) == s2.charAt(j)) {
            return 1 + lcsRecursive(s1, s2, i + 1, j + 1);
        }
        return Math.max(
            lcsRecursive(s1, s2, i + 1, j),
            lcsRecursive(s1, s2, i, j + 1)
        );
    }

    private int lcsMemo(String s1, String s2, int i, int j,
    Map<String, Integer> memo) {
        if(i >= s1.length() || j >= s2.length()) {
            return 0;
        }
        String key = i + "," + j;
        if(memo.containsKey(key))
            return memo.get(key);
        int res = 0;
        if(s1.charAt(i) == s2.charAt(j)) {
            res = 1 + lcsMemo(s1, s2, i + 1, j + 1, memo);
            memo.put(key, res);
            return res;
        }
        res = Math.max(
            lcsMemo(s1, s2, i + 1, j, memo),
            lcsMemo(s1, s2, i, j + 1, memo)
        );
        memo.put(key, res);
        return res;
    }

    public int longestCommonSubsequenceTabulation(String s1, String s2) {
        //translate the recursion into tabulation;
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        // 0th row, col represents empty 
        for(int i = 1; i <= s1.length(); i++) {
            for(int j = 1; j <= s2.length(); j++) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
}
