package BinaryTree;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

/*
 * https://leetcode.com/problems/print-binary-tree
 * Google
 */
public class PrintBinaryTree {
    // O(n), O(h)
    public List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root);
        int rows = height;
        int cols = (int)Math.pow(2, height) - 1;
        List<List<String>> res = new ArrayList<>(rows);

        for (int i = 0; i < rows; i++) {
            List<String> row = new ArrayList<>(cols);
            res.add(row);
            for (int j = 0; j < cols; j++) {
                row.add("");
            }
        }
        fillTree(root, res, 0, 0, cols - 1);
        return res;
    }

    private void fillTree(TreeNode root, List<List<String>> res, int depth, int left, int right) {
        if (root == null)
            return;
        int mid = (right + left) / 2;
        res.get(depth).set(mid, String.valueOf(root.val));
        fillTree(root.left, res, depth + 1, left, mid);
        fillTree(root.right, res, depth + 1, mid + 1, right);
    }

    private int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}
