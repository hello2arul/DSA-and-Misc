package BinaryTree;

import java.util.*;

import javax.swing.tree.TreeNode;


/*
https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/description/?envType=company&envId=google&favoriteSlug=google-all
Google
TODO:
*/
public class HeightOfBinaryTreeAfterSubTreeRemoval {
    // Function to calculate the height of the tree rooted at 'node'
    private int calculateHeight(TreeNode node, Map<TreeNode, Integer> heightMemo) {
        if (node == null) {
            return -1; // Base case: height of an empty subtree is -1
        }
        
        // If we've already computed the height for this node, return it
        if (heightMemo.containsKey(node)) {
            return heightMemo.get(node);
        }
        
        // Recursively calculate the height of left and right subtrees
        int leftHeight = calculateHeight(node.left, heightMemo);
        int rightHeight = calculateHeight(node.right, heightMemo);
        
        // The height of the current node is 1 + the max height of its subtrees
        int currentHeight = 1 + Math.max(leftHeight, rightHeight);
        
        // Memoize the result for this node
        heightMemo.put(node, currentHeight);
        
        return currentHeight;
    }

    public int[] treeQueries(TreeNode root, int[] queries) {
        Map<TreeNode, Integer> heightMemo = new HashMap<>();
        
        // Step 1: Precompute the height of the entire tree
        calculateHeight(root, heightMemo);

        // Step 2: Process each query
        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int queryValue = queries[i];
            TreeNode queryNode = findNode(root, queryValue);
            
            // Compute the height of the tree after removing the query node's subtree
            result[i] = getTreeHeightExcludingSubtree(root, queryNode, heightMemo);
        }

        return result;
    }

    // Function to find a node by value in the tree
    private TreeNode findNode(TreeNode root, int value) {
        if (root == null || root.val == value) {
            return root;
        }
        
        TreeNode left = findNode(root.left, value);
        if (left != null) {
            return left;
        }
        
        return findNode(root.right, value);
    }

    // Helper function to compute the height of the tree excluding the subtree rooted at 'excludeNode'
    private int getTreeHeightExcludingSubtree(TreeNode root, TreeNode excludeNode, Map<TreeNode, Integer> heightMemo) {
        if (root == null || root == excludeNode) {
            return -1;
        }
        
        // If the current node is the root or is not part of the excluded subtree
        int leftHeight = getTreeHeightExcludingSubtree(root.left, excludeNode, heightMemo);
        int rightHeight = getTreeHeightExcludingSubtree(root.right, excludeNode, heightMemo);
        
        return 1 + Math.max(leftHeight, rightHeight);
    }
}