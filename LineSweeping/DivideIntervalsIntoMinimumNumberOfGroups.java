package LineSweeping;

import java.util.Map;
import java.util.TreeMap;

/*
 * https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/description/
 * 
 */

public class DivideIntervalsIntoMinimumNumberOfGroups {
     public int minGroups(int[][] intervals) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int[] interval : intervals) {
            treeMap.put(interval[0], treeMap.getOrDefault(interval[0], 0) + 1);
            // + 1 since [1, 5], [5, 10] are overlaps
            treeMap.put(interval[1] + 1, treeMap.getOrDefault(interval[1] + 1, 0) - 1);
        }
        int overlaps = 0;
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            count += entry.getValue();
            overlaps = Math.max(overlaps, count);
        }
        return overlaps;
    }
}
