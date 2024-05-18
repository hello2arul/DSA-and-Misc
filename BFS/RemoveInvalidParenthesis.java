package BFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * https://leetcode.com/problems/remove-invalid-parentheses/description/
 */
public class RemoveInvalidParenthesis {
    //to prevent duplicate result, use set
    Set<String> result = new HashSet<>();
    int minCount = Integer.MAX_VALUE;

    public List<String> removeInvalidParentheses(String s) {
        dfs(s, 0, 0, new StringBuilder(), 0, 0);
        return new ArrayList<String>(result);
    }

    private void dfs(String s, int opening, int closing,StringBuilder curr, int removalCount, 
                    int idx) {
        if(idx == s.length()) {
            if(removalCount == minCount && opening == closing) {
                result.add(new String(curr));
            } else {
                if(removalCount < minCount && opening == closing) {
                    minCount = removalCount;
                    result.clear();
                    result.add(new String(curr));
                }
            }
            return;            
        } 
         // if not a paranthesis include the char and recurse
        if(s.charAt(idx) != '(' && s.charAt(idx) != ')') {
            dfs(s,opening, closing, curr.append(s.charAt(idx)), removalCount, idx + 1);
            curr.deleteCharAt(curr.length() - 1);
        } else {
            // make a call without including the character
            dfs(s,opening, closing, curr, removalCount + 1, idx + 1);
            curr.append(s.charAt(idx));
            // if opening, pick and recurse
            if(s.charAt(idx) == '(')
                dfs(s, opening + 1, closing, curr, removalCount, idx + 1);
            
            // if it is a closing, check if opening is greater and recurse
            else if(opening > closing) {
                dfs(s, opening, closing + 1, curr, removalCount, idx + 1);
            }
            curr.deleteCharAt(curr.length() - 1);
            
        } 
    }

      private List<String> removeInvalidParenthesesDFS(String s) {
        List<String> res = new ArrayList<>();
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        boolean found = false;
        q.offer(s);

        while (!q.isEmpty()) {
            String cur = q.poll();
            
            if (isValid(cur)) {
                res.add(cur);
                found = true; 
            }
            if (found)  continue;

            for (int i = 0; i < cur.length(); i++) {
                if (cur.charAt(i) != '(' && cur.charAt(i) != ')')
                    continue;
                String temp = cur.substring(0, i) + cur.substring(i + 1);
                if (!visited.contains(temp)) {
                    visited.add(temp);
                    q.offer(temp);
                }
            }
        }
        return res;
    }

    private boolean isValid(String s) {
        int open = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(')  open++;
            else if (ch == ')' && open-- == 0)
                return false;
        }
        return open == 0;
    }
}