# Shift 2d Grid

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Matrix` `Simulation`  
**Time:** O(m * n)  
**Space:** O(m * n)

---

## Solution (java)

```java
class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int total = m * n;
        k %= total;

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++)
                row.add(0);
            ans.add(row);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // index in 1D array (before rotation)
                int oldIndex = i * n + j;

                // index in 1D array (after rotation)
                int newIndex = (oldIndex + k) % total;

                // changing from 1D back to 2D
                int newRow = newIndex / n;
                int newCol = newIndex % n;

                ans.get(newRow).set(newCol, grid[i][j]);
            }
        }

        return ans;
    }
}
```

---

---
## Quick Revision
Given a 2D grid and an integer k, shift the elements in the grid by k positions.
This problem can be solved using a rotation algorithm that takes into account the wrap-around nature of a 2D array.

## Intuition
The key insight is to consider the grid as a 1D array where each element's index is calculated as `i * n + j`. After shifting, we calculate the new index by taking `(oldIndex + k) % total`, which effectively handles wrap-around. We then convert this 1D index back to a 2D coordinate using division and modulo operations.

## Algorithm
1. Initialize an empty result grid with the same dimensions as the input grid.
2. Calculate the total number of elements in the grid (`m * n`) and the remainder `k` after dividing by this total (to handle wrap-around).
3. Iterate over each element in the original grid, calculating its old index and new index using modular arithmetic.
4. Convert the new 1D index back to a 2D coordinate (new row and column indices) using division and modulo operations.
5. Place the shifted value at the calculated new position in the result grid.

## Concept to Remember
• **Modular Arithmetic**: wrapping around an array with periodic boundary conditions can be achieved by taking modulo of the index.
• **1D Array Representation**: considering a 2D array as a single, contiguous 1D array can simplify calculations and avoid off-by-one errors.
• **Wrap-around Logic**: modular arithmetic can handle wrap-around cases efficiently.

## Common Mistakes
• Failing to consider the periodic boundary conditions when shifting elements.
• Not handling edge cases where `k` is large compared to the grid size.
• Incorrectly calculating the new index after shifting, leading to incorrect placement of values in the result grid.

## Complexity Analysis
- Time: O(m * n) - reason: each element is visited once during iteration and calculation.
- Space: O(m * n) - reason: a new grid of the same size is created as the result.

## Commented Code

```java
class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        // total number of elements in the grid
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;

        // handle wrap-around by taking modulo of k
        k %= total;

        // create an empty result grid with same dimensions as input
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++)
                row.add(0);
            ans.add(row);
        }

        // shift elements and place them in the result grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int oldIndex = i * n + j;
                int newIndex = (oldIndex + k) % total;

                // convert 1D index back to 2D coordinate
                int newRow = newIndex / n;
                int newCol = newIndex % n;

                ans.get(newRow).set(newCol, grid[i][j]);
            }
        }

        return ans;
    }
}
```

## Interview Tips
• Make sure you understand the periodic boundary conditions and how to handle them efficiently using modular arithmetic.
• Practice explaining your thought process and solution clearly during the interview.
• Be prepared to discuss edge cases and potential pitfalls in your solution.

## Revision Checklist
- [ ] Understand modular arithmetic and its application to wrap-around problems.
- [ ] Review handling of edge cases, such as large values of k.
- [ ] Double-check calculations for new indices after shifting elements.

## Similar Problems
• [LC 27. Remove Element](https://leetcode.com/problems/remove-element/)
• [LC 485. Max Consecutive Ones](https://leetcode.com/problems/max-consecutive-ones/)

## Tags
`Array`, `Hash Map`
