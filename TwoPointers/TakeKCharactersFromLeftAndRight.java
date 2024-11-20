package TwoPointers;

/*
 * https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/
 

You are given a string s consisting of the characters 'a', 'b', and 'c' and 
a non-negative integer k. Each minute, you may take either the leftmost 
character of s, or the rightmost character of s.

Return the minimum number of minutes needed for you to take at least 
k of each character, or return -1 if it is not possible to take k of each character.

Input: s = "aabaaaacaabc", k = 2
Output: 8
Explanation: 
Take three characters from the left of s. 
You now have two 'a' characters, and one 'b' character.
Take five characters from the right of s. 
You now have four 'a' characters, two 'b' characters, and two 'c' characters.
A total of 3 + 5 = 8 minutes is needed.
It can be proven that 8 is the minimum number of minutes needed.

Input: s = "a", k = 1
Output: -1
Explanation: It is not possible to take one 'b' or 'c' so return -1.

 */
public class TakeKCharactersFromLeftAndRight {
    int res = Integer.MAX_VALUE;

    public int takeCharactersSlidingWindow(String s, int k) {
        int[] count = new int[3];
        int n = s.length();

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        for (int i = 0; i < 3; i++) {
            if (count[i] < k) return -1;
        }

        // largest window that can be deleted from the middle
        int[] window = new int[3];
        int maxWindow = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {
            window[s.charAt(right) - 'a']++;
            while (left <= right &&  left <= right &&
                (count[0] - window[0] < k ||
                    count[1] - window[1] < k ||
                    count[2] - window[2] < k)) {
                window[s.charAt(left) - 'a']--;
                left++;
            }
            maxWindow = Math.max(maxWindow, right - left + 1);
        }
        return n - maxWindow;
    }

    public int takeCharacters(String s, int k) {
        int[] count = new int[3];
        takeChars(s, 0, s.length() - 1, k, 0, count);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void takeChars(String s, int left, int right, int k, int time, int[] count) {
        if (count[0] >= k && count[1] >= k && count[2] >= k) {
            res = Math.min(res, time);
            return;
        }
        if (left > right)
            return;

        count[s.charAt(left) - 'a']++;
        takeChars(s, left + 1, right, k, time + 1, count);
        count[s.charAt(left) - 'a']--;

        count[s.charAt(right) - 'a']++;
        takeChars(s, left, right - 1, k, time + 1, count);
        count[s.charAt(right) - 'a']--;
    }
}