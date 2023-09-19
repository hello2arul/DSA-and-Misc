//https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/

import java.util.HashMap;
import java.util.Map;

public class MaximumSumSubarrayK {
    //sliding window without distinct elements
    public long maximumSubarraySumKNonDistinct(int[] nums, int k) {
        int max = 0;
        int currentSum = 0;

        for (int i = 0; i < k; i++) {
            currentSum += nums[i];
        }
        max = currentSum;

        for(int i = k; i < nums.length; i++) {
            currentSum += nums[i];
            currentSum -= nums[i - k];
            max = Math.max(currentSum, max);
        }
        return max;
    }

    //sliding window with  distinct elements
    public long maximumSubarraySumKDistinct(int[] nums, int k) {
        long max = 0;
        long currentSum = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < k; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            currentSum += nums[i];
        }

        if(map.size() == k) {
            max = currentSum;
        }
        
        for(int i = k; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            map.put(nums[i - k], map.get(nums[i - k]) - 1);
            if(map.get(nums[i - k]) <= 0) 
                map.remove(nums[i - k]);
            currentSum += nums[i];
            currentSum -= nums[i - k];
            if(map.size() == k) 
                max = Math.max(currentSum, max);
        }
        return max;
    }
}
