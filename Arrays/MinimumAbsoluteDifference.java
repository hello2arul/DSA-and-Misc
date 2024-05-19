package Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * https://leetcode.com/problems/minimum-absolute-difference
 */
public class MinimumAbsoluteDifference {
     public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        int min_diff = Integer.MAX_VALUE;
        Arrays.sort(arr);
        for(int i = 0; i < arr.length - 1; i++) {
            int diff = arr[i+1] - arr[i];
            if(diff < min_diff) {
                min_diff = diff;
                result.clear();
                result.add(Arrays.asList(arr[i], arr[i + 1]));
            }else if(diff == min_diff) {
                result.add(Arrays.asList(arr[i], arr[i + 1]));
            }
        }
        return result;
    }
}
