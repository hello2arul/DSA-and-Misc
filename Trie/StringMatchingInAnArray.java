package Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
https://leetcode.com/problems/string-matching-in-an-array/description/

Given an array of string words, return all strings in words that is a 
substring of another word. You can return the answer in any order.

A substring is a contiguous sequence of characters within a string

Example 1:

Input: words = ["mass","as","hero","superhero"]
Output: ["as","hero"]
Explanation: "as" is substring of "mass" and "hero" is substring of "superhero".
["hero","as"] is also a valid answer.

Example 2:

Input: words = ["leetcode","et","code"]
Output: ["et","code"]
Explanation: "et", "code" are substring of "leetcode".

Example 3:

Input: words = ["blue","green","bu"]
Output: []
Explanation: No string of words is substring of another string.

1. Suffix Trie
2. KMP - TODO
*/
public class StringMatchingInAnArray {

    public List<String> stringMatchingKMP(String[] words) {
        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
        List<String> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (kmp(words[j], words[i])) {
                    res.add(words[i]);
                    break;
                }
            }
        }
        return res;
    }

    public int[] lps(String s) {
        int n = s.length();
        int[] lps = new int[n];
        int len = 0;
        lps[0] = 0;
        int i = 1;

        while (i < n) {
            if (s.charAt(i) == s.charAt(len)) {
                lps[i] = len + 1;
                i++;
                len++;
            } else {
                if (len == 0) {
                    lps[i] = 0;
                    i++;
                } else {
                    len = lps[len - 1];
                }
            }
        }
        return lps;
    }

    public boolean kmp(String txt, String pattern) {
        // 1. Construct lps array for pattern
        int[] lps = lps(pattern);
        int n = txt.length();
        int m = pattern.length();
        int i = 0, j = 0;

        while (i < n) {
            if (txt.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            if (j == m) {
                return true;

            } else if (i < n && txt.charAt(i) != pattern.charAt(j)) {
                if (j == 0) {
                    i++;
                } else {
                    j = lps[j - 1];
                }
            }
        }
        return false;
    }

    public List<String> stringMatching(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                trie.add(word.substring(i));
            }
        }
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (trie.isSubstring(word)) {
                res.add(word);
            }
        }
        return res;
    }
}

class Trie {

    class TrieNode {
        Map<Character, TrieNode> children;
        int freq;

        TrieNode() {
            freq = 0;
            children = new HashMap<>();
        }
    }

    TrieNode root = new TrieNode();

    public void add(String s) {
        TrieNode cur = root;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            cur.children.putIfAbsent(ch, new TrieNode());
            cur = cur.children.get(ch);
            cur.freq++;
        }
    }

    public boolean isSubstring(String s) {
        TrieNode cur = root;
        for (char ch : s.toCharArray()) {
            cur = cur.children.get(ch);
        }
        return cur.freq > 1;
    }
}