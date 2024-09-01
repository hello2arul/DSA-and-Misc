package Sorting;

/*
https://leetcode.com/problems/median-of-two-sorted-arrays/description/
must be O(log(m+n)) TODO

*/
public class MedianOfTwoSortedArrays {
    // O(n) O(n)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] nums = new int[m + n];
        
        int i = 0, j = 0, k = 0;
        
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                nums[k++] = nums1[i++];
            } else {
                nums[k++] = nums2[j++];
            }
        }
        
        while (i < m) {
            nums[k++] = nums1[i++];
        }
        
        while (j < n) {
            nums[k++] = nums2[j++];
        }
        
        int totalLength = m + n;

        if (totalLength % 2 == 0) {
            return (nums[(totalLength / 2) - 1] + nums[totalLength / 2]) / 2.0;
        } else {
            return nums[totalLength / 2];
        }
    }
}
