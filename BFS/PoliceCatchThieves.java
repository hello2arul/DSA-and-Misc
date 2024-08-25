package BFS;

import java.util.Queue;
import java.util.LinkedList;
/*
https://www.geeksforgeeks.org/problems/police-and-thieves--141631/1
Given an array of size n such that each element contains either a 'P' for policeman or a 'T' for thief. Find the maximum number of thieves that can be caught by the police. 
Keep in mind the following conditions :

Each policeman can catch only one thief.
A policeman cannot catch a thief who is more than K units away from him.
Example 1:

Input:
N = 5, K = 1
arr[] = {P, T, T, P, T}
Output: 2
Explanation: Maximum 2 thieves can be 
caught. First policeman catches first thief 
and second police man can catch either second 
or third thief.
Example 2:

Input:
N = 6, K = 2
arr[] = {T, T, P, P, T, P}
Output: 3
Explanation: Maximum 3 thieves can be caught. 
*/
public class PoliceCatchThieves {
    static int catchThieves(char arr[], int n, int k) 
	{ 
        Queue<Integer> police = new LinkedList<>();
        Queue<Integer> thieves = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            if (arr[i] == 'P') 
                police.offer(i);
            else
                thieves.offer(i);
        }
        
        int res = 0;
        
        while (!police.isEmpty() && !thieves.isEmpty()) {
            if (Math.abs(police.peek() - thieves.peek()) <= k) {
                police.poll();
                thieves.poll();
                res++;
            } else if (police.peek() > thieves.peek()) {
                thieves.poll();
            } else {
                police.poll();
            }
        }
        return res;
	} 
}
