package Stack;

import java.util.Stack;

/*
 * https://leetcode.com/problems/minimum-string-length-after-removing-substrings/description/
 * You are given a string s consisting only of uppercase English letters.

You can apply some operations to this string where, in one operation, you can remove any occurrence of one of the substrings "AB" or "CD" from s.

Return the minimum possible length of the resulting string that you can obtain.

Note that the string concatenates after removing the substring and could produce new "AB" or "CD" substrings.

Input: s = "ABFCACDB"
Output: 2
Explanation: We can do the following operations:
- Remove the substring "ABFCACDB", so s = "FCACDB".
- Remove the substring "FCACDB", so s = "FCAB".
- Remove the substring "FCAB", so s = "FC".
So the resulting length of the string is 2.
It can be shown that it is the minimum length that we can obtain.

1. Brute force, while (str.contains(AB || CD))
2. Stack
 */
public class MinimumStringLengthAfterRemovingSubstrings {
    public int minLength(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (stack.isEmpty()) {
                stack.push(ch);
            } else if (ch == 'B' && stack.peek() == 'A') {
                stack.pop();  
            } else if (ch == 'D' && stack.peek() == 'C') {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        return stack.size();
    }
}
