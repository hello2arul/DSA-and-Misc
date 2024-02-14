package PrefixSum;//https://leetcode.com/problems/contiguous-array/description/


public class LongestSubArrayEqual01 {
    /*
     * brute force: O(n^2)
     */
    public int findMaxLength(int[] nums) {
        int maxLen = 0;
        for(int i = 0; i < nums.length; i++) {
            int zeroes = 0, ones = 0;
            for(int j = i; j < nums.length; j++) {
                if(nums[j] == 0)
                    zeroes++;
                else
                    ones++;
                if(zeroes == ones)
                    maxLen = Math.max(maxLen, j - i + 1);
            }
        }
        return maxLen;
    }
}
