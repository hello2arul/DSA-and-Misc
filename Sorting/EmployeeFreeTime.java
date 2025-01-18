package Sorting;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.ca/all/759.html
Google

We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

Example 1:

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation:
There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.
 

Example 2:

Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]
 

(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, 
not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, 
and schedule[0][0][0] is not defined.)

Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

Note:

schedule and schedule[i] are lists with lengths in range [1, 50].
0 <= schedule[i].start < schedule[i].end <= 10^8.
 */
public class EmployeeFreeTime {

    class Interval {
        public int start;
        public int end;

        public Interval() {
        }

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    };

    // similar to MergeIntervals.java
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> result = new ArrayList<>();
        List<Interval> allIntervals = new ArrayList<>();

        // Flatten the schedule
        for (List<Interval> employee : schedule) {
            allIntervals.addAll(employee);
        }

        // Sort the intervals by start time
        allIntervals.sort((a, b) -> a.start - b.start);

        // Merge intervals
        Interval prev = allIntervals.get(0);
        for (int i = 1; i < allIntervals.size(); i++) {
            Interval curr = allIntervals.get(i);
            if (curr.start <= prev.end) {
                // Merge intervals
                prev.end = Math.max(prev.end, curr.end);
            } else {
                // Add the free time
                result.add(new Interval(prev.end, curr.start));
                prev = curr;
            }
        }

        return result;
    }
}
