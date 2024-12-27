package DisjointSet;
/*
https://leetcode.com/problems/lexicographically-smallest-equivalent-string/

You are given two strings of the same length s1 and s2 and a string baseStr.

We say s1[i] and s2[i] are equivalent characters.

For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c', 'b' == 'd', and 'c' == 'e'.

Example 1:

Input: s1 = "parker", s2 = "morris", baseStr = "parser"
Output: "makkek"
Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [m,p], [a,o], [k,r,s], [e,i].
The characters in each group are equivalent and sorted in lexicographical order.
So the answer is "makkek".

Example 2:

Input: s1 = "hello", s2 = "world", baseStr = "hold"
Output: "hdld"
Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [h,w], [d,e,o], [l,r].
So only the second letter 'o' in baseStr is changed to 'd', the answer is "hdld".

Example 3:

Input: s1 = "leetcode", s2 = "programs", baseStr = "sourcecode"
Output: "aauaaaaada"
Explanation: We group the equivalent characters in s1 and s2 as [a,o,e,r,s,c], 
[l,p], [g,t] and [d,m], thus all letters in baseStr except 'u' and 'd' are transformed to 'a', the answer is "aauaaaaada".


 */
public class LexicographicallySmallestEquivalentString {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        StringBuilder res = new StringBuilder();
        DisjointSet ds = new DisjointSet();

        for (int i = 0; i < s1.length(); i++) {
            ds.union(s1.charAt(i), s2.charAt(i));
        }

        for (int i = 0; i < baseStr.length(); i++) {
            res.append(ds.find(baseStr.charAt(i)));
        }
        return res.toString();
    }
}

class DisjointSet {
    char[] parent;

    DisjointSet() {
        parent = new char[128];
        for (char ch = 'a'; ch <= 'z'; ch++)
            parent[ch] = ch;
    }

    public char find(char ch) {
        if (ch != parent[ch])
            parent[ch] = find(parent[ch]);
        return parent[ch];
    }

    public boolean union(char u, char v) {
        char rootU = find(u);
        char rootV = find(v);

        if (rootU == rootV)
            return false;
        if (rootU < rootV) {
            parent[rootV] = rootU;
        } else {
            parent[rootU] = rootV;
        }
        return true;
    }
}