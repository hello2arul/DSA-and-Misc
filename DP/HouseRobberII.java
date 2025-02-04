package DP;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/house-robber-ii/description/
 * You are a professional robber planning to rob houses along a street. 
 * Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. 
 * That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected,
 *  and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, 
return the maximum amount of money you can rob tonight without alerting the police.

 

Example 1:

Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 */
public class HouseRobberII {
     public int rob(int[] nums) {
        if(nums.length == 0)    return 0;
        if(nums.length == 1)    return nums[0];
        if(nums.length == 2)    return Math.max(nums[0], nums[1]);
        
        int len = nums.length;
        int[] dp = new int[len];
        
        for(int i = 1; i < len; i++) {
            dp[i] = Math.max(nums[i] + (i - 2 >= 0 ? dp[i - 2] : 0), dp[i - 1]);
        }
        
        int res = dp[len - 1];
        Arrays.fill(dp, 0);
        dp[0] = nums[0];
        
        for(int i = 1; i < len - 1; i++) {
            dp[i] = Math.max(nums[i] + (i - 2 >= 0 ? dp[i - 2] : 0), dp[i - 1]);
        }
        
        return Math.max(res, dp[len - 2]);
    }
}
