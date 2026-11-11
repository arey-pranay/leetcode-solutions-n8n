# Palindrome Partitioning Ii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `String` `Dynamic Programming`  
**Time:** O(N^2)  
**Space:** O(N^2)

---

## Solution (java)

```java
class Solution {
    int[][] memo;
    boolean[][] palindromes;
    public int minCut(String s) {
        int n = s.length();
        memo = new int[n][n];
        palindromes = new boolean[n][n];
        for(int len=1;len<=n;len++)
            for(int i=0;i+len<=n;i++){
                int j = i+len-1;
                if(s.charAt(i)==s.charAt(j) && (len <=2 || palindromes[i+1][j-1] == true)) palindromes[i][j] = true;
            }
        
        for(int[] temp : memo) Arrays.fill(temp,-1);
        return func(s,0,n-1);
    }
    private int func(String s, int i, int j){
        if(palindromes[i][j]) return 0;
        if(memo[i][j] != -1) return memo[i][j];
        int min = Integer.MAX_VALUE;
        for(int k=i;k<=j;k++) if(palindromes[i][k]==true) min = Math.min(min, 1+func(s,k+1,j));
        return memo[i][j] = min;
    }
}
```

---

---
## Quick Revision
Given a string, find the minimum cuts needed to partition it such that every substring of the partition is a palindrome.
Solve this using dynamic programming with a precomputed palindrome check table to optimize the recursive choices.

## Intuition
The problem asks for the minimum cuts to make every substring a palindrome. If we blindly try every partition point, we face exponential combinations. The "aha moment" is realizing this has optimal substructure: if the prefix `s[i...k]` is a palindrome, the minimum cuts for `s[i...j]` is simply `1 + minCuts(s[k+1...j])`. To avoid redundant palindrome checks during our recursive cuts, we can precompute the palindrome status of all substrings `[i...j]` in $O(N^2)$ time. Combining precomputed palindrome flags with memoized recursion yields an efficient solution.

## Algorithm
1. Initialize a 2D boolean array `palindromes` of size $N \times N$ to store whether substring `s[i...j]` is a palindrome, and a 2D integer array `memo` for memoization.
2. Precompute all palindrome substrings using length-based iteration: a substring `s[i...j]` is a palindrome if its endpoints match and its inner substring `s[i+1...j-1]` is a palindrome (or if its length is $\le 2$).
3. Fill the `memo` table with `-1` to indicate uncalculated states.
4. Define a recursive helper function `func(s, i, j)` that returns the minimum cuts for the substring from index `i` to `j`.
5. Base case: If the substring `s[i...j]` is already a palindrome (checked via `palindromes[i][j]`), return `0` cuts.
6. Check if the subproblem result is already memoized in `memo[i][j]`; if so, return it.
7. Iterate through possible partition points `k` from `i` to `j`. If the prefix `s[i...k]` is a palindrome, recursively find the min cuts for the remaining suffix `s[k+1...j]` and take `1 + func(s, k+1, j)`.
8. Store the minimum cuts found across all valid `k` values into `memo[i][j]` and return it.

## Concept to Remember
- Dynamic Programming with State Precomputation: Solving dependent subproblems faster by decoupling heavy validation checks (palindrome detection) from the main recurrence relation.
- Interval DP: Handling problems defined over ranges `[i, j]` by building solutions from smaller intervals to larger intervals.
- Memoization vs Tabulation: Caching recursive results to avoid overlapping subproblems.

## Common Mistakes
- Forgetting to precompute palindromes, which leads to redundant character comparisons and a TLE (Time Limit Exceeded).
- Incorrectly handling the base cases, such as failing to recognize that a single character or a 0-length suffix requires zero cuts.
- Mismanaging index boundaries when slicing the string or iterating through partition points `k`.
- Not resetting or incorrectly initializing the memoization table before starting the recursive search.

## Complexity Analysis
- Time: $O(N^2)$ - Precomputing the palindrome table takes $O(N^2)$ time, and the recursive state space with memoization visits at most $O(N^2)$ states, doing up to $O(N)$ work per state in the worst case (though optimized to $O(1)$ transitions with a 1D DP formulation, this 2D approach fits within acceptable limits with memoization).
- Space: $O(N^2)$ - Required to store the `palindromes` boolean table and the `memo` integer table of size $N \times N$, plus the recursion stack depth.

## Commented Code
```java
class Solution {
    // Memoization table to cache minimum cuts for substring s[i...j]
    int[][] memo;
    // Precomputed boolean table to check if substring s[i...j] is a palindrome in O(1)
    boolean[][] palindromes;
    
    public int minCut(String s) {
        int n = s.length();
        memo = new int[n][n];
        palindromes = new boolean[n][n];
        
        // Precompute palindromes for all possible lengths from 1 to n
        for(int len = 1; len <= n; len++)
            for(int i = 0; i + len <= n; i++){
                int j = i + len - 1;
                // A substring is a palindrome if outer chars match and inner substring is a palindrome
                if(s.charAt(i) == s.charAt(j) && (len <= 2 || palindromes[i+1][j-1] == true)) 
                    palindromes[i][j] = true;
            }
        
        // Initialize the memoization table with -1 to denote unprocessed states
        for(int[] temp : memo) Arrays.fill(temp, -1);
        
        // Start the recursive partition process for the entire string from index 0 to n-1
        return func(s, 0, n - 1);
    }
    
    private int func(String s, int i, int j){
        // If the current substring is already a palindrome, 0 cuts are needed
        if(palindromes[i][j]) return 0;
        
        // If this subproblem has already been solved, return the cached result
        if(memo[i][j] != -1) return memo[i][j];
        
        int min = Integer.MAX_VALUE;
        
        // Try every possible split point k from i to j
        for(int k = i; k <= j; k++) {
            // Only recurse if the prefix s[i...k] is a valid palindrome
            if(palindromes[i][k] == true) {
                min = Math.min(min, 1 + func(s, k + 1, j));
            }
        }
        
        // Store the computed minimum cuts in the memo table and return it
        return memo[i][j] = min;
    }
}
```

## Interview Tips
- Start by explaining the brute force approach (trying all cuts) and why it's inefficient ($O(2^N)$).
- Clearly separate the palindrome validation logic from the cut-minimization logic to show clean separation of concerns.
- Discuss how precomputing the palindrome matrix optimizes the inner loop from $O(N)$ checks down to $O(1)$.
- Mention that this problem can also be solved using a 1D DP array where `dp[i]` represents the min cuts for prefix `s[0...i]`, which reduces space complexity to $O(N)$.

## Revision Checklist
- [ ] Understand why precomputing palindromes is crucial for performance.
- [ ] Write the interval DP condition `s.charAt(i) == s.charAt(j) && palindromes[i+1][j-1]` from memory.
- [ ] Handle boundary conditions for lengths 1 and 2 correctly.
- [ ] Implement memoization to avoid exponential time complexity.
- [ ] Trace space and time complexities clearly.

## Similar Problems
- Palindrome Partitioning
- Longest Palindromic Substring
- Word Break
- Burst Balloons

## Tags
`Dynamic Programming` `String` `Memoization`
