package String;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://leetcode.com/problems/number-of-matching-subsequences
Google

Given a string s and an array of strings words, return the number of 
words[i] that is a subsequence of s.

A subsequence of a string is a new string generated from the original 
string with some characters (can be none) deleted without changing the
relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
 
Example 1:

Input: s = "abcde", words = ["a","bb","acd","ace"]
Output: 3
Explanation: There are three strings in words that are a subsequence of s: 
"a", "acd", "ace".

Example 2:

Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
Output: 2

*/
public class NumberOfMatchingSubsequences {

    /*
     * 1. just call isSubsequence(s, word) -> O(n * m)
     * 2.
     */
    public int numMatchingSubseq(String s, String[] words) {
        int res = 0;

        for (String word : words) {
            if (isSubsequence(s, word)) {
                res++;
            }
        }

        return res;
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

     public int numMatchingSubseqBs(String s, String[] words) {
        // Step 1: Preprocess string s
        Map<Character, List<Integer>> charPositions = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            charPositions.computeIfAbsent(s.charAt(i), k -> new ArrayList<>()).add(i);
        }

        // Step 2: Check each word in words
        int result = 0;
        
        for (String word : words) {
            int lastIndex = -1;  // Start with an invalid index
            
            boolean isSubsequence = true;
            for (char c : word.toCharArray()) {
                if (!charPositions.containsKey(c)) {
                    isSubsequence = false;
                    break;
                }
                
                // Find the next position of character `c` after lastIndex using binary search
                List<Integer> positions = charPositions.get(c);
                int pos = binarySearch(positions, lastIndex);
                
                if (pos == positions.size()) {
                    isSubsequence = false;
                    break;
                }
                
                lastIndex = positions.get(pos);  // Update the last index to the found position
            }

            if (isSubsequence) {
                result++;
            }
        }
        
        return result;
    }
    
    // Helper function to perform binary search for the next position > lastIndex
    private int binarySearch(List<Integer> positions, int lastIndex) {
        int low = 0, high = positions.size();
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (positions.get(mid) > lastIndex) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        
        return low;
    }

    // TLE
    private int linearSearch(List<Integer> positions, int lastIndex) {
        for (int pos : positions) {
            if (pos > lastIndex) {
                return pos;
            }
        }
        return -1;
    }
}
