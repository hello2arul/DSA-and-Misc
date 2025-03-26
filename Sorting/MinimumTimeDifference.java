package Sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
https://leetcode.com/problems/minimum-time-difference
Google

Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference 
between any two time-points in the list.

Example 1:

Input: timePoints = ["23:59","00:00"]
Output: 1
Example 2:

Input: timePoints = ["00:00","23:59","00:00"]
Output: 0
 */
public class MinimumTimeDifference {
    public int findMinDifference(List<String> timePoints) {
        List<Integer> time = new ArrayList<>();
        int min = Integer.MAX_VALUE;

        for (String s : timePoints) {
            int hour = 60 * Integer.parseInt(s.substring(0, 2));
            time.add(hour + Integer.parseInt(s.substring(3)));
        }
        Collections.sort(time);

        for (int i = 1; i < time.size(); i++) {
            min = Math.min(min, time.get(i) - time.get(i - 1));
        }
        int circular = 1440 - time.get(time.size() - 1) + time.get(0);

        return Math.min(min, circular);
    }
}
