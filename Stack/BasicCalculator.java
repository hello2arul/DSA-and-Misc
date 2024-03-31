
/*
 * https://leetcode.com/problems/basic-calculator/
 */

import java.util.Stack;

public class BasicCalculator {
    public int calculate(String s) {
        int len = s.length(), res = 0, sign = 1;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < len; i++) {
            if(Character.isDigit(s.charAt(i))) {
                int val = s.charAt(i) - '0';
                while(i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
                    val = val * 10 + (s.charAt(i++ + 1) - '0');
                }
                res = res + (val * sign);
            } else if(s.charAt(i) == '+')   
                sign = 1;
            else if(s.charAt(i) == '-')
                sign = -1;
            else if(s.charAt(i) == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if(s.charAt(i) == ')') {
                            //sign        val
                res = res * stack.pop() + stack.pop();
            }
        }
        //System.out.println(stack);
        return res;
    }
}
