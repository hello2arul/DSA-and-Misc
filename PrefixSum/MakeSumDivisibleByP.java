package PrefixSum;

import java.util.HashMap;

/*
 * https://leetcode.com/problems/make-sum-divisible-by-p/
 * Given an array of positive integers nums, remove the smallest subarray (possibly empty)
 *  such that the sum of the remaining elements is divisible by p.
 *  It is not allowed to remove the whole array.

Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.

A subarray is defined as a contiguous block of elements in the array.
Input: nums = [3,1,4,2], p = 6
Output: 1
Explanation: The sum of the elements in nums is 10, which is not divisible by 6. 
We can remove the subarray [4], and the sum of the remaining elements is 6, which is divisible by 6.
*/
public class MakeSumDivisibleByP {
    public int minSubarrayBruteForce(int[] nums, int p) {
        // get the sum
        // rem = sum % p
        // subarray sum that adds to rem
        long sum = 0;
        int n = nums.length;
        int minLen = n;

        for (int num : nums)
            sum += num;
        
        long rem = sum % p;
        
        if (rem == 0)
            return 0;

        for (int i = 0; i < n; i++) {
            long curSum = 0;
            for (int j = i; j < n; j++) {
                curSum += nums[j];
                long curRem = (sum - curSum) % p;

                if (curRem == 0) {
                    minLen = Math.min(minLen, j - i + 1);
                }
            }
        }
        
        return minLen == n ? -1 : minLen;
    }

    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        int totalSum = 0;

        // Step 1: Calculate total sum and target remainder
        for (int num : nums) {
            totalSum = (totalSum + num) % p;
        }

        int target = totalSum % p;
        if (target == 0) {
            return 0; // The array is already divisible by p
        }

        // Step 2: Use a hash map to track prefix sum mod p
        HashMap<Integer, Integer> modMap = new HashMap<>();
        modMap.put(0, -1); // To handle the case where the whole prefix is the answer
        int currentSum = 0;
        int minLen = n;

        // Step 3: Iterate over the array
        for (int i = 0; i < n; ++i) {
            currentSum = (currentSum + nums[i]) % p;

            // Calculate what we need to remove
            int needed = (currentSum - target + p) % p;

            // If we have seen the needed remainder, we can consider this subarray
            if (modMap.containsKey(needed)) {
                minLen = Math.min(minLen, i - modMap.get(needed));
            }

            // Store the current remainder and index
            modMap.put(currentSum, i);
        }

        // Step 4: Return result
        return minLen == n ? -1 : minLen;
    }
}
