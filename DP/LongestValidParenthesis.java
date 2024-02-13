package DP;


/*
* https://leetcode.com/problems/longest-valid-parentheses/submissions/423167134/
* Also refer stack solution
* */
public class LongestValidParenthesis {
    public int longestValidParentheses(String s) {
        int len = s.length();
        int max = 0;
        if(len <= 1)    return max;
        
        int[] dp = new int[len];
        
        for(int i = 1; i < len; i++) {
            if(s.charAt(i) == ')') {
                if(s.charAt(i - 1) == '(') {
                    dp[i] = 2 + (i - 2 >= 0 ? dp[i - 2] : 0);
                } else {
                    if(i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = 2 + dp[i - 1] + (i - dp[i - 1] - 2 >=0 ? 
                                                    dp[i - dp[i - 1] - 2] : 0);
                    }
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
