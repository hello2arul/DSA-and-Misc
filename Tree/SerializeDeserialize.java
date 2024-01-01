package Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/
public class SerializeDeserialize {
    /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    
    private void serialize(TreeNode node, StringBuilder sb) {
        if(node == null) {
            sb.append("#,");
        } else {
            sb.append(node.val + ",");
            serialize(node.left, sb);
            serialize(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserialize(q);
    }
    
    private TreeNode deserialize(Queue<String> q) {
        if(q.isEmpty()) return null;
        String val = q.poll();
        if(val.equals("#")) return null;
        int intVal = Integer.parseInt(val);
        TreeNode node = new TreeNode(intVal);
        node.left = deserialize(q);
        node.right = deserialize(q);
        return node;
    }
}