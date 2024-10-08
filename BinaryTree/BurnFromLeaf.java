package Tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/
public class BurnFromLeaf {
    TreeNode start;
    public int amountOfTime(TreeNode root, int startVal) {
        if(root == null)    return 0;
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        buildParent(root, null, parentMap, startVal);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(start);
        int time = 0;
        TreeNode cur = start;
        Set<TreeNode> visited = new HashSet<>();
        visited.add(start);

        while(!q.isEmpty()) {
            int size = q.size(); 
            while(size-- > 0) {
                cur = q.poll();
                TreeNode parent = parentMap.get(cur);
                validateAndAdd(parent, visited, q);
                validateAndAdd(cur.left, visited, q);
                validateAndAdd(cur.right, visited, q);

            }
            time++;
        }
        return time - 1;
    }

    private void validateAndAdd(TreeNode node, Set<TreeNode> visited, Queue<TreeNode> q) {
        if(node != null && !visited.contains(node)) {
            visited.add(node);
            q.offer(node);
        }
    }

    private void buildParent(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> parentMap, int targetVal) {
        if(node != null) {
            parentMap.put(node, parent);
            if(node.val == targetVal)
                start = node;
            buildParent(node.left, node, parentMap, targetVal);
            buildParent(node.right, node, parentMap, targetVal);
        }
    }
}