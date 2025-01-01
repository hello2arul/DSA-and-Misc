package Sorting;

import java.util.Arrays;

/*
Meta

Given an array of integers (which may include repeated integers), determine if there's a
way to split the array into two subsequences A and B such that the sum of the integers in 
both arrays is the same, and all of the integers in A are strictly smaller than all of the 
integers in B.
Note: Strictly smaller denotes that every integer in A must be less than, and not equal to,
every integer in B.

Signature
bool balancedSplitExists(int[] arr)

Input
All integers in array are in the range [0, 1,000,000,000].

Output
Return true if such a split is possible, and false otherwise.

Example 1
arr = [1, 5, 7, 1]
output = true
We can split the array into A = [1, 1, 5] and B = [7].

Example 2
arr = [12, 7, 6, 7, 6]
output = false
We can't split the array into A = [6, 6, 7] and B = [7, 12] since this doesn't satisfy the requirement that all integers in A are smaller than all integers in B.
 */
public class BalancedSplit {
    
     public boolean balancedSplitExists(int[] arr) {
        // Sort the array
        Arrays.sort(arr);

        // Calculate the total sum of the array
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        // If the total sum is odd, it's impossible to split into two equal parts
        if (totalSum % 2 != 0) {
            return false;
        }

        // Find the split point
        int runningSum = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            runningSum += arr[i];
            if (runningSum == totalSum / 2) {
                // Ensure the next element is strictly greater than the current element
                if (arr[i] < arr[i + 1]) {
                    return true;
                }
            }
        }

        return false;
    }
}
