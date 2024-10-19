package DP;


/*
 * https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/
 * Recursion - 2^n
 * memo - n*m
 * dp - n*m
 */
public class CountNumberOfMaximumBitwiseOrSubsets {
    public int countMaxOrSubsets(int[] nums) {
        int maxOr = 0;

        // Find the maximum OR value that can be obtained from any subset
        for (int num : nums) {
            maxOr |= num;
        }

        return dfs(nums, 0, maxOr, 0);
    }

    private int dfs(int[] nums, int idx, int maxOr, int curOr) {
        if (idx == nums.length) {
            return curOr == maxOr ? 1 : 0;
        }

        // Include the current number in the subset
        int include = dfs(nums, idx + 1, maxOr, curOr | nums[idx]);
        
        // Exclude the current number from the subset
        int exclude = dfs(nums, idx + 1, maxOr, curOr);

        return include + exclude;
    }

    public int countMaxOrSubsetsDP(int[] nums) {
        int maxOr = 0;

        // Calculate the maximum OR value
        for (int num : nums) {
            maxOr |= num;
        }

        // Initialize a DP array where dp[j] represents the number of subsets with OR value j
        int[] dp = new int[maxOr + 1];
        dp[0] = 1; // One way to achieve OR 0 (the empty subset)

        // Update the DP array for each number
        for (int num : nums) {
            // Iterate backward to avoid overwriting values in the same iteration
            for (int j = maxOr; j >= 0; j--) {
                if (dp[j] > 0) {
                    int newOr = j | num; // Calculate new OR value with current number
                    dp[newOr] += dp[j]; // Update the count for the new OR value
                }
            }
        }

        // The maximum OR value count is stored in dp[maxOr]
        return dp[maxOr];
    }
}
