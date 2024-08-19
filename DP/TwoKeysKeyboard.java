package DP;

/*
https://leetcode.com/problems/2-keys-keyboard/description/

There is only one character 'A' on the screen of a notepad. 
You can perform one of two operations on this notepad for each step:

Copy All: You can copy all the characters present on the screen 
(a partial copy is not allowed).
Paste: You can paste the characters which are copied last time.
Given an integer n, return the minimum number of operations to get
the character 'A' exactly n times on the screen.

 

Example 1:

Input: n = 3
Output: 3
Explanation: Initially, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.
Example 2:

Input: n = 1
Output: 0
 
 */
public class TwoKeysKeyboard {
    public int minStepsDP(int n) {
        int[] dp = new int[n + 1];

        for (int i = 2; i <=n; i++) {
            dp[i] = i;
            // copy and paste the largest to minize
            // hence, j = i - 1
            for (int j = i - 1; j > 0; j--) {
                if (i % j == 0) {
                    dp[i] = dp[j] + (i / j);
                    break;
                }
            }
        }

        return dp[n];
    }

    public int minSteps(int n) {
        int res = minSteps(n, 1, "A", "A");
        return res == Integer.MAX_VALUE ? 0: res;
    }
    private int minSteps(int n, int op, String cp, String res) {
        if(n == 1)  return 0;
        if(res.length() == n) {
            return op;
        }
        if(res.length() > n) {
            return Integer.MAX_VALUE;
        }
        // 1 - paste the previously copied A or copy whatever is on 
        // screen again and repaste (2)
        return Math.min(
            minSteps(n, op + 1, cp, res + cp),
            minSteps(n, op + 2, res, res + res)
        );
    }
}
