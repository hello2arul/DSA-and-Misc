package Heap;

import java.util.PriorityQueue;
import java.util.Queue;

/*
https://leetcode.com/problems/trapping-rain-water-ii/

Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D 
elevation map, return the volume of water it can trap after raining.


Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
Output: 4
Explanation: After the rain, water is trapped between the blocks.
We have two small ponds 1 and 3 units trapped.
The total volume of water trapped is 4.

Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
Output: 10

Note: see picture for better understanding
 */
public class TrappingRainWaterII {

    class Cell implements Comparable<Cell> {
        int row;
        int col;
        int height;

        Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }

        public int compareTo(Cell other) {
            return Integer.compare(this.height, other.height);
        }
    }

    // O(log(m * n)), O(m * n)
    public int trapRainWater(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int res = 0;
        int max = 0;
        Queue<Cell> minHeap = new PriorityQueue<>();
        boolean[][] visited = new boolean[m][n];
        int[][] dirs = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        for (int i = 0; i < m; i++) {
            visited[i][0] = true;
            visited[i][n - 1] = true;
            minHeap.offer(new Cell(i, 0, heights[i][0]));
            minHeap.offer(new Cell(i, n - 1, heights[i][n - 1]));
        }

        for (int i = 1; i < n - 1; i++) {
            visited[0][i] = true;
            visited[m - 1][i] = true;
            minHeap.offer(new Cell(0, i, heights[0][i]));
            minHeap.offer(new Cell(m - 1, i, heights[m - 1][i]));
        }

        while (!minHeap.isEmpty()) {
            Cell cur = minHeap.poll();
            int row = cur.row;
            int col = cur.col;
            int height = cur.height;

            for (int[] dir : dirs) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow > 0 && newRow < m - 1 && newCol > 0 && newCol < n - 1 &&
                        !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    res += Math.max(0, height - heights[newRow][newCol]);
                    minHeap.offer(new Cell(newRow, newCol, Math.max(
                            heights[newRow][newCol], height)));
                }
            }
        }
        return res;
    }
}
