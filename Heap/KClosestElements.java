package Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
 * https://leetcode.com/problems/find-k-closest-elements/
 * 
 */
public class KClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num: arr) {
            if (minHeap.size() < k)
                minHeap.offer(num);
            else if (Math.abs(num - x) < Math.abs(minHeap.peek() - x)) {
                minHeap.poll();
                minHeap.offer(num);
            }
        }
        while (!minHeap.isEmpty()) {
            res.add(minHeap.poll());
        }

        return res;
    }
}
