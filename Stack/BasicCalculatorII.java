package Stack;
/*
https://leetcode.com/problems/basic-calculator-ii/
Google
*/

import java.util.Stack;

public class BasicCalculatorII {

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0, res = 0, len = s.length();
        char op = '+';
        
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }
            if ((!Character.isDigit(ch) && ch != ' ') || i == len - 1) {
                if (op == '+')
                    stack.push(num);
                if (op == '-')
                    stack.push(-num);
                if (op == '*')
                    stack.push(stack.pop() * num);
                if (op == '/')
                    stack.push(stack.pop() / num);
                num = 0;
                op = ch;
            }
            // System.out.println(stack);
        }
        // System.out.println(stack);
        while (!stack.isEmpty())
            res += stack.pop();
        return res;
    }
}
