
traversals:
1. Inorder -> left, root, right
2. Preorder -> root, left, right
3. Postorder -> left, right, root

a binary tree can be constructed fromn
1. inorder & preorder
2. indoer & postorder

Complete binary tree:
    Completely filled except maybe the last level and the last level should be filled from left to right.

Self balancing trees:
uses left or right rotations to keep themself balanced.
1. AVL tree
   -> is a BST
   -> maintains balance factor
   -> has difference between height of left and right subtrees at most 1

2. RedBlack tree
    -> loose in terms of balanced as compared to AVL tree so retructuring happens less during
    insert, delete
    -> Java's TreeSet and TreeMap uses this.
    -> Every node is either Red or Black.
    -> Root is always black
    -> No two consecutive reds
    -> No of black nodes from every nodes to all of its descendents shoule be the same