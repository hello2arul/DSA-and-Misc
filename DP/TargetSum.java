package DP;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/target-sum/

You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and 
'-' before each integer in nums and then concatenate all the integers.

For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and 
concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.

Example 1:

Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3

Example 2:

Input: nums = [1], target = 1
Output: 1
 

Constraints:

1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000

*/
public class TargetSum {
    // O(n * sum)
    public int findTargetSumWays(int[] nums, int target) {
        Map<Integer, Integer> sumToCount = new HashMap<>();
        sumToCount.put(0, 1); // sum = 0 when no nums are selected

        for (int num : nums) {
            Map<Integer, Integer> temp = new HashMap<>(); // to prevent failing the sumToCount iteration

            for (Map.Entry<Integer, Integer> entry : sumToCount.entrySet()) {
                int sum = entry.getKey();
                int count = entry.getValue();
                int plus = sum + num;
                int minus = sum - num;

                temp.put(plus, temp.getOrDefault(plus, 0) + count);
                temp.put(minus, temp.getOrDefault(minus, 0) + count);
            }
            sumToCount = temp;
        }   
        return sumToCount.getOrDefault(target, 0);
    }

    // O(n * sum)
    private int findTargetSumWays(int[] nums, int target, int idx, int sum, Map<String, Integer> cache) {
        if (idx == nums.length) {
            return sum == target ? 1 : 0;
        }
        String key = idx + "," + sum;
        if (cache.containsKey(key))
            return cache.get(key);
        int count = 0;
        count += findTargetSumWays(nums, target, idx + 1, sum - nums[idx], cache);

        count += findTargetSumWays(nums, target, idx + 1, sum + nums[idx], cache);
        cache.put(key, count);
        return count;
    }

    // 2^N
    private int findTargetSumWays(int[] nums, int target, int idx, int sum) {
        if (idx >= nums.length) {
            return sum == target ? 1 : 0;
        }
        int plus = findTargetSumWays(nums, target, idx + 1, sum + nums[idx]);
        int minus = findTargetSumWays(nums, target, idx + 1, sum - nums[idx]);
        return plus + minus;
    }
}
