package Sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
/*
 * Given a size N array, for every K sized rolling window in the array, find the minimum absolute difference between any two elements in the K sized window.
 * Uber
 * O(logK)
 */
public class MinAbsDiffInRollingWindow {
    public static List<Integer> minAbsDiffInWindow(int[] arr, int K) {
        List<Integer> result = new ArrayList<>();
        if (K > arr.length || K < 2) {
            return result; // Invalid case
        }

        TreeSet<Integer> window = new TreeSet<>();

        for (int i = 0; i < arr.length; i++) {
            // Add the current element to the window
            window.add(arr[i]);

            // Remove the element that is sliding out of the window
            if (i >= K) {
                window.remove(arr[i - K]);
            }

            // Once we have a full window, calculate the minimum absolute difference
            if (i >= K - 1) {
                int minDiff = Integer.MAX_VALUE;
                Integer prev = null;
                for (Integer current : window) {
                    if (prev != null) {
                        minDiff = Math.min(minDiff, current - prev);
                    }
                    prev = current;
                }
                result.add(minDiff);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 6, 19, 20};
        int K = 3;
        List<Integer> result = minAbsDiffInWindow(arr, K);
        System.out.println(result); // Output: [2, 3, 1]
    }
}
