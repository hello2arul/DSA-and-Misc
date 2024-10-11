package SegmentTree;


class SegmentTree {
    private class Node {
        int start, end, sum;
        Node left, right;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
            this.sum = 0;
            this.left = this.right = null;
        }

        @Override 
        public String toString() {
            return start + " -> " + end + ": " + sum;
        }
    }

    private Node root;
    public SegmentTree(int[] nums) {
        root = buildTree(nums, 0, nums.length - 1);   
    }

    private Node buildTree(int[] nums, int start, int end) {
        if (start > end) return null;
        Node node = new Node(start, end);
        if(start == end) {
            node.sum = nums[start];
        } else {
            int mid = (start + end) / 2;
            node.left = buildTree(nums, start, mid);
            node.right = buildTree(nums, mid + 1, end);
            node.sum = node.left.sum + node.right.sum;
        }
        return node;
    }

    public void update(int idx, int val) {
        update(root, idx, val);
    }

    private void update(Node node, int idx, int val) {
        if (node.start == node.end) {
            node.sum = val;
            return;
        }
        int mid = (node.start + node.end) / 2;
        if (idx <= mid) {
            update(node.left, idx, val);
        } else {
            update(node.right, idx, val);
        }
        node.sum = node.left.sum + node.right.sum;
    }

    public int getSum(int start, int end) {
        return getSum(root, start, end);
    }

    private int getSum(Node node, int start, int end) {
        if (node.start == start && node.end == end) {
            return node.sum;
        }
        int mid = (node.start + node.end) / 2;
        if (end <= mid) {
            return getSum(node.left, start, end);
        } else if(start >= mid + 1) {
            return getSum(node.right, start, end);
        }
        return getSum(node, start, mid) + getSum(node, mid + 1, end);
    }
    
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        SegmentTree tree = new SegmentTree(arr);
        tree.update(3, 10);
        for(int i =0; i < arr.length; i++)
            System.out.println(tree.getSum(0, i));
    }
}