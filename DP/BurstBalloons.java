package DP;

/*
https://leetcode.com/problems/burst-balloons/
*/
public class BurstBalloons {
    Map<String, Integer> memo = new HashMap<>();
    public int maxCoins(int[] nums) {
        int[] newNums = new int[nums.length + 2];
        newNums[0] = newNums[newNums.length - 1] = 1;
        for(int i = 1; i < newNums.length - 1; i++)
            newNums[i] = nums[i - 1];
        return maxCoins(newNums, 0, newNums.length - 1);
    }

    private int maxCoins(int[] nums, int start, int end) {
        String key = start + "," + end;
        if(memo.containsKey(key))
            return memo.get(key);
        if(start == end - 1)    return 0;
        int res = 0;
        for(int i = start + 1; i < end; i++) {
            res = Math.max(res, maxCoins(nums, start, i) + maxCoins(nums, i, end) +
                    nums[i] * nums[start] * nums[end]);
        }
        memo.put(key, res);
        return res;
    }
}
