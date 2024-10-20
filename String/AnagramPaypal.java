package Strings;

import java.util.HashMap;
import java.util.Map;

/*
 * s = "programmerasdfrprogramme";
 * output = Start index of second anagram - End index of first anagram of programmer
 TODO: fix
 */
public class AnagramPaypal {

    private static final String PROGRAMMER = "programmer";

    public int getIdxDiff(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < PROGRAMMER.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        int end = -1;
        int start = -1;
        Map<Character, Integer> tMap = new HashMap<>();

        /*
         * Assumptions:
         * two programmer strings aren't overlapping
         * there exists only two anagrams
         */
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if (!map.containsKey(ch))
                continue;
            tMap.put(ch, tMap.getOrDefault(ch, 0) + 1);
            
            if (end != -1 && start == -1) {
                start = i;
            }
            if (tMap.equals(map)) {
                if (end == -1) {
                    end = i;
                    tMap.clear();
                } else {
                    break; // found second
                }
            }
        }
        System.out.println(end);
        System.out.println(start);

        while (!tMap.equals(map) && start < s.length()) {
            tMap.put(s.charAt(start), tMap.get(s.charAt(start)) - 1);
            start++;
    
        }
        return tMap.equals(map) && end != -1 && start != -1 ? end - start : -99;
    }

    public static void main(String[] args) {
        System.out.println(new AnagramPaypal().getIdxDiff("programmerasdfrprogramme"));
    }
}