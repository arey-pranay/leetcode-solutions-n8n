# Number Of Sets Of K Non Overlapping Line Segments

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Math` `Dynamic Programming` `Combinatorics` `Prefix Sum`  
**Time:** O(n \* k^2)  
**Space:** O(n \* k)

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
The problem asks to find the number of sets of k non-overlapping line segments that can be formed with n points. We solve this by using dynamic programming to calculate the number of sets for each possible number of segments.

## Intuition
The key insight is to realize that for each point, we have two choices: either include it in the current segment or skip it. If we include it, we have (k-1) remaining segments to fill, and if we skip it, we still have k segments to fill. This leads to a recursive relationship between the number of sets for each point.

## Algorithm
1. Initialize a memoization table to store the number of sets for each point and remaining segments.
2. Define a recursive function `func` that calculates the number of sets for a given point and remaining segments.
3. Base case: if there are no remaining segments, return 1 (one way to form a set with no segments).
4. If the point index is out of bounds, return 0 (no way to form a set with more segments than points).
5. If the result is already memoized, return it.
6. Otherwise, calculate the number of sets by recursively calling `func` with the next point and (k-1) remaining segments, and with the next point and k remaining segments.
7. Memoize the result and return it.

## Concept to Remember
* Memoization: storing intermediate results to avoid redundant calculations
* Dynamic programming: breaking down a problem into smaller sub-problems and solving each one only once
* Recursive relationships: using a recursive function to represent a relationship between sub-problems

## Common Mistakes
* Not initializing the memoization table correctly
* Not handling base cases properly
* Not memoizing results correctly
* Misunderstanding the recursive relationship between sub-problems

## Complexity Analysis
- Time: O(n \* k^2) - reason: each point and remaining segment pair is processed at most twice
- Space: O(n \* k) - reason: storing the memoization table

## Commented Code
```java
class Solution {
    int MOD = 1_000_000_007;
    int[][] memo;
    
    public int numberOfSets(int n, int k) {
        memo = new int[n+k][2*k+1];
        for(int[] temp : memo) Arrays.fill(temp,-1);
        return func(0,2*k,n+k-1)%MOD;
    }
    
    public int func(int index, int rem, int n){
        // base case: no remaining segments
        if(rem == 0) return 1;
        // base case: out of bounds
        if(index >= n) return 0;
        // memoize result if already calculated
        if(memo[index][rem] != -1) return memo[index][rem];
        // calculate number of sets by recursively calling func
        int take = func(index+1, rem-1, n)%MOD; //n+k-1
        int skip = func(index+1, rem, n)%MOD;
        // memoize result and return it
        return memo[index][rem] = (take + skip)%MOD;
    }
}
```

## Interview Tips
* Be careful with the base cases and memoization
* Understand the recursive relationship between sub-problems
* Use dynamic programming to optimize the solution
* Practice solving similar problems to improve your skills

## Revision Checklist
- [ ] Understand the problem statement and requirements
- [ ] Review the solution and make sure it's correct and efficient
- [ ] Practice solving similar problems to improve your skills
- [ ] Review the concept of memoization and dynamic programming

## Similar Problems
* 1205. [Trees by Level](https://leetcode.com/problems/trees-by-level/)
* 1025. [Divisor Game](https://leetcode.com/problems/divisor-game/)
* 1143. [Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/)

## Tags
`Array` `Hash Map` `Dynamic Programming` `Memoization`
