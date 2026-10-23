# Number Of Ways To Paint N 3 Grid

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Dynamic Programming` `Graph Coloring`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    int MOD = 1_000_000_007;
    public int numOfWays(int n) {
        long X = 6, Y = 6; // X -> ABC, Y -> ABA
        for(int i=2;i<=n;i++){
            long newX = (2*X + 2*Y)%MOD;
            long newY = (2*X + 3*Y)%MOD;
            X = newX;
            Y = newY;
        }
        return (int) ((X+Y)%MOD);
    }
}

// prev => ABC, curr => ABC wale 2, ABA wale 2
// prev => ABA, curr => ABC wale 2, ABA wale 3

// "r g y"
//  g y r
//  y r g
 
//  and
//  g y g
//  g r g

// "r g r" 
//  g r g
//  g y g 
//  y r y
 
//  and 
 
//  y r g
//  g r y 
 
 
```

---

---
## Quick Revision
The problem asks to calculate the number of ways to paint an `n x 3` grid with three colors (red, green, and yellow) such that no two adjacent cells have the same color.
We can solve this problem by using dynamic programming and modular arithmetic to calculate the number of ways to paint the grid.

## Intuition
The key insight is to recognize that the number of ways to paint the grid is determined by the number of ways to paint the previous row and the current row. We can use modular arithmetic to avoid overflow and calculate the number of ways to paint the grid modulo `10^9 + 7`.

## Algorithm
1. Initialize variables `X` and `Y` to represent the number of ways to paint the first row with `ABC` and `ABA` patterns respectively.
2. For each row from `2` to `n`, calculate the new values of `X` and `Y` using the formulas `newX = (2*X + 2*Y)%MOD` and `newY = (2*X + 3*Y)%MOD`.
3. Return the sum of `X` and `Y` modulo `MOD` as the final result.

## Concept to Remember
* **Modular Arithmetic**: We use modular arithmetic to avoid overflow and calculate the number of ways to paint the grid modulo `10^9 + 7`.
* **Dynamic Programming**: We use dynamic programming to calculate the number of ways to paint the grid by considering the number of ways to paint the previous row and the current row.
* **Pattern Recognition**: We recognize the patterns in the grid and use them to calculate the number of ways to paint the grid.

## Common Mistakes
* **Incorrect Modular Arithmetic**: Failing to use modular arithmetic to avoid overflow can lead to incorrect results.
* **Incorrect Dynamic Programming**: Failing to correctly update the dynamic programming variables can lead to incorrect results.
* **Not Considering Patterns**: Failing to recognize the patterns in the grid can lead to incorrect results.

## Complexity Analysis
- Time: O(n) - we iterate from 2 to n to calculate the new values of X and Y.
- Space: O(1) - we only use a constant amount of space to store the variables X and Y.

## Commented Code
```java
class Solution {
    int MOD = 1_000_000_007;
    public int numOfWays(int n) {
        // Initialize variables X and Y to represent the number of ways to paint the first row with ABC and ABA patterns respectively
        long X = 6, Y = 6;
        
        // For each row from 2 to n, calculate the new values of X and Y using the formulas
        for(int i=2;i<=n;i++){
            // Calculate the new value of X using the formula (2*X + 2*Y)%MOD
            long newX = (2*X + 2*Y)%MOD;
            // Calculate the new value of Y using the formula (2*X + 3*Y)%MOD
            long newY = (2*X + 3*Y)%MOD;
            // Update the values of X and Y
            X = newX;
            Y = newY;
        }
        // Return the sum of X and Y modulo MOD as the final result
        return (int) ((X+Y)%MOD);
    }
}
```

## Interview Tips
* **Use Modular Arithmetic**: Use modular arithmetic to avoid overflow and calculate the number of ways to paint the grid modulo `10^9 + 7`.
* **Recognize Patterns**: Recognize the patterns in the grid and use them to calculate the number of ways to paint the grid.
* **Use Dynamic Programming**: Use dynamic programming to calculate the number of ways to paint the grid by considering the number of ways to paint the previous row and the current row.

## Revision Checklist
- [ ] Understand the problem and the requirements
- [ ] Use modular arithmetic to avoid overflow
- [ ] Recognize the patterns in the grid
- [ ] Use dynamic programming to calculate the number of ways to paint the grid

## Similar Problems
* [LeetCode 1200. Minimum Absolute Sum](https://leetcode.com/problems/minimum-absolute-sum/)
* [LeetCode 1202. Smallest String With Swaps](https://leetcode.com/problems/smallest-string-with-swaps/)

## Tags
`Array` `Hash Map` `Dynamic Programming` `Modular Arithmetic`
