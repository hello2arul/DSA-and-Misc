package Recursion;

//https://leetcode.com/problems/add-digits/description/


public class SumOfDigits {
    public int addDigits(int num) {
        if (num < 10)
            return num;
        int res = (num % 10) + addDigits(num / 10); 
        while(res >= 10)
            res = addDigits(res);
        return res;
    }
}
