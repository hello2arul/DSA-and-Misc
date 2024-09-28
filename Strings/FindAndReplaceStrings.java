package Strings;

import java.util.HashMap;
import java.util.Map;

/*
 * https://leetcode.com/problems/find-and-replace-in-string
 * Input: s = "abcd", indices = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]
Output: "eeebffff"
Explanation:
"a" occurs at index 0 in s, so we replace it with "eee".
"cd" occurs at index 2 in s, so we replace it with "ffff".
 */
public class FindAndReplaceStrings {
     public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        StringBuilder res = new StringBuilder();
        int idx = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < indices.length; i++) {
            if (s.startsWith(sources[i], indices[i])) {
                map.put(indices[i], i);
            }
        }

        for (int i = 0; i < s.length(); ) {
            if (map.containsKey(i)) {
                res.append(targets[map.get(i)]);
                i += sources[map.get(i)].length();
            } else {
                res.append(s.charAt(i++));
            }
        }

        return res.toString();
    }
}
