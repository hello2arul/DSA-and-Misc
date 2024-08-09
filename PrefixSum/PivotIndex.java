package PrefixSum;

//https://leetcode.com/problems/find-pivot-index/

public class PivotIndex {
    public int pivotIndex(int[] nums) {
        int sum = 0, left = 0;

        for(int num: nums)
            sum += num;
        for(int i = 0; i < nums.length; i++) {
            // left == right
            if(left == sum - nums[i] - left)
                return i;
            left += nums[i];
        }
        return -1;
    }
}
