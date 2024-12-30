package DP;

import java.util.Map;

/*

Given the integers zero, one, low, and high, we can construct a string by 
starting with an empty string, and then at each step perform either of the following:

Append the character '0' zero times.
Append the character '1' one times.
This can be performed any number of times.

A good string is a string constructed by the above process having a length between 
low and high (inclusive).

Return the number of different good strings that can be constructed satisfying these properties. 
Since the answer can be large, return it modulo 109 + 7.

 

Example 1:

Input: low = 3, high = 3, zero = 1, one = 1
Output: 8
Explanation: 
One possible valid good string is "011". 
It can be constructed as follows: "" -> "0" -> "01" -> "011". 
All binary strings from "000" to "111" are good strings in this example.
Example 2:

Input: low = 2, high = 3, zero = 1, one = 2
Output: 5
Explanation: The good strings are "00", "11", "000", "110", and "011".
*/

public class CountWaysToBuildGoodStrings {
    
    private static final int MOD = 1000000007;

    public int countGoodStrings(int low, int high, int zero, int one) {
        return countGoodStringsHelper(0, low, high, zero, one);
    }

    // O(2^n) time complexity and O(n) space complexity
    // n is the maximum length of the string
    private int countGoodStringsHelper(int length, int low, int high, int zero, int one) {
        if (length > high) {
            return 0;
        }
        int count = 0;
        if (length >= low && length <= high) {
            count = 1;
        }
        count = (count + countGoodStringsHelper(length + zero, low, high, zero, one)) % MOD;
        count = (count + countGoodStringsHelper(length + one, low, high, zero, one)) % MOD;
        return count;
    }

    // O(n) time complexity and O(n) space complexity
    private int countGoodStringsHelperMemo(int length, int low, int high, int zero, int one, Map<Integer, Integer> memo) {
        if (length > high) {
            return 0;
        }
        if (memo.containsKey(length)) {
            return memo.get(length);
        }
        int count = 0;
        if (length >= low && length <= high) {
            count = 1;
        }
        count = (count + countGoodStringsHelper(length + zero, low, high, zero, one, memo)) % MOD;
        count = (count + countGoodStringsHelper(length + one, low, high, zero, one, memo)) % MOD;
        memo.put(length, count);
        return count;
    }

    // O(n) time complexity and O(n) space complexity
    public int countGoodStringsTabulation(int low, int high, int zero, int one) {
        int[] dp = new int[high + 1];
        dp[0] = 1; // Base case: one way to build an empty string

        for (int length = 1; length <= high; length++) {
            if (length >= zero) {
                dp[length] = (dp[length] + dp[length - zero]) % MOD;
            }
            if (length >= one) {
                dp[length] = (dp[length] + dp[length - one]) % MOD;
            }
        }

        int result = 0;
        for (int length = low; length <= high; length++) {
            result = (result + dp[length]) % MOD;
        }

        return result;
    }

}