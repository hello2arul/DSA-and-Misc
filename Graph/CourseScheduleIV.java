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
https://leetcode.com/problems/course-schedule-iv/

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you 
must take course ai first if you want to take course bi.

For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a 
prerequisite of course c, then course a is a prerequisite of course c.

You are also given an array queries where queries[j] = [uj, vj]. For the jth query,
you should answer whether course uj is a prerequisite of course vj or not.

Return a boolean array answer, where answer[j] is the answer to the jth query.

Input: numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
Output: [false,true]
Explanation: The pair [1, 0] indicates that you have to take course 1 before you can take course 0.
Course 0 is not a prerequisite of course 1, but the opposite is true.

Input: numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
Output: [false,false]
Explanation: There are no prerequisites, and each course is independent.

Input: numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
Output: [true,true]
 */

public class CourseScheduleIV {

    // O(n^3) O(n^2)
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        Map<Integer, Integer> indegrees = new HashMap<>();

        for (int[] edge : prerequisites) {
            adjList.putIfAbsent(edge[0], new ArrayList<>());
            adjList.get(edge[0]).add(edge[1]);
            indegrees.put(edge[1], indegrees.getOrDefault(edge[1], 0) + 1);
            indegrees.putIfAbsent(edge[0], 0);
        }    

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegrees.getOrDefault(i, 0) == 0) {
                q.offer(i);
            }
        }

        Map<Integer, Set<Integer>> prereq = new HashMap<>();

        while (!q.isEmpty()) {
            int node = q.poll();
            
            for (int neigh : adjList.getOrDefault(node, new ArrayList<>())) {
                prereq.putIfAbsent(neigh, new HashSet<>());
                prereq.get(neigh).add(node);

                for (int pre : prereq.getOrDefault(node, new HashSet<>())) {
                    prereq.get(neigh).add(pre);
                }

                indegrees.put(neigh, indegrees.get(neigh) - 1);

                if (indegrees.get(neigh) == 0) {
                    q.offer(neigh);
                }
            }
        }

        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            res.add(prereq.getOrDefault(query[1], new HashSet<>()).contains(query[0]));
        }
        return res;
    }
}
