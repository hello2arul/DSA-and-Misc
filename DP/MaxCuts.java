package DP;

/*
 * https://www.geeksforgeeks.org/maximise-number-of-cuts-in-a-rod-if-it-can-be-cut-only-in-given-3-sizes/#practice
 * https://www.geeksforgeeks.org/problems/rod-cutting0840/1?utm_source=geeksforgeeks&utm_medium=ml_article_practice_tab&utm_campaign=article_practice_tab
 */
public class MaxCuts {
    public int maxCuts(int n, int a, int b, int c) {
        if(n == 0)
            return 1;
        if(n < 0)
            return -1;
        return Math.max(
            Math.max(maxCuts(n - a, a, b, c), maxCuts(n - b, a, b, c)),
            maxCuts(n - c, a, b, c)
        );
    }

    public int maxCutsDP(int n, int a, int b, int c) {
        int[] dp = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            dp[i] = -1;
            if(i - a >= 0)
                dp[i] = Math.max(dp[i], dp[i - a]);
            if(i - b >= 0)
                dp[i] = Math.max(dp[i], dp[i - b]);
            if(i - c >= 0)
                dp[i] = Math.max(dp[i], dp[i - c]);
            if(dp[i] != -1)
                dp[i]++;
        }
        return dp[n];
    }
}
