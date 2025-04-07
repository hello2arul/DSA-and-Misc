package BFS.JumpGame;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/jump-game/description/?envType=company&envId=google&favoriteSlug=google-all
Google

You are given an integer array nums. You are initially positioned at the array's first index, 
and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.

*/
public class JumpGameI {

    // ( O(n^2) 
    public boolean canJumpBFS(int[] nums) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        int n = nums.length;
        boolean[] visited = new boolean[n];
        visited[0] = true;

        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                int cur = q.poll();

                // >= for nums = [0]
                for (int j = cur + nums[cur]; j >= cur; j--) {
                    if (j >= n - 1) {
                        return true;
                    }
                    if (!visited[j]) {
                        q.offer(j);
                        visited[j] = true;
                    }
                }
            }
        }

        return false;
    }

    //  O(n^2)
    public boolean canJumpDP(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[0] = true;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && j + nums[j] >= i) {
                    dp[i] = true;
                    break;
                } 
            }
        }

        return dp[n - 1];
    }
}
