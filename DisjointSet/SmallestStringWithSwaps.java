package DisjointSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
https://leetcode.com/problems/smallest-string-with-swaps
Google

You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.

You can swap the characters at any pair of indices in the given pairs any number of times.

Return the lexicographically smallest string that s can be changed to after using the swaps.

Example 1:

Input: s = "dcab", pairs = [[0,3],[1,2]]
Output: "bacd"
Explaination: 
Swap s[0] and s[3], s = "bcad"
Swap s[1] and s[2], s = "bacd"
 */
public class SmallestStringWithSwaps {

    // Time Complexity: O(n log n + m), where n is the length of the string and m is
    // the number of pairs.
    // Space Complexity: O(n), where n is the length of the string.
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        DisjointSet dj = new DisjointSet(n);

        for (List<Integer> idx: pairs) {
            dj.union(idx.get(0), idx.get(1));
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        StringBuilder sb = new StringBuilder(s);

        for (int i = 0; i < n; i++) {
            int root = dj.find(i);
            map.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }

        for (List<Integer> idx: map.values()) {
            // can use PriorityQueue instead of sorting
            List<Character> chars = new ArrayList<>();

            for (int i: idx)
                chars.add(s.charAt(i));
            
            Collections.sort(chars);
            int k = 0;
            for (int i: idx)
                sb.setCharAt(i, chars.get(k++));
        }
        return sb.toString();
    } 

    // O(n!) both time and space in worst case
    // if the input pairs explores all possible swaps
    public String smallestStringWithSwapsBFS(String s, List<List<Integer>> pairs) {
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        visited.add(s);
        q.offer(s);
        String res = s;

        while (!q.isEmpty()) {
            String cur = q.poll();

            for (List<Integer> idx: pairs) {
                String swapped = swap(new StringBuilder(cur), idx.get(0), idx.get(1));

                if (!visited.contains(swapped)) {
                    q.offer(swapped);
                    visited.add(swapped);

                    if (swapped.compareTo(res) < 0)
                        res = swapped;
                }
            }

        }

        return res;
    }

    private String swap(StringBuilder sb, int i, int j) {
        char ch1 = sb.charAt(i);
        char ch2 = sb.charAt(j);
        sb.setCharAt(i, ch2);
        sb.setCharAt(j, ch1);
        return sb.toString();
    }
}
