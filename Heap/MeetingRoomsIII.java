

/*
 * https://leetcode.com/problems/meeting-rooms-iii/
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
}
