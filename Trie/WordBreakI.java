package Trie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * https://leetcode.com/problems/word-break/description/
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
 */
public class WordBreakI {
    class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;

        TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    TrieNode root;
    WordBreakI() {
        root = new TrieNode();
    }

    public TrieNode getRoot() {
        return root;
    }

    public void insert(String s) {
        TrieNode cur = root;
        for(char ch: s.toCharArray()) {
            TrieNode node = cur.children.get(ch);
            if(node == null) {
                node = new TrieNode();
                cur.children.put(ch, node);
            }
            cur = node;
        }
        cur.endOfWord = true;
    }

    public boolean canBreakdfs(String s, int idx, TrieNode node, Map<String, Boolean> memo) {
        String key = idx + ", " + node;
        if(memo.containsKey(key)) 
            return memo.get(key);
        if(node == null)    
            return false;
        if(idx == s.length())   
            return node.endOfWord;
        char ch = s.charAt(idx);
        TrieNode childNode = node.children.get(ch);
        if(childNode != null) {
            if(childNode.endOfWord) {
                memo.put(key, canBreakdfs(s, idx + 1, root, memo) || 
                              canBreakdfs(s, idx + 1, childNode, memo));
            } else {
                memo.put(key, canBreakdfs(s, idx + 1, childNode, memo));
            }
        } else {
            memo.put(key, false);
        }
        return memo.get(key);
    }

    public boolean canBreakbfs(String s, Set<String> dict) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        q.offer(0);

        while(!q.isEmpty()) {
            int start = q.poll();
            if(visited[start])  continue;
            for(int end = start + 1; end <= s.length(); end++) {
                if(dict.contains(s.substring(start, end))) {
                    q.offer(end);
                    if(end == s.length())
                        return true;
                }
            }
            visited[start] = true;
        }
        return false;
    }
}
