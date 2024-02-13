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
        )
    }
}
