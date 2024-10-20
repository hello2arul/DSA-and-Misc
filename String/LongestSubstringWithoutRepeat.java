package Strings;

import java.util.HashSet;
import java.util.Set;

/*
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeat {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0)  return 0;
        Set<Character> set = new HashSet<>();
        int maxLen = 1;
        
        int left = 0;
        for(int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);

            while(set.contains(ch)) {
                set.remove(s.charAt(left++));
            }
  
            set.add(ch);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
