package BFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Google
 * https://leetcode.com/problems/pacific-atlantic-water-flow/description/

There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges,
and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] 
represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west
 if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) 
to both the Pacific and Atlantic oceans.

*/
public class PacificAtlanticWaterFlow {
     int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        int m = heights.length;
        int n = heights[0].length;
        Queue<int[]> pq = new LinkedList<>();
        Queue<int[]> aq = new LinkedList<>();
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            pq.offer(new int[] {i, 0});
            aq.offer(new int[] {i, n - 1});
        }

        for (int i = 0; i < n; i++) {
            pq.offer(new int[] {0, i});
            aq.offer(new int[] {m - 1, i});
        }

        bfs(heights, pq, pacific);
        bfs(heights, aq, atlantic);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }
    // O(m*n)
    private void bfs(int[][] arr, Queue<int[]> q, boolean[][] visited) {
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int i = cur[0];
            int j = cur[1];
            visited[i][j] = true;

            for (int[] dir : dirs) {
                int x = i + dir[0];
                int y = j + dir[1];

                if (isValid(arr, x, y, visited) && arr[i][j] <= arr[x][y]) {
                    q.offer(new int[] {x, y});
                }
            }
        }
    }

    public List<List<Integer>> pacificAtlanticdfs(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            dfs(heights, i, 0, pacific, Integer.MIN_VALUE);
            dfs(heights, i, n - 1, atlantic, Integer.MIN_VALUE);
        }

        for (int i = 0; i < n; i++) {
            dfs(heights, 0, i, pacific, Integer.MIN_VALUE);
            dfs(heights, m - 1, i, atlantic, Integer.MIN_VALUE);
        }


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    private void dfs(int[][] arr, int i, int j, boolean[][] visited, int height) {
        if (!isValid(arr, i, j, visited) || arr[i][j] < height)
            return;
        visited[i][j] = true;
        
        for (int[] dir : dirs) {
            dfs(arr, i + dir[0], j + dir[1], visited, arr[i][j]);
        }
    }

    private boolean isValid(int[][] arr, int x, int y, boolean[][] visited) {
        return x >= 0 && x < arr.length && y >= 0 && y < arr[0].length && !visited[x][y];
    }
}
