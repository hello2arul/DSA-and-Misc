package Math;

import java.util.HashMap;
import java.util.Map;

import DisjointSet.DisjointSet;

/*
You are given an integer n. There is an undirected graph with n vertices, 
numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] 
denotes that there exists an undirected edge connecting vertices ai and bi.

Return the number of complete connected components of the graph.

A connected component is a subgraph of a graph in which there exists a path between any two vertices,
 and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.

A connected component is said to be complete if there exists an edge between every pair of its 
vertices.

Example 1:

Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4]]
Output: 3
Explanation: From the picture above, one can see that all of the components of this graph are 
complete.
 */

public class CountTheNumberOfCompleteComponents {
    public int countCompleteComponents(int n, int[][] edges) {
        DisjointSet ds = new DisjointSet(n);
        for (int[] edge : edges) {
            ds.union(edge[0], edge[1]);
        }
        Map<Integer, Integer> edgeCount = new HashMap<>();

        for (int[] edge : edges) {
            int root = ds.find(edge[0]);
            edgeCount.put(root, edgeCount.getOrDefault(root, 0) + 1);
        }

        int completeCount = 0;

        for (int vertex = 0; vertex < n; vertex++) {
            if (ds.find(vertex) == vertex) {
                int nodeCount = ds.rank[vertex];
                int expectedEdges = (nodeCount * (nodeCount - 1)) / 2;
                if (edgeCount.getOrDefault(vertex, 0) == expectedEdges) {
                    completeCount++;
                }
            }
        }
        return completeCount;
    }
}
