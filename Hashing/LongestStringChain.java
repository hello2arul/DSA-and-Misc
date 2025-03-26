package Hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/longest-string-chain/description/
Google

You are given an array of words where each word consists of lowercase English letters.

wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in 
wordA without changing the order of the other characters to make it equal to wordB.

For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
Return the length of the longest possible word chain with words chosen from the given list of words.

Example 1:

Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest word chains is ["a","ba","bda","bdca"]
 */
public class LongestStringChain {
    
    int max = 0;
    Map<String, Integer> memo = new HashMap<>();

    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
        for (int i = 0; i < words.length; i++) {
            longestChainHelper(words, i, 1);
        }
        return max;
    }

    private void longestChainHelper(String[] words, int idx, int curLen) {
        String cur = words[idx];

        if (memo.containsKey(cur)) {
            max = Math.max(max, memo.get(cur) + curLen - 1);
            return;
        }

        max = Math.max(max, curLen);

        for (int i = idx + 1; i < words.length; i++) {
            if (isValid(cur, words[i])) {
                longestChainHelper(words, i, curLen + 1);
            }
        }

        memo.put(cur, curLen);
    }

    private boolean isValid(String a, String b) {
        if (b.length() - a.length() != 1)
            return false;
        int i = 0;
        for (int j = 0; j < b.length() && i < a.length(); j++) {
            if (a.charAt(i) == b.charAt(j)) {
                i++;
            }
        }
        return i == a.length();
    }

    // O(n * m^2), where n is the number of words and m is the maximum length of a word
    public int longestStrChainMemo(String[] words) {
        int maxLen = 0;
        Arrays.sort(words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
        Map<String, Integer> memo = new HashMap<>();

        for (String word : words) {
            int curLen = 1;
            
            for (int i = 0; i < word.length(); i++) {
                String cur = word.substring(0, i) + word.substring(i + 1);
                
                if (memo.containsKey(cur)) {
                    curLen = Math.max(curLen, memo.get(cur) + 1);
                }
            }
            memo.put(word, curLen);
            maxLen = Math.max(maxLen, curLen);
        }

        return maxLen;
    }
}
