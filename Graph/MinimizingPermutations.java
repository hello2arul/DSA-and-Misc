package Graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
Meta

In this problem, you are given an integer N, and a permutation, 
P of the integers from 1 to N, denoted as (a_1, a_2, ..., a_N). 
You want to rearrange the elements of the permutation into increasing order,
repeatedly making the following operation:
Select a sub-portion of the permutation, (a_i, ..., a_j), and reverse its order.
Your goal is to compute the minimum number of such operations required to return the 
permutation to increasing order.

Signature
int minOperations(int[] arr)

Input
Array arr is a permutation of all integers from 1 to N, N is between 1 and 8

Output
An integer denoting the minimum number of operations required to arrange the permutation in increasing order

Example
If N = 3, and P = (3, 1, 2), we can do the following operations:
Select (1, 2) and reverse it: P = (3, 2, 1).
Select (3, 2, 1) and reverse it: P = (1, 2, 3).
output = 2
 */

public class MinimizingPermutations {

    // O(N!) O(N!)
    public int minOperations(int[] arr) {
        int n = arr.length;
        int[] target = new int[n];
        for (int i = 0; i < n; i++) {
            target[i] = i + 1;
        }

        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(arr);
        visited.add(Arrays.toString(arr));

        int operations = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                if (Arrays.equals(current, target)) {
                    return operations;
                }

                for (int j = 0; j < n; j++) {
                    for (int k = j + 1; k < n; k++) {
                        int[] next = reverseSubarray(current, j, k);
                        if (visited.add(Arrays.toString(next))) {
                            queue.offer(next);
                        }
                    }
                }
            }
            operations++;
        }

        return -1; // Should never reach here if input is a valid permutation
    }

    private int[] reverseSubarray(int[] arr, int start, int end) {
        int[] result = arr.clone();
        while (start < end) {
            int temp = result[start];
            result[start] = result[end];
            result[end] = temp;
            start++;
            end--;
        }
        return result;
    }
}