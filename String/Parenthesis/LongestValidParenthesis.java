package Parenthesis;

import java.util.LinkedList;
import java.util.Queue;

/*
 * https://leetcode.com/problems/longest-valid-parentheses/description/
 * TLE, refer stack for optimized solution
 */
public class LongestValidParenthesis {
    public int longestValidParentheses(String s) {
        Queue<String> q = new LinkedList<>();
        q.offer(s);
        int depth = 0;
        
        while(!q.isEmpty()) {
            for(int size = q.size(); size > 0; size--) {
                String cur = q.poll();
                if(isValid(cur)) {
                    return s.length() - depth;
                }
                q.offer(cur.substring(1));
                q.offer(cur.substring(0, cur.length() - 1));
            }
            depth++;
        }
        
        return 0;        
    }
    
    private boolean isValid(String s) {
        int left = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(')  left++;
            else if(s.charAt(i) == ')' && left-- == 0)
                return false;
        }
        return left == 0;
    }

    public int longestValidParenthesesDP(String s) {
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
