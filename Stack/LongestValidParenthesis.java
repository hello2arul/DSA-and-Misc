package Stack;

import java.util.Stack;

/*
* https://leetcode.com/problems/longest-valid-parentheses/description/
* also refer DP solution
* */
public class LongestValidParenthesis {
    public int longestValidParentheses(String s) {
        int res = 0;
        if(s == null || s.length() <= 1)
            return res;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); //()
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if(ch == ')') {
                stack.pop();
                if(!stack.isEmpty()) {
                    res = Math.max(res, i - stack.peek());
                } else {
                    stack.push(i);
                }
            } else {
                stack.push(i);
            }
        }

        return res;
    }
}
