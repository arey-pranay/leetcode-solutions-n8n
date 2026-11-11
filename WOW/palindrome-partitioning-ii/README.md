# Palindrome Partitioning Ii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `String` `Dynamic Programming`  
**Time:** See complexity section  
**Space:** See complexity section

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
Given a string, find the minimum cuts needed to partition it such that every substring is a palindrome.
Solve it using dynamic programming with precomputed palindrome checks to optimize substring partitioning.

## Intuition
The "aha moment" is realizing that we can break the problem into two distinct phases: first, precomputing all palindrome substrings using an interval DP table, and second, finding the minimum cuts using a recursive function with memoization. Instead of checking if each substring is a palindrome on the fly (which takes $O(N)$ time per check), we precompute the palindrome status of every possible substring in $O(N^2)$ time. Then, if the current substring from `i` to `j` is already a palindrome, 0 cuts are needed. Otherwise, we try every possible split point `k` where the prefix `i` to `k` is a palindrome, and recursively compute the minimum cuts for the remaining suffix `k+1` to `j`, adding 1 for the cut.

## Algorithm
1. Initialize a 2D boolean array `palindromes` of size $N \times N$ and a 2D integer array `memo` initialized to -1.
2. Precompute palindrome substrings by iterating over length from 1 to $N$:
   - For each start index `i`, compute the end index `j = i + len - 1`.
   - Mark `palindromes[i][j]` as true if characters at `i` and `j` match, and either the length is $\le 2$ or the inner substring `palindromes[i+1][j-1]` is true.
3. Fill the `memo` table with -1 to prepare for memoization.
4. Invoke the recursive helper function `func(s, 0, n - 1)` to find the minimum cuts for the entire string.
5. In the helper function `func(s, i, j)`:
   - Base case 1: If the substring from `i` to `j` is already a palindrome, return 0 cuts.
   - Base case 2: If the result is already memoized in `memo[i][j]`, return it.
   - Iterate a pointer `k` from `i` to `j`: if the prefix from `i` to `k` is a palindrome, calculate `1 + func(s, k + 1, j)` and update the minimum cuts.
   - Store the minimum result in `memo[i][j]` and return it.

## Concept to Remember
- Interval Dynamic Programming: Solving problems where states are defined by a range `[i, j]`.
- Substring Palindrome Precomputation: Using a boolean table to answer palindrome queries in $O(1)$ time.
- Memoization and Optimal Substructure: Breaking a complex optimization problem into overlapping subproblems.

## Common Mistakes
- Not precomputing palindromes, leading to an inefficient $O(N^3)$ time complexity due to repeated palindrome

## My Notes
nice common question. bute force dp optimized a lot somewhat
