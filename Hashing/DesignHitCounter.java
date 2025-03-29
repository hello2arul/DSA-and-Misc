package Hashing;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.ca/2016-11-26-362-Design-Hit-Counter/
Google

Design a hit counter which counts the number of hits received in the past 5 minutes (i.e., the past 300
seconds).

Your system should accept a timestamp parameter (in seconds granularity), and you may assume that calls are
being made to the system in chronological order (i.e., timestamp is monotonically increasing). 
Several hits may arrive roughly at the same time.

Input
["HitCounter", "hit", "hit", "hit", "getHits", "hit", "getHits", "getHits"]
[[], [1], [2], [3], [4], [300], [300], [301]]
Output
[null, null, null, null, 3, null, 4, 3]

Explanation
HitCounter hitCounter = new HitCounter();
hitCounter.hit(1);       // hit at timestamp 1.
hitCounter.hit(2);       // hit at timestamp 2.
hitCounter.hit(3);       // hit at timestamp 3.
hitCounter.getHits(4);   // get hits at timestamp 4, return 3.
hitCounter.hit(300);     // hit at timestamp 300.
hitCounter.getHits(300); // get hits at timestamp 300, return 4.
hitCounter.getHits(301); // get hits at timestamp 301, return 3.

Follow up: What if the number of hits per second could be huge? Does your design scale?

*/
public class DesignHitCounter {
    
     private Queue<Integer> hits;

    public DesignHitCounter() {
        hits = new LinkedList<>();
    }

    // Record a hit at the given timestamp
    public void hit(int timestamp) {
        hits.offer(timestamp);
    }

    // Return the number of hits in the past 300 seconds
    public int getHits(int timestamp) {
        // Remove hits that are older than 300 seconds
        while (!hits.isEmpty() && hits.peek() <= timestamp - 300) {
            hits.poll();
        }
        return hits.size();
    }
}
