package Greedy;

/*
 * https://leetcode.com/problems/maximum-matrix-sum/
You are given an n x n integer matrix. You can do the following operation 
any number of times:

Choose any two adjacent elements of matrix and multiply each of them by -1.
Two elements are considered adjacent if and only if they share a border.

Your goal is to maximize the summation of the matrix's elements. 
Return the maximum sum of the matrix's elements using the operation mentioned above

Input: matrix = [[1,-1],[-1,1]]
Output: 4
Explanation: We can follow the following steps to reach sum equals 4:
- Multiply the 2 elements in the first row by -1.
- Multiply the 2 elements in the first column by -1.
 */
public class MaximumMatrixSum {
    public long maxMatrixSum(int[][] matrix) {
        // if we have odd number of negatives, at least one negative remains
        // else, res = max(abs(sum))
        int minAbsVal = Integer.MAX_VALUE;
        long sum = 0;
        int negatives = 0;

        for (int[] row : matrix) {
            for (int num : row) {
                sum += Math.abs(num);
                if (num < 0)
                    negatives++;
                minAbsVal = Math.min(minAbsVal, Math.abs(num));
            }
        }

        if (negatives % 2 != 0) {
            sum -= (2 * minAbsVal);
        }
        return sum;
    }
    
}