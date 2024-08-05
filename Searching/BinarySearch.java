package Searching;

/**
 * https://leetcode.com/problems/binary-search/description/
 * works only when sorted O(logn)
 */

public class BinarySearch {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            int num = nums[mid];
            if(num == target)       return mid;
            else if(num > target)   right = mid - 1;
            else                    left = mid + 1;
        }
        return -1;
    }

    public int binarySearchRecursive(int[] nums, int target, int left, int right) {
        if(left > right)    return -1;
        int mid = (left + right) / 2;
        if(nums[mid] == target)    return mid;
        else if(nums[mid] < target)
            return binarySearchRecursive(nums, target, mid + 1, right);
        else
            return binarySearchRecursive(nums, target, left, mid - 1);
    }
}
