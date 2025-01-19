package Heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/*
https://leetcode.com/problems/count-of-smaller-numbers-after-self/
Google

Given an integer array nums, return an integer array counts where counts[i] is 
the number of smaller elements to the right of nums[i].

 

Example 1:

Input: nums = [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Example 2:

Input: nums = [-1]
Output: [0]
Example 3:

Input: nums = [-1,-1]
Output: [0,0]
 
 */
public class CountOfSmallerNumbersAfterSelf {
    
    // I came up with this years ago
    // O(n^2 log n)  O(n)
    public List<Integer> countSmaller(int[] nums) {
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int n = nums.length;
        Integer[] res = new Integer[n];

        for (int i = n - 1; i >= 0; i--) {
            if (maxHeap.isEmpty() || maxHeap.peek() < nums[i]) {
                res[i] = maxHeap.size();
            } else {
                List<Integer> temp = new ArrayList<>(maxHeap.size());
                while (!maxHeap.isEmpty() && maxHeap.peek() >= nums[i])
                    temp.add(maxHeap.poll());
                res[i] = maxHeap.size();
                maxHeap.addAll(temp);
            }
            maxHeap.offer(nums[i]);
        }

        return Arrays.asList(res);
    }

    // O(n log n) O(n)
    public List<Integer> countSmallerTreeMap(int[] nums) {
        int n = nums.length;
        Integer[] res = new Integer[n];
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>();
        int[] smallerCount = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            // Find the number of elements smaller than nums[i]
            int count = 0;
            for (Map.Entry<Integer, Integer> entry : sortedMap.headMap(nums[i]).entrySet()) {
                count += entry.getValue();
            }
            res[i] = count;
            // Add the current element to the sorted map
            sortedMap.put(nums[i], sortedMap.getOrDefault(nums[i], 0) + 1);
        }

        return Arrays.asList(res);
    }

    // TODO: merge sort, BST, BIT, Segment Tree
}
