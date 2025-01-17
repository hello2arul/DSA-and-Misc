package String;

/*
 * refer Notes.md
 */
public class KnuthMorrisPratt {
    // O(n^3) naive
    public int[] longestProperPrefixSuffixArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];

        for (int i = 0; i < m; i++) {
            lps[i] = longestPrefixSuffix(pattern.substring(0, i + 1));
        }

        return lps;
    }

    private int longestPrefixSuffix(String s) {
        int n = s.length();
        for (int len = n - 1; len > 0; len--) {
            if (s.substring(0, len).equals(s.substring(n - len))) {
                return len;
            }
        }
        return 0;
    }

    /*
     * O(n)
     * 1. If len = lps[i - 1] and s[len] == s[i], then lps[i] = len + 1
     * str = "aaaa"
     * lps = {0, 1, 2, 3}
     * 2. If s[len] != s[i],
     * a.) if len = 0, then lps[i] = 0
     * b.) else we recurse, len = lps[len - 1], then compare s[i] with s[len]
     * Eg) "a b c b a b c a"
     *      0 1 2 3 4 5 6 7
     * lps[0] = 0, len = 0
     * lps[1] = 0, len = 0, case 2.a
     * lps[2] = 0, len = 0, case 2.a
     * lps[3] = 0, len = 0, case 2.a
     * lps[4] = 1, len = 0, case 1
     * lps[5] = 2, len = 1, case 1
     * lps[6] = 3, len = 2, case 1
     * lps[7] = case 2.b, len = lps[len - 1] = lps[2] = 0 -> case 1, len = 1
     * 
     */
    public int[] lps(String s) {
        int n = s.length();
        int[] lps = new int[n];
        int len = 0;
        lps[0] = 0;
        int i = 1;

        while (i < n ) {
            if (s.charAt(i) == s.charAt(len)) {
                lps[i] = len + 1;
                i++;
                len++;
            } else {
                if (len == 0) {
                    lps[i] = 0;
                    i++;
                } else {
                    len = lps[len - 1];
                }
            }
        }
        return lps;
    }

    public void kmp(String txt, String pattern) {
        // 1. Construct lps array for pattern
        int[] lps = lps(pattern);
        int n = txt.length();
        int m = pattern.length();
        int i = 0, j = 0;

        while (i < n) {
            if (txt.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            if (j == m) {
                System.out.println("Pattern found at index " + (i - j));
                j = lps[j - 1];
            } else if (i < n && txt.charAt(i) != pattern.charAt(j)) {
                if (j == 0) {
                    i++;
                } else {
                    j = lps[j - 1];
                }
            }
        }
    }
}
