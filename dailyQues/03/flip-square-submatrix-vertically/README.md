# Flip Square Submatrix Vertically

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Two Pointers` `Matrix`  
**Time:** O(k^2)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        int done = 1;
        for(int i = x;i< x+k/2;i++){
            for(int j=y;j<(y+k);j++){
                int temp = grid[i][j] ;
                grid[i][j] = grid[x+k-done][j];
                grid[x+k-done][j] = temp;
            }
            done++;
        }
        return grid;  
    }
}
```

---

---
## Quick Revision
Given a 2D grid, flip a square submatrix of size k x k vertically.
Swap rows within the specified submatrix boundaries.

## Intuition
The problem asks us to flip a square submatrix vertically. This means that for each column within the submatrix, we need to reverse the order of elements in the rows that constitute that column. Imagine the submatrix as a smaller grid. Flipping it vertically is equivalent to reversing each column of this smaller grid. The key is to identify the correct row indices to swap. If the submatrix starts at `(x, y)` and has size `k x k`, the rows involved are from `x` to `x + k - 1`. To flip vertically, we swap the `i`-th row from the top of the submatrix with the `i`-th row from the bottom of the submatrix.

## Algorithm
1.  Identify the top-left corner of the submatrix: `(x, y)`.
2.  Determine the size of the square submatrix: `k`.
3.  Iterate through the rows of the top half of the submatrix. The loop for rows will go from `i = x` up to `x + k/2 - 1`.
4.  For each row `i` in the top half, determine its corresponding row `i'` in the bottom half that needs to be swapped. This row `i'` will be `x + k - 1 - (i - x)`. A simpler way to think about this is that the `done`-th row from the top (starting `done` from 1) needs to be swapped with the `done`-th row from the bottom. So, if the current row is `i`, its distance from the top of the submatrix is `i - x`. The corresponding row from the bottom will be `x + k - 1 - (i - x)`.
5.  Iterate through all the columns `j` within the submatrix, from `y` to `y + k - 1`.
6.  For each cell `grid[i][j]`, swap its value with the corresponding cell in the bottom row: `grid[i'][j]`.
7.  The provided solution uses a `done` counter which increments for each pair of rows swapped. `done` starts at 1. The row `i` is swapped with `x + k - done`. This effectively achieves the same result as the `i'` calculation above.

## Concept to Remember
*   2D Array Manipulation: Understanding how to access and modify elements in a 2D array using row and column indices.
*   In-place Swapping: Efficiently exchanging values between two locations without using significant extra memory.
*   Submatrix Operations: Identifying and processing a specific rectangular or square portion of a larger 2D array.

## Common Mistakes
*   Off-by-one errors in loop bounds or index calculations for `k` and row/column boundaries.
*   Incorrectly calculating the corresponding row index for swapping, leading to incorrect flips or no flips.
*   Not handling the middle row correctly if `k` is odd (though for vertical flip, the middle row doesn't move, so this is less of an issue than horizontal).
*   Confusing vertical flip with horizontal flip logic.

## Complexity Analysis
*   Time: O(k^2) - The algorithm iterates through approximately half of the rows (`k/2`) and for each of those, it iterates through all `k` columns within the submatrix. This results in `(k/2) * k` operations, which simplifies to O(k^2).
*   Space: O(1) - The algorithm performs the flip in-place, using only a constant amount of extra space for the `temp` variable during swapping.

## Commented Code
```java
class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        // 'done' is a counter to track how many pairs of rows have been swapped.
        // It starts at 1 and increments after each pair of rows is swapped.
        // This helps in calculating the corresponding row from the bottom to swap with.
        int done = 1;

        // Iterate through the top half of the rows of the k x k submatrix.
        // We only need to iterate up to k/2 because we swap pairs of rows.
        // 'i' represents the current row index starting from 'x'.
        for(int i = x; i < x + k/2; i++){

            // Iterate through all the columns 'j' within the k x k submatrix.
            // 'j' starts from 'y' and goes up to 'y + k - 1'.
            for(int j = y; j < (y + k); j++){

                // Store the value of the current cell in a temporary variable.
                int temp = grid[i][j];

                // Swap the current cell's value with the corresponding cell in the bottom row.
                // The corresponding bottom row is calculated as 'x + k - done'.
                // For example, if k=4, x=0, done=1, we swap row 0 with row 3 (0+4-1).
                // If done=2, we swap row 1 with row 2 (0+4-2).
                grid[i][j] = grid[x + k - done][j];

                // Assign the stored temporary value to the cell in the bottom row.
                grid[x + k - done][j] = temp;
            }
            // Increment 'done' after swapping all columns for the current pair of rows.
            // This prepares for the next pair of rows to be swapped.
            done++;
        }
        // Return the modified grid with the submatrix flipped vertically.
        return grid;
    }
}
```

## Interview Tips
*   Clearly explain your approach to identify the submatrix boundaries and the swapping logic.
*   Walk through an example with `k=2` and `k=3` to demonstrate how your row swapping indices work.
*   Be prepared to discuss the time and space complexity of your solution.
*   Ask clarifying questions about edge cases, such as `k=1` or if the submatrix extends beyond the grid boundaries (though the problem statement implies valid inputs).

## Revision Checklist
- [ ] Understand the problem: Flip a k x k submatrix vertically.
- [ ] Identify submatrix boundaries: `x`, `y`, `k`.
- [ ] Determine row swapping logic: Swap `i`-th row from top with `i`-th row from bottom of submatrix.
- [ ] Implement loop for rows (top half).
- [ ] Implement loop for columns.
- [ ] Implement in-place swap.
- [ ] Calculate complexity: Time and Space.
- [ ] Test with examples: `k` even and odd.

## Similar Problems
*   Flip Square Submatrix Horizontally
*   Rotate Image (90 degrees clockwise)
*   Set Matrix Zeroes

## Tags
`Array` `Matrix` `In-place`
