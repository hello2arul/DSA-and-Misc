package Hashing;

import java.util.HashMap;
import java.util.Map;

/*
 * https://leetcode.com/problems/verifying-an-alien-dictionary/
 * In an alien language, surprisingly, they also use English lowercase letters,
 *  but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only 
if the given words are sorted lexicographically in this alien language.

Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 */
public class VerifyAlienDictionary {
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }

        for (int i = 1; i < words.length; i++) {
            if (isBigger(words[i - 1], words[i], map))
                return false;
        }
        return true;
    }

    private boolean isBigger(String s1, String s2, Map<Character, Integer> map) {
        for (int i = 0; i < s1.length() && i < s2.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i))
                return map.get(s1.charAt(i)) > map.get(s2.charAt(i));
        }
        return s1.length() > s2.length();
    }
}
