package BackTracking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/verbal-arithmetic-puzzle
Google

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
    // TC - O(10^n⋅m)
    // n -> number of chars
    // m -> TC of doesMatch
    // SC - > O(n), recursive call stack depth
    public boolean isSolvable(String[] words, String result) {
        Set<Character> uniqueChars = new HashSet<>();
        Set<Character> firstChars = new HashSet<>();

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                uniqueChars.add(ch);

                if (i == 0 && word.length() != 1)
                    firstChars.add(ch);
            }
        }

        for (int i = 0; i < result.length(); i++) {
            char ch = result.charAt(i);
            uniqueChars.add(ch);

            if (i == 0)
                firstChars.add(ch);
        }

        if (uniqueChars.size() > 10)
            return false;
        // AA -> A, can be 0
        // AA -> AA, can't be 0
        if (uniqueChars.size() == 1 && result.length() == 1)
            return true;
        
        Character[] chars = uniqueChars.toArray(new Character[0]);
        boolean[] used = new boolean[10];
        int[] mapping = new int[128];

        return backtract(chars, firstChars, used, mapping, 0, words, result);
    }

    private boolean backtract(Character[] chars, Set<Character> firstChars, boolean[] used, int[] mapping, int idx, String[] words, String result) {
        if (idx == chars.length) {
            return doesMatch(mapping, words, result);
        }
        char ch = chars[idx];

        for (int digit = 0; digit <= 9; digit++) {
            if (used[digit])
                continue;
            if (digit == 0 && firstChars.contains(ch))
                continue;

            used[digit] = true;
            mapping[ch] = digit;

            if (backtract(chars, firstChars, used, mapping, idx + 1, words, result)) {
                return true;
            }

            used[digit] = false;
            mapping[ch] = 0;
        }
        return false;
    }

    public boolean doesMatch(int[] mapping, String[] words, String res) {
        int sum = 0;
        int resultSum = convertToNum(res, mapping);
        for (String word : words) {
            sum += convertToNum(word, mapping);
            if (sum > resultSum)
                return false;
        }
        return sum == resultSum;
    }

    private int convertToNum(String word, int[] mapping) {
        int num = 0;
        for (char ch : word.toCharArray()) {
            num = (num * 10) + mapping[ch];
        }
        return num;
    }
}
