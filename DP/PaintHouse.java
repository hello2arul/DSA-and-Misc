package DP;

/*
 * https://leetcode.ca/all/256.html
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. 
 * The cost of painting each house with a certain color is different.
 *  You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
 For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, 
 and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
             Minimum cost: 2 + 5 + 3 = 10.
 */
public class PaintHouse {
    
    public static int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }

        int n = costs.length;
        int[][] dp = new int[n][3];

        // Initialize the first house's cost
        dp[0][0] = costs[0][0]; // Cost to paint the first house red
        dp[0][1] = costs[0][1]; // Cost to paint the first house blue
        dp[0][2] = costs[0][2]; // Cost to paint the first house green

        // Fill the dp table
        for (int i = 1; i < n; i++) {
            dp[i][0] = costs[i][0] + Math.min(dp[i-1][1], dp[i-1][2]); // Paint house i red
            dp[i][1] = costs[i][1] + Math.min(dp[i-1][0], dp[i-1][2]); // Paint house i blue
            dp[i][2] = costs[i][2] + Math.min(dp[i-1][0], dp[i-1][1]); // Paint house i green
        }

        // The result is the minimum cost to paint all houses
        return Math.min(dp[n-1][0], Math.min(dp[n-1][1], dp[n-1][2]));
    }

    public static void main(String[] args) {
        int[][] costs = {
            {17, 2, 17},
            {16, 16, 5},
            {14, 3, 19}
        };

        System.out.println(minCost(costs));  // Output: 10
    }
}
