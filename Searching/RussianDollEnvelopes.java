package Searching;

import java.util.Arrays;
/*
https://leetcode.com/problems/russian-doll-envelopes/
You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.

One envelope can fit into another if and only if both the width and height of one envelope are greater than the other envelope's width and height.

Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).

Note: You cannot rotate an envelope.

Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
Output: 3
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

1. use LIS, dp -> TLE O(n^2)
2. use binarySearch on the sorted array somehow -> O(nlogn) TODO
*/
public class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0)
            return 0;
        int n = envelopes.length;
        int[] lis = new int[n];
        Arrays.sort(
            envelopes, 
            (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) :
            Integer.compare(a[0], b[0])
        );
        Arrays.fill(lis, 1);
        int max = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[j][1] < envelopes[i][1] && envelopes[j][0] < envelopes[i][0]) {
                    lis[i] = Math.max(lis[i], 1 + lis[j]);
                    max = Math.max(max, lis[i]);
                }
            }
        }

        return max;
    }
}
