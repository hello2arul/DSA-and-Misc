package DP;

import java.util.HashMap;
import java.util.Map;

import Tree.TreeNode;

/*
    https://leetcode.com/problems/house-robber-iii
 */
public class HouseRobberIII {
    Map<TreeNode, Integer> map = new HashMap<>();

    public int rob(TreeNode root) {
        if(root == null)
            return 0;
        if (map.containsKey(root))
            return map.get(root);
        int robbingCurrentRoot = root.val +
                (root.left != null ? (rob(root.left.left) + rob(root.left.right)) : 0) +
                (root.right != null ? (rob(root.right.left) + rob(root.right.right)) : 0);
        int notRobbingCurrent = rob(root.left) + rob(root.right);
        int res = Math.max(robbingCurrentRoot, notRobbingCurrent);
        map.put(root, res);
        return res;
    }
}
