package BackTracking;

import java.util.HashSet;
import java.util.Set;

/*
 * https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings
 
Given a string s, return the maximum number of unique substrings that the
given string can be split into.

You can split string s into any list of non-empty substrings, 
where the concatenation of the substrings forms the original string.
However, you must split the substrings such that all of them are unique.

A substring is a contiguous sequence of characters within a string.

Input: s = "ababccc"
Output: 5
Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc'].
Splitting like ['a', 'b', 'a', 'b', 'c', 'cc'] is not valid as you have 
'a' and 'b' multiple times.
 */
public class SpitAStringIntoTheMaxNumberOfUniqueSubstrings {
    public int maxUniqueSplit(String s) {
        return backtrack(s, 0, new HashSet<>(), 0);
    }

    private int backtrack(String s, int idx, Set<String> set, int maxFound) {
        if (idx == s.length()) {
            return set.size();
        }

        int maxUnique = maxFound;
        for (int i = idx + 1; i <= s.length(); i++) {
            String sub = s.substring(idx, i);
            if (!set.contains(sub)) {
                set.add(sub);
                maxUnique = Math.max(maxUnique, backtrack(s, i, set, maxUnique));
                set.remove(sub);

                // Pruning: If the current max + remaining characters is not promising
                if (set.size() + (s.length() - i) <= maxUnique) {
                    break; // Not enough characters left to exceed current maximum
                }
            }
        }

        return maxUnique;
    }
}
