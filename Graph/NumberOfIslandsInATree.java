package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javax.swing.tree.TreeNode;

/*
https://leetcode.com/discuss/interview-question/6275124/Google-or-Phone-Screen-or-Number-of-Islands-in-a-Tree
Google

Given a Binary tree having nodes with value 0 and 1. 
write a function to return the number of islands ?


Follow Up Questions-


Return the sizes of unique islands

 */

public class NumberOfIslandsInATree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    
        TreeNode(int x) {
            val = x;
        }
    }
    
    // Helper function to perform DFS and mark visited nodes
    private int dfs(TreeNode node, Set<TreeNode> visited) {
        if (node == null || node.val == 0 || visited.contains(node)) {
            return 0;
        }
        
        // Mark the node as visited
        visited.add(node);
        
        // Initialize the size of the current island
        int islandSize = 1;
        
        // Visit the left and right children
        islandSize += dfs(node.left, visited);
        islandSize += dfs(node.right, visited);
        
        return islandSize;
    }

    // Function to count the number of islands and return the sizes of unique islands
    public Map<String, Object> countIslands(TreeNode root) {
        Set<TreeNode> visited = new HashSet<>();
        List<Integer> islandSizes = new ArrayList<>();
        int islandCount = 0;
        
        // Traverse the entire tree
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            // If it's part of an island and hasn't been visited yet
            if (node.val == 1 && !visited.contains(node)) {
                islandCount++;  // New island found
                int islandSize = dfs(node, visited);  // Size of the island found
                if (islandSize > 0) {
                    islandSizes.add(islandSize);
                }
            }

            // Add child nodes to the queue for further traversal
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        // Return the number of islands and the sizes of each island
        Map<String, Object> result = new HashMap<>();
        result.put("islandCount", islandCount);
        result.put("islandSizes", islandSizes);
        return result;
    }
}
