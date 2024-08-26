package NAryTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/*
 * https://leetcode.com/problems/n-ary-tree-postorder-traversal
 * https://leetcode.com/problems/n-ary-tree-preorder-traversal/
 */
public class NAryTree {
    class Node {
        int val;
        List<Node> children;
    }

    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private void dfs(Node node, List<Integer> res) {
        if (node == null) {
            return;
        }
        for (Node child : node.children) {
            dfs(child, res);
        }
        res.add(node.val);
    }

    public List<Integer> postorderIterative(Node root) {
        List<Integer> res = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (node == null)
                continue;
            res.add(node.val);

            for (Node child: node.children) {
                stack.push(child);
            }
        }
        Collections.reverse(res);
        return res;
    }

    private void preOrder(Node node, List<Integer> res) {
        if (node == null)
            return;
        res.add(node.val);
        if (node.children == null)
            return;
        for (Node child: node.children)
            dfs(child, res);
        
    }

    public List<Integer> preorderIterative(Node root) {
        List<Integer> res = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (node == null)
                continue;
            res.add(node.val);
            for (int i = node.children.size() - 1; i >= 0; i--)
                stack.push(node.children.get(i));
        }
        return res;
    }
}
