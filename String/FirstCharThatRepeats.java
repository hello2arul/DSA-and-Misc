package Strings;

/*
 * finding index of first repeating char
 */
public class FirstCharThatRepeats {
    public char firstCharThatRepeats(String s) {
        int[] freq = new int[256];
        for(int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)] = i;
        }
        return '\0';
    }
}
