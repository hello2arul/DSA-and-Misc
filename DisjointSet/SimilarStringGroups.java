package DisjointSet;

/*
https://leetcode.com/problems/similar-string-groups/

Two strings, X and Y, are considered similar if either they are identical or 
we can make them equivalent by swapping at most two letters (in distinct positions)
 within the string X.

For example, "tars" and "rats" are similar (swapping at positions 0 and 2), 
and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".

Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and 
{"star"}.  Notice that "tars" and "arts" are in the same group even though they 
are not similar.  Formally, each group is such that a word is in the group if and 
only if it is similar to at least one other word in the group.

We are given a list strs of strings where every string in strs is an 
anagram of every other string in strs. How many groups are there?
Example 1:

Input: strs = ["tars","rats","arts","star"]
Output: 2
Example 2:

Input: strs = ["omv","ovm"]
Output: 1
 */
public class SimilarStringGroups {
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        DisjointSet ds = new DisjointSet(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSimilar(strs[i], strs[j]))
                    ds.union(i, j);
            }
        }
        return ds.size;
    }

    private boolean isSimilar(String s1, String s2) {
        int n = 0;
        for (int i = 0; i < s1.length(); i++)
            if (s1.charAt(i) != s2.charAt(i) && ++n > 2)
                return false;
        return true;
    }
}
