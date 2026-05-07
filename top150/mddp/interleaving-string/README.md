# Interleaving String

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Dynamic Programming`  
**Time:** O(s1.length x s2.length)  
**Space:** O(s1.length x s2.length)

---

## Solution (java)

```java
class Solution {
    String a;
    String b;
    String c;
    Boolean[][] memo;
    public boolean isInterleave(String s1, String s2, String s3) {
        a=s1;
        b=s2;
        c=s3;
        memo = new Boolean[s1.length()+1][s2.length()+1];
        if(s1.length() + s2.length() != s3.length()) return false;
        return func(0,0,0);
    }
    public boolean func(int i, int j,int k){
        if(i==a.length() && j==b.length()) return true;
        if(memo[i][j]!=null) return memo[i][j];
        boolean ans = false;
        if(i<a.length() && a.charAt(i) == c.charAt(k)) ans = func(i+1,j,k+1);
        if(j<b.length() && b.charAt(j) == c.charAt(k)) ans |= func(i,j+1,k+1);
        return memo[i][j] = ans;
        
    }
}

```

---

---
## Quick Revision
Given two strings `s1` and `s2`, determine if they can be interleaved to form another string `s3`.
We solve this problem by using dynamic programming with memoization.

## Intuition
The key insight is that an interleaving of `s1` and `s2` into `s3` can be represented as a 2D grid, where each cell at position `(i, j)` represents whether the first `i` characters of `s1` and the first `j` characters of `s2` can be interleaved to form the first `k` characters of `s3`. We use memoization to store these values and avoid redundant computation.

## Algorithm
1. Initialize a 2D array `memo` with dimensions `(s1.length + 1) x (s2.length + 1)` to store the interleave results.
2. Check if the total length of `s1` and `s2` is equal to the length of `s3`. If not, return false.
3. Call the helper function `func(0, 0, 0)` to start the interleave process.

## Concept to Remember
* Dynamic programming: break down a problem into smaller sub-problems and store their results to avoid redundant computation.
* Memoization: use a cache to store the results of expensive function calls so that they can be reused instead of recalculated.

## Common Mistakes
* Failing to initialize the `memo` array properly, leading to incorrect interleave results.
* Not checking if the total length of `s1` and `s2` is equal to the length of `s3`, which would lead to an incorrect result.
* Not using memoization effectively, resulting in redundant computation.

## Complexity Analysis
- Time: O(s1.length x s2.length) - We need to fill up the 2D `memo` array with dimensions `(s1.length + 1) x (s2.length + 1)`
- Space: O(s1.length x s2.length) - The size of the `memo` array

## Commented Code
```java
class Solution {
    String a; // input string 1
    String b; // input string 2
    String c; // target string
    Boolean[][] memo; // memoization table

    public boolean isInterleave(String s1, String s2, String s3) {
        a = s1;
        b = s2;
        c = s3;

        // Check if total length of s1 and s2 equals the length of s3
        if (s1.length() + s2.length() != s3.length()) return false;

        // Initialize memoization table
        memo = new Boolean[s1.length() + 1][s2.length() + 1];

        // Start interleave process from index (0, 0)
        return func(0, 0, 0);
    }

    public boolean func(int i, int j, int k) {
        // Base case: if we have reached the end of both strings
        if (i == a.length() && j == b.length()) return true;

        // Check memoization table for result
        if (memo[i][j] != null) return memo[i][j];

        boolean ans = false; // Initialize answer as false

        // Try to match character at index i in s1 with character at index k in s3
        if (i < a.length() && a.charAt(i) == c.charAt(k)) {
            ans |= func(i + 1, j, k + 1); // Recursively try to match next characters
        }

        // Try to match character at index j in s2 with character at index k in s3
        if (j < b.length() && b.charAt(j) == c.charAt(k)) {
            ans |= func(i, j + 1, k + 1); // Recursively try to match next characters
        }

        // Store result in memoization table and return it
        return memo[i][j] = ans;
    }
}
```

## Interview Tips
* Make sure to initialize the `memo` array properly.
* Use memoization effectively to avoid redundant computation.
* Check if the total length of `s1` and `s2` is equal to the length of `s3`.
* Practice explaining your thought process and solution to a problem.

## Revision Checklist
- [ ] Initialize `memo` array correctly
- [ ] Use memoization to store results
- [ ] Check total length of `s1` and `s2`
- [ ] Review dynamic programming concepts

## Similar Problems
* LeetCode 1105: **Interleaving String II** (more complex version with multiple strings)
* LeetCode 1279: **Maximum Number of Tasty Buns** (related problem using dynamic programming)
