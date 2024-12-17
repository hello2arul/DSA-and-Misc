package Heap;

import java.util.Comparator;
import java.util.TreeSet;

/*
https://leetcode.com/problems/construct-string-with-repeat-limit/
You are given a string s and an integer repeatLimit. Construct a new string 
repeatLimitedString using the characters of s such that no letter appears more 
than repeatLimit times in a row. You do not have to use all characters from s.

Return the lexicographically largest repeatLimitedString possible.

A string a is lexicographically larger than a string b if in the first position 
where a and b differ, string a has a letter that appears later in the alphabet 
than the corresponding letter in b. If the first min(a.length, b.length) characters do not differ, 
then the longer string is the lexicographically larger one.

Input: s = "cczazcc", repeatLimit = 3
Output: "zzcccac"
Explanation: We use all of the characters from s to construct the repeatLimitedString "zzcccac".
The letter 'a' appears at most 1 time in a row.
The letter 'c' appears at most 3 times in a row.
The letter 'z' appears at most 2 times in a row.
Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
The string is the lexicographically largest repeatLimitedString possible so we return "zzcccac".
Note that the string "zzcccca" is lexicographically larger but the letter 'c' 
appears more than 3 times in a row, so it is not a valid repeatLimitedString.

Input: s = "aababab", repeatLimit = 2
Output: "bbabaa"
Explanation: We use only some of the characters from s to construct the repeatLimitedString "bbabaa". 
The letter 'a' appears at most 2 times in a row.
The letter 'b' appears at most 2 times in a row.
Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
The string is the lexicographically largest repeatLimitedString possible so we return "bbabaa".
Note that the string "bbabaaa" is lexicographically larger but the letter 'a' 
appears more than 2 times in a row, so it is not a valid repeatLimitedString.
 */
public class ConstructStringWithRepeatLimit {
    public String repeatLimitedString(String s, int repeatLimit) {
        TreeSet<Character> set = new TreeSet<>(Comparator.reverseOrder());

        int[] count = new int[128];
        StringBuilder res = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
            count[s.charAt(i)]++;
        }

        while (!set.isEmpty()) {
            char ch = set.removeFirst();
            int limit = Math.min(count[ch], repeatLimit);

            for (int i = 0; i < limit; i++) {
                res.append(ch);
                count[ch]--;
            }
            // just insert the next char once to break the continuity
            if (count[ch] > 0 && !set.isEmpty()) {
                char next = set.removeFirst();
                count[next]--;
                res.append(next);
                if (count[next] > 0)
                    set.add(next);
                set.add(ch);
            }
        }
        return res.toString();
    }
}
