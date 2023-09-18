//https://leetcode.com/problems/max-consecutive-ones/

public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int current = 0;

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                res = Math.max(current, res);
                current = 0;
            } else {
                current++;
            }
        }
        return Math.max(res, current);
    }
}
