package Graph;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/critical-connections-in-a-network
Find bridges
Google
Tarjan's algorithm
 */
public class CriticalConnectionsInANetwork {
     public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Graph myGraph = new Graph(n, connections);
        return myGraph.bridges;
    }
}

class Graph {
    private List<List<Integer>> adjList;
    private int vertices;
    public List<List<Integer>> bridges;
    private int time;

    Graph(int vertices, List<List<Integer>> graph) {
        this.adjList = new ArrayList<>();
        this.vertices = vertices;
        this.time = 0;
        this.bridges = new ArrayList<>();
        for (int i = 0; i < vertices; i++)
            adjList.add(new ArrayList<>());
        for (List<Integer> pair: graph) 
            this.add(pair.get(0), pair.get(1));
        this.findBridges();
    }

    public void add(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }
    
    public void findBridges() {
        int[] parent = new int[vertices];
        int[] disc = new int[vertices];
        int[] low = new int[vertices];
        boolean[] visited = new boolean[vertices];
        for (int i = 0; i < vertices; i++) {
            if (!visited[i])
                findBridgesHelper(i, visited, parent, disc, low);
        }
    }
    
    private void findBridgesHelper(int u, boolean[] visited, int[] parent, int[] disc, int[] low) {
        visited[u] = true;
        disc[u] = low[u] = ++time;
        for (int v: adjList.get(u)) {
            if (!visited[v]) {
                parent[v] = u;
                findBridgesHelper(v, visited, parent, disc, low);
                low[u]  = Math.min(low[u], low[v]); 
                // If low[v] > disc[u], it means:
                // The only way to reach u (or any of its ancestors) from v is through the edge (u, v).
                // Therefore, removing this edge would disconnect v from the component containing u.
                if (low[v] > disc[u])
                    bridges.add(List.of(u, v));
            } else if (v != parent[u])
                low[u] = Math.min(low[u], disc[v]);                
        }
    }
}     
