package Tree;

//https://leetcode.com/problems/root-equals-sum-of-children
public class ChildrenSumEqualsRoot {
    public boolean checkTree(TreeNode root) {
        if(root == null || (root.left == null && root.right == null))
            return true;
        int sum = (root.left != null ? root.left.val : 0) + 
                  (root.right != null ? root.right.val: 0);
        return sum == root.val && checkTree(root.left) && checkTree(root.right);
    }
}
