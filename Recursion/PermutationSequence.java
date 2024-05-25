package Recursion;

import java.util.ArrayList;
import java.util.List;

/*
 * https://leetcode.com/problems/permutation-sequence
 * can be converted to BFS also but still TLE
 */
public class PermutationSequence {
    public String getPermutation(int n, int k) {
        String num = "";
        for (int i = 1; i <=n; i++) {
            num += i;
        }
        List<String> res = new ArrayList<>();
        generateKthPermutation("", num, res);
        return res.get(k - 1);
    }

    private void generateKthPermutation(String prefix, String suffix, List<String> res) {
        if (suffix.isEmpty()) {
            res.add(prefix);
        } else {
            for (int i = 0; i < suffix.length(); i ++) {
                generateKthPermutation(
                    prefix + suffix.charAt(i), 
                    suffix.substring(0, i) + suffix.substring(i + 1),
                    res
                );
            }
        }
    }
}
