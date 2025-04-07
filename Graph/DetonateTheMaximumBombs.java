package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://leetcode.com/problems/detonate-the-maximum-bombs/
Google

You are given a list of bombs. The range of a bomb is defined as the area where its effect 
can be felt. This area is in the shape of a circle with the center as the location of the 
bomb.

The bombs are represented by a 0-indexed 2D integer array bombs where 
bombs[i] = [xi, yi, ri]. xi and yi denote the X-coordinate and Y-coordinate 
of the location of the ith bomb, whereas ri denotes the radius of its range.

You may choose to detonate a single bomb. When a bomb is detonated, 
it will detonate all bombs that lie in its range. These bombs will further detonate
 the bombs that lie in their ranges.

Given the list of bombs, return the maximum number of bombs that can be detonated 
if you are allowed to detonate only one bomb.

Input: bombs = [[2,1,3],[6,1,4]]
Output: 2
Explanation:
The above figure shows the positions and ranges of the 2 bombs.
If we detonate the left bomb, the right bomb will not be affected.
But if we detonate the right bomb, both bombs will be detonated.
So the maximum bombs that can be detonated is max(1, 2) = 2.

*/

public class DetonateTheMaximumBombs {
    
     public int maximumDetonation(int[][] bombs) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int n = bombs.length;

        for (int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long dx = bombs[i][0] - bombs[j][0];
                long dy = bombs[i][1] - bombs[j][1];
                long distanceSquared = dx * dx + dy * dy;
                long radiusSquaredI = (long) bombs[i][2] * bombs[i][2];
                long radiusSquaredJ = (long) bombs[j][2] * bombs[j][2];
                
                // Check if bomb i can trigger bomb j
                if (distanceSquared <= radiusSquaredI) {
                    adj.get(i).add(j);
                }
                // Check if bomb j can trigger bomb i
                if (distanceSquared <= radiusSquaredJ) {
                    adj.get(j).add(i);
                }
            }
        }
        int maxBombs = 0;

        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            int count = dfs(i, adj, visited);
            maxBombs = Math.max(maxBombs, count);
        }

        return maxBombs;
    }

    private int dfs(int i, Map<Integer, List<Integer>> graph, boolean[] visited) {
        visited[i] = true;
        int count = 1;

        for (int neigh : graph.getOrDefault(i, new ArrayList<>())) {
            if (!visited[neigh]) {
                count += dfs(neigh, graph, visited);
            }
        }

        return count;
    }
}
