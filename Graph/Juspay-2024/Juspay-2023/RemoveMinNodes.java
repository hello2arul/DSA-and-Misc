package Juspay;
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
// Remove min nodes so that A can't reach B
import java.util.*;

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
             graph.add(sc.nextInt(), sc.nextInt());
         }
         graph.canReachNew(sc.nextInt(), sc.nextInt());

    }
}

class Graph {
    public Map<Integer, List<Integer>> adj;
    public Map<Integer, Integer> indegrees;
    
    Graph() {
        adj = new HashMap<>();
        indegrees = new HashMap<>();
    }
    
    public void add(int from, int to) {
        adj.putIfAbsent(from, new LinkedList<>());
        adj.putIfAbsent(to, new LinkedList<>());
        adj.get(from).add(to);
        indegrees.putIfAbsent(from, 0);
        indegrees.put(to, indegrees.getOrDefault(to, 0) + 1);
    }

    public boolean canReach(int A, int B) {
        Set<Integer> visited = new HashSet<>();
        visited.add(A);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(A);

        while(!queue.isEmpty()) {
            int current = queue.poll();
            if(current == B) {
                return true;
            }
            for(int neigh: adj.get(current)) {
                if(!visited.contains(neigh)) {
                    visited.add(neigh);
                    queue.add(neigh);
                }
            }
        }
        return false;
    }

    public void canReachNew(int A, int B) {

        for(int neigh: adj.get(A)) {
            if(neigh != B && canReach(neigh, B)) {
                System.out.print(neigh + " ");
            }
        }
                System.out.print(A +" ");
    }
}
