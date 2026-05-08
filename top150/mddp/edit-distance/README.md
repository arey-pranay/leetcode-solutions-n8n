# Edit Distance

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Dynamic Programming`  
**Time:** O(m x n)  
**Space:** O(m x n)

---

## Solution (java)

```java
class Solution {
    int[][] memo;
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        memo = new int[m][n];
        for(int[] temp : memo)Arrays.fill(temp,-1);
        // return solve(word1,word2,m-1,n-1);
        
        int[][] matrix = new int[m+1][n+1];
        for(int i =0;i<m+1;i++){
            for(int j =0 ; j<n+1;j++){
                if(i==0 || j==0) matrix[i][j]=0;
                else if (word1.charAt(i-1)==word2.charAt(j-1)) matrix[i][j] = matrix[i-1][j-1];
                else{
                    int x = Math.min(matrix[i-1][j]+1,matrix[i][j-1]+1);
                    int y = Math.min(x,matrix[i-1][j-1]+1);
                    matrix[i][j] = y;
                }
            }
        }
        if(matrix[m][n]==0) return 0;
        return matrix[m][n]+1;
    }
    public int solve(String s1, String s2, int i, int j){
        if(i<0) return j+1; 
        if(j<0) return i+1; //i =0 mtlb 1 character. i=4 mtlb 5 character etc. isliye i+1 add kara
        if(memo[i][j]!=-1) return memo[i][j];
        if(s1.charAt(i) == s2.charAt(j)) return solve(s1,s2,i-1,j-1);
        // s1 me se:
        int delete = 1 + solve(s1,s2,i-1,j);// -> i wala character solved, kyuki delete krdiya usko
        int insert = 1 + solve(s1,s2,i,j-1); // j wala character solved, kyuki usi ko s1 me insert krdiya
        int replace= 1 + solve(s1,s2,i-1,j-1); // -> i and j dono solved
        return memo[i][j]= Math.min(replace,Math.min(delete,insert));

    }
}
//     h o r s e 
//     0 0 0 0 0
// r 0 1 1 0 1 1
// o 0 
// s 0


// h o r s e i=4
// - - r o s j=2

```

---

---

## Quick Revision

* Problem: Given two strings `word1` and `word2`, find the minimum number of operations (insertions, deletions, substitutions) to change `word1` into `word2`.
* Solution: Dynamic programming with memoization.

## Intuition

The key insight is that the edit distance between two strings can be broken down into three types of operations: insertion, deletion, and substitution. By analyzing the last operation performed on each string, we can reduce the problem size and use dynamic programming to fill in a 2D table with the minimum edit distances.

## Algorithm

1. Initialize a memoization table `memo` of size `m x n`, where `m` is the length of `word1` and `n` is the length of `word2`.
2. Fill in the first row and column of the memoization table, where `matrix[i][j] = 0` if either `i` or `j` is zero.
3. Iterate over the rest of the memoization table, and for each cell `(i, j)`, calculate the minimum edit distance by considering three operations:
	* Insertion: `matrix[i-1][j]+1`
	* Deletion: `matrix[i][j-1]+1`
	* Substitution: `matrix[i-1][j-1]+1` if the current characters in both strings are the same
4. Return the value of `matrix[m][n]`, which is the minimum edit distance between `word1` and `word2`.

## Concept to Remember

* **Dynamic Programming**: break down a problem into smaller subproblems, solve each subproblem only once, and store the results in a table for future reference.
* **Memoization**: use a table to store the results of expensive function calls so that they can be reused instead of recalculated.

## Common Mistakes

* Failing to initialize the memoization table correctly
* Not considering all three operations (insertion, deletion, substitution) when calculating the minimum edit distance
* Not handling edge cases (e.g., when one string is empty)

## Complexity Analysis

- Time: O(m x n), where `m` and `n` are the lengths of the input strings.
- Space: O(m x n), for the memoization table.

## Commented Code
```java
public class Solution {
    int[][] memo;

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // Initialize memoization table
        memo = new int[m + 1][n + 1];
        for (int[] temp : memo) Arrays.fill(temp, -1);

        // Fill in first row and column of memoization table
        int[][] matrix = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0 || j == 0) matrix[i][j] = 0;
                else if (word1.charAt(i - 1) == word2.charAt(j - 1)) matrix[i][j] = matrix[i - 1][j - 1];
                else {
                    int x = Math.min(matrix[i - 1][j] + 1, matrix[i][j - 1] + 1);
                    int y = Math.min(x, matrix[i - 1][j - 1] + 1);
                    matrix[i][j] = y;
                }
            }
        }

        // Return minimum edit distance
        return matrix[m][n];
    }
}
```

## Interview Tips

* Be prepared to explain the dynamic programming approach and how it reduces the problem size.
* Make sure to handle edge cases correctly, such as when one string is empty.
* Practice solving similar problems on LeetCode or other platforms to improve your skills.

## Revision Checklist
- [ ] Understand the dynamic programming approach for solving edit distance problems.
- [ ] Initialize memoization table correctly.
- [ ] Handle edge cases (e.g., when one string is empty).
- [ ] Practice solving similar problems on LeetCode or other platforms.

## Similar Problems

* LeetCode: `Edit Distance II`, `Word Break`
* Other resources: `Minimum Edit Distance` on GeeksforGeeks, `Dynamic Programming for Beginners` on Udemy
