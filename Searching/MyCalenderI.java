package Searching;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/*
 * https://leetcode.com/problems/my-calendar-i/
 * You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a double booking.

A double booking happens when two events have some non-empty intersection (i.e., some moment is common to both events.).

The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end), 
the range of real numbers x such that start <= x < end.

Implement the MyCalendar class:

MyCalendar() Initializes the calendar object.
boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a double booking.
 Otherwise, return false and do not add the event to the calendar.
 */
public class MyCalenderI {
    TreeMap<Integer, Integer> calendar;
    List<int[]> booked;

    MyCalenderI() {
        calendar = new TreeMap<>();
        booked = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start),
                next = calendar.ceilingKey(start);
        if ((prev == null || calendar.get(prev) <= start) &&
                (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }

    public boolean bookBruteForce(int start, int end) {
        for (int[] prev : booked) {
            if (start < prev[1] && end > prev[0])
                return false;
        }
        booked.add(new int[] {start, end});
        return true;
    }
}
