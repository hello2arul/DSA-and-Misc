package Strings;

/*
 * https://leetcode.com/problems/text-justification
 * Given an array of strings words and a width maxWidth, 
 * format the text such that each line has exactly maxWidth characters 
 * and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, 
pack as many words as you can in each line. Pad extra spaces ' ' 
when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible.
If the number of spaces on a line does not divide evenly between words,
the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left-justified, and no extra space is inserted between words.

Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
 */
class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int idx = 0;
        int n = words.length;

        while (idx < n) {
            StringBuilder line = new StringBuilder();
            int totalChars = words[idx].length();
            int last = idx + 1;

            while (last < n) {
                // current + ' ' + next
                if (totalChars + 1 + words[last].length() > maxWidth)
                    break;
                totalChars += 1 + words[last].length();
                last++;
            }
            // 2 words = 1 gap
            int gaps = last - idx - 1;

            // last line left justified || only one word
            if (last == n || gaps == 0) {
                for (int i = idx; i < last; i++) {
                    line.append(words[i]).append(' ');
                }
                line.deleteCharAt(line.length() - 1);
                while (line.length() < maxWidth) {
                    line.append(' ');
                }
            } else {
                int spaces = (maxWidth - totalChars) / gaps;
                int extraSpaces = (maxWidth - totalChars) % gaps; // odd spaces

               for (int i = idx; i < last; i++) {
                    line.append(words[i]);
                    if (i < last - 1) { // Don't add spaces after the last word
                        for (int j = 0; j <= spaces; j++) { // <= since including +1 space in totalChars
                            line.append(' '); // Add base spaces
                        }
                        if (extraSpaces > 0) { // Distribute extra spaces
                            line.append(' ');
                            extraSpaces--;
                        }
                    }
                }
            }
            res.add(line.toString());
            idx = last;
        }

        return res;
    }
}
