package BST;

import javax.swing.tree.TreeNode;

/*
 * https://leetcode.com/problems/validate-binary-search-tree/description/
 * 1. Inorder.isSorted? O(n) O(n)
 * 2. pass min and max value, check if the node is in range
 * 3. Inorder without using extra array 
 */
public class IsBST {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean isValidBST(TreeNode node, long min, long max) {
        if(node == null)    return true;
        if(node.val <= min || node.val >= max) 
            return false;
        return isValidBST(node.left, min, node.val) && 
               isValidBST(node.right, node.val, max);
    }

    Integer prev = null;
    public boolean isValidBSTInorder(TreeNode root) {
        if(root == null)
            return true;
        if(!isValidBSTInorder(root.left) || (prev != null && root.val <= prev) )
            return false;
        prev = root.val;
        return  isValidBSTInorder(root.right);
    }
}
