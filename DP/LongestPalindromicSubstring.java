package DP;

/*
 * https://leetcode.com/problems/longest-palindromic-substring/description/
 */
public class LongestPalindromicSubstring {

    /**
     * Consider each idx as middle of palindrome and expand,
     * two cases
     * 1. aa
     * 2. aba
     * O(n^2) since we don't generate all substrings
     */
    public String longestPalindromeOptimized(String s) {
        String res = "";
        int start = 0;
        int end = 1;
        int maxLen = 0;

        for (int i = 1; i < s.length(); i++) {
            int[] odd = getPalindrome(s, i - 1, i + 1);
            int[] even = getPalindrome(s, i - 1, i);
            int[] longest = even[1] - even[0] > odd[1] - odd[0] ? even : odd;

            if (longest[1] - longest[0] > maxLen) {
                maxLen = longest[1] - longest[0];
                start = longest[0];
                end = longest[1];
            }
        }
        return s.substring(start, end);
    }

    private int[] getPalindrome(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return new int[] { i + 1, j };
    }

    // O(n^3)
    public String longestPalindrome(String s) {
        String res = "";

        for (int i = 0; i < s.length(); i++) {
            int j = s.length() - 1;

            while (j > i && j - i + 1 > res.length() &&
                    !isPalindrome(s, i, j)) {
                j--;
            }
            int newLen = j - i + 1;

            if (newLen > res.length()) {
                res = s.substring(i, j + 1);
            }
        }
        return res;
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    // O(n^2)
    public String longestPalindromeDP(String s) {
        if (s.length() <= 1) {
            return s;
        }

        int maxLen = 1;
        int start = 0;
        int end = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); ++i) {
            dp[i][i] = true;
            for (int j = 0; j < i; ++j) {
                if (s.charAt(j) == s.charAt(i) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                    if (i - j + 1 > maxLen) {
                        maxLen = i - j + 1;
                        start = j;
                        end = i;
                    }
                }
            }
        }

        return s.substring(start, end + 1);
    }
}
