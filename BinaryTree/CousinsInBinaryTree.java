package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

import javax.swing.tree.TreeNode;

/*
 * https://leetcode.com/problems/cousins-in-binary-tree/

Given the root of a binary tree with unique values and the values of two different 
nodes of the tree x and y, return true if the nodes corresponding to the values x and y
in the tree are cousins, or false otherwise.

Two nodes of a binary tree are cousins if they have the same depth with different parents.

Note that in a binary tree, the root node is at the depth 0, and children of each 
depth k node are at the depth k + 1.

*/
public class CousinsInBinaryTree {
  public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            boolean foundA = false;
            boolean foundB = false;
            for(int i = 0; i < size; i++) {
                TreeNode current = queue.remove();
                if(current.val == x)   foundA = true;
                if(current.val == y)  foundB = true;
                if(current.left != null && current.right != null) {
                    if(current.left.val == x && current.right.val == y)
                        return false;
                    if(current.left.val == y && current.right.val == x)
                        return false;
                }
                if(current.left != null)    queue.add(current.left);
                if(current.right != null)   queue.add(current.right);
            }
            if(foundA && foundB)    return true;
        }
        return false;
    }    
}
