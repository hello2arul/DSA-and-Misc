package DFS;

/*
https://leetcode.com/problems/regions-cut-by-slashes/
Google

An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a
'/', '\', or blank space ' '. These characters divide the square into contiguous regions.

Given the grid grid represented as a string array, return the number of regions.

Note that backslash characters are escaped, so a '\' is represented as '\\'.

Input: grid = [" /","/ "]
Output: 2

Input: grid = [" /","  "]
Output: 1

Input: grid = ["/\\","\\/"]
Output: 5
Explanation: Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.
 */

public class RegionsCutBySlashes {
    public int regionsBySlashes(String[] grid) {
        int n = grid.length, m = grid[0].length();
        int cnt = 0;
        int[][] g = new int[n * 3][m * 3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i].charAt(j) == '/') {
                    g[i * 3][j * 3 + 2] = 1;
                    g[i * 3 + 1][j * 3 + 1] = 1;
                    g[i * 3 + 2][j * 3] = 1;
                } else if (grid[i].charAt(j) == '\\') {
                    g[i * 3][j * 3] = 1;
                    g[i * 3 + 1][j * 3 + 1] = 1;
                    g[i * 3 + 2][j * 3 + 2] = 1;
                }
            }
        }
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[0].length; j++) {
                if (g[i][j] == 0) {
                    dfs(g, i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private void dfs(int[][] g, int i, int j) {
        int n = g.length, m = g[0].length;
        if (i < 0 || i >= n || j < 0 || j >= m || g[i][j] == 1)
            return;
        g[i][j] = 1;
        int d[] = { 0, -1, 0, 1, 0 };
        for (int k = 0; k < 4; k++) {
            dfs(g, i + d[k], j + d[k + 1]);
        }
    }
}
