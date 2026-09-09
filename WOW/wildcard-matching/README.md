# Wildcard Matching

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `String` `Dynamic Programming` `Greedy` `Recursion`  
**Time:** O(m*n)  
**Space:** O(m*n)

---

## Solution (java)

```java
// class Solution {
//     public boolean isMatch(String s, String p) {
//         int i = 0;
//         int j = 0;
//         int star = -1;
//         int match = 0;

//         while (i < s.length()) {
//             if (j < p.length() &&(p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {i++;j++;} //match curr chars
//             else if (j < p.length() && p.charAt(j) == '*') { match = i; star = j++; }  //
//             else if (star != -1) { i = match++; j = star + 1; } 
//             else return false;
//         }
//         while (j < p.length() && p.charAt(j) == '*') j++;
//         return j == p.length();
//     }
// }




class Solution {
    String s,p;
    int m,n;
    Boolean[][] memo;
    boolean[] stars;
    public boolean isMatch(String S, String P) {
       s=S;p=P;m=S.length();n=P.length();
       memo = new Boolean[m][n];
       stars = new boolean[n+1];
       stars[n] = true;
       for(int i=n-1;i>=0;i--) stars[i] = stars[i+1] && p.charAt(i)=='*';
       return func(0,0);
    }
    private boolean func(int i, int j){
        if(i==m) return stars[j];
        if(j==n) return false;
        if(memo[i][j]!=null)return memo[i][j];
        if(p.charAt(j)=='*') return memo[i][j] = func(i+1,j) || func(i,j+1);
        if(p.charAt(j)=='?' || p.charAt(j)==s.charAt(i)) return memo[i][j] = func(i+1,j+1);
        return memo[i][j] = false;
    }
}
```

---

---

## Quick Revision
Wildcard Matching is a problem where we need to determine if a given string `s` matches a pattern `p` that may contain wildcard characters `?` and `*`. We solve this problem by using dynamic programming and memoization.

## Intuition
The key insight here is that we can break down the problem into smaller sub-problems by considering the last character of the pattern `p`. If the last character is `*`, we can either ignore it or match it with the current character in `s`. If the last character is `?` or `s[i]`, we can simply match it with the current character in `s`. This intuition allows us to use a recursive approach with memoization to avoid redundant calculations.

## Algorithm
1. Initialize variables: `m` and `n` to store the lengths of `s` and `p`, `memo` to store the results of sub-problems, and `stars` to store whether the current character in `p` is a `*`.
2. Fill the `stars` array: iterate from the end of `p` to the start and set `stars[i]` to `stars[i+1] && p.charAt(i)=='*'`.
3. Call the `func` method: pass `0,0` as the initial indices for `i` and `j`.
4. The `func` method:
	* If `i` reaches the end of `s`, return `stars[j]`.
	* If `j` reaches the end of `p`, return `false`.
	* If `memo[i][j]` is not null, return its value.
	* If `p.charAt(j) == '*'`, return `memo[i][j] = func(i+1,j) || func(i,j+1)`.
	* If `p.charAt(j) == '?' || p.charAt(j) == s.charAt(i)`, return `memo[i][j] = func(i+1,j+1)`.
	* Otherwise, return `memo[i][j] = false`.

## Concept to Remember
* Dynamic programming: breaking down the problem into smaller sub-problems and storing their results to avoid redundant calculations.
* Memoization: storing the results of sub-problems to avoid redundant calculations.
* Greedy approach: using the last character of the pattern `p` to make decisions about matching.

## Common Mistakes
* Failing to initialize the `memo` array and the `stars` array.
* Not correctly implementing the `func` method to handle the base cases and the recursive cases.
* Not using memoization to avoid redundant calculations.

## Complexity Analysis
- Time: O(m*n) - the size of the `memo` array is m*n, and each cell is calculated at most once.
- Space: O(m*n) - the size of the `memo` array.

## Commented Code
```java
class Solution {
    String s,p;
    int m,n;
    Boolean[][] memo;
    boolean[] stars;

    public boolean isMatch(String S, String P) {
        // Initialize variables
        s=S;p=P;m=S.length();n=P.length();
        // Initialize memo and stars arrays
        memo = new Boolean[m][n];
        stars = new boolean[n+1];
        // Fill stars array
        stars[n] = true;
        for(int i=n-1;i>=0;i--) stars[i] = stars[i+1] && p.charAt(i)=='*';
        // Call func method
        return func(0,0);
    }

    private boolean func(int i, int j){
        // Base case: i reaches the end of s
        if(i==m) return stars[j];
        // Base case: j reaches the end of p
        if(j==n) return false;
        // Check if memo[i][j] is not null
        if(memo[i][j]!=null)return memo[i][j];
        // Handle '*' case
        if(p.charAt(j)=='*') return memo[i][j] = func(i+1,j) || func(i,j+1);
        // Handle '?' or match case
        if(p.charAt(j)=='?' || p.charAt(j)==s.charAt(i)) return memo[i][j] = func(i+1,j+1);
        // Handle mismatch case
        return memo[i][j] = false;
    }
}
```

## Interview Tips
* Make sure to initialize all necessary arrays and variables.
* Use memoization to avoid redundant calculations.
* Break down the problem into smaller sub-problems and store their results.
* Use a greedy approach to make decisions about matching.
* Pay attention to the base cases and the recursive cases.

## Revision Checklist
- [ ] Initialize variables and arrays correctly.
- [ ] Use memoization to avoid redundant calculations.
- [ ] Break down the problem into smaller sub-problems and store their results.
- [ ] Use a greedy approach to make decisions about matching.
- [ ] Pay attention to the base cases and the recursive cases.

## Similar Problems
* Regular Expression Matching (LeetCode 10)
* Longest Common Subsequence (LeetCode 1143)

## Tags
`Dynamic Programming` `Memoization` `Greedy Algorithm` `String Matching`
