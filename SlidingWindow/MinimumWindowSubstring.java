package SlidingWindow;

import java.util.HashMap;
import java.util.Map;


/*
 * https://leetcode.com/problems/minimum-window-substring/description/
 */
public class MinimumWindowSubstring {
        public String minWindow(String s, String t) {
        if(s.length() < t.length()) return "";
        
        int min = s.length();
        int minLeft = 0, minRight = s.length() - 1;
        Map<Character, Integer> tmap = new HashMap<>();
        Map<Character, Integer> smap = new HashMap<>();
        
        for(int i = 0; i < t.length(); i++) {
            tmap.put(t.charAt(i), tmap.getOrDefault(t.charAt(i), 0) + 1);
        }
        
        int neededChars = tmap.size(), charsFound = 0;
        int left = 0, right = 0;
        boolean found = false;
        
        while(right < s.length()) {
            char ch = s.charAt(right);
            if(!tmap.containsKey(ch)) {
                right++;
                continue;
            }
            
            smap.put(ch, smap.getOrDefault(ch, 0) + 1);
            
            if(tmap.get(ch).equals(smap.get(ch))) {
                charsFound++;    
            }
            
            while(charsFound == neededChars) {
                found = true;
                if(right - left + 1 < min) {
                    min = right - left + 1;
                    minLeft = left;
                    minRight = right;
                }
                char leftChar = s.charAt(left);
                if(!tmap.containsKey(leftChar)) {
                    left++;
                    continue;
                }
                if(smap.get(leftChar).equals(tmap.get(leftChar))) {
                   charsFound--;    
                }
                smap.put(leftChar, smap.get(leftChar) - 1);
                if(smap.get(leftChar) <= 0) smap.remove(leftChar);
                left++;
            }
            right++;
        }
        //System.out.println(found);
        return found ? s.substring(minLeft, minRight + 1) : "";
    }
}
