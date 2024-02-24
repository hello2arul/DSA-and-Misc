
/*
 * 
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/description/
 * 
 */

import java.util.Stack;
import javafx.util.Pair;

public class RemoveAdjacentDuplicates {
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            if(stack.isEmpty() || stack.peek() != s.charAt(i)) {
                stack.push(s.charAt(i));
            } else {
                while(!stack.isEmpty() && stack.peek() == s.charAt(i))
                    stack.pop();
            } 
        }
        StringBuilder res = new StringBuilder();
        while(!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return res.reverse().toString();
    }

    public String removeDuplicates2(String s, int k) {
        Stack<Pair<Character, Integer>> stack = new Stack<>();
        
        for(int i = 0; i < s.length(); i++) {
            if(stack.isEmpty() || stack.peek().getKey() != s.charAt(i)) {
                stack.push(new Pair<Character, Integer>(s.charAt(i), 1));
            } else {
                Pair<Character, Integer> pair = stack.peek();
                if(pair.getValue() + 1 == k) {
                    while(!stack.isEmpty() && pair.getKey() == s.charAt(i)) {
                        stack.pop();
                        if(!stack.isEmpty())
                            pair = stack.peek();
                    }
                } else {
                    stack.push(new Pair<Character, Integer> (s.charAt(i), pair.getValue() + 1));
                }
            }
        }
        StringBuilder res = new StringBuilder();
        while(!stack.isEmpty()) {
            res.append(stack.pop().getKey());
        }
        return res.reverse().toString();
    }
}
