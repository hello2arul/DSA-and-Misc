package Sorting;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/*
https://leetcode.com/discuss/interview-question/346621/Google-or-Phone-screen-or-Least-amount-of-changes-to-order-an-array-in-ascending-order

minimum-number-of-operations-to-sort-a-binary-tree-by-level - https://leetcode.com/problems/minimum-number-of-operations-to-sort-a-binary-tree-by-level/
Google


Selection sort minimizes swaps. So there is a O(N^2) solution. 
the problem is that selection sort keeps finding the min element in
the unsorted portion of the array. This leads to finding min by O(N) in each iteration.
So how can we speed it up? We can use a heap to give us the smallest 
element all the time when we need it. 
 */
public class MinSwapsRequiredToSort {
    
    public int minSwaps(int[] arr) {
        int minSwaps = 0;
        int n = arr.length;
        Map<Integer, Integer> numToIdx = new HashMap<>();
        Queue<Integer> minHeap = new PriorityQueue<>();

        for(int i = 0; i < n; i++) {
            numToIdx.put(arr[i], i);
            minHeap.offer(arr[i]);
        }

        for(int i = 0; i < n; i++) {
            int min = minHeap.poll();
            if(min != arr[i]) {
                minSwaps++;
                int idx = numToIdx.get(min);
                Misc.swap(arr, i, idx);
                numToIdx.put(arr[i], i);
                numToIdx.put(arr[idx], idx);
            }
        }

        return minSwaps;
    }
}
