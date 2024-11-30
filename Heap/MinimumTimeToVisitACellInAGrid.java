package Heap;

import java.util.PriorityQueue;

/*
https://leetcode.com/problems/minimum-time-to-visit-a-cell-in-a-grid/description/

You are given a m x n matrix grid consisting of non-negative integers
where grid[row][col] represents the minimum time required to be able 
to visit the cell (row, col), which means you can visit the cell (row, col)
only when the time you visit it is greater than or equal to grid[row][col].

You are standing in the top-left cell of the matrix in the 0th second, 
and you must move to any adjacent cell in the four directions: up, down, 
left, and right. Each move you make takes 1 second.

Return the minimum time required in which you can visit the bottom-right cell of the matrix.
If you cannot visit the bottom-right cell, then return -1.

Input: grid = [[0,1,3,2],[5,1,2,5],[4,3,8,6]]
Output: 7
Explanation: One of the paths that we can take is the following:
- at t = 0, we are on the cell (0,0).
- at t = 1, we move to the cell (0,1). It is possible because grid[0][1] <= 1.
- at t = 2, we move to the cell (1,1). It is possible because grid[1][1] <= 2.
- at t = 3, we move to the cell (1,2). It is possible because grid[1][2] <= 3.
- at t = 4, we move to the cell (1,1). It is possible because grid[1][1] <= 4.
- at t = 5, we move to the cell (1,2). It is possible because grid[1][2] <= 5.
- at t = 6, we move to the cell (1,3). It is possible because grid[1][3] <= 6.
- at t = 7, we move to the cell (2,3). It is possible because grid[2][3] <= 7.
The final time is 7. It can be shown that it is the minimum time possible.

dijkstra - minimum time at each level
 */
public class MinimumTimeToVisitACellInAGrid {
/*
    Let's say time for target cell is 4 and current time is 2, difference = 2 (even).
        Move to prev cell, time = 3
        Move to curr cell, time = 4
        Move to target cell, time = 5.
        Hence we reach target cell with time: target cell time + 1 when difference between target cell time and curr cell time is even.
        Let's say time for target cell is 5 and current time is 2, difference = 3 (odd).
        Move to prev cell, time = 3
        Move to curr cell, time = 4
        Move to target cell, time = 5.
        Hence we reach target cell with time: target cell time when difference between target cell time and curr cell time is odd.
        max(need to wait, not)
*/

    public int minimumTime(int[][] grid) {
        if (grid[0][1] > 1 && grid[1][0] > 1) return -1;
        
        int m = grid.length, n = grid[0].length;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{grid[0][0], 0, 0});
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int time = curr[0], row = curr[1], col = curr[2];
            
            if (row == m - 1 && col == n - 1) return time;
            if (visited[row][col]) continue;
            visited[row][col] = true;
            
            for (int[] dir : dirs) {
                int r = row + dir[0], c = col + dir[1];
                if (r < 0 || r >= m || c < 0 || c >= n || visited[r][c]) continue;
                int wait = ((grid[r][c] - time) % 2 == 0) ? 1 : 0;
                pq.offer(new int[]{Math.max(grid[r][c] + wait, time + 1), r, c});
            }
        }
        return -1;
    }
}
