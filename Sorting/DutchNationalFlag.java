package Sorting;

/*
 * https://leetcode.com/problems/sort-colors/description/
 */

public class DutchNationalFlag {
    public void sortColors(int[] nums) {
        int left = 0;
        int mid = 0;
        int right = nums.length - 1;

        while(mid <= right) {
            switch(nums[mid]) {
                case 0:
                    swap(nums, left, mid);
                    left++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    swap(nums, mid, right);
                    right--;
                    break;
            }
        }
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
