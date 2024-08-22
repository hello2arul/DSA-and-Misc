package BFS;

import java.util.LinkedList;
import java.util.Queue;

/*
 * https://leetcode.com/problems/shortest-path-in-binary-matrix
 */
public class ShortestPathInABinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int path = 0;
        Queue<int[]> q = new LinkedList<>();
        int[][] dirs = {
            {0, 1}, {1, 0}, {1, 1}, {0, -1}, 
            {-1, 0}, {-1, -1}, {-1, 1}, {1, -1} 
        };
        int m = grid.length, n = grid[0].length;
        if(grid[0][0] == 0)
            q.offer(new int[] {0, 0});
        grid[0][0] = 1;

        while(!q.isEmpty()) {
            path++;
            for(int k = q.size(); k > 0; k--) {
                int[] cur = q.poll();
                int i = cur[0];
                int j = cur[1];
                if(i == m - 1 && j == n - 1) {
                    return path;
                }

                for(int[] dir: dirs) {
                    if(isValidDir(grid, i + dir[0], j + dir[1], m, n)) {
                        q.offer(new int[] {i + dir[0], j + dir[1]});
                        grid[i + dir[0]][j + dir[1]] = 1;
                    }
                }
            }
        }
        return -1;
    }

    private boolean isValidDir(int[][] grid, int i, int j, int m, int n) {
        return i >= 0 && j >= 0 && i < m && j < n && grid[i][j] == 0;
    }
}
