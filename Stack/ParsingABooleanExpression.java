package Stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/*
 * https://leetcode.com/problems/parsing-a-boolean-expression

 A boolean expression is an expression that evaluates to either true or false. 
 It can be in one of the following shapes:

't' that evaluates to true.
'f' that evaluates to false.
'!(subExpr)' that evaluates to the logical NOT of the inner expression subExpr.
'&(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical AND 
of the inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1.
'|(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical OR 
of the inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1.
Given a string expression that represents a boolean expression, 
return the evaluation of that expression.

It is guaranteed that the given expression is valid and follows the given rules.

Input: expression = "&(|(f))"
Output: false
Explanation: 
First, evaluate |(f) --> f. The expression is now "&(f)".
Then, evaluate &(f) --> f. The expression is now "f".
Finally, return false.
 */
public class ParsingABooleanExpression {
     public boolean parseBoolExpr(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            Set<Character> seen = new HashSet<>();
            if (ch == ')') {
                while (stack.peek() != '(') 
                    seen.add(stack.pop());
                stack.pop();
                char op = stack.pop();

                if (op == '|') {
                    stack.push(seen.contains('t') ? 't' : 'f');
                } else if (op == '&') {
                    stack.push(seen.contains('f') ? 'f' : 't');
                } else {
                    stack.push(seen.contains('f') ? 't' : 'f');
                }
            } else if (ch != ',') {
                stack.push(ch);
            }
        }
        return stack.peek() == 't';
    }
}
