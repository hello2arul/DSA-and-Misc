package DP;

import java.util.HashMap;
import java.util.*;

/*
https://leetcode.com/discuss/interview-question/6038441/Google-or-L4-or-Phonescreen
Google

Initial Question: You are given an n×m grid and start from the bottom-left corner 
with the goal of reaching the bottom-right corner. The allowed moves are only to 
the right, diagonally up-right, and diagonally down-right. The task is to determine
the total number of unique paths that can lead you from the starting point to the 
destination while adhering to these movement rules.

Follow-Up 1: A list of specific checkpoints within the grid is now introduced.
In this variation, you are required to count only those paths that pass 
through each of these checkpoints once before reaching the bottom-right corner.


Follow-Up 2: The order of visiting these checkpoints is now specified.
For example, if the checkpoints are labeled 1, 2, and 3, any valid path
must encounter checkpoint 1 first, then checkpoint 2, and finally checkpoint 3,
in that exact sequence, on its way to the destination.
*/
import java.util.*;

public class UniquePaths {

    // Memoization map to store computed values
    private static Map<String, Integer> memo;

    // Helper recursive function to compute unique paths
    public static int findPaths(int i, int j, int n, int m, List<int[]> checkpoints, int checkpointIndex) {
        // Base case: If we reach the bottom-right corner and have visited all checkpoints, there's 1 way
        if (i == n - 1 && j == m - 1 && checkpointIndex == checkpoints.size()) {
            return 1;
        }

        // Check if the current position is out of bounds
        if (i < 0 || i >= n || j < 0 || j >= m) {
            return 0;
        }

        // Check if the current position is the next checkpoint
        if (checkpointIndex < checkpoints.size() && i == checkpoints.get(checkpointIndex)[0] && j == checkpoints.get(checkpointIndex)[1]) {
            checkpointIndex++;
        }

        // Check if the result for this cell and checkpoint index is already computed
        String key = i + "," + j + "," + checkpointIndex;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Calculate the number of ways from the current position
        int right = findPaths(i, j + 1, n, m, checkpoints, checkpointIndex); // Move right
        int upRight = (i - 1 >= 0) ? findPaths(i - 1, j + 1, n, m, checkpoints, checkpointIndex) : 0; // Move diagonally up-right
        int downRight = (i + 1 < n) ? findPaths(i + 1, j + 1, n, m, checkpoints, checkpointIndex) : 0; // Move diagonally down-right

        // Total number of paths from current position
        int totalPaths = right + upRight + downRight;

        // Store the result in memo for future reference
        memo.put(key, totalPaths);

        return totalPaths;
    }

    public static int uniquePaths(int n, int m, List<int[]> checkpoints) {
        // Initialize the memoization map
        memo = new HashMap<>();
        // Start from the bottom-left corner (n-1, 0)
        return findPaths(n - 1, 0, n, m, checkpoints, 0);
    }

    public static void main(String[] args) {
        int n = 3; // Number of rows
        int m = 3; // Number of columns
        List<int[]> checkpoints = Arrays.asList(new int[]{1, 1}, new int[]{2, 2}); // Checkpoints
        System.out.println(uniquePaths(n, m, checkpoints)); // Output: Number of unique paths passing through checkpoints
    }
}