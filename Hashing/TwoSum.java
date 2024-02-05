package Hashing;

import java.util.HashMap;
import java.util.Map;

/*
* https://leetcode.com/problems/two-sum/description/
* */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int [] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }

        // for (int i = 0; i < nums.length; i++) {
        //     for (int j = i + 1; j < nums.length; j++) {
        //         if (nums[i] + nums[j] == target) {
        //             return new int[] {i, j};
        //         }
        //     }
        // }
        return new int[] {-1, -1};
    }
}
