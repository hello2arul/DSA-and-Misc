package DP;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/burst-balloons/
Google

You are given n balloons, indexed from 0 to n - 1. 
Each balloon is painted with a number on it represented by an array nums. 
You are asked to burst all the balloons.

If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins.
If i - 1 or i + 1 goes out of bounds of the array, then treat it as if 
there is a balloon with a 1 painted on it.

Return the maximum coins you can collect by bursting the balloons wisely.

Example 1:

Input: nums = [3,1,5,8]
Output: 167
Explanation:
nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167

Example 2:

Input: nums = [1,5]
Output: 10
*/
public class BurstBalloons {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] newNums = new int[n + 2];
        newNums[0] = newNums[n + 1] = 1;

        for (int i = 0; i < n; i++) {
            newNums[i + 1] = nums[i];
        }

        return maxCoins(newNums, 0, n + 1, new Integer[n + 2][n + 2]);
    }

    private int maxCoins(int[] nums, int start, int end, Integer[][] cache) {
        if (start == end - 1)
            return 0;
        
        if (cache[start][end] != null)
            return cache[start][end];
        int res = 0;

        for (int i = start + 1; i < end; i++) {
            // using start, end here instead of i - 1, i + 1
            // as we are then calling maxCoins(nums, start, i) 
            // which will pop all elements in between leaving only
            // start, i, end
            int coins = nums[i] * nums[start] * nums[end];
            res = Math.max(
                res,
                coins + maxCoins(nums, start, i, cache) + maxCoins(nums, i, end, cache));
        }
        cache[start][end] = res;
        return res;
    }

    public int maxCoinsTabulation(int[] nums) {
        int n = nums.length;
        int[] newNums = new int[n + 2];
        newNums[0] = newNums[n + 1] = 1;
    
        // Fill newNums with the original values
        for (int i = 0; i < n; i++) {
            newNums[i + 1] = nums[i];
        }
    
        // DP table, dp[start][end] will store the maximum coins we can collect
        int[][] dp = new int[n + 2][n + 2];
    
        // Iterate over all possible subarrays (start, end) where start < end
        for (int length = 2; length <= n + 1; length++) {
            for (int start = 0; start <= n + 1 - length; start++) {
                int end = start + length;
                // We now solve the subproblem for the subarray (start, end)
                for (int i = start + 1; i < end; i++) {
                    // Calculate the number of coins we would collect by bursting balloon i
                    int coins = newNums[i] * newNums[start] * newNums[end];
                    // Update dp[start][end] with the maximum coins from this subarray
                    dp[start][end] = Math.max(dp[start][end], coins + dp[start][i] + dp[i][end]);
                }
            }
        }
    
        // The result for the entire range (0, n+1) will be in dp[0][n+1]
        return dp[0][n + 1];
    }
}
