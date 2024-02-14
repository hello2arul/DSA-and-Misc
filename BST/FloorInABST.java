package BST;

import Tree.TreeNode;

import java.util.TreeSet;

/*
 * floor -> the closest value less than or equal to
 * https://www.geeksforgeeks.org/problems/floor-in-bst/1
 */
public class FloorInABST {
    //O(logn) O(1)
    public TreeNode getFloor(TreeNode root, int val) {
        TreeNode cur = root;
        TreeNode floor;

        while(cur != null) {
            if(cur.val == val)  return cur;
            else if(cur.val > val)  
                cur = cur.left;
            else {
                floor = cur;
                cur = cur.right;
            }
        }
        return floor;
    }

    public TreeNode getCeil(TreeNode root, int val) {
        TreeNode cur = root;
        TreeNode ceil;

        while(cur != null) {
            if(cur.val == val)  return cur;
            else if(cur.val < val)  
                cur = cur.right;
            else {
                ceil = cur;
                cur = cur.left;
            }
        }
        return ceil;
    }

    public List<Integer> ceilOnLeft(int[] arr) {
        List<Integer> res = new ArrayList<>();
        TreeSet<Integer> treeSet = new TreeSet<>();

        for(int num: arr) {
            if(treeSet.ceiling(num)) {
                res.add(num);
            } else {
                res.add(-1);
            }
            treeSet.add(num);
        }
        return res;
    }
}
