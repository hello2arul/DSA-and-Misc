package BackTracking;

import java.util.ArrayList;
import java.util.List;
//https://leetcode.com/problems/permutations/
public class PermutateArray {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permute(nums, res, new ArrayList<>());
        return res;
    }

    private void permute(int[] nums, List<List<Integer>> res, 
                        List<Integer> cur) {
        if(cur.size() == nums.length) {
            res.add(new ArrayList<>(cur));
        } else {
            for(int i = 0; i < nums.length; i++) {
                if(cur.contains(nums[i]))
                    continue;
                cur.add(nums[i]);
                permute(nums, res, cur);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
