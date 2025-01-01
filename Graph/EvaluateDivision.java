package Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
https://leetcode.com/problems/evaluate-division/
Google

You are given an array of variable pairs equations and an array of real numbers values, 
where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. 
Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where
you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in 
division by zero and that there is no contradiction.

Note: The variables that do not occur in the list of equations are undefined, so the answer 
cannot be determined for them.

 

Example 1:

Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], .
queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]

Explanation: 
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
note: x is undefined => -1.0

Example 2:

Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], 
queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]

Example 3:

Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]
 */
public class EvaluateDivision {
    
    // Time Complexity: O(E + Q * (V + E)), where E is the number of equations, V is the number of variables,
    // and Q is the number of queries.
    // Space Complexity: O(V + E), where V is the number of variables and E is the number of equations.
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Graph graph = new Graph();
        for (int i = 0; i < equations.size(); i++) {
            graph.add(equations.get(i).get(0), equations.get(i).get(1), values[i]);
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            res[i] = graph.getWeight(queries.get(i).get(0), queries.get(i).get(1));
        }
        return res;
    }
}

class Graph {
    
    Map<String, Map<String, Double>> adj = new HashMap<>();

    public void add(String u, String v, double w) {
        adj.putIfAbsent(u, new HashMap<>());
        adj.putIfAbsent(v, new HashMap<>());
        adj.get(u).put(v, w);
        adj.get(v).put(u, 1.0 / w);
    }

    public double getWeight(String u, String v) {
        if (!adj.containsKey(u) || !adj.containsKey(v))
            return -1.0;
        if (u.equals(v))
            return 1.0;
        Double res = adj.get(u).get(v);
        return res != null ? res : dfs(u, v, 1.0, new HashSet<>());
    }

    private double dfs(String src, String dest, double res, Set<String> visited) {
        if (src.equals(dest))
            return res;
        visited.add(src);
        
        for (String neigh : adj.get(src).keySet()) {
            if (!visited.contains(neigh)) {
                double subRes = dfs(neigh, dest, res * adj.get(src).get(neigh), visited);
                if (subRes != -1.0)
                    return subRes;
            }
        }
        return -1.0;
    }
}