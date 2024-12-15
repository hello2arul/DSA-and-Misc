package BackTracking;

import java.util.HashMap;
import java.util.HashSet;

/*
 * https://leetcode.com/problems/verbal-arithmetic-puzzle
 * Google

Given an equation, represented by words on the left side and the result on the right side.

You need to check if the equation is solvable under the following rules:

Each character is decoded as one digit (0 - 9).
No two characters can map to the same digit.
Each words[i] and result are decoded as one number without leading zeros.
Sum of numbers on the left side (words) will equal to the number on the right side (result).
Return true if the equation is solvable, otherwise return false.

Input: words = ["SEND","MORE"], result = "MONEY"
Output: true
Explanation: Map 'S'-> 9, 'E'->5, 'N'->6, 'D'->7, 'M'->1, 'O'->0, 'R'->8, 'Y'->'2'
Such that: "SEND" + "MORE" = "MONEY" ,  9567 + 1085 = 10652
 */
public class VerbalArithmeticPuzzle {
    // TC - O(10n⋅m)
    // n -> number of chars
    // m -> TC of doesMatch
    // SC - > O(n), recursive call stack depth
    public boolean isSolvable(String[] words, String result) {
        HashSet<Character> uniqueChars = new HashSet<>();
        HashSet<Character> startChars = new HashSet<>();

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (i == 0 && word.length() != 1)
                    startChars.add(ch);
                uniqueChars.add(ch);
            }
        }
        if (result.length() != 0)
            startChars.add(result.charAt(0));
        for (char ch : result.toCharArray()) {
            uniqueChars.add(ch);
        }

        if (uniqueChars.size() > 10) return false;
        // if unique char is 1, map it to 0
        // [A, A] = "AA" -> false
        if (uniqueChars.size() == 1 && result.length() == 1) return true;

        Character[] chars = uniqueChars.toArray(new Character[0]);
        boolean[] used = new boolean[10]; // Track used digits
        int[] mapping = new int[128]; // Map characters to digits

        return backtrack(chars, startChars, mapping, used, 0, words, result);
    }

    private boolean backtrack(Character[] chars, HashSet<Character> startChars, int[] mapping, boolean[] used, int idx, String[] words, String result) {
        if (idx == chars.length) {
            return doesMatch(mapping, words, result);
        }

        char ch = chars[idx];
        for (int digit = 0; digit <= 9; digit++) {
            // Skip if the digit is already used
            if (used[digit]) continue;

            // Skip if this character cannot be mapped to zero
            if (digit == 0 && startChars.contains(ch) ) continue;

            // Set mapping
            mapping[ch] = digit;
            used[digit] = true;

            // Recur to map the next character
            if (backtrack(chars, startChars, mapping, used, idx + 1, words, result)) {
                return true;
            }

            // Backtrack
            used[digit] = false;
            mapping[ch] = 0; // Reset mapping
        }
        return false;
    }

    private boolean doesMatch(int[] mapping, String[] words, String result) {
        int sum = 0;
        int resultSum = convertToNum(result, mapping);
        for (String word : words) {
            sum += convertToNum(word, mapping);
            if (sum > resultSum)
                return false;
        }
        return sum == resultSum;
    }

    private int convertToNum(int[] mapping, String s) {
        int num = 0;
        for (char ch : s.toCharArray()) {
            num = (num * 10) + mapping[ch];
        }
        return num;
    }
}
