package LineSweeping;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/two-best-non-overlapping-events/
Google

You are given a 0-indexed 2D integer array of events where 
events[i] = [startTimei, endTimei, valuei]. 
The ith event starts at startTimei and ends at endTimei, 
and if you attend this event, you will receive a value of valuei. 
You can choose at most two non-overlapping events to attend such 
that the sum of their values is maximized.

Return this maximum sum.

Note that the start time and end time is inclusive: 
that is, you cannot attend two events where one of them starts 
and the other ends at the same time. More specifically, 
if you attend an event with end time t, the next event must start at or after t + 1.

Input: events = [[1,3,2],[4,5,2],[2,4,3]]
Output: 4
Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4

Input: events = [[1,3,2],[4,5,2],[1,5,5]]
Output: 5
Explanation: Choose event 2 for a sum of 5.

Input: events = [[1,5,3],[1,5,1],[6,6,5]]
Output: 8
Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.

*/

public class TwoBestNonOverlappingEvents {
    
    public int maxTwoEvents(int[][] events) {
        List<int[]> e = new ArrayList<>();
        
        for (int[] event : events) {
            int l = event[0], r = event[1], v = event[2];
            e.add(new int[]{l, 1, v}); 
            e.add(new int[]{r + 1, -1, v}); 
        }
        
        e.sort((a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
        
        int maxp = 0, best = 0;
        
        for (int[] event : e) {
            int l = event[0], t = event[1], v = event[2];
            if (t == 1) {
                best = Math.max(best, v + maxp); // Update best if a new event starts
            }
            if (t == -1) {
                maxp = Math.max(maxp, v); // Update maxp when an event ends
            }
        }
        
        return best;
    }
}
