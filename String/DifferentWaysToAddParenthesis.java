package Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * https://leetcode.com/problems/different-ways-to-add-parentheses
 */

public class DifferentWaysToAddParenthesis {
    // 𝑂(2^𝑛)(where n is the number of operators), O(n)
     public List<Integer> diffWaysToCompute(String s) {
        List<Integer> res = new ArrayList<>();
        
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                List<Integer> res1 = diffWaysToCompute(s.substring(0, i));
                List<Integer> res2 = diffWaysToCompute(s.substring(i + 1));
                
                for (int I: res1) {
                    int sum = 0;
                    for (int j: res2) {
                        switch (s.charAt(i)) {
                            case '+':
                                sum = I + j;
                                break;
                            case '-':
                                sum = I - j;
                                break;
                            case '*':
                                sum = I * j;
                                break;
                            case '/':
                                sum = I / j;
                                break;
                        }
                        res.add(sum);
                    }
                }
            }
        }
        if (res.isEmpty()) {
            res.add(Integer.valueOf(s));
        }
        return res;
    }

    Map<String, List<Integer>> memo = new HashMap<>();
    // O(n^3) O(n^2)
    public List<Integer> diffWaysToComputeDP(String s) {
        List<Integer> res = new ArrayList<>();
        
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                List<Integer> res1;
                String key1 = s.substring(0, i);

                if (memo.containsKey(key1))
                    res1 = memo.get(key1);
                else 
                    res1 = diffWaysToCompute(key1);

                List<Integer> res2;
                String key2 = s.substring(i + 1);

                if (memo.containsKey(key2))
                    res2 = memo.get(key2);
                else 
                    res2 = diffWaysToCompute(key2);
                
                for (int I: res1) {
                    int sum = 0;
                    for (int j: res2) {
                        switch (s.charAt(i)) {
                            case '+':
                                sum = I + j;
                                break;
                            case '-':
                                sum = I - j;
                                break;
                            case '*':
                                sum = I * j;
                                break;
                            case '/':
                                sum = I / j;
                                break;
                        }
                        res.add(sum);
                    }
                }
            }
        }
        if (res.isEmpty()) {
            res.add(Integer.valueOf(s));
        }
        memo.put(s, res);
        return res;
    }
}
