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
https://leetcode.com/problems/sum-of-distances-in-tree
There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.

You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.

Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.

Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
Output: [8,12,6,10,10,10]
Explanation: The tree is shown above.
We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
equals 1 + 1 + 2 + 2 + 2 = 8.
Hence, answer[0] = 8, and so on.
 */
public class SumOfDistanceInTree {
      Map<Integer, List<Integer>> adj = new HashMap<>();

    public void add(int from, int to) {
        adj.putIfAbsent(from, new LinkedList<>());
        adj.putIfAbsent(to, new LinkedList<>());
        adj.get(from).add(to);
        adj.get(to).add(from);
    }

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        for (int[] edge: edges) {
            add(edge[0], edge[1]);
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = bfs(i);
        }
        return res;
    }

    private int bfs(int node) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        int res = 0;
        int depth = 1;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int cur = q.poll();
                visited.add(cur);
                for (int child: adj.getOrDefault(cur, new ArrayList<>())) {
                    if (!visited.contains(child)) {
                        res += depth;
                        q.offer(child);
                    }
                }
            }
            depth++;
        }
        return res;
    }

}
