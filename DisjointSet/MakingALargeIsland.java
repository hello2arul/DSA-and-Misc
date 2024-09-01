package DisjointSet;

import java.util.*;

/*
https://leetcode.com/problems/making-a-large-island/

You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.

Return the size of the largest island in grid after applying this operation.

An island is a 4-directionally connected group of 1s.

Input: grid = [[1,1],[1,0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
Example 3:

Input: grid = [[1,1],[1,1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.
*/
public class MakingALargeIsland {
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        DisjointSet ds = new DisjointSet(n * n);
        int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        int res = 0;

        // Union adjacent 1's in the grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int[] dir : dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];

                        if (isValid(x, y, n) && grid[x][y] == 1) {
                            ds.union(getHash(i, j, n), getHash(x, y, n));
                        }
                    }
                }
            }
        }

        // Calculate the maximum possible island size by flipping each 0 to 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> seen = new HashSet<>();
                    int size = 1; // Start with the flipped cell itself

                    for (int[] dir : dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];

                        if (isValid(x, y, n) && grid[x][y] == 1) {
                            int uParent = ds.find(getHash(x, y, n));
                            if (!seen.contains(uParent)) {
                                seen.add(uParent);
                                size += ds.size[uParent];
                            }
                        }
                    }
                    res = Math.max(res, size);
                }
            }
        }

        return res == 0 ? n * n : res;
    }

    private int getHash(int i, int j, int n) {
        return i * n + j;
    }

    private boolean isValid(int i, int j, int n) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }
}

class DisjointSet {
    int[] parent;
    int[] size;

    DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1; // Each cell initially represents an island of size 1
        }
    }

    public int find(int u) {
        if (u != parent[u]) {
            parent[u] = find(parent[u]); // Path compression
        }
        return parent[u];
    }

    public boolean union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rootU == rootV) return false;

        if (size[rootU] >= size[rootV]) {
            parent[rootV] = rootU;
            size[rootU] += size[rootV];
        } else {
            parent[rootU] = rootV;
            size[rootV] += size[rootU];
        }
        return true;
    }
}
