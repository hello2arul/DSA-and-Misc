package TwoPointers;

// https://leetcode.com/problems/maximum-width-ramp/
class MaximumWidthRamp {
    public int maxWidthRamp(int[] nums) {
        // max difference in idx i and j where i < j and nums[i] <= nums[j]
        int n = nums.length;
        int maxWidth = 0;
        int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--)
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);

        int left = 0;
        for (int i = 0; i < n; i++) {
            while(left < i && nums[left] > rightMax[i])
                left++;                         
            maxWidth = Math.max(maxWidth, i - left);
        }
        return maxWidth;
    }
}