A disjoint-set data structure is a data structure that keeps track of a set of elements partitioned into a number of disjoint (non-overlapping) subsets.

For Example:
Consider that there are 5 students in a classroom namely, A, B, C, D, E.

They will be denoted as 5 different subsets: {A}, {B}, {C}, {D}, {E}.

As explained above, there are generally two types of operations performed on a Disjoint-Set data structure:
* Union(A, B): This operation tells to merge the sets containing elements A and B respectively by performing a Union operation on the sets.
* Find(A): This operation tells to find the subset to which the element A belongs.

### Time Complexity
- **Find**: `O(α(n))`, where `α(n)` is the inverse Ackermann function. This is nearly constant time.
- **Union**: `O(α(n))`, where `α(n)` is the inverse Ackermann function. This is nearly constant time.

https://leetcode.com/tag/union-find/