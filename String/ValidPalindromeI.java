package String;

/*
https://leetcode.com/problems/valid-palindrome
Meta

A phrase is a palindrome if, after converting all uppercase letters into lowercase 
letters and removing all non-alphanumeric characters, it reads the same forward and backward.

Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.

Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
Example 3:

Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.
 */

public class ValidPalindromeI {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        for (int i = 0, j = s.length() - 1; i < j;) {
            boolean left = Character.isLetterOrDigit(s.charAt(i));
            boolean right = Character.isLetterOrDigit(s.charAt(j));
            if (left && right) {
                if (s.charAt(i) == s.charAt(j)) {
                    i++;
                    j--;
                } else {
                    return false;
                }
            } else if (left && !right) {
                j--;
            } else if (!left && right) {
                i++;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }
}
