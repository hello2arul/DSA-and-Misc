package Heap;

/*
 * https://neetcode.io/problems/meeting-schedule-ii
Given an array of meeting time interval objects consisting of start and end times 
[[start_1,end_1],[start_2,end_2],...] (start_i < end_i), 
find the minimum number of days required to schedule all meetings without any conflicts.

Example 1:

Input: intervals = [(0,40),(5,10),(15,20)]

Output: 2
 */
public class MeetingRoomsII {
    public static int minMeetingRooms(List<Interval>  intervals) {
        // Edge case: if no intervals, return 0 days
        if (intervals == null || intervals.isEmpty()) {
            return 0;
        }

        // Sort intervals by start time
        intervals.sort(Comparator.comparingInt(a -> a.start));

        // Min-heap to keep track of the end times of meetings scheduled
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Process each interval
        for (Interval interval : intervals) {
            int start = interval.start;
            int end = interval.end;

            // If the heap is not empty and the earliest end time is less than or equal to the current start time,
            // we can reuse the day by updating its end time
            if (!minHeap.isEmpty() && minHeap.peek() <= start) {
                minHeap.poll(); // Remove the end time of the day that is now available
            }

            // Add the current meeting's end time to the heap
            minHeap.offer(end);
        }

        // The number of days needed is the size of the heap
        return minHeap.size();
    }

}
