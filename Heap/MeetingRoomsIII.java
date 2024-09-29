package Heap;

/*
 * https://leetcode.com/problems/meeting-rooms-iii/
 * You are given an integer n. There are n rooms numbered from 0 to n - 1.

You are given a 2D integer array meetings where meetings[i] = [starti, endi] means that a
meeting will be held during the half-closed time interval [starti, endi). All the values of starti are unique.

Meetings are allocated to rooms in the following manner:

Each meeting will take place in the unused room with the lowest number.
If there are no available rooms, the meeting will be delayed until a room becomes free. The delayed meeting should have the same duration as the original meeting.
When a room becomes unused, meetings that have an earlier original start time should be given the room.

Return the number of the room that held the most meetings. If there are multiple rooms, return the room with the lowest number.

A half-closed interval [a, b) is the interval between a and b including a and not including b.
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MeetingRoomsIII {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        int[] count = new int[n];
        PriorityQueue<int[]> engaged = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        PriorityQueue<Integer> unused = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            unused.offer(i);
        }
        for (int[] meeting: meetings) {
            int start = meeting[0], end = meeting[1];
            while (!engaged.isEmpty() && engaged.peek()[1] <= start) {
                int room = engaged.poll()[0];
                unused.offer(room);
            }
            if (unused.size() > 0) {
                int room = unused.poll();
                count[room]++;
                engaged.offer(new int[] {room, end});
            } else {
                int[] cur = engaged.poll();
                int room = cur[0], curEnd = cur[1];
                count[room]++;
                int newEnd = curEnd + end - start;
                engaged.offer(new int[] {room, newEnd});
            }
        }
        int maxRoomId = 0;
        for (int i = 1; i < n; i++) {
            if (count[i] > count[maxRoomId])
                maxRoomId = i;
        }
        return maxRoomId;
    }
}
