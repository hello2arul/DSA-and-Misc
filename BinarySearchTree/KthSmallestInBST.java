package BST;

import Tree.TreeNode;

/*
https://leetcode.com/problems/kth-smallest-element-in-a-bst/
naive - store inOrder in an array and return the kth value.
*/
public class KthSmallestInBST {
    
    int count = 0;
    TreeNode res = null;

    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return res.val;
    }

    public void inOrder(TreeNode root, int k) {
        if (root == null)
            return;

        inOrder(root.left, k);
        count++;
        if (count == k) {
            res = root;
            return;
        }
        inOrder(root.right, k);
    }
}
