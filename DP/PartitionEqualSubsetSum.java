package DP;

/*
 * https://leetcode.com/problems/partition-equal-subset-sum/
 * Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in
 *  both subsets is equal or false otherwise.

 

Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
 */
public class PartitionEqualSubsetSum {
    public boolean canPartitionDP(int[] nums) {
        int sum = 0;
        for (int num: nums)
            sum += num;
        if (sum % 2 != 0)
            return false;
        int target = sum / 2;
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][target + 1];
        
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            dp[i][0] = true; // target = 0;
            for (int j = 1; j <= target; j++) {
                if (j < num)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
            }
        }

        return dp[n][target];
    }

    public boolean canPartition(int[] nums, int idx, int target, Boolean[][] memo) {
        if(target == 0) {
            return true;
        }
        if(idx == 0 || target < 0) {
            return false;
        }
        if(memo[idx][target] != null) {
            return memo[idx][target];
        }
        // including & not including
        boolean result = canPartition(nums, idx - 1, target - nums[idx], memo) ||
                         canPartition(nums, idx - 1, target, memo);
        return memo[idx][target] = result;
    }
}
