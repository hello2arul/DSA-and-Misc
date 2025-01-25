package String;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/longest-uncommon-subsequence-i/
https://leetcode.com/problems/longest-uncommon-subsequence-ii/
Google

Given two strings a and b, return the length of the longest uncommon subsequence between a and b. If no such uncommon subsequence exists, return -1.

An uncommon subsequence between two strings is a string that is a 
subsequence of exactly one of them.

Example 1:

Input: a = "aba", b = "cdc"
Output: 3
Explanation: One longest uncommon subsequence is "aba" because "aba" is a subsequence of 
"aba" but not "cdc".
Note that "cdc" is also a longest uncommon subsequence.

Example 2:

Input: a = "aaa", b = "bbb"
Output: 3
Explanation: The longest uncommon subsequences are "aaa" and "bbb".

Example 3:

Input: a = "aaa", b = "aaa"
Output: -1
Explanation: Every subsequence of string a is also a subsequence of string b. 
Similarly, every subsequence of string b is also a subsequence of string a. So the answer would be -1.

II

Example 1:

Input: strs = ["aba","cdc","eae"]
Output: 3
Example 2:

Input: strs = ["aaa","aaa","aa"]
Output: -1

 */

public class LongestUncommonSubsequence {

    // O(1), O(1)
    public int findLUSlength(String a, String b) {
        return a.equals(b) ? -1 : Math.max(a.length(), b.length());
    }

    // O(n^2)
    public int findLUSlength(String[] strs) {
        // if there are no duplicates, longest is the answer
        Arrays.sort(strs, (a, b) -> Integer.compare(b.length(), a.length()));
        Set<String> duplicates = getDuplicates(strs);

        for (int i = 0; i < strs.length; i++) {
            if (!duplicates.contains(strs[i])) {
                boolean isSubsequence = true;
                for (int j = 0; j < i; j++) {
                    if (isSubsequence(strs[j], strs[i])) {
                        isSubsequence = false;
                        break;
                    }
                }
                if (isSubsequence) {
                    return strs[i].length();
                }
            }
        }
        return -1;
    }

    private boolean isSubsequence(String a, String b) {
        int i = 0;
        int j = 0;
        while (i < a.length() && j < b.length()) {
            if (a.charAt(i) == b.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == b.length();
    }

    private Set<String> getDuplicates(String[] strs) {
        Set<String> visited = new HashSet<>();
        Set<String> duplicates = new HashSet<>();

        for (String s : strs) {
            if (!visited.add(s))
                duplicates.add(s);
        }
        return duplicates;
    }

}
