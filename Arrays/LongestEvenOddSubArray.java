package Arrays;
//https://leetcode.com/problems/longest-even-odd-subarray-with-threshold/

public class LongestEvenOddSubArray {
    public int longestAlternatingSubarray(int[] nums) {
        int max = 1;
        int current = 1;
        for(int i = 1; i < nums.length; i++) {
            if((nums[i] % 2 == 0 && nums[i -1] % 2 != 0) ||
            (nums[i - 1] % 2 == 0 && nums[i] % 2 != 0)) {
                current++;
                max = Math.max(current, max);
            } else {
                current = 1;
            }
        }
        return max;
    }
}
