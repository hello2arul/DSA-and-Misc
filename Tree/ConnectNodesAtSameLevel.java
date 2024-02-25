import java.util.LinkedList;
import java.util.Queue;

import org.w3c.dom.Node;

/*
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
 */

public class ConnectNodesAtSameLevel {
     public Node connect(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        q.offer(null);

        while(!q.isEmpty()) {
            for(int i = q.size(); i > 0; i--) {
                Node cur = q.poll();
                if(cur != null) {
                    cur.next = q.peek();
                    if(cur.left != null)
                        q.offer(cur.left);
                    if(cur.right != null)
                        q.offer(cur.right);
                    if(i == 2)
                        q.offer(null);
                }
            } 
        }

        return root;
    }
}
