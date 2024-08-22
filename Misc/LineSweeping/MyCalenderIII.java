package Misc.LineSweeping;

import java.util.Map;
import java.util.TreeMap;

/*
https://leetcode.com/problems/my-calendar-iii/description/

A k-booking happens when k events have some non-empty intersection 
(i.e., there is some time that is common to all k events.)

You are given some events [startTime, endTime), after each given event,
 return an integer k representing the maximum k-booking between all the previous events.

Implement the MyCalendarThree class:

MyCalendarThree() Initializes the object.
int book(int startTime, int endTime) Returns an integer k representing
the largest integer such that there exists a k-booking in the calendar.

["MyCalendarThree", "book", "book", "book", "book", "book", "book"]
[[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
Output
[null, 1, 1, 2, 3, 3, 3]

Imagine a straight line and start marking the points, you'll find the overlapping count.
--------------------
10 20     50  60 etc.,
*/
public class MyCalenderIII {
    
    private Map<Integer, Integer> lines;

    public MyCalenderIII() {
        lines = new TreeMap<>();
    }
    
    public int book(int start, int end) {
        lines.put(start, lines.getOrDefault(start, 0) + 1);
        lines.put(end, lines.getOrDefault(end, 0) - 1);
        int mx = 0, cnt = 0;

        for (int x : lines.values()) {
            cnt += x;
            mx = Math.max(mx, cnt);
        }
        return mx;
    } 
}
