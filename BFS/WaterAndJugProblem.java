package BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * https://leetcode.com/problems/water-and-jug-problem/
You are given two jugs with capacities x liters and y liters. You have an infinite water supply.
Return whether the total amount of water in both jugs may reach target using the following operations:

Fill either jug completely with water.
Completely empty either jug.
Pour water from one jug into another until the receiving jug is full, or the transferring jug is empty.

*/
public class WaterAndJugProblem {
      public boolean canMeasureWater(int x, int y, int target) {
        if (x + y == target)
            return true;
        if (x + y < target)
            return false;
        
        Set<String> visited = new HashSet<>();
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x, y});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int i = cur[0], j = cur[1];
            String key = i + "," + j;
            
            if (!visited.contains(key)) {
                if (i + j == target)
                    return true;
                visited.add(key);
                q.offer(new int[] {0, j});
                q.offer(new int[] {i, 0});
                q.offer(new int[] {x, j});
                q.offer(new int[] {i, y});
                 // Pour jug x into jug y
                int pourToY = Math.min(i, y - j);
                q.offer(new int[] {i - pourToY, j + pourToY});
                
                // Pour jug y into jug x
                int pourToX = Math.min(j, x - i);
                q.offer(new int[] {i + pourToX, j - pourToX});
            }

        }
        return false;
    }
}
