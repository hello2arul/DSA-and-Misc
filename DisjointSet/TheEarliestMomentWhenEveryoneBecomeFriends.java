package DisjointSet;

/*
 * https://leetcode.ca/all/1101.html
 * Google
 * Sorting + Union Find

In a social group, there are N people, with unique integer ids from 0 to N-1.
We have a list of logs, where each logs[i] = [timestamp, id_A, id_B] contains a non-negative 
integer timestamp, and the ids of two different people.

Each log represents the time in which two different people became friends.  
Friendship is symmetric: if A is friends with B, then B is friends with A.

Let's say that person A is acquainted with person B if A is friends with B, or 
A is a friend of someone acquainted with B.

Return the earliest time for which every person became acquainted with every other person. 
Return -1 if there is no such earliest time. 

Input: logs = [[0,2,0],[1,0,1],[3,0,3],[4,1,2],[7,3,1]], n = 4
Output: 3
Explanation: At timestamp = 3, all the persons (i.e., 0, 1, 2, and 3) become friends.
logs[i] = [timestampi, xi, yi]
xi and yi will be friends at the time timestampi
 */
public class TheEarliestMomentWhenEveryoneBecomeFriends {

    public int earliestAcq(int[][] logs, int n) {
        // sort logs by timestamp
        java.util.Arrays.sort(logs, (a, b) -> a[0] - b[0]);
        DisjointSet ds = new DisjointSet(n);
        for (int[] log : logs) {
            ds.union(log[1], log[2]);
            if (ds.size() == 1)
                return log[0];
        }
        return -1;
    }
}
