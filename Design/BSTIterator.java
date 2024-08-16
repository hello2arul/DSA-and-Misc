package Design;

import java.util.Stack;
import Tree.TreeNode;

/*
 * https://leetcode.com/problems/binary-search-tree-iterator
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 */

public class BSTIterator {
    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        pushAllLeft(root);
    }
    
    public int next() {
        TreeNode top = stack.pop();
        pushAllLeft(top.right);
        return top.val;
    }
    
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    
    private void pushAllLeft(TreeNode node) {
        while(node != null) {
            stack.add(node);
            node = node.left;
        }
    }
}
