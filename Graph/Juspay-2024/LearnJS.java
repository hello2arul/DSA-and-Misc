package Graph.Juspay-2024;

/* IMPORTANT: Multiple classes and nested static classes are supported */


//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
// some cases still fail
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
        int[][] edges = new int[N][3];
        for(int i = 0; i < N;i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
            edges[i][2] = sc.nextInt();

        }
        int A = sc.nextInt();
        int B = sc.nextInt();
        
        System.out.println(shortestPath(N, edges, A,B));
    }

    public static int shortestPath(int N,int[][] edges, int A, int B) {
		Graph graph = new Graph();
        Map<Integer, Integer> dist = new HashMap<>();
		for(int[] edge: edges) {
		    graph.add(edge[0], edge[1], edge[2]);
            dist.putIfAbsent(edge[0], Integer.MAX_VALUE);
            dist.putIfAbsent(edge[1], Integer.MAX_VALUE);
		}
		List<Integer> topoSort = graph.getTopoSort();
	    dist.put(A, 0);

	    // for(int u: topoSort) {
	    //     Map<Integer, Integer> vMap = graph.adjList.get(u);
	    //     if(vMap == null) continue;
	    //     for(int key: vMap.keySet()){
	    //           if(dist.get(u) != Integer.MAX_VALUE && 
        //                 dist.get(key) > dist.get(u) + vMap.get(key))
	    //             dist.put(key, dist.get(u) + vMap.get(key));
	            
	    //     }
	    // }


        Queue<Integer> queue = new LinkedList<>();
        queue.offer(A);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            Map<Integer, Integer> neighbors = graph.adjList.get(node);
            if (neighbors == null) continue;
            for (int neighbor : neighbors.keySet()) {
                int newDist = dist.get(node) + neighbors.get(neighbor);
                if (newDist < dist.get(neighbor)) {
                    dist.put(neighbor, newDist);
                    queue.offer(neighbor);
                }
            }
        }

	    return dist.get(B);
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
