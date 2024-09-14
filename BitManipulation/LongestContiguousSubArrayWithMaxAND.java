package BitManipulation;

/**
 * https://leetcode.com/problems/longest-subarray-with-maximum-bitwise-and/
 */
public class LongestContiguousSubArrayWithMaxAND {
    public int longestSubarray(int[] nums) {
        int max = 0;
        int res = 0;
        int len = 0;

        for (int num : nums) {
            max = Math.max(max, num);
        }
        
        for (int num : nums) {
            if (max == num)
                res = Math.max(res, ++len);
            else
                len = 0; // remove this for non contiguous
        }
        return res;
    }
    
}