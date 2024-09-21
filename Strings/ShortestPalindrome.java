package Strings;

/*
https://leetcode.com/problems/shortest-palindrome/description/

You are given a string s. You can convert s to a 
palindrome by adding characters in front of it.

Return the shortest palindrome you can find by performing this transformation.
*/
public class ShortestPalindrome {
    /*
    reverse the string and see how much they match.
        aab
        baa
        res: baab
    */
    public String shortestPalindrome(String s) {
        String rev = new StringBuilder(s).reverse().toString();

        for (int i = 0; i < s.length(); i++) {
            if (s.substring(0, s.length() - i).equals(rev.substring(i))) {
                return rev.substring(0, i) + s;
            }
        }
        return "";
    }
}
