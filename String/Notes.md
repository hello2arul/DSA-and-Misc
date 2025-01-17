## String Matching Algorithms

## 1. Rabin-Karp
- Only compare substrings if their hash values are matching.

## 2. Knuth-Morris-Pratt
### 2.1 Construct Longest Proper Prefix Suffix Array
- Proper prefixes of "abc" -> "", "a", "ab"
- Suffixes of "abc" -> "", "c", "bc", "abc"
- "ababc" -> lps[] {0, 0, 1, 2, 0}
- "aaaa" -> lps[] {0, 1, 2, 3}
- "abcd" -> lps[] {0, 0, 0, 0}
- "ababab" -> lps[] {0, 0, 1, 2, 3, 4}    
- Examples: `StringMatchingInAnArray.java`

## 3. Z-function
- TODO