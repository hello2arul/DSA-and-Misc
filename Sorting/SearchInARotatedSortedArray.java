package Sorting;

/*
 * https://leetcode.com/problems/search-in-rotated-sorted-array/description/
*/
public class SearchInARotatedSortedArray {
    
    public int search(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    public int binarySearch(int[] nums, int target, int left, int right) {
        if (left > right)
            return -1;
        int mid = (left + right) / 2;
        int midNum = nums[mid];
        if (midNum == target)
            return mid;
        else if (nums[left] <= midNum) {
            if (target < midNum && target >= nums[left])
                return binarySearch(nums, target, left, mid - 1);
            else
                return binarySearch(nums, target, mid + 1, right);
        } else {
            if (target > midNum && target <= nums[right])
                return binarySearch(nums, target, mid + 1, right);
            else
                return binarySearch(nums, target, left, mid - 1);
        }
    }
}
