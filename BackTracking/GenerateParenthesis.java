package BackTracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        genPar("", 0, 0, n, res);
        return res;
    }
    
    private void genPar(String cur, int left, int right, int pairs, List<String> res) {
        
        if(cur.length() == 2 * pairs) {
            res.add(cur);
        }
        if(left < pairs) {
            genPar(cur + "(", left + 1, right, pairs, res);
        }
        if(right < left) {
            genPar(cur + ")", left, right + 1, pairs, res);
        }
    }
}
