package DP;

import java.util.Arrays;

/*
 * https://leetcode.com/problems/longest-increasing-subsequence
 */
public class LongestIncreasingSubsequence {
    public int lis(int[] nums, int idx, int prev) {
        if(idx >= nums.length)
            return 0;
        int prevIncluding = 0;
        if(nums[idx] > prev) {
            prevIncluding = 1 + lis(nums, idx + 1, nums[idx]);
        }
        return Math.max(prevIncluding, lis(nums, idx + 1, prev));
    }

    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        int[] lengths = new int[nums.length];
        Arrays.fill(lengths, 1);
        int max = 1;
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i] && lengths[j] + 1 >= lengths[i]) {
                    lengths[i] = lengths[j] + 1;
                    max = Math.max(max, lengths[i]);
                } 
            }
        }
        return max;
    }
}
