package String;

import java.util.HashSet;
import java.util.Set;

/*
Meta

Given two strings s and t of length N, find the maximum number of possible matching pairs in strings
s and t after swapping exactly two characters within s.
A swap is switching s[i] and s[j], where s[i] and s[j] denotes the character that is present at 
the ith and jth index of s, respectively. The matching pairs of the two strings are defined as 
the number of indices for which s[i] and t[i] are equal.

Note: This means you must swap two characters at different indices.

Signature
int matchingPairs(String s, String t)

Input
s and t are strings of length N
N is between 2 and 1,000,000

Output
Return an integer denoting the maximum number of matching pairs

Example 1
s = "abcd"
t = "adcb"
output = 4

Explanation:
Using 0-based indexing, and with i = 1 and j = 3, s[1] and s[3] can be swapped, making it  "adcb".
Therefore, the number of matching pairs of s and t will be 4.

Example 2
s = "mno"
t = "mno"

output = 1

Explanation:
Two indices have to be swapped, regardless of which two it is, only one letter will remain the same. If i = 0 and j=1, s[0] and s[1] are swapped, making s = "nmo", which shares only "o" with t.
 */

public class MatchingPairs {
    
     public int matchingPairs(String s, String t) {
        int n = s.length();
        int initialMatches = 0;
        Set<String> unmatchedPairs = new HashSet<>();
        Set<Character> unmatchedS = new HashSet<>();
        Set<Character> unmatchedT = new HashSet<>();

        // Count initial matches and collect unmatched pairs
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == t.charAt(i)) {
                initialMatches++;
            } else {
                unmatchedPairs.add(s.charAt(i) + "" + t.charAt(i));
                unmatchedS.add(s.charAt(i));
                unmatchedT.add(t.charAt(i));
            }
        }

        // If all characters match, we need to reduce the count by 2 if possible
        if (initialMatches == n) {
            return initialMatches - 2;
        }

        // Check for beneficial swaps
        for (String pair : unmatchedPairs) {
            char sChar = pair.charAt(0);
            char tChar = pair.charAt(1);
            if (unmatchedPairs.contains(tChar + "" + sChar)) {
                return initialMatches + 2;
            }
        }

        // Check for swaps that result in one new match
        for (char sChar : unmatchedS) {
            if (unmatchedT.contains(sChar)) {
                return initialMatches + 1;
            }
        }

        // If no beneficial swap is found, return the initial matches
        return initialMatches;
    }
}
