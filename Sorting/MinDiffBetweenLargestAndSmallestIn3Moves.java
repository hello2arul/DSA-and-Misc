package Sorting;

import java.util.Arrays;

/*
 * Google
 * https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/description/
 * In one move, you can choose one element of nums and change it to any value.

Return the minimum difference between the largest and smallest value of nums after 
performing at most three moves.

1. Sorting - nlogn
2. Heap - 2 heaps to store max 4 and min 4 O(n) TODO;
 */

public class MinDiffBetweenLargestAndSmallestIn3Moves {
    public int minDifference(int[] nums) {
        // if we have 4 nums, we can change 3 into the other
        int n = nums.length;
        if (n <= 4)
            return 0;

        Arrays.sort(nums);
        int res = Integer.MAX_VALUE;
        res = Math.min(res, nums[n - 4] - nums[0]); // remove last 3
        res = Math.min(res, nums[n - 1] - nums[3]); // remove first 3;
        res = Math.min(res, nums[n - 2] - nums[2]); // remove last 1, first 2
        res = Math.min(res, nums[n - 3] - nums[1]); // remove last 2, first 1

        return res;
    }
}
