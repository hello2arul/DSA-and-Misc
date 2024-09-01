package Graph;

import java.util.*;

/*
https://leetcode.com/problems/parallel-courses-iii/description/

You are given an integer n, which indicates that there are n courses labeled from 1 to n. 
You are also given a 2D integer array relations where relations[j] = [prevCoursej, nextCoursej] 
denotes that course prevCoursej has to be completed before course nextCoursej (prerequisite relationship).
 Furthermore, you are given a 0-indexed integer array time where time[i] denotes 
 how many months it takes to complete the (i+1)th course.

You must find the minimum number of months needed to complete all the courses following these rules:

You may start taking a course at any time if the prerequisites are met.
Any number of courses can be taken at the same time.
Return the minimum number of months needed to complete all the courses.

Note: The test cases are generated such that it is possible to complete every course (i.e., the graph is a directed acyclic graph).

Input: n = 3, relations = [[1,3],[2,3]], time = [3,2,5]
Output: 8
Explanation: The figure above represents the given graph and the time required to complete each course. 
We start course 1 and course 2 simultaneously at month 0.
Course 1 takes 3 months and course 2 takes 2 months to complete respectively.
Thus, the earliest time we can start course 3 is at month 3, and the total time required is 3 + 5 = 8 months.
*/

public class ParallelCoursesIII {
    Map<Integer, List<Integer>> adj = new HashMap<>();
    Map<Integer, Integer> indegrees = new HashMap<>();
    Map<Integer, Integer> completionTime = new HashMap<>();

    public int minimumTime(int n, int[][] relations, int[] time) {
        // Build the adjacency list and indegree map
        for (int[] relation : relations) {
            int u = relation[0];
            int v = relation[1];
            adj.putIfAbsent(u, new ArrayList<>());
            adj.get(u).add(v);
            indegrees.putIfAbsent(v, 0);
            indegrees.put(v, indegrees.get(v) + 1);
            indegrees.putIfAbsent(u, 0); // Ensure all nodes are in indegrees map
        }
        
        // Initialize the queue with nodes of zero indegree and their completion times
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegrees.getOrDefault(i, 0) == 0) {
                q.offer(i);
                completionTime.put(i, time[i - 1]); // Time to complete task i
            }
        }
        
        // Process nodes and update their dependencies' completion times
        while (!q.isEmpty()) {
            int u = q.poll();
            int currentTime = completionTime.get(u);

            for (int v : adj.getOrDefault(u, new ArrayList<>())) {
                completionTime.put(v, Math.max(completionTime.getOrDefault(v, 0), currentTime + time[v - 1]));
                indegrees.put(v, indegrees.get(v) - 1);

                if (indegrees.get(v) == 0) {
                    q.offer(v);
                }
            }
        }

        // Return the maximum completion time
        return Collections.max(completionTime.values());
    }
}
