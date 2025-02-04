package Stack;

import java.util.Stack;

/*
https://leetcode.ca/all/772.html
Google

Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].

"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12
*/

public class BasicCalculatorIII {

    // works for I and II as well
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if (c == '(') {
                int j = i, count = 0;
                for (; i < n; i++) {
                    if (s.charAt(i) == '(')
                        count++;
                    if (s.charAt(i) == ')')
                        count--;
                    if (count == 0)
                        break;
                }
                num = calculate(s.substring(j + 1, i));
            }
            if (!Character.isDigit(c) && c != ' ' || i == n - 1) {
                if (sign == '+')
                    stack.push(num);
                if (sign == '-')
                    stack.push(-num);
                if (sign == '*')
                    stack.push(stack.pop() * num);
                if (sign == '/')
                    stack.push(stack.pop() / num);
                sign = c;
                num = 0;
            }
        }
        int res = 0;
        for (int i : stack)
            res += i;
        return res;
    }

    public static void main(String[] args) {
        BasicCalculatorIII calculator = new BasicCalculatorIII();
        System.out.println(calculator.calculate("1 + 1")); // Output: 2
        System.out.println(calculator.calculate(" 6-4 / 2 ")); // Output: 4
        System.out.println(calculator.calculate("2*(5+5*2)/3+(6/2+8)")); // Output: 21
        System.out.println(calculator.calculate("(2+6* 3+5- (3*14/7+2)*5)+3")); // Output: -12
    }
}
