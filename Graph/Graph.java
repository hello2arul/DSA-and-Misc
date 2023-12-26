package Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Graph {
    Map<Integer, List<Integer>> adj;
    Map<Integer, Integer> indegrees;

    Graph() {
        adj = new HashMap<>();
        indegrees = new HashMap<>();
    }

    public void add(int from, int to) {
        adj.putIfAbsent(from, new LinkedList<>());
        adj.putIfAbsent(to, new LinkedList<>());
        indegrees.put(to, indegrees.getOrDefault(to, 0) + 1);
        indegrees.putIfAbsent(from, 0);
        adj.get(from).add(to);
    }

    //toposort
    public boolean hasCyclebfs() {
        Queue<Integer> q = new LinkedList<>();
        
        for(int key: indegrees.keySet()) {
            if(indegrees.get(key) == 0) {
                q.offer(key);
            }
        }
        int depth = 0;

        while(!q.isEmpty()) {
            int u = q.poll();
            depth++;
            for(int neigh: adj.get(u)) {
                indegrees.put(neigh, indegrees.get(neigh) - 1);
                
                if(indegrees.get(neigh) == 0) {
                    q.offer(neigh);
                }
            }
        }
        return depth != adj.size();
    }

    public boolean hasCycledfs() {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> stack = new HashSet<>();
        
        for(int key: adj.keySet()) {
            if(!visited.contains(key) && dfs(key, visited, stack)) {
                return true;
            }
        }
        return false;
    }

     private boolean dfs(int node, Set<Integer> visited, Set<Integer> stack) {
        visited.add(node);
        stack.add(node);
        
        for(int neigh: adj.getOrDefault(node, new LinkedList<>())) {
            if(!visited.contains(neigh) && dfs(neigh, visited, stack)) {
                return true;
            } else if(stack.contains(neigh)) {
                return true;
            } 
        }
        
        stack.remove(node);
        return false;
    }
}