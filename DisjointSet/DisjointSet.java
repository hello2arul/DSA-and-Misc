package DisjointSet;

class DisjointSet {

    private int[] rank;
    private int[] parent;
    
    public DisjointSet(int size) {
        rank = new int[size];
        parent = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int u) {
        if (parent[u] != u) {
            parent[u] = find(parent[u]);
        }
        return parent[u];
    }

    public boolean union(int u, int v) {
        int findU = find(u);
        int findV = find(v);

        if (findU == findV)
            return false;
        if (rank[findU] >= rank[findV]) {
            parent[findV] = findU;
            rank[findU]++;
        } else {
            parent[findU] = findV;
            rank[findV]++;
        }
        return true;
    }
}