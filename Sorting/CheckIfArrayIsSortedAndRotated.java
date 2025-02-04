package Sorting;

/*
https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/description/

Example 1:

Input: nums = [3,4,5,1,2]
Output: true
Explanation: [1,2,3,4,5] is the original sorted array.
You can rotate the array by x = 3 positions to begin on the the element of value 3: [3,4,5,1,2].
Example 2:

Input: nums = [2,1,3,4]
Output: false
Explanation: There is no sorted array once rotated that can make nums.
Example 3:

Input: nums = [1,2,3]
Output: true
Explanation: [1,2,3] is the original sorted array.
You can rotate the array by x = 0 positions (i.e. no rotation) to make nums.
*/
public class CheckIfArrayIsSortedAndRotated {
    
    public boolean check(int[] nums) {
        int n = nums.length;
        int dropCount = 0;

        // Start from i = 1 and compare nums[i-1] with nums[i]
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] > nums[i]) {
                dropCount++;
                // If more than one drop is found, return false
                if (dropCount > 1) {
                    return false;
                }
            }
        }

        // Now handle the circular comparison between the first and last element
        if (nums[n - 1] > nums[0]) {
            dropCount++;
        }

        // If more than one drop, return false
        return dropCount <= 1;
    }
}
