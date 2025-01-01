package SlidingWindow;

/*
https://leetcode.com/problems/defuse-the-bomb

You have a bomb to defuse, and your time is running out! 
Your informer will provide you with a circular array code of length of n and a key k.

To decrypt the code, you must replace every number. All the numbers are replaced simultaneously.

If k > 0, replace the ith number with the sum of the next k numbers.
If k < 0, replace the ith number with the sum of the previous k numbers.
If k == 0, replace the ith number with 0.
As code is circular, the next element of code[n-1] is code[0], and the previous element of code[0] is code[n-1].

Given the circular array code and an integer key k, return the decrypted code to defuse the bomb!

 

Example 1:

Input: code = [5,7,1,4], k = 3
Output: [12,10,16,13]
Explanation: Each number is replaced by the sum of the next 3 numbers. 
The decrypted code is [7+1+4, 1+4+5, 4+5+7, 5+7+1]. Notice that the numbers wrap around.
Example 2:

Input: code = [1,2,3,4], k = 0
Output: [0,0,0,0]
Explanation: When k is zero, the numbers are replaced by 0. 
Example 3:

Input: code = [2,4,9,3], k = -2
Output: [12,5,6,13]
Explanation: The decrypted code is [3+9, 2+3, 4+2, 9+4]. Notice that the numbers wrap around again. 
If k is negative, the sum is of the previous numbers.
 */

public class DefuseTheBomb {

    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] result = new int[n];
        if (k == 0) {
            return result;
        }
        for (int i = 0; i < n; i++) {
            int sum = 0;
            if (k > 0) {
                for (int j = 1; j <= k; j++) {
                    sum += code[(i + j) % n];
                }
            } else {
                for (int j = 1; j <= Math.abs(k); j++) {
                    sum += code[(i - j + n) % n];
                }
            }
            result[i] = sum;
        }
        return result;
    }

    public int[] decryptOptimized(int[] code, int k) {
        int n = code.length;
        int[] result = new int[n];

        if (k == 0) {
            return result; // Return an array of zeros
        }

        int start = k > 0 ? 1 : n + k;
        int end = start + Math.abs(k) - 1;
        int windowSum = 0;

        // Initialize the window sum
        for (int i = start; i <= end; i++) {
            windowSum += code[i % n];
        }

        // Slide the window across the array
        for (int i = 0; i < n; i++) {
            result[i] = windowSum;
            windowSum -= code[start % n];
            start++;
            end++;
            windowSum += code[end % n];
        }

        return result;
    }
}
