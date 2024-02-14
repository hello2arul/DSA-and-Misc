package Arrays;
//https://leetcode.com/problems/maximum-subarray/

public class MaximumSumSubarray {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int maxIncludingCurrent = nums[0];

        for(int i = 1; i < nums.length; i++) {
            maxIncludingCurrent = Math.max(maxIncludingCurrent + nums[i], nums[i]);
            max = Math.max(max, maxIncludingCurrent);
        }
        return max;
    }
}
