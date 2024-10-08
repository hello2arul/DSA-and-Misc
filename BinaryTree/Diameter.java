package Tree;

//https://leetcode.com/problems/diameter-of-binary-tree/description/
public class Diameter {
    int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return diameter - 1;    
    }

    private int maxDepth(TreeNode node) {
        if(node == null) return 0;
        int left = maxDepth(node.left);
        int right = maxDepth(node.right);

        diameter = Math.max(diameter, left + 1 + right);
        return Math.max(left, right) + 1;
    }
}
