package Tree;

//https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
public class ConstructTreeFromTraversals {
    int preStart = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, inorder.length);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int inStart,int inEnd) {
        if(inStart > inEnd || preStart >= preorder.length)
            return null;
        TreeNode node = new TreeNode(preorder[preStart++]);
        int i = inStart;
        while(i < inEnd && inorder[i] != node.val)
            i++;
        
        node.left = buildTree(preorder, inorder, inStart, i - 1);
        node.right = buildTree(preorder, inorder, i + 1, inEnd);
        return node;
    }
}
