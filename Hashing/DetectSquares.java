package Hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




/*
 * https://leetcode.com/problems/detect-squares/
 * You are given a stream of points on the X-Y plane. Design an algorithm that:

Adds new points from the stream into a data structure.
Duplicate points are allowed and should be treated as different points.
Given a query point, counts the number of ways to choose three points
from the data structure such that the three points and the query point 
form an axis-aligned square with positive area.
An axis-aligned square is a square whose edges are all the same length and
are either parallel or perpendicular to the x-axis and y-axis.

Implement the DetectSquares class:

DetectSquares() Initializes the object with an empty data structure.
void add(int[] point) Adds a new point point = [x, y] to the data structure.
int count(int[] point) Counts the number of ways to form axis-aligned squares with point point = [x, y] as described above.
 */
class DetectSquares {
    Map<String, Integer> count;
    List<int[]> points;

    public DetectSquares() {
        count = new HashMap<>();
        points = new ArrayList<>();
    }
    
    public void add(int[] point) {
        points.add(point);
        String key = point[0] + "," + point[1];
        count.put(key, count.getOrDefault(key, 0) + 1);
    }
      // We first need to find the diagonal of SQUARE (using p1 and p3) and 
      // then we can find out if there exist p2 and p4 and multiply their value tp get total square
    public int count(int[] point) {
        int x = point[0];
        int y = point[1];
        int res = 0;

        for (int[] p : points) {
            if (x != p[0] && y != p[1] && Math.abs(x - p[0]) == Math.abs(y - p[1])) {
                // int p1 = count.getOrDefault(p[0] + "," + p[1], 0);
                int p2 = count.getOrDefault(p[0] + "," + y, 0);
                int p3 = count.getOrDefault(x + "," + p[1], 0);
                res += ( p2 * p3);
            }
        }
        return res;
    }
}
