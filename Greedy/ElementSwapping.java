package Greedy;

/*
Meta

Given a sequence of n integers arr, determine the lexicographically smallest sequence 
which may be obtained from it after performing at most k element swaps, each involving a
pair of consecutive elements in the sequence.
Note: A list x is lexicographically smaller than a different equal-length list y if and 
only if, for the earliest index at which the two lists differ, x's element at that index 
is smaller than y's element at that index.

Signature
int[] findMinArray(int[] arr, int k)

Input
n is in the range [1, 1000].
Each element of arr is in the range [1, 1,000,000].
k is in the range [1, 1000].

Output
Return an array of n integers output, the lexicographically smallest sequence achievable after at most k swaps.

Example 1
n = 3
k = 2
arr = [5, 3, 1]
output = [1, 5, 3]
We can swap the 2nd and 3rd elements, followed by the 1st and 2nd elements, to end up with the sequence [1, 5, 3]. This is the lexicographically smallest sequence achievable after at most 2 swaps.

Example 2
n = 5
k = 3
arr = [8, 9, 11, 2, 1]
output = [2, 8, 9, 11, 1]
We can swap [11, 2], followed by [9, 2], then [8, 2].
 */

public class ElementSwapping {
    
    // find the min element at K distance
    // O(n * k)
    public int[] findMinArray(int[] arr, int k) {
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            int min = arr[i];
            int idx = i;
            for(int j = i + 1; j < n && j - i <= k; j++) {
                if(arr[j] < min) {
                    min = arr[j];
                    idx = j;
                }
            }
            for(int j = idx; j > i; j--) {
                swap(arr, j, j - 1);
            }
            k -= idx - i;
        }
        return arr;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
