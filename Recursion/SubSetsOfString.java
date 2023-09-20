/* 
INPUT - "ab"
OUTPUT - " ", "a", "b", "ab"
*/

import java.util.List;

public class SubSetsOfString {
    public void subSetsOfString(String s, int idx, List<String> res, String cur) {
        if(idx == s.length()) {
            res.add(cur);
            return;
        }
        subSetsOfString(s, idx + 1, res, cur);
        subSetsOfString(s, idx + 1, res, cur + s.charAt(idx));
    }
}
