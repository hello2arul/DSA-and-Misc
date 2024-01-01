package Tree;

//https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
public class ConvertSortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        public TreeNode sortedArrayToBST(int[] nums) {
            return toBST(nums, 0, nums.length - 1);
        }
    
        private TreeNode toBST(int[] nums, int start, int end) {
            if(start > end)    return null;
            int mid = (start + end) / 2;
            TreeNode node = new TreeNode(nums[mid]);
            node.left = toBST(nums, start, mid - 1);
            node.right = toBST(nums, mid + 1, end);
            return node;
        }
    }
}
