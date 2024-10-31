## Graph

A graph is a data structure consisting of:

- **Vertices (Nodes)**: A finite set of vertices.
- **Edges**: A finite set of ordered pairs (u, v). In a directed graph (digraph), (u, v) is not the same as (v, u). Edges may have weights, values, or costs.

### Types of Graphs

- **Directed Graphs**: Graphs where edges have a direction, meaning they go from vertex `u` to vertex `v`.
- **Undirected Graphs**: Graphs where edges are bidirectional, meaning an edge between vertices `u` and `v` allows travel from both `u` to `v` and `v` to `u`.

### Special Concepts

- **Articulation Point**: 
  - A vertex that, when removed, increases the number of connected components in the graph. 
  - Example: In a DFS tree, if the root has two or more children, it is an articulation point.

- **Bridges**:
  - An edge that, when removed, increases the number of connected components in the graph. 
  - Similar to an articulation point, but applicable to edges.
  - Can be found using Tarjan's algorithm.

## Minimum Spanning Tree (MST)

- **Definition**: A spanning tree of a graph is a subset of the graph that connects all vertices with the minimum possible number of edges, and does not contain any cycles. A graph may have more than one spanning tree.

### Algorithms to Find MST

- **Prim's Algorithm**:
  - Maintain two sets: 
    1. **In MST**
    2. **Not in MST**
  - Start from an initial vertex and greedily pick the minimum-weight edge connecting a vertex in the MST to a vertex not yet in the MST.

- **Kruskal's Algorithm**:
  - Sort all edges in non-decreasing order of their weight.
  - Add edges one by one to the growing spanning tree, ensuring no cycles are formed, until all vertices are connected.


### Eulerian path:
- In graph theory, an Eulerian trail (or Eulerian path) is a trail in a finite graph that visits every edge exactly once (allowing for revisiting vertices). Similarly, an Eulerian circuit or Eulerian cycle is an Eulerian trail that starts and ends on the same vertex. 

---

# Shortest Path Algorithms

## Dijkstra's Algorithm
- **Use Case**: Designed for **weighted graphs** with **non-negative weights**, shortest path algo.
- **Mechanism**: 
  - Uses a **priority queue** to explore the nearest node first based on **minimum cumulative weight**.
- **Complexity**: \(O((V + E) \log V)\) when using a priority queue.

- **Type**: Greedy algorithm
- **Time Complexity**: 
  - O(V log V) with a Fibonacci heap
- **Use Case**: 
  - Works for graphs with non-negative edge weights
- **Limitations**: 
  - Does not handle graphs with negative weight edges

## Bellman-Ford Algorithm
- **Time Complexity**: 
  - O(VE)
- **Use Case**: 
  - Suitable for graphs with negative weight edges
- **Advantages**:
  - Simpler implementation compared to Dijkstra
  - Well-suited for distributed systems

## 3. 0-1 BFS
- **Use Case**: For graphs with edges that can have weights of **0 or 1**.
- **Mechanism**: 
  - Utilizes a **double-ended queue (deque)**.
  - Nodes connected by **0-weight edges** are added to the front; **1-weight edges** are added to the back.
- **Complexity**: \(O(V + E)\), making it efficient like BFS but suitable for weights of 0 and 1.

---

## Strongly Connected Components
-  a graph is said to be strongly connected if every vertex is reachable from every other vertex