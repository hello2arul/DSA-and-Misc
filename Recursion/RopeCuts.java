package Recursion;/*
 * Given a rope of length n, you need to find maximum number of pieces
 * you can make such that length of every piece is in the set {a, b, c}
 */

public class RopeCuts {
    public int maxCuts(int n, int[] lengths) {
        if (n < 0)  return -1;
        if (n == 0) return 0;

        int res = -1;
        for(int length: lengths) {
            res = Math.max(res, maxCuts(n - length, lengths));
        }

        return 1 + res;
    }
}
