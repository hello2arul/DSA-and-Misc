package Trie;

/*
 * https://leetcode.com/problems/sum-of-prefix-scores-of-strings/
 * Input: words = ["abc","ab","bc","b"]
Output: [5,4,3,2]
Explanation: The answer for each string is the following:
- "abc" has 3 prefixes: "a", "ab", and "abc".
- There are 2 strings with the prefix "a", 2 strings with the prefix "ab", and 1 string with the prefix "abc".
The total is answer[0] = 2 + 2 + 1 = 5.
- "ab" has 2 prefixes: "a" and "ab".
- There are 2 strings with the prefix "a", and 2 strings with the prefix "ab".
The total is answer[1] = 2 + 2 = 4.
- "bc" has 2 prefixes: "b" and "bc".
- There are 2 strings with the prefix "b", and 1 string with the prefix "bc".
The total is answer[2] = 2 + 1 = 3.
- "b" has 1 prefix: "b".
- There are 2 strings with the prefix "b".
The total is answer[3] = 2.
 */
public class SumOfPrefixScores {
    public int[] sumPrefixScores(String[] words) {
        int[] res = new int[words.length];
        Trie trie = new Trie();

        for (String word : words)
            trie.add(word);
        
        for (int i = 0; i < words.length; i++) {
            res[i] = trie.getPrefixCount(words[i]);
        }

        return res;
    }

    public int getPrefixCount(String s) {
        TrieNode cur = root;
        int prefixCount = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            TrieNode node = cur.children.get(ch);
            prefixCount += node.count;
            cur = node;
        }
        return prefixCount;
    }
}