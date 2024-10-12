package SlidingWindow;


/*
 * applicable for II also which is k = 1
 * https://leetcode.ca/all/487.html
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 */
public class MaxConsecutiveOnesIII {

    // O(n^2)
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int maxOnes = 0;

        for (int i = 0; i < n; i++) {
            int zeros = 0;

            for (int j = i; j < n; j++) {
                if (nums[j] == 0) {
                    zeros++;
                }

                if (zeros > k) {
                    break;
                }

                maxOnes = Math.max(maxOnes, j - i + 1);
            }
        }

        return maxOnes;
    }

    public int longestOnesSlidingWindow(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int zeroes = 0;
        int max = 0;

        for (right = 0; right < nums.length; right++) {
            if (nums[right] == 0)
                zeroes++;
            if (zeroes > k) {
                if (nums[left++] == 0)
                    zeroes--;
            }
            if (zeroes <= k) {
                max = Math.max(max, right - left + 1);
            }
        }

        return max;
    }
}
