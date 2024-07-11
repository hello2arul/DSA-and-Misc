/* IMPORTANT: Multiple classes and nested static classes are supported */

//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
// https://www.geeksforgeeks.org/minimum-edges-to-be-removed-from-given-undirected-graph-to-remove-any-existing-path-between-nodes-a-and-b/
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
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            sc.nextInt();
        }
        int N = sc.nextInt();
        Graph graph = new Graph();

        for(int i = 0; i < N; i++) {
            graph.add(sc.nextInt(), sc.nextInt());
        }
        int A = sc.nextInt();
        int B = sc.nextInt();
        List<Integer> res = new ArrayList<>();
        graph.minCuts(A, B, res);
        for(int i : res)
            System.out.print(i + " ");

    }
}

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

    public void minCuts(int A, int B, List<Integer> res) {
        List<Integer> neigh = adj.get(A);
        if(neigh == null || neigh.size() == 0)
            return;
        Set<Integer> set = new HashSet<>(neigh);

        for(int n: neigh) {
            if(n != B) {
                minCuts(n, B, res);
            }
        }
        if(set.contains(B) && !res.contains(A)) {
            res.add(A);
        }
    }

}