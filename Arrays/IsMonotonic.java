package Arrays;

/*
 * https://leetcode.com/problems/monotonic-array/description/
 * asked in amazon
 */
public class IsMonotonic {
    public boolean isMonotonic(int[] nums) {
        if(nums == null || nums.length <= 1)
            return true;
        //doesn't handle case when nums[0] == nums[1]
        boolean isIncreasing = nums[0] < nums[1];

        for(int i = 1; i < nums.length; i++) {
            if(isIncreasing) {
                if(nums[i - 1] >  nums[i])
                    return false;
            } else {
                if(nums[i] > nums[i - 1])
                    return false;
            }
        }
        return true;
    }
}
