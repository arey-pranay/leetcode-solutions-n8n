# Minimum Xor Path In A Grid

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Bit Manipulation` `Matrix`  
**Time:** O(m \* n \* 2^m \* n)  
**Space:** O(m \* n \* 2^m \* n)

---

## Solution (java)

```java
class Solution {
    int[][] grid;
    int m,n;
    int min = Integer.MAX_VALUE;
    Boolean[][][] memo;
    public int minCost(int[][] g) {
        grid=g;
        m=g.length; n = g[0].length;
        memo = new Boolean[m][n][1024];
        func(0,0,0);
        return min;
    }
    public void func(int i, int j, int curr){
        if(i == m || j == n) return;
        
        if(memo[i][j][curr]!=null) return;
        memo[i][j][curr] = true;
        
        curr ^= grid[i][j];
        if(i==m-1 && j==n-1){min = Math.min(min,curr); return;}
        func(i+1,j,curr); func(i,j+1,curr);
    }
}
```

---

---

## Quick Revision
This problem involves finding the minimum XOR sum path from the top-left to the bottom-right corner of a grid, where each cell's value is the XOR of its top, bottom, left, and right neighbors.

To solve this problem, we use dynamic programming and memoization to optimize the search process.

## Intuition
The key insight here is that the XOR operation has the property that `a ^ b ^ b == a`. This means that if we've already visited a cell with a certain XOR value, we can skip visiting its neighbors with the same XOR value, as the path through those neighbors would be equivalent.

## Algorithm
1. Initialize the `memo` array to keep track of visited cells and their corresponding XOR values.
2. Define the `func` function to recursively explore the grid and calculate the minimum XOR sum path.
3. In the `func` function, check if the current cell has been visited before. If so, return the stored result.
4. Calculate the XOR of the current cell's value with its neighbors' values.
5. If the current cell is the bottom-right corner, update the minimum XOR sum if necessary.
6. Recursively explore the next cells by calling `func` with the updated XOR value.

## Concept to Remember
* **Memoization**: storing the results of expensive function calls to avoid redundant computation.
* **Dynamic Programming**: breaking down a problem into smaller subproblems and solving each one only once.
* **XOR properties**: understanding the behavior of the XOR operation, including its commutativity and associativity.

## Common Mistakes
* Failing to initialize the `memo` array properly, leading to incorrect results.
* Not updating the minimum XOR sum correctly when reaching the bottom-right corner.
* Not considering the case where the input grid is empty or has invalid dimensions.

## Complexity Analysis
- Time: O(m \* n \* 2^m \* n) / O(m \* n \* 2^1024) due to memoization.
- Space: O(m \* n \* 2^m \* n) / O(m \* n \* 1024) for the `memo` array.

## Commented Code
```java
class Solution {
    int[][] grid;
    int m, n;
    int min = Integer.MAX_VALUE;
    Boolean[][][] memo;

    public int minCost(int[][] g) {
        // Store the input grid and its dimensions
        grid = g;
        m = g.length; n = g[0].length;
        // Initialize the memoization table
        memo = new Boolean[m][n][1024];

        // Call the recursive function to start the search
        func(0, 0, 0);
        // Return the minimum XOR sum found
        return min;
    }

    public void func(int i, int j, int curr) {
        // Base case: if we've reached the bottom-right corner, update the minimum XOR sum
        if (i == m - 1 && j == n - 1) {
            min = Math.min(min, curr);
            return;
        }

        // Check if we've already visited this cell and its XOR value
        if (memo[i][j][curr] != null) return;
        memo[i][j][curr] = true;

        // Calculate the XOR of the current cell's value with its neighbors
        curr ^= grid[i][j];

        // Recursively explore the next cells
        func(i + 1, j, curr); func(i, j + 1, curr);
    }
}
```

## Interview Tips
* Be prepared to explain the memoization and dynamic programming techniques used in the solution.
* Emphasize the importance of initializing the `memo` array correctly.
* Practice solving similar problems to improve your skills in this area.

## Revision Checklist
- [ ] Review the XOR properties and how they're used in the solution.
- [ ] Understand the role of memoization in optimizing the search process.
- [ ] Practice solving similar problems with different grid sizes and input values.

## Similar Problems
* Minimum Path Sum (LeetCode 64)
* Unique Paths (LeetCode 63)
* Minimize the XOR Sum of All Elements in an Array (LeetCode 1572)

## Tags
`Array` `Hash Map` `Memoization` `Dynamic Programming`
