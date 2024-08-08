package DP;


/**
 * https://leetcode.com/problems/01-matrix/
 * also see BFS solution
 */
public class O1Matrix {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != 0) {
                    int first = Integer.MAX_VALUE;
                    int second = Integer.MAX_VALUE;
                    if (i != 0)
                        first = mat[i - 1][j];
                    if (j != 0)
                        second = mat[i][j - 1];
                    mat[i][j] = Math.min(first, second);
                    if (mat[i][j] != Integer.MAX_VALUE)
                        mat[i][j]++;
                }
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (mat[i][j] != 0) {
                    int first = Integer.MAX_VALUE;
                    int second = Integer.MAX_VALUE;
                    int third = Integer.MAX_VALUE;
                    int fourth = Integer.MAX_VALUE;
                    if (i != 0)
                        first = mat[i - 1][j];
                    if (j != 0)
                        second = mat[i][j - 1];
                    if (i != m - 1)
                        third = mat[i + 1][j];
                    if (j != n - 1)
                        fourth = mat[i][j + 1];
                    mat[i][j] = Math.min(
                            Math.min(first, second),
                            Math.min(third, fourth)
                    );
                    if (mat[i][j] != Integer.MAX_VALUE)
                        mat[i][j]++;
                }
            }
        }

        return mat;
    }
}
