package Graph;

import java.util.PriorityQueue;
import DisjointSet.DisjointSet;

/*
 * https://leetcode.com/problems/min-cost-to-connect-all-points/
 * can be solved using Prims and Kruskals
 * TODO: implement Prims
 */
public class MinimumCostToConnectPointsMST {
     // Kruskal
    public int minCostConnectPoints(int[][] points) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        int minCost = 0;
        int n = points.length;
        DisjointSet ds = new DisjointSet(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                minHeap.offer(new int[] {getDist(points, i, j), i, j});

            }
        }

        while (!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();

            if (ds.union(cur[1], cur[2])) {
                minCost += cur[0];
            }
        }
        return minCost;
    }

    private int getDist(int[][] points, int i, int j) {
        return Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
    }
}
