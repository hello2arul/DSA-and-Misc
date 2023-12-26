package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

//https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph/1
public class ShortestPathInADAG {
    public int[] shortestPath(int N,int M, int[][] edges) {
		Graph graph = new Graph();
		for(int[] edge: edges) {
		    graph.add(edge[0], edge[1], edge[2]);
		}
		List<Integer> topoSort = graph.getTopoSort();
	    int[] dist = new int[N];
	    Arrays.fill(dist, Integer.MAX_VALUE);
	    dist[0] = 0;

	    for(int u: topoSort) {
	        Map<Integer, Integer> vMap = graph.adjList.get(u);
	        if(vMap == null) continue;
	        for(int key: vMap.keySet()){
	              if(dist[u] != Integer.MAX_VALUE && dist[key] > dist[u] + vMap.get(key))
	                dist[key] = dist[u] + vMap.get(key);
	            
	        }
	    }
        for(int i = 0; i < dist.length; i++) {
            if(dist[i] == Integer.MAX_VALUE)
                dist[i] = -1;
        }
	    return dist;
	}
}



class Graph {
    Map<Integer, Map<Integer, Integer>> adjList;
    Map<Integer, Integer> indegrees;
    
    Graph() {
        adjList = new HashMap<>();
        indegrees = new HashMap<>();
    }
    
    public void add(int u, int v, int weight) {
        adjList.putIfAbsent(u, new HashMap<>());
        indegrees.putIfAbsent(u, 0);
        indegrees.put(v, indegrees.getOrDefault(v,0) + 1);
        adjList.get(u).put(v, weight);
    }
    
    public List<Integer> getTopoSort() {
        List<Integer> topoSort = new ArrayList<>();
        
        Queue<Integer> q = new LinkedList<>();
        for(int key: indegrees.keySet()) {
            if(indegrees.get(key) == 0) {
                q.offer(key);
            }
        }
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            topoSort.add(cur);
            if(adjList.get(cur) == null) continue;
            for(int neigh: adjList.get(cur).keySet()) {
                indegrees.put(neigh, indegrees.get(neigh) - 1);
                if(indegrees.get(neigh) == 0)
                    q.offer(neigh);
            }
        }
        return topoSort;
    }
    
}