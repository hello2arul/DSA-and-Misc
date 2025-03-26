package BFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * https://leetcode.com/problems/word-ladder/description/
 * https://leetcode.com/problems/word-ladder-ii/description/
 * asked in amazon
 * BFS while finding words with 1diff
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> q = new LinkedList<>();

        q.offer(beginWord);
        int level = 0;
        Set<String> visited = new HashSet<>();

        while (!q.isEmpty()) {
            level++;
            for (int i = q.size(); i > 0; i--) {
                String cur = q.poll();
                visited.add(cur);

                if (cur.equals(endWord)) {
                    return level;
                }

                for (String s : getOneDiffWords(cur, wordList, visited)) {
                    q.offer(s);
                }
            }
        }

        return 0;
    }

    private List<String> getOneDiffWords(String cur, List<String> words, Set<String> visited) {
        List<String> results = new ArrayList<>();
        for (String word : words) {
            int diff = 0;
            if (!visited.contains(word) && cur.length() == word.length()) {
                for (int i = 0; i < cur.length(); i++) {
                    if (cur.charAt(i) != word.charAt(i)) {
                        if (++diff > 1)
                            break;
                    }
                }
            }
            if (diff == 1)
                results.add(word);
        }
        return results;
    }
}
