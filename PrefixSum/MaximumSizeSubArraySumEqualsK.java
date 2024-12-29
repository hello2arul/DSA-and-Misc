package PrefixSum;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.ca/all/325.html
Google

Given an integer array nums and an integer k, return the maximum
length of a subarray that sums to k. If there isn't one, return 0 instead.

Example 1:
Input: nums = [1,-1,5,-2,3], k = 3
Output: 4
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.

Example 2:
Input: nums = [-2,-1,2,1], k = 1
Output: 2
Explanation: The subarray [-1, 2] sums to 1 and is the longest.

*/

public class MaximumSizeSubArraySumEqualsK {
    
    public int lenOfLongSubarr(int[] arr, int n, int k) {
        int prefixSum = 0; // prefix sum
        int result = 0;
        HashMap<Integer, Integer> map = new HashMap<>(); // {prefix sum, index}
        map.put(0, -1); // Initialize with 0 at index -1 to handle cases where the subarray starts from index 0
        
        for (int i = 0; i < n; i++) {
            prefixSum += arr[i];
            
            // If prefixSum - k exists in the map, then we found a subarray with sum = k
            if (map.containsKey(prefixSum - k)) {
                result = Math.max(result, i - map.get(prefixSum - k));
            }
            
            // Only add the current prefixSum to the map if it is not already present
            if (!map.containsKey(prefixSum)) {
                map.put(prefixSum, i);
            }
        }
        
        return result;
    }
}
