package DP;

import java.util.HashMap;
import java.util.Map;

/*
 * https://leetcode.com/problems/fibonacci-number/description/
 */
public class Fibonacci {
    Map<Integer, Integer> memo = new HashMap<>();
    
    public int fibMemo(int n) {
        if(memo.containsKey(n))
            return memo.get(n);
        int res = 0;
        if(n <= 1)  
            res = n;
        else
            res = fib(n - 1) + fib(n - 2);
        memo.put(n, res);
        return res;
    }

    public int fibTabulation(int n) {
        if(n <= 0)
            return n;
        int[] memo = new int[n + 1];
        memo[1] = 1;

        for(int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }
}
