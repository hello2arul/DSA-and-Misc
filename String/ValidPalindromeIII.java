package String;

/*
https://leetcode.ca/all/1216.html
Meta

Given a string s and an integer k, find out if the given string is a K-Palindrome or not.

A string is K-Palindrome if it can be transformed into a palindrome by 
removing at most k characters from it.

Example 1:

Input: s = "abcdeca", k = 2
Output: true
Explanation: Remove 'b' and 'e' characters.
 */
public class ValidPalindromeIII {

    public boolean isValidPalindrome(String s, int k) {
        // same as LongestPalindromicSubsequence.java
        return isValidPalindromeHelper(s, 0, s.length() - 1, k);
    }

    // can be optimized by using memoization
    private boolean isValidPalindromeHelper(String s, int i, int j, int k) {
        if (i >= j) {
            return true;
        }
        if (s.charAt(i) == s.charAt(j)) {
            return isValidPalindromeHelper(s, i + 1, j - 1, k);
        } else {
            if (k == 0) {
                return false;
            }
            return isValidPalindromeHelper(s, i + 1, j, k - 1) || isValidPalindromeHelper(s, i, j - 1, k - 1);
        }
    }
}
