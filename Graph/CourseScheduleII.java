import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/course-schedule-ii/
 * toposort using dfs
 */
public class CourseScheduleII {
     public int[] findOrder(int n, int[][] prerequisites) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        Map<Integer, Integer> indegrees = new HashMap<>();

        for (int[] edges : prerequisites) {
            adj.putIfAbsent(edges[1], new ArrayList<>());
            adj.get(edges[1]).add(edges[0]);
            indegrees.put(edges[0], indegrees.getOrDefault(edges[0], 0) + 1);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (indegrees.getOrDefault(i, 0) == 0) {
                dfs(i, adj, indegrees, res);
            }
        }
        return res.size() == n ?  res.stream().mapToInt(Integer::intValue).toArray() : new int[] {};
    }

    private void dfs(int cur, Map<Integer, List<Integer>> adj, Map<Integer, Integer> indegrees, 
        List<Integer> res) {
        res.add(cur);
        indegrees.put(cur, -1);
        for (int neigh : adj.getOrDefault(cur, new ArrayList<>())) {
            indegrees.put(neigh, indegrees.get(neigh) - 1);
            if (indegrees.get(neigh) == 0)
                dfs(neigh, adj, indegrees, res);
        }
    }
}
