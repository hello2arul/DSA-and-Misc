package BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.tree.TreeNode;

/*
 * https://leetcode.com/problems/cousins-in-binary-tree-ii
Given the root of a binary tree, replace the value of each node in the 
tree with the sum of all its cousins' values.

Two nodes of a binary tree are cousins if they have the same depth with different parents.
Return the root of the modified tree.
Note that the depth of a node is the number of edges in the path from the root node to it.

cousinSum = levelSum - current - sibling
 */
public class CousinsInBinaryTreeII {
    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        root.val = 0;

        while (!q.isEmpty()) {
            int nextLevelSum = 0;
            List<TreeNode> levelNodes = new ArrayList<>();

            for (int i = q.size(); i > 0; i--) {
                TreeNode cur = q.poll();
                levelNodes.add(cur);

                if (cur.left != null) {
                    nextLevelSum += cur.left.val;
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    nextLevelSum += cur.right.val;
                    q.offer(cur.right);
                }
            }
            for (TreeNode node : levelNodes) {
                int sum = nextLevelSum;
                if (node.left != null) {
                    sum -= node.left.val;
                }
                if (node.right != null) {
                    sum -= node.right.val;
                }
                if (node.left != null) {
                    node.left.val = sum;
                }
                if (node.right != null) {
                    node.right.val = sum;
                }
            }
        }

        return root;
    }
}
