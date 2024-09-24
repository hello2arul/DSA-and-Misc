package BitManipulation;

/*
 * https://leetcode.com/problems/single-number/
 * Given a non-empty array of integers nums, 
 * every element appears twice except for one. Find that single one.
 * 1. Hashing
 * 2. Sort and check adjacent
 * 3. XOR
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int result = 0;
        for(int num : nums)
            result ^= num;
        return result;
        
    }
}
