// https://leetcode.com/problems/maximum-difference-between-increasing-elements/description/

class MaxDiff {
    public int maximumDifference(int[] nums) {
        int res = -1;
        int min = nums[0];

        for(int i = 1; i < nums.length; i++) {
            res = Math.max(res, nums[i] - min);
            min = Math.min(min, nums[i]);
        }
        return res == 0 ? -1 : res;
    }
}