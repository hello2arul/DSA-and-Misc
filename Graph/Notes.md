A Graph is a data structure that consists of the following two components:
* A finite set of vertices also called nodes.
* A finite set of ordered pair of the form (u, v) called as edge. The pair is ordered because (u, v) is not the same as (v, u) in case of a directed graph(digraph). The pair of the form (u, v) indicates that there is an edge from vertex u to vertex v. The edges may contain weight/value/cost.


* Directed Graphs: The Directed graphs are such graphs in which edges are directed in a single direction.
* Undirected Graphs: Undirected graphs are such graphs in which the edges are directionless or in other words bi-directional. That is, if there is an edge between vertices u and v then it means we can use the edge to go from both u to v and v to u.

* Articulation point: 
    - Removal of this will increase the number of connected components
    1. If the root of DFS tree has two or more children, then it's an articulation point.
* Bridges:
    - Similar to articulation point but it's an edge instead of a node.