package BFS;

/**
 * https://leetcode.com/problems/01-matrix/
 * also see DP solution
 */
public class O1Matrix {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        Queue<int[]> q = new LinkedList<>();
        int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    q.offer(new int[] {i ,j});
                } else {
                    mat[i][j] = -1;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int i = cur[0], j = cur[1];

            for (int[] dir: dirs) {
                int iNew = i + dir[0], jNew = j + dir[1];
                if (iNew < 0 || jNew < 0 || iNew >= m || jNew >= n || mat[iNew][jNew] != -1)
                    continue;
                mat[iNew][jNew] = mat[i][j] + 1;
                q.offer(new int[] {iNew, jNew});
            }
        }
        return mat;
    }
}
