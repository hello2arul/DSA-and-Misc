package Tree;

//https://leetcode.com/problems/balanced-binary-tree/
public class IsBalanced {
    public boolean isBalanced(TreeNode root) {
        return height(root) != - 1;
    }

    public int height(TreeNode node) {
        if(node == null)    return 0;
        int lh = height(node.left);
        int rh = height(node.right);

        if(lh == -1 || rh == -1)
            return -1;
        if(Math.abs(lh - rh) > 1)
            return -1;

        return Math.max(lh, rh) + 1;
    }
}
