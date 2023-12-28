package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//https://leetcode.com/problems/find-if-path-exists-in-graph/description/
public class FindIfPathExistsBetweenSourceDest {
 public boolean validPath(int n, int[][] edges, int source, int destination) {
        Graph undirected = new Graph();

        for(int[] edge: edges) {
            undirected.add(edge[0], edge[1]);
        }
        return undirected.canReachbfs(source, destination);       
    }
}

class Graph {
    Map<Integer, List<Integer>> adjList;
    Map<Integer, Integer> indegrees;

    Graph() {
        adjList = new HashMap<>();
        indegrees = new HashMap<>();
    }

    public void add(int u, int v) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.putIfAbsent(v, new ArrayList<>());
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    public boolean canReachbfs(int A, int B) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.offer(A);

        while(!q.isEmpty()) {
            int u = q.poll();
            if(!visited.contains(u) && adjList.get(u) != null) {
                for(int v: adjList.get(u)) {
                    if(v == B)
                        return true;
                    q.offer(v);
                }
            }
            visited.add(u);
        }
        return A == B;
    }

    public boolean canReachdfs(int A, int B, Set<Integer> visited) {
        if(A == B)  return true;
        visited.add(A);

        for(int v: adjList.get(A)) {
            if(!visited.contains(v)) {
                if(canReachdfs(v, B, visited))
                    return true;
            }
        }
        return false;
    }
}