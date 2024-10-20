package Strings;
/*
 * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
 * 
 * 1. RabinKarp -> only compare substrings if their hash values are matching
 * 2. KnuthMorrisPratt ->
 */

public class SubstringSearch {
    //fails when repetition is present
    public int strStr(String s, String sub) {
        int m = s.length();
        int n = sub.length();

        for(int i = 0; i <= m - n;) {
            int j = 0;
            while(j < n && s.charAt(i + j) == sub.charAt(j)) {
                j++;
            }
            // System.out.println(i + ", " + j);
            if(j == n) {
                return i;
            } else if(j == 0) {
                i++;
            } else {
                i += j;
            }
        }
        return -1;
    }
}
