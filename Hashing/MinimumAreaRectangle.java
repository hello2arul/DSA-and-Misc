package Hashing;

import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/minimum-area-rectangle
Google

You are given an array of points in the X-Y plane points where points[i] = [xi, yi].

Return the minimum area of a rectangle formed from these points, with sides parallel to the X and Y axes. 
If there is not any such rectangle, return 0.

Formation:

A rectangle is defined by two diagonal points. For example, if we have two points (x1, y1) and (x2, y2), 
they can form a rectangle if the other two points (x1, y2) and (x2, y1) also exist in the set of points.
*/
public class MinimumAreaRectangle {
    public int minAreaRect(int[][] points) {
        Set<String> pointSet = new HashSet<>();

        for (int[] point : points) {
            String key = point[0] + "," + point[1];
            pointSet.add(key);
        }
        int n = points.length;
        int minArea = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];

                if (x1 != x2 && y1 != y2) {
                    if (pointSet.contains(x1 + "," + y2) && pointSet.contains(x2 + "," + y1)) {
                        minArea = Math.min(minArea, Math.abs(x1 - x2) * Math.abs(y1 - y2));
                    }
                }
            }
        }
        return minArea != Integer.MAX_VALUE ? minArea : 0;
    }
}
