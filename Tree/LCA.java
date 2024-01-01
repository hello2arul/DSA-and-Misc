package Tree;

//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
public class LCA {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null)    return root;
        //we reached either p or q meaning thats the LCA
        if(root.val == p.val || root.val == q.val)
            return root;
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //both left and right had subtrees with either p or q so root is LCA
        if(left != null && right != null)
            return root;
        if(left != null)
            return left;
        return right;
    }
}
