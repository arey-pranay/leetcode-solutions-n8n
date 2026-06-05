# Word Break

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `String` `Dynamic Programming` `Trie` `Memoization`  
**Time:** O(n^3)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    Boolean[] memo;
    public boolean wordBreak(String s, List<String> wordDict) {
        StringBuilder sb= new StringBuilder(s);
        HashSet<String> hs = new HashSet<>(wordDict);
        memo= new Boolean[s.length()+1];
        return func(0,0,sb,hs);
        
    }
    public boolean func(int i, int j, StringBuilder sb, HashSet<String> hs){
        if(i >= sb.length()) return true;
        if(j >= sb.length()) return false;
        if(memo[i] != null) return memo[i];
        if(hs.contains(sb.substring(i,j+1))){
            return memo[i] = (func(j+1,j+1,sb,hs) || func(i,j+1,sb,hs));
        } else return memo[i] = func(i,j+1,sb,hs);
    }
}
```

---

---
## Quick Revision
Given a string `s` and a dictionary of words `wordDict`, determine if `s` can be segmented into a space-separated sequence of one or more dictionary words.
This problem can be solved using dynamic programming or recursion with memoization, checking all possible segmentations.

## Intuition
The core idea is to break down the problem into smaller, overlapping subproblems. If we can determine if a prefix of the string can be segmented, we can use that information to solve the problem for the entire string. For example, if `s = "leetcode"` and `wordDict = ["leet", "code"]`, we first check if "leet" is in the dictionary. If it is, we then recursively check if the remaining substring "code" can be segmented. This recursive structure, combined with memoization to avoid redundant computations, leads to an efficient solution.

## Algorithm
1. **Initialization**:
    * Convert the `wordDict` into a `HashSet` for O(1) average time lookups.
    * Initialize a memoization array (e.g., `Boolean[] memo`) of size `s.length() + 1` to store results of subproblems. `memo[i]` will store whether the substring `s[i:]` can be segmented.
2. **Recursive Function `func(startIndex, endIndex, s, wordDictSet, memo)`**:
    * **Base Case 1**: If `startIndex` reaches the end of the string (`s.length()`), it means we have successfully segmented the entire string, so return `true`.
    * **Base Case 2**: If `memo[startIndex]` is not null, it means we have already computed the result for this `startIndex`, so return `memo[startIndex]`.
    * **Recursive Step**: Iterate through all possible `endIndex` from `startIndex` to `s.length() - 1`.
        * For each `endIndex`, extract the substring `s.substring(startIndex, endIndex + 1)`.
        * Check if this substring is present in the `wordDictSet`.
        * If it is present, recursively call `func(endIndex + 1, s, wordDictSet, memo)`. If this recursive call returns `true`, it means the rest of the string can also be segmented. In this case, store `true` in `memo[startIndex]` and return `true`.
    * **Failure Case**: If the loop finishes without finding any valid segmentation starting from `startIndex`, store `false` in `memo[startIndex]` and return `false`.
3. **Main Function `wordBreak(s, wordDict)`**:
    * Perform the initialization steps.
    * Call the recursive function starting from index 0: `func(0, s, wordDictSet, memo)`.

## Concept to Remember
*   **Dynamic Programming / Memoization**: Breaking down a problem into overlapping subproblems and storing their solutions to avoid recomputation.
*   **String Manipulation**: Efficiently extracting substrings and checking for their existence in a dictionary.
*   **Set Data Structure**: Using a `HashSet` for fast average-time lookups of dictionary words.

## Common Mistakes
*   **Not using memoization**: Leading to exponential time complexity due to redundant calculations.
*   **Incorrect base cases**: Forgetting to handle the case where the entire string is successfully segmented or when a substring cannot be segmented.
*   **Off-by-one errors in substring indexing**: Miscalculating the start or end indices when extracting substrings.
*   **Inefficient dictionary lookup**: Using a `List` for dictionary lookups instead of a `HashSet`, resulting in O(N) lookups.

## Complexity Analysis
- Time: O(n^3) - The outer loop iterates up to `n` times (for `startIndex`). The inner loop iterates up to `n` times (for `endIndex`). Substring extraction takes O(n) time. In the worst case, we might have `n` recursive calls for each `startIndex`, and each call involves substring operations. With memoization, each `startIndex` is computed only once. The substring operation `s.substring(i, j+1)` takes O(j-i+1) time, which can be up to O(n). So, the total time complexity is roughly O(n * n * n) = O(n^3).
- Space: O(n) - For the memoization array `memo` of size `n+1`. The recursion depth can also go up to `n` in the worst case, contributing to the call stack space. The `HashSet` also takes space proportional to the total length of words in the dictionary.

## Commented Code
```java
class Solution {
    // Memoization array to store results of subproblems.
    // memo[i] will store whether the substring s[i:] can be segmented.
    Boolean[] memo;

    // Main function to initiate the word break process.
    public boolean wordBreak(String s, List<String> wordDict) {
        // Convert the list of dictionary words into a HashSet for efficient O(1) average time lookups.
        HashSet<String> hs = new HashSet<>(wordDict);
        // Initialize the memoization array with null values. The size is s.length() + 1 to handle the base case where the entire string is segmented.
        memo = new Boolean[s.length() + 1];
        // Start the recursive helper function from the beginning of the string (index 0).
        return func(0, s, hs);
    }

    // Recursive helper function with memoization.
    // i: the starting index of the current substring we are trying to segment.
    // s: the original input string.
    // hs: the HashSet containing dictionary words.
    public boolean func(int i, String s, HashSet<String> hs) {
        // Base case: If the starting index 'i' has reached the end of the string,
        // it means we have successfully segmented the entire string.
        if (i == s.length()) {
            return true;
        }
        // Check if the result for this starting index 'i' has already been computed and stored in memo.
        // If memo[i] is not null, return the stored result to avoid redundant computations.
        if (memo[i] != null) {
            return memo[i];
        }

        // Iterate through all possible end indices 'j' for the current substring starting at 'i'.
        // 'j' goes from 'i' up to the end of the string.
        for (int j = i; j < s.length(); j++) {
            // Extract the substring from index 'i' to 'j' (inclusive).
            String sub = s.substring(i, j + 1);
            // Check if this extracted substring is present in the dictionary (HashSet).
            if (hs.contains(sub)) {
                // If the substring is in the dictionary, recursively call 'func' for the rest of the string,
                // starting from the index immediately after the current substring (j + 1).
                // If the recursive call returns true, it means the rest of the string can also be segmented.
                if (func(j + 1, s, hs)) {
                    // Store 'true' in memo[i] because we found a valid segmentation starting from 'i'.
                    memo[i] = true;
                    // Return 'true' as we have found a way to segment the string from index 'i' onwards.
                    return true;
                }
            }
        }

        // If the loop finishes without finding any valid segmentation starting from index 'i',
        // it means the substring s[i:] cannot be segmented.
        // Store 'false' in memo[i] to record this result.
        memo[i] = false;
        // Return 'false'.
        return false;
    }
}
```

## Interview Tips
1.  **Explain the DP approach first**: Start by explaining the intuition behind breaking the problem into subproblems and how memoization or tabulation can optimize it.
2.  **Walk through an example**: Use a small example string and dictionary to trace the execution of your algorithm, highlighting how subproblems are solved and memoized.
3.  **Discuss time and space complexity**: Be prepared to analyze the complexity of your solution, especially the impact of substring operations and memoization.
4.  **Consider edge cases**: Think about empty strings, empty dictionaries, and strings that cannot be segmented at all.

## Revision Checklist
- [ ] Understand the problem statement clearly.
- [ ] Recognize the overlapping subproblems nature.
- [ ] Implement memoization or dynamic programming correctly.
- [ ] Handle base cases for recursion/DP.
- [ ] Optimize dictionary lookups using a HashSet.
- [ ] Analyze time and space complexity.
- [ ] Practice tracing with examples.

## Similar Problems
Word Break II
Palindrome Partitioning
Partition Equal Subset Sum

## Tags
`Array` `Hash Map` `String` `Dynamic Programming` `Recursion`
