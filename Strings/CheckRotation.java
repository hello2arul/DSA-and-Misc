/*
 * https://leetcode.com/problems/rotate-string/
 */
public class CheckRotation {
    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() &&
               (s + s).indexOf(goal) != -1;
    }
}
