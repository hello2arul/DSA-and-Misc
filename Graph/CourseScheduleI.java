package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * https://leetcode.com/problems/course-schedule/
 
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must 
take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.

Return true if you can finish all courses. Otherwise, return false.

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.

Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. 
So it is impossible.

 */
public class CourseScheduleI {

    Map<Integer, List<Integer>> adj = new HashMap<>();
    Map<Integer, Integer> indegrees = new HashMap<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        for (int[] edges : prerequisites) {
            add(edges[1], edges[0]);
        }
        return !hasCycleBFS();
    }

    public void add(int from, int to) {
        adj.putIfAbsent(from, new ArrayList<>());
        adj.putIfAbsent(to, new ArrayList<>());
        adj.get(from).add(to);
        indegrees.put(to, indegrees.getOrDefault(to, 0) + 1);
        indegrees.putIfAbsent(from, 0);
    }

    public boolean hasCycleBFS() {
        Queue<Integer> q = new LinkedList<>();

        for (Map.Entry<Integer, Integer> entry : indegrees.entrySet()) {
            if (entry.getValue() == 0)
                q.offer(entry.getKey());
        }
        int depth = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            depth++;

            for (int neigh : adj.get(cur)) {
                indegrees.put(neigh, indegrees.get(neigh) - 1);

                if (indegrees.get(neigh) == 0)
                    q.offer(neigh);
            }
        }

        return depth != adj.size();
    }

    /**
    With visited alone, we cannot determine a cycle as a node can be visited multiple times in different parts of the graph.
    If a node is in stack, it means you're currently visiting that node in the ongoing DFS recursion. 
    If you encounter such a node, it means you've encountered a back edge, and thus you have a cycle.
    */
    public boolean hasCycleDFS() {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> stack = new HashSet<>();

        for (int key: adj.keySet()) {
            if (!visited.contains(key) && hasCycleDFS(key, visited, stack))
                return true;
        }
        return false;
    }

    private boolean hasCycleDFS(int cur, Set<Integer> visited, Set<Integer> stack) {
        visited.add(cur);
        stack.add(cur);

        for (int neigh : adj.getOrDefault(cur, new ArrayList<>())) {
            if (!visited.contains(neigh) && hasCycleDFS(neigh, visited, stack))
                return true;
            else if (stack.contains(neigh))
                return true;
        }
        stack.remove(cur);
        return false;
    }

}
