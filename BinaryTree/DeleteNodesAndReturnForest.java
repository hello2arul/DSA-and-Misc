package BinaryTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.tree.TreeNode;

/*
 * https://leetcode.com/problems/delete-nodes-and-return-forest/
 * Google
 */
public class DeleteNodesAndReturnForest {
    Set<Integer> toDeleteSet;
    List<TreeNode> res;

    public List<TreeNode> delNodes(TreeNode root, int[] toDelete) {
        toDeleteSet = new HashSet<>();
        res = new ArrayList<>();    
        for (int num : toDelete)
            toDeleteSet.add(num);
        helper(root, true);
        return res;
    }

    private TreeNode helper(TreeNode node, boolean isRoot) {
        if (node == null)
            return null;
        boolean deleted = toDeleteSet.contains(node.val);
        if (isRoot && !deleted)
            res.add(node);
        // if their parent is deleted, child becomes root    
        node.left = helper(node.left, deleted);
        node.right = helper(node.right, deleted);
        return deleted ? null : node;
    }
}
