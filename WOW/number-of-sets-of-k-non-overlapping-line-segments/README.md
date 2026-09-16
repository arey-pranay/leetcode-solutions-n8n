# Number Of Sets Of K Non Overlapping Line Segments

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Math` `Dynamic Programming` `Combinatorics` `Prefix Sum`  
**Time:** O(n+k)  
**Space:** O(n+k)

---

## Solution (java)

```java
class Solution {
    int MOD = 1_000_000_007;
    int[][] memo;
    public int numberOfSets(int n, int k) {
        memo = new int[n+k][2*k+1];
        for(int[] temp : memo)Arrays.fill(temp,-1);
        return func(0,2*k,n+k-1)%MOD;
    }
    public int func(int index, int rem, int n){
        if(rem == 0) return 1;
        if(index >= n) return 0;
        if(memo[index][rem] != -1) return memo[index][rem];
        int take = func(index+1, rem-1, n)%MOD; //n+k-1
        int skip = func(index+1, rem, n)%MOD;
        return memo[index][rem] = (take + skip)%MOD;
    }
}
// a 1 1 2
// b 1 2 1

// a+b < n

// {01 23}
// {12 23}
// {01 12}

// 01 => 13

_ _ _ _ _
12 23 34 45
4=> 5

1 
2
3
4
5
_ _ _

01 12 23
01 12 34
01 12 24
01 13 34
---


```

---

---
## Quick Revision
Given a range [0, n] and a parameter k, find the number of ways to select k non-overlapping line segments from the range. This can be solved using dynamic programming with memoization.

## Intuition
The problem can be visualized as arranging line segments in a range [0, n] without overlap. The key insight is to use dynamic programming to break down the problem into smaller sub-problems and store the results in a memo table.

## Algorithm
1. Initialize a memo table with dimensions (n+k) x (2k+1) and fill all entries with -1.
2. Define a helper function `func` that takes three parameters: `index`, `rem`, and `n`.
3. If `rem` is 0, return 1 (base case).
4. If `index` is greater than or equal to `n`, return 0 (out of range).
5. If the memo table entry at `memo[index][rem]` is not -1, return its value (memoization).
6. Recursively call `func` with `index+1` and `rem-1` (take a line segment), and `index+1` and `rem` (skip a line segment).
7. Store the result in the memo table and return it.

## Concept to Remember
• **Memoization**: storing the results of expensive function calls to avoid redundant calculations.
• **Dynamic Programming**: breaking down a problem into smaller sub-problems and solving each one only once.
• **Backtracking**: exploring all possible solutions by recursively trying different choices.

## Common Mistakes
• Failing to initialize the memo table correctly.
• Not properly handling the base case when `rem` is 0.
• Not using memoization effectively to avoid redundant calculations.

## Complexity Analysis
- Time: O(n+k) - We iterate over the range [0, n] and use memoization to avoid redundant calculations.
- Space: O(n+k) - We need to store the memo table with dimensions (n+k) x (2k+1).

## Commented Code
```java
class Solution {
    int MOD = 1_000_000_007;
    int[][] memo;
    public int numberOfSets(int n, int k) {
        // Initialize memo table
        memo = new int[n+k][2*k+1];
        for(int[] temp : memo) Arrays.fill(temp,-1);
        
        // Call helper function
        return func(0, 2*k, n+k-1) % MOD;
    }
    
    public int func(int index, int rem, int n){
        // Base case
        if(rem == 0) return 1;
        
        // Out of range
        if(index >= n) return 0;
        
        // Memoization
        if(memo[index][rem] != -1) return memo[index][rem];
        
        // Recursively try taking or skipping a line segment
        int take = func(index+1, rem-1, n) % MOD; //n+k-1
        int skip = func(index+1, rem, n) % MOD;
        
        // Store result in memo table
        return memo[index][rem] = (take + skip) % MOD;
    }
}
```

## Interview Tips
• Make sure to initialize the memo table correctly and handle the base case properly.
• Use memoization effectively to avoid redundant calculations.
• Practice solving similar problems to improve your dynamic programming skills.

## Revision Checklist
- [ ] Review memoization technique
- [ ] Practice dynamic programming problems
- [ ] Implement the solution from scratch without any libraries

## Similar Problems
- [LeetCode 377. Combination Sum IV](https://leetcode.com/problems/combination-sum-iv/)
- [LeetCode 698. Partition to K Equal Sum Subsets](https://leetcode.com/problems/partition-to-k-equal-sum-subsets/)
- [LeetCode 1180. Count Subgraphs Enumerated By Edge List](https://leetcode.com/problems/count-subgraphs-enumerated-by-edge-list/)

## Tags
`Array` `Hash Map` `Dynamic Programming` `Memoization`
