package Strings;
/*
 * https://leetcode.com/problems/valid-anagram/
 */

public class Anagram {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
        int[] freq = new int[256];
        for(int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)]++;
            freq[t.charAt(i)]--;
        }
        for(int i : freq)
            if(i != 0)
                return false;
        return true;
    }
}
