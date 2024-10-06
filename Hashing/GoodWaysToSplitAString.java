package Hashing;

import java.util.HashSet;
import java.util.Set;

/*
 * Google
 * https://leetcode.com/problems/number-of-good-ways-to-split-a-string/description/.
 * A split is called good if you can split s into two non-empty strings sleft and sright 
 * where their concatenation is equal to s (i.e., sleft + sright = s) and .
 * the number of distinct letters in sleft and sright is the same.

Return the number of good splits you can make in s.
Input: s = "aacaba"
Output: 2
Explanation: There are 5 ways to split "aacaba" and 2 of them are good. 
("a", "acaba") Left string and right string contains 1 and 3 different letters respectively.
("aa", "caba") Left string and right string contains 1 and 3 different letters respectively.
("aac", "aba") Left string and right string contains 2 and 2 different letters respectively (good split).
("aaca", "ba") Left string and right string contains 2 and 2 different letters respectively (good split).
("aacab", "a") Left string and right string contains 3 and 1 different letters respectively.
 * 
*/
public class GoodWaysToSplitAString {
    public int numSplits(String s) {
        int n = s.length();
        Set<Character> charsFound = new HashSet<>();
        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 0; i < n; i++) {
            charsFound.add(s.charAt(i));
            left[i] = charsFound.size();
        }
        charsFound.clear();
        for (int i = n - 1; i >= 0; i--) {
            charsFound.add(s.charAt(i));
            right[i] = charsFound.size();
        }
        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            if (left[i] == right[i + 1])
                res++;
        }
        return res;
    }
}
