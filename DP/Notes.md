Optimization over plain recursion,

Idea is to reuse the solution of subproblems,

1. Memoization (top down)
2. Tabulation (bottom up)

Variations of LIS:
1. Min deletions to make array sorted
    n - lengthOfLIS
2. Max sum increasing subsequence
    -> slightly modify LIS
3. Max length bitonic subsequence
    -> LIS[i] + LDS[i] - 1
4. Building bridges - https://www.geeksforgeeks.org/dynamic-programming-building-bridges/
5. Longest chain - https://www.geeksforgeeks.org/maximum-length-chain-of-pairs-dp-20/
    both 4 and 5 are similar(sort and find LIS)

Variation of LCS:
1. Diff utility Eg) git
2. Min insertions, deletions to convert S1 to S2 -> EditDistance
3. ShortestCommonSupersequence
    shortest string that has both s1 and s2 as subsequence
    find LCS, then insert missing characters from both the strings
4. LongestPalindromicSubsequence
    reverse s1 and find LCS(s1, s2) if even insert any middle character
5. LongestRepeatingSubsequence
    s1, s2 = s1 LCS(s1, s2) where i != j
6. Print LCS
    traverse the dp from bottom
    If current character in both the strings are same, then current character is part of LCS
    if(dp[i - 1][j] > dp[i][j - 1]) i--;
    else j--;



