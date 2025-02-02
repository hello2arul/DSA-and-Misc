package DisjointSet;

import java.util.HashMap;
import java.util.Map;

import DisjointSet.DisjointSet;

/*
https://leetcode.com/problems/count-servers-that-communicate/

You are given a map of a server center, represented as a m * n integer matrix grid, 
where 1 means that on that cell there is a server and 0 means that it is no server. 
Two servers are said to communicate if they are on the same row or on the same column.

Return the number of servers that communicate with any other server.

Input: grid = [[1,0],[0,1]]
Output: 0
Explanation: No servers can communicate with others.

Input: grid = [[1,0],[1,1]]
Output: 3
Explanation: All three servers can communicate with at least one other server.
*/
public class CountServersThatCommunicate {
    
     public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        DisjointSet ds = new DisjointSet(m, n); // Initialize with rows and columns
        int res = 0;

        // Union servers in the same row
        for (int i = 0; i < m; i++) {
            int prevJ = -1;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    if (prevJ != -1) {
                        ds.union(ds.getHash(i, j, n), ds.getHash(i, prevJ, n)); // Use n here
                    }
                    prevJ = j;
                }
            }
        }

        // Union servers in the same column
        for (int j = 0; j < n; j++) {
            int prevI = -1;
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 1) {
                    if (prevI != -1) {
                        ds.union(ds.getHash(i, j, n), ds.getHash(prevI, j, n)); // Use n here
                    }
                    prevI = i;
                }
            }
        }

        // Count servers that communicate in the same component
        Map<Integer, Integer> componentSize = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int root = ds.find(ds.getHash(i, j, n)); // Use n here
                    componentSize.put(root, componentSize.getOrDefault(root, 0) + 1);
                }
            }
        }

        // Add to the result the number of servers in components of size > 1
        for (int size : componentSize.values()) {
            if (size > 1) {
                res += size;
            }
        }

        return res;
    }

    public int countServersOptimized(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] rowCount = new int[m];
        int[] colCount = new int[n];

        // Count the number of servers in each row and column
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }

        int res = 0;

        // Count the number of servers that can communicate
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && (rowCount[i] > 1 || colCount[j] > 1)) {
                    res++;
                }
            }
        }

        return res;
    }
}
