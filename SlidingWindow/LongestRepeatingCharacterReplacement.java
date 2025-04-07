package SlidingWindow;

/*
https://leetcode.com/problems/longest-repeating-character-replacement/description/?envType=company&envId=google&favoriteSlug=google-all
Google

You are given a string s and an integer k. You can choose any character of the string and change 
it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing 
the above operations.

 

Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.
 */
public class LongestRepeatingCharacterReplacement {
    
    public int characterReplacement(String s, int k) {
        int[] table = new int[128];
        int left = 0;
        int maxLen = 0;
        int maxFreq = 0; // max freq of any char in the window

        for (int right = 0; right < s.length(); right++) {
            table[s.charAt(right)]++;
            maxFreq = Math.max(maxFreq, table[s.charAt(right)]);
            
            while (right - left + 1 - maxFreq > k) {
                table[s.charAt(left++)]--;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
