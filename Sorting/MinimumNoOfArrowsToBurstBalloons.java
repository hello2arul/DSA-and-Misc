package Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/description/
 * just find the overlapping intervals
 */
public class MinimumNoOfArrowsToBurstBalloons {
     public int findMinArrowShots(int[][] points) {
        if(points == null || points.length == 0)
            return 0;
        Arrays.sort(points, (a, b)-> Integer.compare(a[1], b[1]));
        List<int[]> res = new ArrayList<>();
        res.add(points[0]);
        
        for(int i = 1; i < points.length; i++) {
            int[] current = points[i];
            int[] previous = res.get(res.size() - 1);
            if(previous[1] < current[0]) {
                res.add(current);
            }
        }
        return res.size();
    }
}
