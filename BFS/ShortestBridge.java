package BFS;


/**
 * https://leetcode.com/problems/shortest-bridge/
 * find 1 island and add its coordinates to queue
 * bfs to its neighbours till you find 1 and BFS's current level
 * would be the minimum number of 0 that you crossed to visit the next island
 */
public class ShortestBridge {
    private int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public int shortestBridge(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        boolean foundIsland = false;

        for (int i = 0; i < grid.length && !foundIsland; i++) {
            for (int j = 0; j < grid[i].length && !foundIsland; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, q);
                    foundIsland = true;
                }
            }
        }

        int level = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] cur = q.poll();
                int i = cur[0], j = cur[1];

                for (int[] dir: dirs) {
                    int iNew = i + dir[0];
                    int jNew = j + dir[1];

                    if (isValid(grid, iNew, jNew)) {
                        if (grid[iNew][jNew] == 1)
                            return level;
                        grid[iNew][jNew] = -1;
                        q.offer(new int[] {i + dir[0], j + dir[1]});
                    }
                }
            }
            level++;
        }
        return -1;
    }

    private void dfs(int[][] grid, int i, int j, Queue<int[]> q) {
        if (!isValid(grid, i, j) || grid[i][j] == 0)
            return;
        grid[i][j] = -1;
        q.offer(new int[] {i, j});
        for (int[] dir: dirs) {
            dfs(grid, i + dir[0], j + dir[1], q);
        }
    }

    private boolean isValid(int[][] grid, int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[i].length
                && grid[i][j] != -1;
    }
}
