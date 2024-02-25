import java.util.LinkedList;
import java.util.Queue;

/*
* https://leetcode.com/problems/find-the-winner-of-the-circular-game
* https://www.youtube.com/watch?v=uCsD3ZGzMgE
* 
* 
*/

public class JosephusProblem {
      public int findTheWinner(int n, int k) {
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= n; i++)
            q.offer(i);
        
        while(q.size() != 1) {
            int x = k;
            while(x > 1) {
                q.offer(q.poll());
                x--;
            }
            q.poll();
        }
        return q.peek();
    }
}
