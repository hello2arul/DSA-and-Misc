package BFS.01BFS;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/description/
Google

Given an m x n grid. Each cell of the grid has a sign pointing to the next cell you 
should visit if you are currently in this cell. The sign of grid[i][j] can be:

1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])

You will initially start at the upper left cell (0, 0). 
A valid path in the grid is a path that starts from the upper left cell (0, 0) 
and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. 
The valid path does not have to be the shortest.

You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.

Return the minimum cost to make the grid have at least one valid path.
1. Dijkstra - naive
2. 01 BFS - optimized
*/
public class MinimumCostToMakeAtleast1ValidPath {
    // O(m×n)
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int dirs[][] = {{0, 1, 1}, {0, -1, 2}, {1, 0, 3}, {-1, 0, 4}};
        Deque<int[]> dq = new LinkedList<>();
        dq.offer(new int[] {0, 0, 0});

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int x = cur[0];
            int y = cur[1];
            int cost = cur[2];

            if (x == m - 1 && y == n - 1)
                return cost;

            if (grid[i][j] == 0) {
                continue;
            }

            for (int[] dir : dirs) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (isValid(grid, newX, newY)) {
                    if (grid[x][y] == dir[2])
                        dq.addFirst(new int[] {newX, newY, cost});
                    else
                        dq.addLast(new int[] {newX, newY, cost + 1});
                }
            }
            grid[x][y] = 0;
        }
        return -1;
    }

    // dijkstra
    public int minCostDijkstra(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = {{0, 1, 1}, {0, -1, 2}, {1, 0, 3}, {-1, 0, 4}};

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[2], b[2])
        );
        pq.offer(new int[] {0, 0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int i = cur[0];
            int j = cur[1];
            int cost = cur[2];

            if (i == m - 1 && j == n - 1) {
                return cost;
            }

            if (grid[i][j] == 0) {
                continue;
            }

            for (int[] dir : dirs) {
                int newI = i + dir[0];
                int newJ = j + dir[1];
                
                if (isValid(grid, newI, newJ)) {
                    int newCost = grid[i][j] == dir[2] ? cost : cost + 1;                    
                    pq.offer(new int[] {newI, newJ, newCost});
                }
            }
            grid[i][j] = 0;
        } 

        return -1;
    }

    private boolean isValid(int[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] != 0;
    }
}
