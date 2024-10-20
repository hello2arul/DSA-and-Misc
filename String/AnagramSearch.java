package Strings;
/*
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnagramSearch {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s.length() < p.length())
            return res;
        int[] freqP = new int[256];
        int[] window = new int[256];

        for(int i = 0; i < p.length(); i++) {
            freqP[p.charAt(i)]++;
            window[s.charAt(i)]++;
        }

        if(Arrays.equals(freqP, window)) res.add(0);

        for(int i = p.length(); i < s.length(); i++) {
            window[s.charAt(i - p.length())]--;
            window[s.charAt(i)]++;

            if(Arrays.equals(freqP, window)) res.add(i - p.length() + 1);
        }
        return res;
    }
}
