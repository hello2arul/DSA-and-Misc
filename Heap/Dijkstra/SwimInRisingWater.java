package Heap.Dijkstra;

import java.util.PriorityQueue;

/*
https://leetcode.com/problems/swim-in-rising-water/description/
Google

You are given an n x n integer matrix grid where each value grid[i][j] 
represents the elevation at that point (i, j).

The rain starts to fall. At time t, the depth of the water everywhere is t. 
You can swim from a square to another 4-directionally adjacent square if and 
only if the elevation of both squares individually are at most t. 
You can swim infinite distances in zero time. Of course, you must stay within 
the boundaries of the grid during your swim.

Return the least time until you can reach the bottom right square (n - 1, n - 1) 
if you start at the top left square (0, 0).

Input: grid = [[0,2],[1,3]]
Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have
a higher elevation than t = 0.
You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.
*/
public class SwimInRisingWater {
    public int swimInWater(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(a[2], b[2]));
        pq.offer(new int[] { 0, 0, grid[0][0] });

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int i = cur[0];
            int j = cur[1];
            int cost = cur[2];

            if (visited[i][j]) {
                continue;
            }

            if (i == m - 1 && j == n - 1) {
                return cost;
            }

            for (int[] dir : dirs) {
                int newI = dir[0] + i;
                int newJ = dir[1] + j;

                if (isValid(grid, visited, newI, newJ)) {
                    pq.offer(new int[] { newI, newJ, Math.max(cost, grid[newI][newJ]) });
                }
            }
            visited[i][j] = true;
        }

        return -1;
    }

    private boolean isValid(int[][] grid, boolean[][] visited, int i, int j) {
        return i >= 0 && j >= 0 && i < grid.length && j < grid[0].length && !visited[i][j];
    }
}
