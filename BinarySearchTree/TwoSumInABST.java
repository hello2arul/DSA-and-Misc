package BST;

import java.util.HashSet;
import java.util.Set;

import javax.swing.tree.TreeNode;

/*
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/
 * 
 * 1. store inorder in an array
 * 2. Use inorder + hashmap
 */
public class TwoSumInABST {
     Set<Integer> set = new HashSet<>();
    public boolean findTarget(TreeNode root, int k) {
        if(root == null)
            return false;
        if(findTarget(root.left, k))
            return true;
        if(set.contains(k - root.val))
            return true;
        set.add(root.val);
        return findTarget(root.right, k);
    }
}
