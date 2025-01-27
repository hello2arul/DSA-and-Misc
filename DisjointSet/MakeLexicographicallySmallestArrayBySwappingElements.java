package DisjointSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DisjointSet.DisjointSet;

/*
https://leetcode.com/problems/make-lexicographically-smallest-array-by-swapping-elements/

You are given a 0-indexed array of positive integers nums and a positive integer limit.

In one operation, you can choose any two indices i and j and swap nums[i] and nums[j] if
|nums[i] - nums[j]| <= limit.

Return the lexicographically smallest array that can be obtained by performing the operation 
any number of times.

An array a is lexicographically smaller than an array b if in the first position where a and b differ
array a has an element that is less than the corresponding element in b. 
For example, the array [2,10,3] is lexicographically smaller than the array [10,2,3]
because they differ at index 0 and 2 < 10.

Example 1:

Input: nums = [1,5,3,9,8], limit = 2
Output: [1,3,5,8,9]
Explanation: Apply the operation 2 times:
- Swap nums[1] with nums[2]. The array becomes [1,3,5,9,8]
- Swap nums[3] with nums[4]. The array becomes [1,3,5,8,9]
We cannot obtain a lexicographically smaller array by applying any more operations.
Note that it may be possible to get the same result by doing different operations.

Example 2:

Input: nums = [1,7,6,18,2,1], limit = 3
Output: [1,6,7,18,1,2]
Explanation: Apply the operation 3 times:
- Swap nums[1] with nums[2]. The array becomes [1,6,7,18,2,1]
- Swap nums[0] with nums[4]. The array becomes [2,6,7,18,1,1]
- Swap nums[0] with nums[5]. The array becomes [1,6,7,18,1,2]
We cannot obtain a lexicographically smaller array by applying any more operations.

Example 3:

Input: nums = [1,7,28,19,10], limit = 3
Output: [1,7,28,19,10]
Explanation: [1,7,28,19,10] is the lexicographically smallest array we can obtain
because we cannot apply the operation on any two indices.
*/
public class MakeLexicographicallySmallestArrayBySwappingElements {

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        DisjointSet uf = new DisjointSet(n);

        // Sort the indices by the value of nums
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        // Sort indices by corresponding values in nums
        // this is the optimization over the bubble sort O(n^2) -> O(nlogn)
        Arrays.sort(indices, (a, b) -> Integer.compare(nums[a], nums[b]));

        // Union adjacent elements whose difference is less than or equal to limit
        for (int i = 0; i < n - 1; i++) {
            if (Math.abs(nums[indices[i]] - nums[indices[i + 1]]) <= limit) {
                uf.union(indices[i], indices[i + 1]);
            }
        }

        // Group elements by their root
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            groups.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }

        // Sort each group and place the elements back in the array
        for (List<Integer> group : groups.values()) {
            List<Integer> values = new ArrayList<>();
            for (int index : group) {
                values.add(nums[index]);
            }
            // could use prioity queue instead of sorting
            Collections.sort(values);
            for (int i = 0; i < group.size(); i++) {
                nums[group.get(i)] = values.get(i);
            }
        }

        return nums;
    }

    public int[] lexicographicallySmallestArrayBubbleSort(int[] nums, int limit) {
        boolean swapped = true;

        while (swapped) {
            swapped = false;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (nums[i] > nums[j] && Math.abs(nums[i] - nums[j]) <= limit) {
                        swap(nums, i, j);
                        swapped = true;
                    }
                }
            }
        }

        return nums;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
