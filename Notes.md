
## DSA

- **Big O (O)**: Upper bound (worst-case scenario)
- **Big Omega (Ω)**: Lower bound (best-case scenario)
- **Big Theta (Θ)**: Exact bound (average-case scenario)

### Optimizing with BUD
- Bottlenecks
- Unnecessary work
- Duplicated work

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

### Binary Indexed Tree (BIT) or Fenwick Tree

A Binary Indexed Tree (BIT), also known as a Fenwick Tree, is a data structure that provides efficient methods for cumulative frequency tables. It supports point updates and prefix sum queries in logarithmic time. BIT is simpler and more space-efficient but less flexible.
Segment Tree is more complex and requires more space but is more versatile and can handle a wider variety of range queries and updates

#### Key Operations:
- **Update**: Increment the value at a specific index.
- **Query**: Compute the prefix sum up to a specific index.

#### Time Complexity:
- **Update**: `O(log n)`
- **Query**: `O(log n)`

#### Use Cases:
- Efficiently computing prefix sums.
- Range sum queries.
- Inversion count in an array.
- Counting smaller elements after self.

### TreeMap in Java

`TreeMap` is a Red-Black tree-based implementation of the `Map` interface, maintaining keys in sorted order.

#### Key Methods:

- **ceilingKey(K key)**:
  - Returns the least key greater than or equal to the given key, or `null` if there is no such key.
  - Time Complexity: `O(log n)`

- **floorKey(K key)**:
  - Returns the greatest key less than or equal to the given key, or `null` if there is no such key.
  - Time Complexity: `O(log n)`

- **higherKey(K key)**:
  - Returns the least key strictly greater than the given key, or `null` if there is no such key.
  - Time Complexity: `O(log n)`

- **lowerKey(K key)**:
  - Returns the greatest key strictly less than the given key, or `null` if there is no such key.
  - Time Complexity: `O(log n)`

- **headMap(K toKey)**:
  - Returns a view of the portion of this map whose keys are strictly less than `toKey`.
  - Time Complexity: `O(log n)`

- **tailMap(K fromKey)**:
  - Returns a view of the portion of this map whose keys are greater than or equal to `fromKey`.
  - Time Complexity: `O(log n)`

- **subMap(K fromKey, K toKey)**:
  - Returns a view of the portion of this map whose keys range from `fromKey`, inclusive, to `toKey`, exclusive.
  - Time Complexity: `O(log n)`

- **descendingMap()** :

   - Returns a view of the map in reverse order. This is quite useful when you need to iterate in descending order. Time Complexity: O(log n)


## Misc
### Windows
### Getting saved wifi password
- `wlan show profile`
- `netsh wlan show profile name= "JioFiber-Fu6a6" key=clear`