package Graph;

/*
https://leetcode.com/problems/cheapest-flights-within-k-stops/

There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that 
there is a flight from city fromi to city toi with cost pricei.

You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.

Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
Output: 700
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
*/

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