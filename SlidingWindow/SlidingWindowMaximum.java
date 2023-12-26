package SlidingWindow;

import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/sliding-window-maximum/description/
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        int idx = 0;
        Queue<Integer> maxHeap = new PriorityQueue<>(
            (i, j) -> {return Integer.compare(nums[j], nums[i]);}
        );

        for(int i = 0; i < k; i++) {
            maxHeap.offer(i);
        }
        for(int i = k; i < nums.length; i++) {
            if(maxHeap.size() >= k) {
                res[idx++]  = nums[maxHeap.peek()];
            }
            while(!maxHeap.isEmpty() && maxHeap.peek() <= i - k) 
                maxHeap.poll();
            maxHeap.offer(i);
        }
        res[idx] = nums[maxHeap.peek()];
        return res;
    }
}
