
/*
 * https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/description/
 */
public class MinAddToMakeParenthesisValid {
    public int minAddToMakeValid(String s) {
        while (s.indexOf("()") != -1) {
            s = s.replace("()", "");
        }
        return s.length();
    }

    public int minAddToMakeValid2(String s) {
        int opens = 0, invalids = 0;
         for(int i = 0; i < s.length(); i++) {
             char ch = s.charAt(i);
             if(ch == '(') {
                 opens++;
             } else {
                 if(opens > 0)   opens--;
                 else            invalids++;
             }
         }
         return invalids + opens;
     }
}
