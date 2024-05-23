package DP;

/*
 * https://www.geeksforgeeks.org/maximum-sum-such-that-no-two-elements-are-adjacent/
 * https://leetcode.com/problems/house-robber/description/
 */
public class MaxSumWithNoConsecutives {
    //O(2^n)
    public int maxSum(int[] arr, int n) {
        if(n == 1)  return arr[0];
        if(n == 2)  return Math.max(arr[0], arr[1]);
        return Math.max(
            maxSum(arr, n - 1),
            arr[n - 1] + maxSum(arr, n - 2)
        );
    }

    public int robdp(int[] nums) {
        int len = nums.length;
        if(len == 0)    return 0;
        if(len == 1)    return nums[0];
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i = 2; i < len; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[len - 1];        
    }
}
