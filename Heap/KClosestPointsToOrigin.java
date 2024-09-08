package Heap;

import java.util.Queue;
import java.util.PriorityQueue;

/*
https://leetcode.com/problems/k-closest-points-to-origin/
naive: sort
*/
public class KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][2];
        Queue<int[]> maxHeap = new PriorityQueue<int[]>(
            (p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
        for(int[] point : points) {
            maxHeap.add(point);
            if(maxHeap.size() > K)
                maxHeap.remove();
        }
        for(int i = 0; i < K; i++)
            res[i] = maxHeap.remove();
        return res;
    }
}
