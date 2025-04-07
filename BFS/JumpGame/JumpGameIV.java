package BFS.JumpGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
https://leetcode.com/problems/jump-game-iv/description/

Given an array of integers arr, you are initially positioned at the first index of the array.

In one step you can jump from index i to index:

i + 1 where: i + 1 < arr.length.
i - 1 where: i - 1 >= 0.
j where: arr[i] == arr[j] and i != j.
Return the minimum number of steps to reach the last index of the array.

Example 1:

Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
Output: 3
Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. 
Note that index 9 is the last index of the array

*/
public class JumpGameIV {
    
    public int minJumps(int[] arr) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        int depth = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        visited[0] = true;
        Map<Integer, List<Integer>> valToIdx = new HashMap<>();

        for (int i = 0; i < n; i++) {
            valToIdx.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                int cur = q.poll();
                if (cur == n - 1) {
                    return depth;
                }
                if (cur + 1 < n && !visited[cur + 1]) {
                    q.offer(cur + 1);
                    visited[cur + 1] = true;
                } 
                if (cur - 1 >= 0 && !visited[cur - 1]) {
                    q.offer(cur - 1);
                    visited[cur - 1] = true;
                }

                for (int idx : valToIdx.getOrDefault(arr[cur], new ArrayList<>())) {
                    if (idx != cur && !visited[idx]) {
                        visited[idx] = true;
                        q.offer(idx);
                    }
                }
                valToIdx.get(arr[cur]).clear();
            }
            
            depth++;
        }
        return -1;
    }
}
