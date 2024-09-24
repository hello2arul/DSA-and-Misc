package Sorting;

/*
 * https://leetcode.com/problems/first-missing-positive/
 * Given an unsorted integer array nums. Return the smallest positive integer 
 * that is not present in nums.

You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.

1. index sort
 */
public class FindMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int i = 0;

        while (i < n) {
            if (nums[i] <= 0 || nums[i] >= n || nums[i] == i + 1)
                i++;
            else if (nums[i] != nums[nums[i] - 1])
                swap(nums, i, nums[i] - 1);
            else
                i++;
        }
        i = 0;
        while (i < n && nums[i] == i + 1)
            i++;
        return i + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
