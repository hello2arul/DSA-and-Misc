package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * https://leetcode.com/problems/concatenated-words
 * Given an array of strings words (without duplicates), return all the concatenated words in the given list of words.

A concatenated word is defined as a string that is comprised entirely of at least two shorter words (not necessarily distinct) in the given array.

 

Example 1:

Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
"dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
Example 2:

Input: words = ["cat","dog","catdog"]
Output: ["catdog"]
 */
public class ConcatenatedWords {
      public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        Arrays.sort(words, (s1, s2)-> Integer.compare(s1.length(), s2.length()));
        
        Set<String> dict = new HashSet<>();
        for(String word: words) {
            if(canBreak(word, dict)) {
                res.add(word);
            }
            dict.add(word);
        }
        return res;
    }
    
    private boolean canBreak(String s, Set<String> words) {
        boolean[] canBreak = new boolean[s.length() + 1];  
        canBreak[0] = true;
        for(int i = 1; i <= s.length(); i++) {
            for(int j = 0; j < i ; j++) {
                if(canBreak[j] && words.contains(s.substring(j, i))) {
                    canBreak[i] = true;
                    break;
                }
            }
        }
        return s.length() > 0 && canBreak[s.length()];
    }

     class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;

        TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode cur = root;
        for(char ch: word.toCharArray()) {
            TrieNode node = cur.children.get(ch);
            if(node == null) {
                node = new TrieNode();
                cur.children.put(ch, node);
            }
            cur = node;
        }
        cur.endOfWord = true;
    }

    public boolean canBreakIntoOtherWords(String word) {
        return canBreakIntoOtherWords(word, 0, 0) >= 1;
    }

    private int canBreakIntoOtherWords(String word, int idx, int count) {
        TrieNode node = root;
        for(int i = idx; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode child = node.children.get(ch);
            if(child == null)   return -1;
            if(child.endOfWord) {
                if(i + 1 == word.length())  return count;
                else {
                    int subRes= canBreakIntoOtherWords(word, i + 1, count + 1);
                    if (subRes > 0) return subRes;
                }
            }
            node = child;
        }
        return -1;
    }
}
