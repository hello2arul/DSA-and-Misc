package String;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/unique-length-3-palindromic-subsequences
Google, Amazon, Meta, Bloomberg

Given a string s, return the number of unique palindromes of length three 
that are a subsequence of s.

Note that even if there are multiple ways to obtain the same subsequence, 
it is still only counted once.

A palindrome is a string that reads the same forwards and backwards.

A subsequence of a string is a new string generated from the original string
with some characters (can be none) deleted without changing the relative order
of the remaining characters.

For example, "ace" is a subsequence of "abcde".
 

Example 1:

Input: s = "aabca"
Output: 3
Explanation: The 3 palindromic subsequences of length 3 are:
- "aba" (subsequence of "aabca")
- "aaa" (subsequence of "aabca")
- "aca" (subsequence of "aabca")
Example 2:

Input: s = "adc"
Output: 0
Explanation: There are no palindromic subsequences of length 3 in "adc".
Example 3:

Input: s = "bbcbaba"
Output: 4
Explanation: The 4 palindromic subsequences of length 3 are:
- "bbb" (subsequence of "bbcbaba")
- "bcb" (subsequence of "bbcbaba")
- "bab" (subsequence of "bbcbaba")
- "aba" (subsequence of "bbcbaba")
 */

public class UniqueLength3PalindromicSubsequence {

    public int countPalindromicSubsequence(String s) {
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        // Track first and last occurrences of each character
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            if (first[idx] == -1)
                first[idx] = i;
            last[idx] = i;
        }

        int res = 0;

        // Check for palindromic subsequences
        for (int i = 0; i < 26; i++) {
            if (first[i] != -1 && first[i] < last[i]) {
                res += getUniqueCharsBetween(s, first[i], last[i]);
            }
        }
        return res;
    }

    private int getUniqueCharsBetween(String s, int i, int j) {
        Set<Character> set = new HashSet<>();
        for (int k = i + 1; k < j; k++) {
            set.add(s.charAt(k));
        }
        return set.size();
    }
}
