package Trie;

import java.util.Map;
import java.util.HashMap;

//https://leetcode.com/problems/implement-trie-prefix-tree/
class PrefixTrie {
    class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;
        int wordCount;

        TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
            wordCount = 0;
        }

        @Override
        public String toString() {
            return children + ", " + endOfWord;
        }
    }

    private TrieNode root;

    public PrefixTrie() {
        root = new TrieNode();
    }
    
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
        cur.wordCount++;
    }
    
    public boolean search(String word) {
        TrieNode cur = root;
        for(char ch: word.toCharArray()) {
            TrieNode node = cur.children.get(ch);
            if(node == null) {
                return false;
            }
            cur = node;
        }
        return cur.endOfWord;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for(char ch: prefix.toCharArray()) {
            TrieNode node = cur.children.get(ch);
            if(node == null) {
                return false;
            }
            cur = node;
        }
        return cur != null;
    }

    private TrieNode delete(TrieNode root, String word, int idx) {
        if(root == null) {
            return null;
        }
        if(idx == word.length()) {
            if(root.endOfWord) {
                root.endOfWord = false;
                root.wordCount = 0;
            }
            if(root.children.size() == 0) {
                root = null;
            }
            return root;
        }

        root.children.put(word.charAt(idx), delete(root.children.get(word.charAt(idx)), word, idx + 1));
        if(root.children.size() == 0) {
                root = null;
        }
        return root;
    }

    public TrieNode delete(String word) {
        return delete(root, word, 0);
    }

    public static void main(String[] args) {
        PrefixTrie trie = new PrefixTrie();
        String[] words = new String[] {"abc", "abd"};
        for(String word: words)
            trie.insert(word);
        System.out.println(trie);
        trie.delete("abc");
        System.out.println(trie);
    }

    @Override
    public String toString() {
        return root.toString();
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */