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
    // TODO: TLE
    public boolean isSolvable(String[] words, String result) {
        HashSet<Character> uniqueChars = new HashSet<>();
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                uniqueChars.add(ch);
            }
        }
        for (char ch : result.toCharArray()) {
            uniqueChars.add(ch);
        }
        // can have only 0-9 nums
        if (uniqueChars.size() > 10) return false;

        // Create an array of unique characters
        Character[] chars = uniqueChars.toArray(new Character[0]);
        HashMap<Character, Integer> charToIndex = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            charToIndex.put(chars[i], i);
        }

        return backtrack(chars, new boolean[10], new int[chars.length], 0, result, words, charToIndex);
    }

    private boolean backtrack(Character[] chars, boolean[] used, int[] mapping, int idx, String res, String[] words, HashMap<Character, Integer> charToIndex) {
        if (idx == chars.length) {
            return doesMatch(mapping, words, res, charToIndex);
        }
        char ch = chars[idx];

        for (int digit = 0; digit <= 9; digit++) {
            if (!used[digit]) {
                // Set mapping
                int digitBackup = mapping[idx];
                mapping[idx] = digit;
                used[digit] = true;

                if (backtrack(chars, used, mapping, idx + 1, res, words, charToIndex)) {
                    return true;
                }

                // Backtrack
                used[digit] = false;
                mapping[idx] = digitBackup;
            }
        }

        return false;
    }

    private boolean doesMatch(int[] mapping, String[] words, String res, HashMap<Character, Integer> charToIndex) {
        if (mapping[charToIndex.get(res.charAt(0))] == 0)
            return false;
        int sum = 0;

        for (String word : words) {
            if (mapping[charToIndex.get(word.charAt(0))] == 0)
                return false;
            sum += convertToNum(mapping, word, charToIndex);
        }
        return sum == convertToNum(mapping, res, charToIndex);
    }

    private int convertToNum(int[] mapping, String s, HashMap<Character, Integer> charToIndex) {
        int num = 0;
        for (char ch : s.toCharArray()) {
            num = (num * 10) + mapping[charToIndex.get(ch)];
        }
        return num;
    }
}
