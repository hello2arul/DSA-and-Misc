package BitManipulation;

/*
https://leetcode.com/problems/hamming-distance
https://leetcode.com/problems/minimum-bit-flips-to-convert-number

The Hamming distance between two integers is the number of positions at 
which the corresponding bits are different.

Given two integers x and y, return the Hamming distance between them.

 

Example 1:

Input: x = 1, y = 4
Output: 2
Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑
The above arrows point to positions where the corresponding bits are different.
Example 2:

Input: x = 3, y = 1
Output: 1
 */

public class HammingDistance {

    public int hammingDistance(int x, int y) {
        int res = 0;
        int m = x ^ y; // take the xor of two numbers, diff bits will be set
        while (m != 0) { // count the no of "1"s
            if ((m & 1) == 1)
                res++;
            m = m >> 1;
        }
        return res;
    }
}
