package Trie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * https://leetcode.com/problems/word-break-ii/
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word.
 *  Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:

Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]
Example 2:

Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.
 */
public class WordBreakII {
    class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;

        TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    TrieNode root = new TrieNode();

    public void add(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = cur.children.get(ch);

            if (node == null) {
                node = new TrieNode();
                cur.children.put(ch, node);
            }
            cur = node;
        }
        cur.endOfWord = true;
    }

    public void search(String s, TrieNode cur, StringBuilder sb, int idx, List<String> res) {
        if (idx == s.length())
            return;
        char ch = s.charAt(idx);
        TrieNode next = cur.children.get(ch);
        if (next == null)
            return;
        sb.append(ch);

        if (next.endOfWord) {
            if (idx + 1 == s.length()) {
                res.add(sb.toString());
                sb.deleteCharAt(sb.length() - 1);
                return;
            } else {
                sb.append(' ');
                search(s, root, sb, idx + 1, res);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        search(s, next, sb, idx + 1, res);
        sb.deleteCharAt(sb.length() - 1);
    }
}
