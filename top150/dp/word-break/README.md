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
    HashSet<String> hs;
    public boolean wordBreak(String s, List<String> wordDict) {
        hs = new HashSet<>(wordDict);
        memo=new Boolean[s.length()+1];
        return func(s,0);
    }
    
    public boolean func(String s, int start){
        if(start==s.length()) return true;
        if(memo[start] != null) return memo[start];
        for(int i=start+1;i<=s.length();i++) if(hs.contains(s.substring(start,i)) && func(s,i)) return memo[start]=true;
        return memo[start] = false;
    }

}

```

---

---
## Quick Revision
Given a string `s` and a dictionary of words `wordDict`, determine if `s` can be segmented into a space-separated sequence of one or more dictionary words.
This problem is solved using dynamic programming or recursion with memoization.

## Intuition
The core idea is to break down the problem into smaller, overlapping subproblems. If we can determine if a prefix of the string can be segmented, and if the remaining suffix is a valid dictionary word, then the whole string can be segmented. This naturally leads to a recursive structure. To avoid redundant computations, we use memoization to store the results of subproblems.

## Algorithm
1. Initialize a `HashSet` from the `wordDict` for efficient O(1) average time lookups.
2. Initialize a memoization array (e.g., `Boolean[] memo`) of size `s.length() + 1` to store results of subproblems. `memo[i]` will store whether the substring `s[i:]` can be segmented. Initialize all entries to `null`.
3. Define a recursive helper function `func(s, start)`:
    a. Base Case: If `start` reaches the end of the string (`start == s.length()`), it means we have successfully segmented the entire string, so return `true`.
    b. Memoization Check: If `memo[start]` is not `null`, return the stored result `memo[start]`.
    c. Iteration: Iterate from `i = start + 1` to `s.length()`:
        i. Extract the substring `s.substring(start, i)`.
        ii. Check if this substring is present in the `HashSet`.
        iii. If it is present, recursively call `func(s, i)` to check if the rest of the string (from index `i` onwards) can also be segmented.
        iv. If both conditions (substring in dictionary AND recursive call returns `true`) are met, then `s[start:]` can be segmented. Store `true` in `memo[start]` and return `true`.
    d. If the loop finishes without finding a valid segmentation, it means `s[start:]` cannot be segmented. Store `false` in `memo[start]` and return `false`.
4. Call the helper function `func(s, 0)` to start the process from the beginning of the string.

## Concept to Remember
*   **Recursion with Memoization:** Breaking down a problem into smaller, overlapping subproblems and storing their solutions to avoid recomputation.
*   **Dynamic Programming (Bottom-Up):** An alternative approach where subproblems are solved iteratively from smallest to largest.
*   **String Manipulation:** Efficiently extracting substrings and checking for their existence in a dictionary.
*   **Hash Sets:** Using hash sets for fast average-case O(1) lookups of dictionary words.

## Common Mistakes
*   **Inefficient Dictionary Lookups:** Using a `List` for `wordDict` and iterating through it for each substring check, leading to O(N*M) complexity for lookups where N is string length and M is dictionary size.
*   **Not Handling Base Cases Correctly:** Forgetting the `start == s.length()` base case in recursion, or not initializing memoization array properly.
*   **Off-by-One Errors in Substring Indices:** Incorrectly defining the start or end indices for `s.substring()`.
*   **Forgetting to Memoize:** Implementing recursion without memoization, leading to exponential time complexity for overlapping subproblems.

## Complexity Analysis
*   Time: O(n^3) - The outer loop iterates `n` times (for `start`), the inner loop iterates up to `n` times (for `i`), and `s.substring(start, i)` takes O(n) time in Java (though it can be O(1) in some languages or with specific implementations). The `hs.contains()` is O(1) on average. If `substring` is O(1), then it's O(n^2).
*   Space: O(n) - For the memoization array `memo` and the `HashSet` which stores dictionary words (up to `n` distinct words, each of length at most `n`).

## Commented Code
```java
class Solution {
    // Boolean array for memoization. memo[i] stores whether the substring s[i:] can be segmented.
    // Initialized to null, indicating the subproblem hasn't been solved yet.
    Boolean[] memo;
    // HashSet to store dictionary words for efficient O(1) average time lookups.
    HashSet<String> hs;

    // Main function to check if string s can be segmented using words from wordDict.
    public boolean wordBreak(String s, List<String> wordDict) {
        // Populate the HashSet with words from the dictionary.
        hs = new HashSet<>(wordDict);
        // Initialize the memoization array with size s.length() + 1.
        // The +1 is to handle the base case where start == s.length().
        memo = new Boolean[s.length() + 1];
        // Start the recursive helper function from the beginning of the string (index 0).
        return func(s, 0);
    }

    // Recursive helper function with memoization.
    // s: the input string.
    // start: the starting index of the current substring to consider.
    public boolean func(String s, int start) {
        // Base case: If we have reached the end of the string, it means we have successfully segmented it.
        if (start == s.length()) return true;
        // Memoization check: If the result for this 'start' index is already computed, return it.
        if (memo[start] != null) return memo[start];

        // Iterate through all possible end points 'i' for a substring starting at 'start'.
        // 'i' goes from 'start + 1' up to 's.length()'.
        for (int i = start + 1; i <= s.length(); i++) {
            // Extract the substring from 'start' to 'i' (exclusive of 'i').
            String sub = s.substring(start, i);
            // Check if the extracted substring is present in the dictionary (HashSet).
            // AND recursively call func for the remaining part of the string starting from index 'i'.
            if (hs.contains(sub) && func(s, i)) {
                // If both conditions are true, it means s[start:] can be segmented.
                // Store 'true' in memo[start] and return 'true'.
                return memo[start] = true;
            }
        }
        // If the loop completes without finding a valid segmentation for s[start:],
        // it means s[start:] cannot be segmented.
        // Store 'false' in memo[start] and return 'false'.
        return memo[start] = false;
    }
}
```

## Interview Tips
1.  **Explain the Recursive Structure First:** Start by explaining the recursive breakdown of the problem: "Can `s` be broken if `s[0...i]` is a word and `s[i+1...n]` can also be broken?"
2.  **Address Overlapping Subproblems:** Immediately follow up by discussing how the same subproblems (e.g., checking if `s[k:]` can be broken) will be encountered multiple times, motivating the need for memoization or dynamic programming.
3.  **Discuss Time Complexity Trade-offs:** Be prepared to explain the O(n^3) vs. O(n^2) time complexity based on the `substring` operation's cost in the specific language.
4.  **Consider Edge Cases:** Think about empty strings, empty dictionaries, and dictionaries with very long words.

## Revision Checklist
- [ ] Understand the problem statement clearly.
- [ ] Identify the recursive nature of the problem.
- [ ] Recognize overlapping subproblems.
- [ ] Implement memoization (top-down DP) or iterative DP (bottom-up).
- [ ] Use a HashSet for efficient dictionary lookups.
- [ ] Handle base cases correctly in recursion/iteration.
- [ ] Analyze time and space complexity.
- [ ] Test with various edge cases.

## Similar Problems
*   Word Break II
*   Partition Equal Subset Sum
*   Decode Ways
*   Combination Sum IV

## Tags
`Array` `Hash Map` `Dynamic Programming` `Recursion` `String`
