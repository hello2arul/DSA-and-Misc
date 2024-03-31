package Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * https://leetcode.com/problems/word-search-ii
 */
public class WordSearchII {
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        Set<String> res = new HashSet<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                dfs(board, i, j, root, res);
            }
        }
        return new ArrayList<>(res);
    }

    private void dfs(char[][] board, int i, int j, TrieNode cur, Set<String> res) {
        char ch = board[i][j];
        if (ch == '#' || !cur.children.containsKey(ch))  return;
        cur = cur.children.get(ch);
        
        if (cur.word != null) {
            res.add(cur.word);
            cur.word = null;
        }

        board[i][j] = '#';
        if (i > 0)  dfs(board, i - 1, j, cur, res);
        if (j > 0)  dfs(board, i, j - 1, cur, res);
        if (i < board.length - 1)   dfs(board, i + 1, j, cur, res);
        if (j < board[i].length - 1) dfs(board, i, j + 1, cur, res);
        board[i][j] = ch;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word: words) {
            TrieNode cur = root;

            for (char ch: word.toCharArray()) {
                TrieNode next = cur.children.get(ch);
                if (next == null) {
                    next = new TrieNode();
                    cur.children.put(ch, next);
                }
                cur = next;
            }
            cur.word = word;
        }
        return root;
    }

    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word = null;
    }
}
