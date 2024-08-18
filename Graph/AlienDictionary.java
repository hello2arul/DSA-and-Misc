package Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. 
 * You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. 
 * Derive the order of letters in this language.
 
  Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"

Input:
[
  "z",
  "x"
]

Output: "zx"

Input:
[
  "z",
  "x",
  "z"
]

Output: "" 

Explanation: The order is invalid, so return "".
Note:

You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
 */

public class AlienDictionary {
    Map<Character, Set<Character>> adj;
    Map<Character, Integer> indegrees;

    public AlienDictionary() {
        adj = new HashMap<>();
        indegrees = new HashMap<>();
    }

    public String alienOrder(String[] words) {
        for (String word: words) {
            for (int i = 0; i < word.length(); i++) {
                adj.putIfAbsent(word.charAt(i), new HashSet<>());
                indegrees.put(word.charAt(i), 0);
            }
        }

        for (int i = 1; i < words.length; i++) {
            String first = words[i - 1];
            String second = words[i];
            int len = Math.min(first.length(), second.length());

            for (int j = 0; j < len; j++) {
                char out = first.charAt(j);
                char in = second.charAt(j);

                if (out != in) {
                    adj.get(out).add(in);
                    indegrees.put(in, indegrees.getOrDefault(in, 0) + 1);
                    break;
                }
            }
        }

        StringBuilder res = new StringBuilder();
        Queue<Character> q = new LinkedList<>();

        // toposort
        for (char key: indegrees.keySet()) {
            if (indegrees.get(key) == 0) {
                q.offer(key);
            }
        }

        while (!q.isEmpty()) {
            char ch = q.poll();
            res.append(ch);

            for (char n: adj.get(ch)) {
                indegrees.put(n, indegrees.get(n) - 1);
            
                if (indegrees.get(n) == 0) {
                    q.offer(n);
                }
            }

        }
        return res.length() == adj.size() ? res.toString() : "";
    }

    public static void main(String[] args) {
    }
}
