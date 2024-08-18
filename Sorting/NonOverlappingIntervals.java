package Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NonOverlappingIntervals {
      public int eraseOverlapIntervals(int[][] arr) {
        if (arr == null || arr.length <= 1)
            return 0;
        Arrays.sort(arr, (a, b) -> Integer.compare(a[1], b[1]));
        List<int[]> res = new ArrayList<>();
        res.add(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            int[] prev = res.get(res.size() - 1);
            int[] cur = arr[i];

            if (cur[0] >= prev[1]) {
                res.add(cur);
            }
        }

        return arr.length - res.size();
    }
}
