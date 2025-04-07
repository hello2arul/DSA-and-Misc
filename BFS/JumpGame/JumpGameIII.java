package BFS.JumpGame;

import java.util.LinkedList;
import java.util.Queue;

/*
https://leetcode.com/problems/jump-game-iii/
Given an array of non-negative integers arr, you are initially positioned at start index of the array..
When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach any index
with value 0.

Notice that you can not jump outside of the array at any time.
*/
public class JumpGameIII {
    // space can be optimized by modifying the original array
    public boolean canReach(int[] arr, int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        int n = arr.length;
        boolean[] visited = new boolean[n];
        visited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (arr[cur] == 0) {
                return true;
            }
            int plus = arr[cur] + cur;
            int minus = cur - arr[cur];

            if (plus < n && !visited[plus]) {
                visited[plus] = true;
                q.offer(plus);
            }

            if (minus >= 0 && !visited[minus]) {
                visited[minus] = true;
                q.offer(minus);
            }
        }
        return false;
    }
}
