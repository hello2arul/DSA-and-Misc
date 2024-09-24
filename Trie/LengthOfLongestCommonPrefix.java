package Trie;

import java.util.HashSet;
import java.util.Set;

/*
* https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix/
Input: arr1 = [1,10,100], arr2 = [1000]
Output: 3
Explanation: There are 3 pairs (arr1[i], arr2[j]):
- The longest common prefix of (1, 1000) is 1.
- The longest common prefix of (10, 1000) is 10.
- The longest common prefix of (100, 1000) is 100.
The longest common prefix is 100 with a length of 3.

TODO: trie
*/

public class LengthOfLongestCommonPrefix {
     public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<Integer> set = new HashSet<>();
        int res = 0;

        for (int num : arr1) {
            while (num > 0) {
                set.add(num);
                num /= 10;
            }
        }

        for (int num : arr2) {
            while (num > 0 && !set.contains(num))
                num /= 10;
            if (num > 0) {
                res = Math.max(res, (int)Math.log10(num) + 1);
            }
        }
        return res;
    }
}
