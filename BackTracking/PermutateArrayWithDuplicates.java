package BackTracking;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/permutations-ii/description/
public class PermutateArrayWithDuplicates {
     public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        permute(nums, res, new LinkedList<>(), new boolean[nums.length]);
        return res;
    }
    
    private void permute(int[] nums, List<List<Integer>> res, LinkedList<Integer> cur,
                         boolean[] used) {
        if(cur.size() == nums.length) {
            res.add(new LinkedList<>(cur));
        } else {
            for(int i = 0; i < nums.length; i++) {
                if(used[i] || i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) continue;
                cur.addLast(nums[i]);
                used[i] = true;
                permute(nums, res, cur, used);
                used[i] = false;
                cur.removeLast();
            }
        }
    }
}
