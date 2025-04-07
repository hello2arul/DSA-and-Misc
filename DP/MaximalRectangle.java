package DP;

/*

Google: https://leetcode.com/problems/maximal-rectangle

Given a rows x cols binary matrix filled with 0's and 1's, 
find the largest rectangle containing only 1's and return its area.

Input: matrix = [
["1","0","1","0","0"],
["1","0","1","1","1"],
["1","1","1","1","1"],
["1","0","0","1","0"]
]
Output: 6
*/
public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] heights = new int[n];
        int maxArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }
            maxArea = Math.max(maxArea, maxAreaInAHistogram(heights));
        }

        return maxArea;
    }

    private int maxAreaInAHistogram(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = -1;
        right[n - 1] = n;

        for (int i = 1; i < n; i++) {
            int j = i - 1;

            while (j >= 0 && heights[j] >= heights[i]) {
                j = left[j];
            }
            left[i] = j;
        }

        for (int i = n - 2; i >= 0; i--) {
            int j = i + 1;
            while (j < n && heights[j] >= heights[i]) {
                j = right[j];
            }
            right[i] = j;
        }

        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            maxArea = Math.max(maxArea, (right[i] - left[i] - 1) * heights[i]);
        }

        return maxArea;
    }
}
