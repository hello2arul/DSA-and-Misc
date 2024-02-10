package BFS;

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
}
