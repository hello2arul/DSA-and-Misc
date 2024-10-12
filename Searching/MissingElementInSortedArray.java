package Sorting;

import java.util.HashSet;

/*
 * https://leetcode.ca/all/1060.html

Input: nums = [4,7,9,10], k = 3
Output: 8
Explanation: The missing numbers are [5,6,8,...], hence the third missing number is 8.

Input: A = [1,2,4], K = 3
Output: 6
Explanation: 
The missing numbers are [3,5,6,7,...], hence the third missing number is 6.
 */
public class MissingElementInSortedArray {
     public int missingElement(int[] nums, int k) {
        // Use a HashSet for O(1) average time complexity for contains check
        HashSet<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        // Starting from the first element and checking for missing elements
        int current = nums[0];
        int missingCount = 0;

        while (true) {
            if (!numSet.contains(current)) {
                missingCount++;
                if (missingCount == k) {
                    return current; // Return the current number when k missing numbers are found
                }
            }
            current++;
        }
    }

    public int missingElementBinarySearch(int[] nums, int k) {
        int n = nums.length;

        // Binary search to find the missing element
        int left = 0, right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            // Calculate the number of missing elements up to mid
            // [1, 2, 4] => 3 / 2 = 1, k = 3
            // 2 - 1 - 1 = 0
            int missingCount = nums[mid] - nums[0] - mid;

            // If the number of missing elements is less than k,
            // it means the missing element is in the right half
            if (missingCount < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // The missing element will be at the left index
        // Since `left` is the first index where we have at least `k` missing numbers
        return nums[0] + k + left - 1;
    }
}
