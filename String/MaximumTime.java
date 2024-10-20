package Strings;

/*
 * Google
 * https://leetcode.com/discuss/interview-question/396769/
 * You are given a string that represents time in the format hh:mm. 
 * Some of the digits are blank (represented by ?). .
 * Fill in ? such that the time represented by this string is the maximum possible. 
 * Maximum time: 23:59, minimum time: 00:00.
 * You can assume that input string is always valid.
 * 
 * Input: "?4:5?"
 * Output: "14:59"
 */
public class MaximumTime {
    public static void giveMeMaxTime(String tim){
        char[] timChar = tim.toCharArray();
   
       if(timChar[0] == '?')
           timChar[0] = (timChar[1] <= '3' || timChar[1] == '?') ? '2' : '1'; // 23 : 14
   
       if(timChar[1] == '?')
           timChar[1] = (timChar[0] == '2') ? '3' : '9';
   
       timChar[3] = (timChar[3] == '?') ? '5' : timChar[3];
       timChar[4] = (timChar[4] == '?') ? '9' : timChar[4];
   
   }
}
