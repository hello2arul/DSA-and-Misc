package Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
https://leetcode.com/problems/find-eventual-safe-states
Google

There is a directed graph of n nodes with each node labeled from 0 to n - 1. 
The graph is represented by a 0-indexed 2D integer array graph where graph[i] 
is an integer array of nodes adjacent to node i, meaning there is an edge from
node i to each node in graph[i].

A node is a terminal node if there are no outgoing edges.
A node is a safe node if every possible path starting from that 
node leads to a terminal node (or another safe node).

Return an array containing all the safe nodes of the graph.
The answer should be sorted in ascending order.


Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Explanation: The given graph is shown above.
Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
Example 2:

Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
Output: [4]
Explanation:
Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.

 */
public class FindEventualSafeStates {

    private static final int UNVISITED = 0;
    private static final int VISITING = 1;
    private static final int SAFE = 2;

    private List<List<Integer>> adj;
    private int[] state;

    // O(V + E)
    public List<Integer> eventualSafeNodesDFS(int[][] graph) {
        int n = graph.length;
        adj = new ArrayList<>();
        state = new int[n];

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j : graph[i]) {
                adj.get(i).add(j);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (isSafe(i)) {
                res.add(i);
            }
        }

        return res;
    }

    private boolean isSafe(int node) {
        if (state[node] != UNVISITED) {
            return state[node] == SAFE;
        }

        state[node] = VISITING;
        for (int neighbor : adj.get(node)) {
            if (!isSafe(neighbor)) {
                return false;
            }
        }

        state[node] = SAFE;
        return true;
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        Graph directedGraph = new Graph();
        for (int u = 0; u < graph.length; u++) {
            if (graph[u].length > 0) {
                for (int v : graph[u]) {
                    directedGraph.add(u, v);
                }
            } else {
                directedGraph.add(u);
            }
        }
        return directedGraph.findNonCyclicNodes();
    }

 public List<Integer> eventualSafeNodesTopo(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> adj = new ArrayList<>();
        List<List<Integer>> reverseAdj = new ArrayList<>();
        int[] outDegree = new int[n];

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            reverseAdj.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j : graph[i]) {
                adj.get(i).add(j);
                reverseAdj.get(j).add(i);
                outDegree[i]++;
            }
        }   

         Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (outDegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            res.add(node);
            for (int neighbor: reverseAdj.get(node)) {
                outDegree[neighbor]--;
                if (outDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        Collections.sort(res);
        return res;
    }

}

class Graph {
    int vertices;
    Map<Integer, List<Integer>> adj;

    Graph() {
        vertices = 0;
        adj = new HashMap<>();
    }

    public void add(int u, int v) {
        if (!adj.containsKey(u))
            adj.put(u, new ArrayList<>());
        adj.get(u).add(v);
    }

    public void add(int u) {
        if (!adj.containsKey(u)) {
            adj.put(u, new ArrayList<>());
        }
    }

    public List<Integer> findNonCyclicNodes() {
        List<Integer> res = new ArrayList<>();
        for (int key : adj.keySet()) {
            if (!reachesCycle(key)) {
                res.add(key);
            }
        }
        return res;
    }

    private boolean reachesCycle(int source) {
        return hasCycle(source, new HashSet<>(), new HashSet<>());
    }

    private boolean hasCycle(int u, Set<Integer> visited, Set<Integer> stack) {
        visited.add(u);
        stack.add(u);
        for (int v : adj.getOrDefault(u, new ArrayList<>())) {
            if (!visited.contains(v) && hasCycle(v, visited, stack)) {
                return true;
            } else if (stack.contains(v)) {
                return true;
            }
        }
        stack.remove(u);
        return false;
    }
}