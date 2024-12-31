### Notes
- Converting List to array.
```
List<int[]> res = new ArrayList<>();
res.toArray(new int[res.size()][]);
```

#### Bitmap
-  Bitmap is typically used to represent a grid of bits (1s and 0s), often for tasks like image processing, memory-efficient data storage, or implementing boolean matrices
```
 BitSet bitmap = new BitSet(10);

// Set some bits to true
bitmap.set(0); // Set the first bit to true
bitmap.set(5); // Set the sixth bit to true
bitmap.clear(0); // Set the first bit to false

System.out.println("Updated Bitmap: " + bitmap); -> {5}
```