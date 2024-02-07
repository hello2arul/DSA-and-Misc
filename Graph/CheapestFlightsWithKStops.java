

//https://leetcode.com/problems/cheapest-flights-within-k-stops/

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class CheapestFlightsWithKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Graph graph = new Graph();
        for(int[] flight: flights) {
            graph.add(flight[0], flight[1], flight[2]);
        }
        return graph.minPath(src, dst, k);
    }
}

class Graph {
    Map<Integer, Map<Integer, Integer>> adj;
    
    Graph() {
        adj = new HashMap<>();
    }
    
    public void add(int u, int v, int w) {
        adj.putIfAbsent(u, new HashMap<>());
        adj.get(u).put(v, w);
    }
    
    public int minPath(int src, int dst, int k) {
        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        minHeap.offer(new int[] {src, 0, k + 1}); // vertex, weight, stops
        // cost from src to src is 0;
        //System.out.println(adj);
        while(!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            int vertex = cur[0];
            int cost = cur[1];
            int stops = cur[2];
            //System.out.println(Arrays.toString(cur));
            if(vertex == dst)   return cost;
            
            for(int v: adj.getOrDefault(vertex, new HashMap<>()).keySet()) {
                if(stops > 0) {
                    minHeap.offer(new int[] 
                                  { v, cost + adj.get(vertex).get(v), stops - 1});
                }
            }
        }
        return -1;
    }
}