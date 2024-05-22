package PrefixSum;//https://leetcode.com/problems/contiguous-array/description/

import java.util.HashMap;
import java.util.Map;

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

    /*
     * And at any index, we encounter a sum that's already been encountered at some previous index, 
     * it means that the number of 0's and 1's are equal between the indices corresponding to the equal
     * sum values
     */
    public int findMaxLengthOptimized(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) nums[i] = -1;
        }
        // magical hashing solution
        Map<Integer, Integer> sumToIndex = new HashMap<>();
        sumToIndex.put(0, -1);
        int sum = 0, max = 0;
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sumToIndex.containsKey(sum)) {
                max = Math.max(max, i - sumToIndex.get(sum));
            }
            else {
                sumToIndex.put(sum, i);
            }
        }
        
        return max;
    }
}
