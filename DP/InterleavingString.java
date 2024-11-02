package DP;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/interleaving-string
Google
Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where s and t are divided into n and m 
substrings
respectively, such that:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Explanation: One way to obtain s3 is:
Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
Since s3 can be obtained by interleaving s1 and s2, we return true.

Follow up: Could you solve it using only O(s2.length) additional memory space?

*/
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        return areInterleaving(s1, s2, s3, 0, 0, 
                               new Boolean[s1.length() + 1][s2.length() + 1]);
    }
    
    // O(m * n) T & S, without memo O(2 ^ m + n)
    private boolean areInterleaving(String s1, String s2, String s3, int i, int j,
                                    Boolean[][] memo) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        int k = i + j;
        if (k == s3.length()) {
            return true;
        }
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        boolean res = false;
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            if (areInterleaving(s1, s2, s3, i + 1, j, memo)) {
                res = true;
            }
        }
        
        if (!res && j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            if (areInterleaving(s1, s2, s3, i, j + 1, memo)) {
                res = true;
            }
        }
        return memo[i][j] = res;
    }

    public boolean isInterleaveTabulation(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        if (m + n != s3.length())
            return false;

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0)
                    dp[i][j] = true;
                else if (i == 0)
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
                else if (j == 0)
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i - 1);
                else
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)
                        || dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }

        return dp[m][n];
    }

    public boolean isInterleaveBFS(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        if (m + n != s3.length())
            return false;

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[m + 1][n + 1];
        q.offer(new int[] {0, 0});

        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            if (i + j == s3.length())
                return true;
            if (visited[i][j])
                continue;
            if (i < s1.length() && s1.charAt(i) == s3.charAt(i + j))
                q.offer(new int[] {i + 1, j});
            if (j < s2.length() && s2.charAt(j) == s3.charAt(i + j))
                q.offer(new int[] {i, j + 1});

            visited[i][j] = true;
        }
        return false;
    }
}