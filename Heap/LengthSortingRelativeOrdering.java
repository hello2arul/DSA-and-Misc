package Heap;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Queue;
import java.util.TreeMap;
import java.util.LinkedList;
import java.util.List;

/*
 * similar easy: https://leetcode.com/problems/rearrange-words-in-a-sentence
 * asked in paypal
 * Given a sentence, rearrange it in the following
 * 1. Order based on length, if same length, maintain original order
 * 2. First letter of the sentence should be capitalized
 * 3. Last letter should be followed by a dot.
 * Eg) Input: Also notice that preprocessor statements which is highlighted appear at the start of the program these statements cause the contents of the header file stdio and ctype to inserted into the program the compilation process begins the information contains in these files is essential for the proper functioning of the library functions getchar putchar and toupper.
 * Output: Is at of of to in is of the the the the and the the the for the the and also that file into which start these cause stdio ctype these files notice appear header begins proper program program process library getchar putchar toupper contents inserted contains essential functions statements statements highlighted compilation information functioning preprocessor.
 */
public class LengthSortingRelativeOrdering {
    public static String arrangeWords(String sentence) {
        String[] words = sentence.split("\\s+");
        StringBuilder res = new StringBuilder();
        Map<String, Integer> orderMap = new HashMap<>();
        Map<String, Integer> frequencyMap = new HashMap<>();
        TreeMap<Integer, List<String>> lengthMap = new TreeMap<>();

        PriorityQueue<String> minHeap = new PriorityQueue<>(
            new Comparator<String>() {
                @Override
                public int compare(String a, String b) {
                    if (a.length() != b.length())
                        return Integer.compare(a.length(), b.length());
                    // if (frequencyMap.get(a) == 1 && frequencyMap.get(b) == 1), doesn't work with duplicates
                    return Integer.compare(orderMap.get(a), orderMap.get(b));
                }
            }
        );
        String lastWord = words[words.length - 1];
        words[words.length - 1] = lastWord.substring(0, lastWord.length() - 1);
        
        // doesn't work with duplicates
        for (int i = 0; i < words.length; i++) {
            String word = i == 0 ? words[i].toLowerCase() : words[i];
            orderMap.putIfAbsent(word, i);
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            minHeap.offer(word);
        }

        while (!minHeap.isEmpty()) {
            String word = minHeap.poll();
            if (res.isEmpty()) {
                res.append(word.substring(0, 1).toUpperCase() + word.substring(1));
            } else {
                res.append(word);
            }
            res.append(minHeap.isEmpty() ? "." : " ");
        }
        // for duplicates
        res = new StringBuilder();
        if (words.length > 0)
            words[0] = words[0].toLowerCase();
        for (String word: words) {
            lengthMap.putIfAbsent(word.length(), new LinkedList<>());
            lengthMap.get(word.length()).add(word);
        }

        for (int key: lengthMap.keySet()) {
            for (String word: lengthMap.get(key)) {
                if (res.isEmpty()) {
                    res.append(word.substring(0, 1).toUpperCase() + word.substring(1));
                } else {
                    res.append(word);
                }
                res.append(" ");
            }
        }
        res.replace(res.length() - 1, res.length(), ".");

        return res.toString();
    }
    public static void main(String[] args) {
        final String input = "Also notice that preprocessor statements which is highlighted appear at the start of the program these statements cause the contents of the header file stdio and ctype to inserted into the program the compilation process begins the information contains in these files is essential for the proper functioning of the library functions getchar putchar and toupper.";
        final String expected = "Is at of of to in is of the the the the and the the the for the the and also that file into which start these cause stdio ctype these files notice appear header begins proper program program process library getchar putchar toupper contents inserted contains essential functions statements statements highlighted compilation information functioning preprocessor.";
        final String actual = "Is is at of of of to in the the the the the the the the the and and for also that file into which start these these cause stdio ctype files notice appear header begins proper program program process library getchar putchar toupper contents inserted contains essential functions statements statements highlighted compilation information functioning preprocessor.";
        System.out.println(arrangeWords(input).equals(expected));
    }
}
