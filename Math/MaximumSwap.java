package Math;

/*
 * https://leetcode.com/problems/maximum-swap/description/
 * swap once to get the max number
 */
public class MaximumSwap {
    public int maximumSwap(int num) {
        char[] nums = String.valueOf(num).toCharArray();
        int[] lastSeen = new int[10];

        for (int i = 0; i < nums.length; i++) {
            lastSeen[nums[i] - '0'] = i;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = 9; j > nums[i] - '0'; j--) {
                if (lastSeen[j] > i) {
                    swap(nums, i, lastSeen[j]);
                    return Integer.valueOf(String.valueOf(nums));
                }
            }
        }

        return num;
    }

    private void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
