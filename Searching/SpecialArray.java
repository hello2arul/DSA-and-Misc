package Searching;

import java.util.ArrayList;

/*
https://leetcode.com/problems/special-array-i/
https://leetcode.com/problems/special-array-ii/

An array is considered special if every pair of its adjacent elements 
contains two numbers with different parity.

You are given an array of integer nums and a 2D integer matrix queries, 
where for queries[i] = [fromi, toi] your task is to check that 
subarray nums[fromi..toi] is special or not.

Return an array of booleans answer such that answer[i] is true if nums[fromi..toi] is special.

Example 1:

Input: nums = [3,4,1,2,6], queries = [[0,4]]

Output: [false]

Explanation:

The subarray is [3,4,1,2,6]. 2 and 6 are both even.

Example 2:

Input: nums = [4,3,1,6], queries = [[0,2],[2,3]]

Output: [false,true]
 */

public class SpecialArray {

    public boolean isArraySpecial(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if ((nums[i] % 2 == nums[i - 1] % 2))
                return false;
        }
        return true;
    }

    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        boolean[] ans = new boolean[queries.length];
        ArrayList<Integer> violatingIndices = new ArrayList<>();

        for (int i = 1; i < nums.length; i++) {
            // same parity, found violating index
            if (nums[i] % 2 == nums[i - 1] % 2) {
                violatingIndices.add(i);
            }
        }

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int start = query[0];
            int end = query[1];

            boolean foundViolatingIndex = binarySearch(
                    start + 1,
                    end,
                    violatingIndices);

            ans[i] = !foundViolatingIndex;
        }

        return ans;
    }

    private boolean binarySearch(
            int start,
            int end,
            ArrayList<Integer> violatingIndices) {
        int left = 0;
        int right = violatingIndices.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int violatingIndex = violatingIndices.get(mid);

            if (violatingIndex < start) {
                // check right half
                left = mid + 1;
            } else if (violatingIndex > end) {
                // check left half
                right = mid - 1;
            } else {
                // violatingIndex falls in between start and end
                return true;
            }
        }

        return false;
    }

    public boolean[] isArraySpecialPrefixSearch(int[] nums, int[][] queries) {
        boolean[] ans = new boolean[queries.length];
        int[] prefix = new int[nums.length];
        prefix[0] = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] % 2 == nums[i - 1] % 2) {
                // new violative index found
                prefix[i] = prefix[i - 1] + 1;
            } else {
                prefix[i] = prefix[i - 1];
            }
        }

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int start = query[0];
            int end = query[1];

            ans[i] = prefix[end] - prefix[start] == 0;
        }

        return ans;
    }
}
