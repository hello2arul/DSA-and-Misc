package DP;

import java.util.Arrays;

/*
CourseSchedule 1, 2 -> topoSorting based
https://leetcode.com/problems/course-schedule-iii/description/ 
 
There are n different online courses numbered from 1 to n. You are given an array courses where courses[i] = [durationi, lastDayi] 
indicate that the ith course should be taken continuously for durationi days and must be finished before or on lastDayi.

You will start on the 1st day and you cannot take two or more courses simultaneously.

Return the maximum number of courses that you can take.

Example 1:

Input: courses = [[100,200],[200,1300],[1000,1250],[2000,3200]]
Output: 3
Explanation: 
There are totally 4 courses, but you can take 3 courses at most:
First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day. 
Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day. 
The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
 */

public class CourseScheduleIII {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> Integer.compare(a[1], b[1]));
        return dfs(courses, 0, 0, 0);
    }

    // optimize it using cache
    private int dfs(int[][] courses, int idx, int res, int days) {
        if (idx >= courses.length)
            return 0;
        int taken = 0;

        if (days + courses[idx][0] <= courses[idx][1])
            taken = 1 + dfs(courses, idx + 1, res + 1, days + courses[idx][0]);

        return Math.max(taken, dfs(courses, idx + 1, res, days));
    }
}
