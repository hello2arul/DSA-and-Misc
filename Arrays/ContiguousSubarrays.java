package Arrays;

import java.util.Stack;

/*
Meta

You are given an array arr of N integers. For each index i, 
you are required to determine the number of contiguous subarrays 
that fulfill the following conditions:

The value at index i must be the maximum element in the contiguous subarrays, and
These contiguous subarrays must either start from or end on index i.

Signature
int[] countSubarrays(int[] arr)

Input
Array arr is a non-empty list of unique integers that range between 1 to 1,000,000,000
Size N is between 1 and 1,000,000
Output
An array where each index i contains an integer denoting the maximum number of contiguous 
subarrays of arr[i]

Example:
arr = [3, 4, 1, 6, 2]
output = [1, 3, 1, 5, 1]
Explanation:
For index 0 - [3] is the only contiguous subarray that starts (or ends) at 
index 0 with the maximum value in the subarray being 3.
For index 1 - [4], [3, 4], [4, 1]
For index 2 - [1]
For index 3 - [6], [6, 2], [1, 6], [4, 1, 6], [3, 4, 1, 6]
For index 4 - [2]
So, the answer for the above input is [1, 3, 1, 5, 1]
 */
public class ContiguousSubarrays {

    public static int[] countSubarrays(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int count = 1;
            int j = i - 1;
            while (j >= 0 && arr[j] < arr[i]) {
                count++;
                j--;
            }
            j = i + 1;
            while (j < n && arr[j] < arr[i]) {
                count++;
                j++;
            }
            result[i] = count;
        }
        return result;
    }

    public static int[] countSubarraysStack(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Calculate left span
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                stack.pop();
            }
            // If the stack is empty after popping,
            // it means all elements to the left are smaller, so result[i] = i + 1.
            // eg) [1, 2, 3, 4] -> result = [1, 2, 3, 4] (i + 1)
            // If the stack is not empty, the top of the stack is the
            // nearest element to the left that is greater than or equal to arr[i],
            // so result[i] = i - stack.peek().
            // eg) [4, 3, 2, 1] -> result = [1, 1, 1, 1] (i - stack.peek())
            result[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        stack.clear();

        // Calculate right span and add to result
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                stack.pop();
            }
            /*
             * Why -1?
             * For the empty stack case:
             * 
             * n - i - 1 gives the count of elements to the right of i.
             * For the non-empty stack case:
             * 
             * stack.peek() - i - 1 gives the count of elements between i and the nearest
             * greater or equal element to the right.
             * We subtract 1 because stack.peek() is the index of the nearest greater or
             * equal element, and we want the count of elements strictly between i and
             * stack.peek().
             * Example
             * Consider the array [3, 4, 1, 6, 2] and focus on the element 4 at index 1:
             * 
             * Right span calculation for 4:
             * The nearest greater element to the right is 6 at index 3.
             * The elements between 4 and 6 are [1] (only one element).
             * Therefore, result[1] += 3 - 1 - 1 = 1.
             */
            result[i] += stack.isEmpty() ? n - i - 1 : stack.peek() - i - 1;
            stack.push(i);
        }

        return result;
    }
}
