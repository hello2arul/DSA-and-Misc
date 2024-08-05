package Sorting;

/*
 * https://leetcode.com/problems/merge-intervals/
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        int rows = intervals.length;
        if(rows <= 1)   return intervals;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> res = new ArrayList<>(rows);
        res.add(intervals[0]);
        int[] cur = intervals[0];

        for(int i = 1; i < rows; i++) {
            if(cur[1] >= intervals[i][0]) {
                cur[1] = Math.max(cur[1], intervals[i][1]);
            } else {
                cur = intervals[i];
                res.add(cur);
            }
        }
            return res.toArray(new int[res.size()][]);
    }
}
