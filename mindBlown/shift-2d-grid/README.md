# Shift 2d Grid

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Matrix` `Simulation`  
**Time:** O(m \* n)  
**Space:** O(m \* n)

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
Shift a 2D grid by k positions to the right. Solve this problem using matrix rotation and modular arithmetic.

## Intuition
The key insight is that we can treat the 2D grid as a 1D array, apply modular arithmetic to rotate it, and then convert back to 2D format.

## Algorithm
1. Calculate the total number of elements in the grid.
2. Reduce k by modulo total to handle large rotations.
3. Create an empty result grid with the same dimensions as the input grid.
4. Iterate through each element in the original grid:
	* Calculate its old index (before rotation) using 2D coordinates.
	* Apply modular arithmetic to get its new index after rotation.
	* Convert the new index back to 2D coordinates and assign the element to the result grid.

## Concept to Remember
* **Matrix rotation**: treating a 2D matrix as a 1D array for efficient operations.
* **Modular arithmetic**: handling large numbers using modulo operation.
* **2D coordinate system**: understanding how to convert between linear and spatial indices.

## Common Mistakes
* Forgetting to reduce k by modulo total, leading to incorrect rotation.
* Misunderstanding the conversion from 1D to 2D coordinates after rotation.
* Failing to initialize the result grid properly before assigning elements.

## Complexity Analysis
- Time: O(m \* n) - each element is rotated once.
- Space: O(m \* n) - storing the result grid.

## Commented Code
```java
class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        // total number of elements in the grid
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;

        // reduce k by modulo total for large rotations
        k %= total;

        // create an empty result grid with same dimensions as input grid
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++)
                row.add(0);
            ans.add(row);
        }

        // iterate through each element in the original grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // index in 1D array (before rotation)
                int oldIndex = i * n + j;

                // apply modular arithmetic to get new index after rotation
                int newIndex = (oldIndex + k) % total;

                // convert new index back to 2D coordinates and assign element
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
* Practice explaining your thought process and insights during the interview.
* Be prepared to handle edge cases, such as large rotations or grids with specific dimensions.
* Emphasize the importance of modular arithmetic in efficiently handling large numbers.

## Revision Checklist
- [ ] Understand matrix rotation and modular arithmetic concepts.
- [ ] Practice solving similar problems on LeetCode.
- [ ] Review time and space complexity analysis.

## Similar Problems
* 498. Diameter of Binary Tree
* 542. 01 Matrix
* 79. Word Search

## Tags
`Array` `Hash Map` `Matrix Rotation` `Modular Arithmetic`
