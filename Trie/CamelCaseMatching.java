package Trie;
//https://leetcode.com/problems/camelcase-matching/

import java.util.ArrayList;
import java.util.List;

public class CamelCaseMatching {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> results = new ArrayList<>();

        for(String query: queries) {
            boolean doesMatch = true;
            int i = 0;
            for(int j = 0; j < query.length(); j++) {
                if(i < pattern.length() && pattern.charAt(i) == query.charAt(j)) {
                    i++;
                } else if(i == pattern.length() && 
                    Character.isUpperCase(query.charAt(j))) {
                    doesMatch = false;
                    break;
                } else if(Character.isUpperCase(query.charAt(j))){
                    doesMatch = false;
                    break;
                }

            }
            results.add(i == pattern.length() && doesMatch);
        }
        return results;
    }

}
