package Strings;


/**
 * https://www.geeksforgeeks.org/find-pattern-in-infinite-binary-stream-i/
 * use rabin karp -> hash of window
 */
public class FindPatternInInfiniteBinaryStream {

    public static int findPattern(InfiniteStream stream,
                                  List<Integer> pattern)
    {
        // Calculate the hash for the pattern.
        int patternHash = 0;
        for (int bit : pattern) {
            // Efficiently shift and add bits
            patternHash = (patternHash << 1) | bit;
        }

        // Calculate the initial window hash.
        int windowHash = 0;
        for (int i = 0; i < pattern.size(); i++) {
            int current = stream.next();
            // Efficiently shift and add bits
            windowHash = (windowHash << 1) | current;
        }

        // Check if the initial window matches the pattern.
        if (windowHash == patternHash) {
            return 0; // Pattern found at the beginning of
            // the stream
        }

        // Initialize variables for window management.
        int currentIndex = 1; // Index of the first element
        // in the current window
        int unsetMask
                = (1 << (pattern.size() - 1))
                - 1; // Mask to unset the largest bit

        while (true) {
            // Unset the largest bit from the window hash,
            // representing the element leaving the window.
            windowHash &= unsetMask;
            windowHash
                    <<= 1; // Shift the window hash to the left

            // Get the next element from the stream and
            // update the window hash.
            int current = stream.next();
            if (current == 1) {
                windowHash
                        |= 1; // Set the least significant bit
                // if the element is 1
            }

            // Check if the current window matches the
            // pattern.
            if (windowHash == patternHash) {
                return currentIndex; // Pattern found at the
                // current index
            }
            else {
                currentIndex++; // Move the window forward
            }
        }
    }

    public static void main(String[] args)
    {
        // Initialize the stream and pattern
        Integer[] stream = { 1, 1, 1, 0, 1, 1, 1 };
        Integer[] pattern = { 0, 1 };

        // Create an InfiniteStream object
        InfiniteStream infiniteStream
                = new InfiniteStream(Arrays.asList(stream));

        // Find the pattern in the stream
        int index = findPattern(infiniteStream,
                Arrays.asList(pattern));

        // Print the result
        if (index != -1) {
            System.out.println("Pattern found at index "
                    + index);
        }
        else {
            System.out.println("Pattern not found");
        }
    }

}

class InfiniteStream {
    private final List<Integer> bits;
    private int index;

    public InfiniteStream(List<Integer> bits)
    {
        this.bits = bits;
        this.index = 0;
    }

    public int next()
    {
        // Return the next bit in the stream and increment
        // the index
        int bit = this.bits.get(this.index);
        this.index++;
        return bit;
    }
}
