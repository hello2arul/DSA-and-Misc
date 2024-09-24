package BitManipulation;

/*
 * https://leetcode.com/problems/single-number-iii
 * Given an integer array nums, in which exactly two elements appear only once and 
 * all the other elements appear exactly twice. Find the two elements that appear only once.
 *  You can return the answer in any order.

You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
 */
public class SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        int[] res = new int[2];
        int xor = 0;

        for (int num : nums)
            xor ^= num;
        int lsb = xor & (-xor);
        // XOR numbers into two groups to get the res
        // [1,2,1,3,2,5]
        // lsb = 0010
        // Eg) first group - > 2, 3, 2
        for (int num : nums) {
            if ((lsb & num) == 0) {
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }

        return res;
    }
}
