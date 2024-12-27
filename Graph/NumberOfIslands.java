package Graph;
/*
 * https://leetcode.com/problems/number-of-islands/description/
 */

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {

    public int numIslandsDS(char[][] grid) {
        DisjointSet ds = new DisjointSet(grid);       
        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    for (int[] dir : dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        
                        if (isValidDir(x, y, grid)) {
                            ds.union(x * n + y, i * n + j);
                        }
                    }
                }
            }
        } 
        return ds.count;
    }
    
    private boolean isValidDir(int i, int j, char[][] grid) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length 
               && grid[i][j] == '1'; 
    }

    public int numIslands(char[][] grid) {
        int res = 0;
        if(grid == null)
            return res;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] dirs = {{0, 1}, {-1, 0}, {1, 0}, {0, - 1}};

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j] && grid[i][j] == '1') {
                    res++;
                    // dfs(grid, i, j, visited, dirs);
                    visited[i][j] = true;
                    Queue<int[]> q = new LinkedList<>();

                    q.offer(new int[] {i, j});

                    while(!q.isEmpty()) {
                        int[] cur = q.poll();
                        for(int[] dir: dirs) {
                            int x = cur[0] + dir[0];
                            int y = cur[1] + dir[1];
                            if(!isInValidDir(grid, visited,x, y)) {
                                visited[x][y] = true;
                                q.offer(new int[] {x,  y});
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int i, int j, boolean[][] visited, int[][] dirs) {
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1' || 
        visited[i][j])
            return;
        visited[i][j] = true;

        for(int[] dir: dirs) {
            dfs(grid, i + dir[0], j + dir[1], visited, dirs);
        }
    }

    private boolean isInValidDir(char[][] grid, boolean[][] visited, int i, int j) {
        return i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1' || 
                visited[i][j];
    }
}

class DisjointSet {

    int size;
    int[] parent;
    int[] rank;
    int count;

    DisjointSet(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        parent = new int[m * n];
        rank = new int[m * n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    int idx = i * n + j;
                    parent[idx] = idx;
                    rank[idx] = idx; 
                    count++;
                }
            }
        }
    }

    public int find(int u) {
        if (u != parent[u]) {
            parent[u] = find(parent[u]);
        }
        return parent[u];
    }

    public boolean union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rootU == rootV)
            return false;
        if (rank[rootU] >= rank[rootV]) {
            parent[rootV] = rootU;
            rank[rootU] += rank[rootV];
        } else {
            parent[rootU] = rootV;
            rank[rootV] += rank[rootU]; 
        }
        count--;
        return true;
    }
}
