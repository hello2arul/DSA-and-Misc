package Tree;

import java.util.LinkedList;
import java.util.Queue;

import Tree.BinaryTree.Node;

/*
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
 */
public class NextRightPointers {
     public Node connect(Node root) {
        if(root == null)    return root;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        q.offer(null);
        
        while(!q.isEmpty()) {
            for(int size = q.size(); size > 0; size--) {
                Node cur = q.poll();
                if(cur == null) continue;
                cur.next = q.peek();
                if(cur.left != null)    q.offer(cur.left);
                if(cur.right != null)   q.offer(cur.right);
                if(size == 2)   q.offer(null);
            }
        }
        return root;
    }
}
