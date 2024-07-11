package Juspay;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;
*/

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class TestClass {
    public static void main(String args[] ) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        //Scanner
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();                 // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        */

        // Write your code here
         Graph graph = new Graph();
         Scanner sc = new Scanner(System.in);
         int n = sc.nextInt();
         for(int i = 0; i < n; i++) {
             sc.nextInt();
         }
         n = sc.nextInt();
         for(int i = 0; i < n; i++) {
             graph.add(sc.nextInt(), sc.nextInt(), sc.nextInt());
         }
         int res = graph.shortestPath(sc.nextInt(), sc.nextInt());
         System.out.println(res);

    }
}

class Graph {
    public Map<Integer, Map<Integer, Integer>> adj;
    
    Graph() {
        adj = new HashMap<>();
    }
    
    public void add(int from, int to, int weight) {
        adj.putIfAbsent(from, new HashMap<>());
        adj.putIfAbsent(to, new HashMap<>());
        adj.get(from).put(to, weight);
    }

    public int shortestPath(int A, int B) {
        Map<Integer, Integer> paths = new HashMap<>();
        paths.put(A, 0);
        return shortPath(A, B, 0, new TreeSet<>(), new HashMap<>());
    }
    public int shortPath(int A, int B, int path, TreeSet<Integer> results,
    Map<String, Integer> visited) {
        if(A == B) {
            if(results.size() == 0)
                return path;
            return Math.min(path, results.first());
        }
        if(!results.isEmpty() && path > results.first()) {
            return results.first();
        }
             String key = A + ", " + path;
        if(visited.containsKey(key)) {
            return visited.get(key);
        }

        Map<Integer, Integer> neighs = adj.getOrDefault(A, new HashMap<>());
        for(int k: neighs.keySet()) {
            int res = shortPath(k, B, path + neighs.get(k), results, visited);  
            results.add(res);
            visited.put(key, results.first());
        }
        return !results.isEmpty()? results.first(): Integer.MAX_VALUE;
    }
}
