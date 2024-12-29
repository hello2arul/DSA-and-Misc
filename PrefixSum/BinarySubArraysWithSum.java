package PrefixSum;

/*
https://leetcode.com/problems/binary-subarrays-with-sum/

Given a binary array nums and an integer goal, 
return the number of non-empty subarrays with a sum goal.

A subarray is a contiguous part of the array.

Example 1:

Input: nums = [1,0,1,0,1], goal = 2
Output: 4
Explanation: The 4 subarrays are bolded and underlined below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
Example 2:

Input: nums = [0,0,0,0,0], goal = 0
Output: 15
 */
public class BinarySubArraysWithSum {
    
    // O(n) O(1)
    public int numSubarraysWithSum(int[] nums, int goal) {
        return atMostSubarray(nums, goal) - atMostSubarray(nums, goal - 1);
    }

    private int atMostSubarray(int[] nums, int goal) {
        int n = nums.length;
        int res = 0;
        int sum = 0;
        int right = 0, left = 0;

        while (right < n) {
            sum += nums[right];

            while (sum > goal && left <= right) {
                sum -= nums[left++];
            }
            res += (right - left + 1);
            right++;
        }

        return res;
    }
}
