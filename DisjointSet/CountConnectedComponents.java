package DisjointSet;

/*
 https://neetcode.io/problems/count-connected-components
 Input:
n=3
edges=[[0,1], [0,2]]

Output:
1

Input:
n=6
edges=[[0,1], [1,2], [2,3], [4,5]]

Output:
2
 */
class CountConnectedComponents {
    public int countComponents(int n, int[][] edges) {
        DisjointSet ds = new DisjointSet(n);
        for (int[] edge: edges) {
            if (ds.union(edge[0], edge[1]))
                n--;
        }
        return n;
    }
}