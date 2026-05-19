# Word Break

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `String` `Dynamic Programming` `Trie` `Memoization`  
**Time:** O(N^2 * L)  
**Space:** O(N)

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
This problem can be solved using dynamic programming or recursion with memoization by checking all possible segmentations.

## Intuition
The core idea is to break down the problem into smaller, overlapping subproblems. If we can determine if a prefix of the string can be segmented, we can use that information to solve the problem for the entire string. For example, if `s = "leetcode"` and `wordDict = ["leet", "code"]`, we first check if "leet" is in the dictionary. If it is, we then recursively check if the remaining part "code" can be segmented. This recursive structure, combined with memoization to avoid recomputing results for the same substrings, leads to an efficient solution.

## Algorithm
1. **Initialization**:
    * Convert the `wordDict` into a `HashSet` for efficient O(1) average time lookups.
    * Initialize a memoization array (e.g., `Boolean[] memo`) of size `s.length() + 1` to store results of subproblems. `memo[i]` will store whether the substring `s[i:]` can be segmented. Initialize all entries to `null`.
2. **Recursive Function `func(startIndex, endIndex, s, wordDictSet, memo)`**:
    * **Base Case 1**: If `startIndex` reaches the end of the string (`startIndex >= s.length()`), it means we have successfully segmented the entire string, so return `true`.
    * **Base Case 2**: If `endIndex` goes beyond the string length, it means the current segmentation path is invalid, return `false`.
    * **Memoization Check**: If `memo[startIndex]` is not `null`, return the stored result.
    * **Recursive Step**:
        * Consider the substring `s.substring(startIndex, endIndex + 1)`.
        * **If `s.substring(startIndex, endIndex + 1)` is in `wordDictSet`**:
            * We have found a valid word. Now, we have two choices:
                * **Option A**: Try to segment the rest of the string starting from `endIndex + 1`. This is `func(endIndex + 1, endIndex + 1, s, wordDictSet, memo)`.
                * **Option B**: Continue extending the current word by incrementing `endIndex`. This is `func(startIndex, endIndex + 1, s, wordDictSet, memo)`.
            * The result for `memo[startIndex]` is `true` if either Option A or Option B returns `true`.
        * **If `s.substring(startIndex, endIndex + 1)` is NOT in `wordDictSet`**:
            * We cannot form a valid word with this substring. We must continue extending the current word by incrementing `endIndex`. This is `func(startIndex, endIndex + 1, s, wordDictSet, memo)`.
    * **Store and Return**: Store the computed result in `memo[startIndex]` before returning.
3. **Initial Call**: Call `func(0, 0, s, wordDictSet, memo)` to start the process from the beginning of the string.

*Note: The provided solution uses a slightly different recursive structure where `i` is the start of the current segment and `j` is the end of the potential word being formed. The logic is similar: `func(i, j)` checks if `s[i:]` can be segmented, considering `s[i...j]` as a potential word.*

## Concept to Remember
*   **Dynamic Programming / Memoization**: Breaking down a problem into overlapping subproblems and storing their solutions to avoid redundant computations.
*   **String Manipulation**: Efficiently extracting substrings and checking for their existence in a dictionary.
*   **Recursion**: Defining a problem in terms of itself, with base cases to terminate the recursion.
*   **Hash Sets**: Using hash sets for O(1) average time complexity lookups of dictionary words.

## Common Mistakes
*   **Inefficient Dictionary Lookups**: Using a `List` for `wordDict` and iterating through it for each substring check, leading to O(N*M) complexity for lookups where N is string length and M is dictionary size.
*   **Not Handling Overlapping Subproblems**: Without memoization, the recursive solution can recompute the same subproblems multiple times, leading to exponential time complexity.
*   **Off-by-One Errors in Substring Indices**: Incorrectly defining the start or end indices when extracting substrings.
*   **Incorrect Base Cases**: Missing or incorrectly defined base cases in the recursion can lead to infinite loops or incorrect results.
*   **Mutable String Issues**: Modifying the string directly or using `StringBuilder` in a way that affects subsequent recursive calls without proper management.

## Complexity Analysis
*   **Time**: O(N^2 * L) in the worst case, where N is the length of the string `s` and L is the average length of words in the dictionary. The `N^2` comes from the fact that for each starting position `i`, we might iterate up to `N` characters to form a substring, and there are `N` possible starting positions. The `L` factor is for the `substring` operation and `HashSet.contains` which can take up to O(L) in worst case if hash collisions are bad, but on average it's O(L) for hashing the substring. A more precise analysis considering DP is O(N^2) if substring and hash lookups are considered O(1) on average.
*   **Space**: O(N) for the memoization array and O(M*L) for the HashSet, where M is the number of words in the dictionary and L is the average length of words. The dominant factor is usually O(N) for the memoization array.

## Commented Code
```java
class Solution {
    // Memoization array to store results of subproblems.
    // memo[i] will store whether the substring s[i:] can be segmented.
    // Initialized to null, indicating the subproblem hasn't been solved yet.
    Boolean[] memo;

    // Main function to initiate the word break check.
    public boolean wordBreak(String s, List<String> wordDict) {
        // Convert the input string to a StringBuilder for efficient substring operations (though not strictly necessary here as substring is used).
        // The provided solution uses StringBuilder but then uses substring, which is fine.
        StringBuilder sb = new StringBuilder(s);
        // Convert the word dictionary list into a HashSet for O(1) average time complexity lookups.
        HashSet<String> hs = new HashSet<>(wordDict);
        // Initialize the memoization array with a size one greater than the string length.
        // memo[i] corresponds to the substring starting at index i. memo[s.length()] is implicitly true (empty string).
        memo = new Boolean[s.length() + 1];
        // Start the recursive helper function from index 0.
        // The initial call func(0, 0, ...) means we start checking from index 0, and the potential word ends at index 0.
        return func(0, 0, sb, hs);
    }

    // Recursive helper function with memoization.
    // i: the starting index of the current substring being considered for segmentation.
    // j: the ending index of the potential word being formed starting from i.
    // sb: the StringBuilder representation of the input string.
    // hs: the HashSet of dictionary words.
    public boolean func(int i, int j, StringBuilder sb, HashSet<String> hs) {
        // Base Case 1: If the starting index 'i' has reached or surpassed the end of the string,
        // it means we have successfully segmented the entire string.
        if (i >= sb.length()) return true;

        // Base Case 2: If the ending index 'j' has reached or surpassed the end of the string,
        // but 'i' has not, it means the current path of segmentation is invalid because we ran out of string to form a word.
        if (j >= sb.length()) return false;

        // Memoization Check: If the result for the subproblem starting at index 'i' has already been computed and stored, return it.
        if (memo[i] != null) return memo[i];

        // Check if the substring from index 'i' to 'j' (inclusive) is a valid word in the dictionary.
        if (hs.contains(sb.substring(i, j + 1))) {
            // If it's a valid word, we have two possibilities:
            // 1. Try to segment the rest of the string starting from the next character after the current word (j + 1).
            //    This is represented by func(j + 1, j + 1, sb, hs). We reset the start and end pointers for the next word.
            // 2. Continue extending the current word by incrementing 'j' to see if a longer word can be formed.
            //    This is represented by func(i, j + 1, sb, hs).
            // The result for memo[i] is true if EITHER of these possibilities leads to a successful segmentation.
            return memo[i] = (func(j + 1, j + 1, sb, hs) || func(i, j + 1, sb, hs));
        } else {
            // If the substring s[i...j] is NOT a valid word, we must continue extending the current word by incrementing 'j'.
            // We cannot form a valid word at this point, so we explore the possibility of a longer word starting at 'i'.
            return memo[i] = func(i, j + 1, sb, hs);
        }
    }
}
```

## Interview Tips
1.  **Clarify Constraints**: Ask about the maximum length of the string `s` and the size of `wordDict`. This helps in choosing the most efficient approach.
2.  **Explain the DP/Memoization Approach**: Clearly articulate why DP or memoization is necessary to avoid exponential time complexity. Walk through an example to illustrate overlapping subproblems.
3.  **Discuss Trade-offs**: Mention the trade-off between using a `HashSet` for dictionary lookups versus a `List`. Explain the time complexity implications.
4.  **Edge Cases**: Be prepared to discuss edge cases like an empty string `s`, an empty `wordDict`, or a `wordDict` containing an empty string.
5.  **Alternative DP Approach**: Briefly mention the bottom-up DP approach where `dp[i]` represents whether `s[0...i-1]` can be segmented. This shows a broader understanding of DP techniques.

## Revision Checklist
- [ ] Understand the problem statement clearly.
- [ ] Recognize the overlapping subproblems nature.
- [ ] Implement memoization or bottom-up DP correctly.
- [ ] Use a HashSet for efficient dictionary lookups.
- [ ] Handle substring indexing carefully.
- [ ] Test with edge cases (empty string, empty dictionary).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Word Break II
*   Combination Sum
*   Partition Equal Subset Sum
*   Decode Ways

## Tags
`Array` `Hash Map` `String` `Dynamic Programming` `Recursion`
