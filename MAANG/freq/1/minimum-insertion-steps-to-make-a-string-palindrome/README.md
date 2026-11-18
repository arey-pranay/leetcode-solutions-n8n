# Minimum Insertion Steps To Make A String Palindrome

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `String` `Dynamic Programming`  
**Time:** O(n^2)  
**Space:** O(n^2)

---

## Solution (java)

```java
class Solution {
    int[][] memo;
    public int minInsertions(String s) {
        int n = s.length(); memo = new int[n][n];
        for(int[] temp : memo) Arrays.fill(temp,-1);
        return n-lps(s,0,n-1);
    }
    public int lps(String s, int i, int j){
        if(memo[i][j]!=-1)return memo[i][j];
        if(i>j) return 0; if(i==j) return 1;
        if(s.charAt(i)==s.charAt(j)) return memo[i][j] = 2 + lps(s,i+1,j-1); //take
        return memo[i][j]=Math.max(lps(s,i,j-1),lps(s,i+1,j)); //notTake
    }
}

```

---

---

## Quick Revision
The problem requires finding the minimum number of insertions needed to make a string a palindrome.
We solve this by using dynamic programming and memoization to calculate the longest palindromic subsequence.

## Intuition
The key insight is that a palindrome can be broken down into smaller palindromes. We can use this insight to build a table where each cell represents the length of the longest palindromic subsequence ending at that position.

## Algorithm
1. Create a memoization table `memo` to store the results of subproblems.
2. Initialize the table with -1, indicating that the subproblem has not been solved yet.
3. Define a helper function `lps` to calculate the longest palindromic subsequence.
4. In the `lps` function, if the subproblem has been solved before, return the stored result.
5. If the subproblem is trivial (i.e., `i > j`), return 0.
6. If the characters at positions `i` and `j` are the same, return 2 plus the length of the longest palindromic subsequence of the substring between them.
7. Otherwise, return the maximum of the lengths of the longest palindromic subsequences of the substrings `s[i..j-1]` and `s[i+1..j]`.

## Concept to Remember
* Dynamic programming
* Memoization
* Longest palindromic subsequence

## Common Mistakes
* Forgetting to initialize the memoization table
* Not handling the base cases correctly
* Not using memoization to avoid redundant calculations

## Complexity Analysis
- Time: O(n^2) / Reason: We have two nested loops that iterate over the string.
- Space: O(n^2) / Reason: We need to store the memoization table.

## Commented Code
```java
class Solution {
    int[][] memo;
    public int minInsertions(String s) {
        int n = s.length(); memo = new int[n][n];
        for(int[] temp : memo) Arrays.fill(temp,-1);
        return n-lps(s,0,n-1);
    }
    public int lps(String s, int i, int j){
        // If the subproblem has been solved before, return the stored result
        if(memo[i][j]!=-1) return memo[i][j];
        
        // If the subproblem is trivial (i.e., i > j), return 0
        if(i>j) return 0;
        
        // If the characters at positions i and j are the same, return 2 plus the length of the longest palindromic subsequence of the substring between them
        if(s.charAt(i)==s.charAt(j)) return memo[i][j] = 2 + lps(s,i+1,j-1); //take
        
        // Otherwise, return the maximum of the lengths of the longest palindromic subsequences of the substrings s[i..j-1] and s[i+1..j]
        return memo[i][j]=Math.max(lps(s,i,j-1),lps(s,i+1,j)); //notTake
    }
}
```

## Interview Tips
* Make sure to initialize the memoization table correctly.
* Pay attention to the base cases and the recursive calls.
* Use memoization to avoid redundant calculations.
* Practice solving similar problems to improve your dynamic programming skills.

## Revision Checklist
- [ ] Understand the problem and the solution
- [ ] Review the dynamic programming and memoization concepts
- [ ] Practice solving similar problems
- [ ] Review the code and the algorithm

## Similar Problems
* Longest Palindromic Substring (LeetCode 5)
* Longest Palindromic Subsequence (LeetCode 516)
* Palindrome Partitioning (LeetCode 132)

## Tags
`Array` `Hash Map` `Dynamic Programming` `Memoization`
