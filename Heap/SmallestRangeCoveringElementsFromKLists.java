package Heap;

import java.util.List;
import java.util.PriorityQueue;

/*
 * https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists
 
You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at least one number from each of the k lists.
Example 1:

Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
Output: [20,24]
Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
Example 2:

Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
Output: [1,1]
 */

/**
 * SmallestRangeCoveringElementsFromKLists
 */
public class SmallestRangeCoveringElementsFromKLists {

     public int[] smallestRange(List<List<Integer>> nums) {
        int start = -1;
        int end = -1;
        int range = Integer.MAX_VALUE;
        int n = nums.size();
        int max = Integer.MIN_VALUE;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));

        for (int i = 0; i < n; i++) {
            minHeap.offer(new int[] {i, 0, nums.get(i).get(0)});
            max = Math.max(max, nums.get(i).get(0));
        }

        while (minHeap.size() >= n) {
            int[] cur = minHeap.poll();
            int row = cur[0];
            int col = cur[1];
            int val = cur[2];

            if (max - val < range) {
                start = val;
                end = max;
                range = end - start;
            }

            if (col + 1 < nums.get(row).size()) {
                minHeap.offer(new int[] {row, col + 1, nums.get(row).get(col + 1)});
                max = Math.max(max, nums.get(row).get(col + 1));
            }
        }

        return new int[] {start, end};
    }
}