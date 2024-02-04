package Tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
* https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/
* One more idea is to use LCA somehow to build this
* */
class StepByStepDirFromAtoB {
    String result;
    public String getDirections(TreeNode root, int startValue, int destValue) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        buildParentMap(root, null, parentMap);
        String res = "";
        Set<TreeNode> visited = new HashSet<>();
        dfs(getNodeFromVal(startValue, root), destValue, res, parentMap, visited);
        return result;
    }

    private boolean dfs(TreeNode node, int destValue, String cur,
                        Map<TreeNode, TreeNode> parentMap, Set<TreeNode> visited) {
        if(node == null || visited.contains(node))
            return false;
        if(node.val == destValue) {
            result = new String(cur);
            return true;
        }

        visited.add(node);
        boolean res = false;
        if(parentMap.get(node) != null) {
            res = dfs(parentMap.get(node), destValue, cur + "U", parentMap, visited);
        }
        return res || dfs(node.left, destValue, cur + "L", parentMap, visited) ||
                dfs(node.right, destValue, cur + "R", parentMap, visited);
    }

    private void buildParentMap(TreeNode node, TreeNode parent,
                                Map<TreeNode, TreeNode> parentMap) {
        if(node != null) {
            parentMap.put(node, parent);
            buildParentMap(node.left, node, parentMap);
            buildParentMap(node.right, node, parentMap);
        }
    }

    private TreeNode getNodeFromVal(int val, TreeNode node) {
        if(node == null)
            return node;
        if(node.val == val)
            return node;
        TreeNode left = getNodeFromVal(val, node.left);
        if(left != null)
            return left;
        return getNodeFromVal(val, node.right);
    }
}