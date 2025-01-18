## B-tree

A B-tree is a self-balancing tree data structure that maintains sorted data and allows for efficient insertion, deletion, and search operations. It is a generalization of a binary search tree (BST) in that a node can have more than two children. B-trees are designed to work well on systems that read and write large blocks of data, such as databases and file systems.

### Characteristics of a B-tree
- **Balanced**: All leaf nodes are at the same depth, ensuring balanced tree height.
- **Multi-way Tree**: Each node can have multiple children, not just two.
- **Node Capacity**: Each node can contain multiple keys and children pointers.
- **Sorted Order**: Keys within a node and across nodes are maintained in sorted order.
- **Efficient Operations**: Insertion, deletion, and search operations are efficient, typically `O(log n)`.

### When is a B-tree used?
B-trees are commonly used in scenarios where data needs to be stored and retrieved efficiently, especially when dealing with large datasets that do not fit entirely in memory. Some common use cases include:
- **Databases**: B-trees are used in database indexing to allow quick access to records.
- **File Systems**: B-trees are used in file systems to manage directories and files efficiently.
- **Key-Value Stores**: B-trees are used in key-value stores to maintain sorted keys and enable fast lookups.

### Variations of B-trees
- **B+ Tree**: Stores all values at the leaf level and links leaf nodes for efficient range queries.
- **B* Tree**: Maintains higher occupancy for nodes, reducing splits and merges.
- **B- Tree**: Allows more flexible balancing with fewer keys per node.


### Example of a B-tree
Here's a simple example of a B-tree of degree `t = 2`:
``` 
    [10, 20]
   /    |    \
[5, 7] [15, 17] [25, 30]
```

- The root node contains two keys: 10 and 20.
- The root node has three children: `[5, 7]`, `[15, 17]`, and `[25, 30]`.
- Each child node contains between `t-1` and `2t-1` keys.

### Further Reading
- [Wikipedia: B-tree](https://en.wikipedia.org/wiki/B-tree)