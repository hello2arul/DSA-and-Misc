package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * 1. use a stack
 * 2. efficient -> use 2 stacks
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 */
public class ZigZagTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        if(root != null)
        s1.push(root);
        
        while(!s1.isEmpty() || !s2.isEmpty()) {
            
            if(!s1.isEmpty()) {
                List<Integer> subRes = new ArrayList<>(s1.size());
                while(!s1.isEmpty()) {
                    TreeNode node = s1.pop();
                    subRes.add(node.val);
                    if(node.left != null)   s2.add(node.left);
                    if(node.right != null)  s2.add(node.right);
                }
                res.add(subRes);
            }
            
            if(!s2.isEmpty()) {
                List<Integer> subRes = new ArrayList<>(s2.size());
                while(!s2.isEmpty()) {
                    TreeNode node = s2.pop();
                    subRes.add(node.val);
                    if(node.right != null)  s1.add(node.right);
                    if(node.left != null)   s1.add(node.left);
                }
               res.add(subRes);

            }
        }
        return res;
    }
}
