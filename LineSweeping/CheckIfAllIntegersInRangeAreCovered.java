package LineSweeping;

import java.util.TreeMap;

/*
 * https://leetcode.com/problems/check-if-all-the-integers-in-a-range-are-covered/description/
 * You are given a 2D integer array ranges and two integers left and right. Each ranges[i] = [starti, endi]
 *  represents an inclusive interval between starti and endi.

Return true if each integer in the inclusive range [left, right] is covered by at least 
one interval in ranges. Return false otherwise.

An integer x is covered by an interval ranges[i] = [starti, endi] if starti <= x <= endi.

Input: ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
Output: true
Explanation: Every integer between 2 and 5 is covered:
- 2 is covered by the first range.
- 3 and 4 are covered by the second range.
- 5 is covered by the third range.
 */
public class CheckIfAllIntegersInRangeAreCovered {
    public boolean isCovered(int[][] ranges, int left, int right) {
        TreeMap<Integer, Integer> line = new TreeMap<>();

        // Populate the TreeMap with increments and decrements
        for (int[] r : ranges) {
            line.put(r[0], line.getOrDefault(r[0], 0) + 1);
            line.put(r[1] + 1, line.getOrDefault(r[1] + 1, 0) - 1);
        }

        int overlaps = 0;

        // Check coverage from 1 to right
        for (int i = 1; i <= right; ++i) {
            overlaps += line.getOrDefault(i, 0);
            // Only check the overlaps for the range [left, right]
            if (i >= left && overlaps == 0) {
                return false; // If there is a gap in coverage
            }
        }

        return true; // All points in [left, right] are covered
    }
}
