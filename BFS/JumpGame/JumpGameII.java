package BFS.JumpGame;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/* 
https://leetcode.com/problems/jump-game-ii/description/
Google

also check recursive and dp

*/
public class JumpGameII {

    public int jump(int[] nums) {
        int len = nums.length;
        if (len <= 1)
            return 0;

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        int depth = 0;

        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; k--) {
                int idx = q.poll();
                if (idx >= len - 1)
                    return depth;

                int maxReach = nums[idx];
                for (int i = maxReach; i >= 1; i--) {
                    q.offer(idx + i);
                }
            }
            depth++;
        }
        return depth;
    }

    public int jumpDP(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) {
                    dp[i] = Math.min(dp[j] + 1, dp[i]);
                }
            }
        }

        return dp[n - 1];
    }
}
