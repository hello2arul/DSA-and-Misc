package Sorting;

/*
 * https://leetcode.com/problems/minimum-absolute-difference/
 * Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements.
 */
public class MinimumAbsoluteDifference {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        int minDiff = Integer.MAX_VALUE;
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(arr);

        for (int i = 1; i < arr.length; i++) {
            int diff = arr[i] - arr[i - 1];
            
            if (diff < minDiff) {
                minDiff = diff;
                res.clear();
                res.add(Arrays.asList(arr[i - 1], arr[i]));
            } else if (diff == minDiff) {
                res.add(Arrays.asList(arr[i - 1], arr[i]));
            }
        }
        return res;
    }
}
