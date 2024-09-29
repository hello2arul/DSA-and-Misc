package Searching;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/my-calendar-ii/
You are implementing a program to use as your calendar. We can add a new event
if adding the event will not cause a triple booking.

A triple booking happens when three events have some non-empty intersection
(i.e., some moment is common to all the three events.).

The event can be represented as a pair of integers start and end that represents 
a booking on the half-open interval [start, end), the range of real numbers x such 
that start <= x < end.

Implement the MyCalendarTwo class:

MyCalendarTwo() Initializes the calendar object.
boolean book(int start, int end) Returns true if the event can be added to 
the calendar successfully without causing a triple booking. Otherwise, return false 
and do not add the event to the calendar.

*/
public class MyCalenderII {
    List<int[]> one;
    List<int[]> two;

    public MyCalenderII() {
        one = new ArrayList<>();
        two = new ArrayList<>();    
    }
    
    public boolean book(int start, int end) {
        for (int[] arr: two) {
            if (arr[0] < end && start < arr[1])
                return false;
        }
        for (int[] arr: one) {
            if (arr[0] < end && start < arr[1])
                two.add(new int[] {Math.max(start, arr[0]), Math.min(end, arr[1])});
        }
        one.add(new int[] {start, end});
        return true;
    }

    private TreeMap<Integer, Integer> map;    
        
    public boolean bookLineSweep(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int count = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            count += entry.getValue();
            if(count > 2) {
                map.put(start, map.get(start) - 1);
                if(map.get(start) == 0) {
                    map.remove(start);
                }
                map.put(end, map.get(end) + 1);
                if(map.get(end) == 0) {
                    map.remove(end);
                }
                return false;
            }
        }
        return true;
    }
}
