package Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * https://leetcode.com/problems/task-scheduler/description/
 * You are given an array of CPU tasks, each labeled with a letter from A to Z, and a number n. 
 * Each CPU interval can be idle or allow the completion of one task. Tasks can be completed in any order, but there's a constraint: 
 * there has to be a gap of at least n intervals between two tasks with the same label.

Return the minimum number of CPU intervals required to complete all tasks.

Input: tasks = ["A","A","A","B","B","B"], n = 2

Output: 8

Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.

Input: tasks = ["A","C","A","B","D","B"], n = 1

Output: 6

Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.
TODO:
 */
public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for(char ch: tasks) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(map.values());
        int time = 0;
        
        while (!maxHeap.isEmpty()) {
            List<Integer> temp = new ArrayList<>(n + 1);
            for (int i = 0; i < n + 1; i++) {
                if (!maxHeap.isEmpty()) {
                    temp.add(maxHeap.remove());
                }
            }
            
            for (int i: temp) {
                if (--i > 0) {
                    maxHeap.add(i);
                }
            }
            time += maxHeap.isEmpty() ? temp.size() : n + 1;
        }
        
        return time;
    }
}
