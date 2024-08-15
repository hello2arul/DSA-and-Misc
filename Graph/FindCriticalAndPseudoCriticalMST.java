package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DisjointSet.DisjointSet;

/*
 * https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/description/
 * Kruskal's algo
 */
public class FindCriticalAndPseudoCriticalMST {
     public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        List<Integer> critical = new ArrayList<>();
        List<Integer> psuedo = new ArrayList<>();

        for (int i = 0; i < edges.length; i++) {
            edges[i] = new int[] {edges[i][0], edges[i][1], edges[i][2], i};
        }
        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));
        int mstWeight = buildMSTandGetWeight(n, edges, -1, -1);
        
        for (int i = 0; i < edges.length; i++) {
            if (mstWeight < buildMSTandGetWeight(n, edges, i, -1)) {
                critical.add(edges[i][3]);
            }
            else if (mstWeight == buildMSTandGetWeight(n, edges, -1, i)) {
                psuedo.add(edges[i][3]);
            }
        }
        return Arrays.asList(critical, psuedo);
    }
    
    private int buildMSTandGetWeight(int n, int[][] edges, int block, int preEdge) {
        int weight = 0; 
        DisjointSet ds = new DisjointSet(n);
        // force include this for psuedo calculation
        if (preEdge != -1) {
            weight += edges[preEdge][2];
            ds.union(edges[preEdge][0], edges[preEdge][1]);
        }
        for (int i = 0; i < edges.length; i++) {
            if (i == block || i == preEdge) {
                continue;
            }
            if (ds.union(edges[i][0], edges[i][1])) {
                weight += edges[i][2];
            }
        }
        // ensure all vertices are part of the built MST
        for (int i = 0; i < n; i++) {
            if (ds.find(i) != ds.find(0)) {
                return Integer.MAX_VALUE;
            }
        }
        return weight;
    }
}
