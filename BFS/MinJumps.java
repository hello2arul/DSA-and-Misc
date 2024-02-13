package BFS;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 
 * https://leetcode.com/problems/jump-game-ii/description/
 * also check recursive and dp
 */
public class MinJumps {
     public int jump(int[] nums) {
        int len = nums.length;
        if(len <= 1)    return 0;
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        int depth = 0;
        
        while(!q.isEmpty()) {
            for(int k = q.size(); k > 0; k--) {
                int idx = q.poll();
                if(idx >= len - 1)  return depth;
                
                int maxReach = nums[idx];
                for(int i = maxReach; i >= 1; i--) {
                    q.offer(idx + i);
                }
            }
            depth++;
        }
        return depth;
    }
}
