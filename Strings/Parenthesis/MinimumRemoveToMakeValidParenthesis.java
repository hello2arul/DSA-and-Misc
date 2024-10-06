package Parenthesis;

import java.util.Stack;

/*
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/description/
 * Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

Meta
 */
public class MinimumRemoveToMakeValidParenthesis {
     public String minRemoveToMakeValid(String s) {
        StringBuilder res = new StringBuilder(s);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else if (ch == ')') {
                if (!stack.isEmpty())
                    stack.pop();
                else
                    res.setCharAt(i, '*');
            }
        }

        while(!stack.isEmpty()) 
            res.setCharAt(stack.pop(), '*');
        return res.toString().replaceAll("\\*","");
    }

    public String minRemoveToMakeValidWithoutStack(String s) {
        if (s == null || s.length() == 0) return s;       
        int open = 0, closed = 0;       
    
        for (int i = 0; i < s.length(); ++i) {
            if(s.charAt(i) == ')')  closed++;
        }
    
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                open++;     // Increment count of '(' to the left so that a future ')' can match it.
                if (closed > 0) {
                    closed--;
                     // Only add the current '(' to the result if there is a ')' to the right that can match (close) it.
                    result.append(s.charAt(i));    
                }
            } else if (s.charAt(i) == ')') {
                if (open > 0) {
                    open--;
                    // Only add the current ')' to the result if there is a '(' to the left that can match it.
                    result.append(s.charAt(i));
                } else {
                    closed--;   // The current ')' hasn't been closed by a '(' to the left AND it can't be used to close future '('.
                }
            } else {
                result.append(s.charAt(i));  // Simply append all non-parenthesis characters.
            }
        }
        return result.toString();
    }
}
