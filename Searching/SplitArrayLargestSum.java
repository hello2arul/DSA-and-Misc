package Searching;

/*
https://leetcode.com/problems/split-array-largest-sum/description/
Google

Given an integer array nums and an integer k, 
split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.

Return the minimized largest sum of the split.

A subarray is a contiguous part of the array.

Example 1:

Input: nums = [7,2,5,10,8], k = 2
Output: 18
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two
subarrays is only 18.

Example 2:

Input: nums = [1,2,3,4,5], k = 2
Output: 9
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [1,2,3] and [4,5], where the largest sum among the 
two subarrays is only 9.
*/
public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int k) {
        int low = 0, high = 0;

        // Determine the range for binary search
        for (int num : nums) {
            low = Math.max(low, num); // The largest single element
            high += num; // The sum of the entire array
        }

        // Binary search to find the minimized largest sum
        while (low < high) {
            int mid = low + (high - low) / 2;

            if (canSplit(nums, k, mid)) {
                high = mid; // Try a smaller maximum sum
            } else {
                low = mid + 1; // Try a larger maximum sum
            }
        }

        return low;
    }

    // Helper function to check if we can split the array into k or fewer subarrays
    // such that the largest sum of any subarray is <= maxSum
    private boolean canSplit(int[] nums, int k, int maxSum) {
        int subarrayCount = 1; // Start with one subarray
        int currentSum = 0;

        for (int num : nums) {
            if (currentSum + num > maxSum) {
                // Start a new subarray
                subarrayCount++;
                currentSum = num;

                // If the number of subarrays exceeds k, return false
                if (subarrayCount > k) {
                    return false;
                }
            } else {
                currentSum += num;
            }
        }

        return true;
    }

    public int splitArray(int[] nums, int k) {
        return helper(nums, 0, k);
    }

    // Recursive helper function to calculate the minimum largest sum
    private int helper(int[] nums, int start, int k) {
        // Base case: If only one subarray is left, return the sum of the remaining elements
        if (k == 1) {
            int sum = 0;
            for (int i = start; i < nums.length; i++) {
                sum += nums[i];
            }
            return sum;
        }

        int sum = 0;
        int minLargestSum = Integer.MAX_VALUE;

        // Try splitting the array at every possible position
        for (int i = start; i < nums.length - k + 1; i++) {
            sum += nums[i]; // Add the current element to the current subarray

            // Recursively calculate the largest sum for the remaining subarrays
            int largestSum = Math.max(sum, helper(nums, i + 1, k - 1));

            // Update the minimum largest sum
            minLargestSum = Math.min(minLargestSum, largestSum);

            // Optimization: If the current sum exceeds the minimum largest sum, break early
            if (sum > minLargestSum) {
                break;
            }
        }

        return minLargestSum;
    }

}
