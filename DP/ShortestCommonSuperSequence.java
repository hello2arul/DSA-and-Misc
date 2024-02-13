package DP;

/*
 * https://leetcode.com/problems/shortest-common-supersequence/ 
 * shortest string that has both s1 and s2 as subsequence
    find LCS, then insert missing characters from both the strings
 */
public class ShortestCommonSuperSequence {
    public String shortestCommonSupersequence(String s1, String s2) {
        String lcs = findLCS(s1, s2);
        int i = 0, j = 0;
        StringBuilder res = new StringBuilder();
        for(char ch: lcs.toCharArray()) {
            while(s1.charAt(i) != ch) {
                res.append(s1.charAt(i++));
            }
            while(s2.charAt(j) != ch) {
                res.append(s2.charAt(j++));
            }
            res.append(ch);
            i++;
            j++;
        }
        return res + s1.substring(i) + s2.substring(j);
    }
    
    private String findLCS(String s1, String s2) {
        StringBuilder[][] dp = new StringBuilder[s1.length() + 1][s2.length() + 1];
        for(int i = 0; i <= s1.length(); i++) {
            for(int j = 0; j <= s2.length(); j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = new StringBuilder("");
                } else if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = new StringBuilder("" + dp[i - 1][j - 1] + s1.charAt(i - 1));
                } else {
                    dp[i][j] = dp[i][j - 1].length() > dp[i - 1][j].length() ?
                               dp[i][j - 1] : dp[i - 1][j];
                }
            }
        }
            
        return dp[s1.length()][s2.length()].toString();
    }
}
