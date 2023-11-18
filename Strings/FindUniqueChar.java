/*
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 * store index 
 */
public class FindUniqueChar {
    public int firstUniqChar(String s) {
        int[] freq = new int[256];
        for(char c: s.toCharArray())
            freq[c]++;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(freq[c] == 1)
                return i;
        }
        return -1;
    }
}
