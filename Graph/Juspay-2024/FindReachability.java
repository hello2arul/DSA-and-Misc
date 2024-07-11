package Graph.Juspay-2024;

/* IMPORTANT: Multiple classes and nested static classes are supported */

//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;


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

        Scanner sc = new Scanner(System.in);
        Graph undirected = new Graph();
        int n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            sc.nextInt();
        }

        int n1 = sc.nextInt();
        for(int i = 0; i  < n1; i++) {
            undirected.add(sc.nextInt(), sc.nextInt());
        } 
                boolean res=  undirected.canReachbfs(sc.nextInt(), sc.nextInt());
        System.out.println(res ? "1" : "0");
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

