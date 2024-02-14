package Arrays;
//https://leetcode.com/problems/maximum-sum-circular-subarray

public class MaxSumCircularSubArray {
    /*
     * possible cases:
     * 1. max sum is a non circular subarray in that case can use Kadane(max sum including, not including)
     * 2. max sum is a circular subarray in that case, find min contiguous sum and subtract that from total sum 
     */

    public int maxSubarraySumCircular(int[] nums) {
        int total = 0, maxSum = nums[0], minSum = nums[0], curMax = 0, curMin = 0;

        for(int num: nums) {
            curMax = Math.max(num, curMax + num);
            curMin = Math.min(num, curMin + num);
            maxSum = Math.max(curMax, maxSum);
            minSum = Math.min(minSum, curMin);
            total += num;
        }   
        //handle case when sum is negative
        return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum; 
    }
}
