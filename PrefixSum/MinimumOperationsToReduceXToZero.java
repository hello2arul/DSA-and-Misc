package PrefixSum;

/*
https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
Google

You are given an integer array nums and an integer x. In one operation,
you can either remove the leftmost or the rightmost element from the array nums and 
subtract its value from x. Note that this modifies the array for future operations.

Return the minimum number of operations to reduce x to exactly 0 if it is possible, 
otherwise, return -1.

Example 1:

Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.

Example 2:

Input: nums = [5,6,7,8,9], x = 4
Output: -1

Example 3:

Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the 
first two elements (5 operations in total) to reduce x to zero.
 */
public class MinimumOperationsToReduceXToZero {

    /*
     * [1,1,4,2,3], x = 5
     * target = 1 + 1 + 4 + 2 + 3 = 11
     * target = 11 - 5 = 6
     * find the longest subarray with sum = 6
     * 
     * left = 0, right = 0, sum = 1, max = -1
     * sum = 1, left = 0, right = 1, sum = 2
     * sum = 2, left = 0, right = 2, sum = 6
     * sum = 6, left = 0, right = 3, sum = 8
     * sum = 8, left = 0, right = 4, sum = 11
     * sum = 11, left = 1, right = 4, sum = 10
     * sum = 10, left = 2, right = 4, sum = 7
     * sum = 7, left = 3, right = 4, sum = 3
     * sum = 3, left = 4, right = 4, sum = 3
     * sum = 3, left = 4, right = 5, sum = 0
     * max = 5
     */
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int target = 0;
        for (int num : nums) {
            target += num;
        }
        target -= x;
        if (target == 0) {
            return n;
        }
        int left = 0, right = 0, sum = 0, max = -1;
        while (right < n) {
            sum += nums[right];
            while (sum > target && left <= right) {
                sum -= nums[left];
                left++;
            }
            if (sum == target) {
                max = Math.max(max, right - left + 1);
            }
            right++;
        }
        return max == -1 ? -1 : n - max;
    }
}
