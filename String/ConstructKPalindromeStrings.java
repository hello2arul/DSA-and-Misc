package String;

/*
https://leetcode.com/problems/construct-k-palindrome-strings

Given a string s and an integer k, return true if you can use all the characters in s 
to construct k palindrome strings or false otherwise.

Example 1:

Input: s = "annabelle", k = 2
Output: true
Explanation: You can construct two palindromes using all characters in s.
Some possible constructions "anna" + "elble", "anbna" + "elle", "anellena" + "b"
Example 2:

Input: s = "leetcode", k = 3
Output: false
Explanation: It is impossible to construct 3 palindromes using all the characters of s.
Example 3:

Input: s = "true", k = 4
Output: true
Explanation: The only possible solution is to put each character in a separate string.
 */
public class ConstructKPalindromeStrings {

    /*
     * a palindrome can have at most 1 odd frequency character
     * eg
     * aabb, k=1| abba
     * aabb, k=2 | aa, bb
     * aabb, k=3 | a, a, bb
     * aabb, k=4 | a, a, b, b
     * 
     * For any string with odd character count <=k , we can always form k palindrome
     * string for sure with k<=n
     * eg2
     * aabbc, k=1 | aacbb
     * aabbc, k=2 | aca, bb
     * aabbc, k=3 | a,a, bcb
     * aabbc, k=4 | a, a, c ,bb
     * aabbc, k=5 | a, a, c, b, b
     * 
     * eg3
     * aabc, k=1 | N/A
     * aabc, k=2 | aba, c
     * aabc, k=3 | aa, b, c
     * aabc, k=4 | a, a, b, c
     */
    public boolean canConstruct(String s, int k) {
        if (s.length() < k)
            return false;
        if (s.length() == k)
            return true;
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        int odd = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                odd++;
            }
        }
        return odd <= k;
    }
}