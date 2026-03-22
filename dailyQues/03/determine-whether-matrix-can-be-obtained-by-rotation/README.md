# Determine Whether Matrix Can Be Obtained By Rotation

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Matrix`  
**Time:** O(N^2)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        boolean found = true;
        int n = mat.length;
        boolean r0=true,r1=true,r2=true,r3= true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[i][j]) r0 = false;
                if (mat[i][j] != target[n - 1 - i][n-1-j]) r1 = false;
                if (mat[i][j] != target[n-1-j][i]) r2 = false;
                if (mat[i][j] != target[j][n-1-i]) r3 = false;
            }
        }
        return r0 || r1 || r2 || r3;
    }
}

//Another solution to be considered.
// class Solution {
//     public boolean findRotation(int[][] mat, int[][] target) {
//         boolean found = true;
//         int n = mat.length;
//         // ith row becomes ((n-1)-i)th col

//         //row to row
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (mat[i][j] != target[i][j]) {
//                     found = false;
//                     break;
//                 }
//                 if (!found)
//                     break;
//             }
//         }
//         if (found)
//             return true;
//         //row to opp row
//         found = true;
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < n; j++) {
//                 if (mat[i][j] != target[n - 1 - i][n-1-j]) {
                    
//                     found = false;
//                     break;
//                 }
//             }
//             if (!found)
//                 break;
//         }
//         if (found)
//             return true;

//         //row to col
//         found = true;
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < n; j++) {
                
//                 if (mat[i][j] != target[n-1-j][i]) {
//                     found = false;
//                     break;
//                 }
//             }
//             if (!found)
//                 break;
//         }
//         if (found)
//             return true;

//         //row to opp col
//         found = true;
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < n; j++) {
                
//                 if (mat[i][j] != target[j][n-1-i]) {
//                     found = false;
//                     break;
//                 }
//             }
//             if (!found)
//                 break;
//         }        
//         return found;
//     }
// }
```

---

---
## Quick Revision
The problem asks if a given matrix `target` can be obtained by rotating another matrix `mat` by 0, 90, 180, or 270 degrees.
We solve this by checking if `target` matches `mat` in its original orientation or any of its three rotations.

## Intuition
The core idea is that a square matrix can be rotated by 90 degrees clockwise multiple times. If the `target` matrix is a rotated version of `mat`, it must match `mat` in one of these four orientations:
1. Original (0-degree rotation)
2. 90-degree clockwise rotation
3. 180-degree clockwise rotation
4. 270-degree clockwise rotation

Instead of explicitly performing rotations and comparing, we can directly check if the elements of `mat` correspond to the elements of `target` in these four possible rotated positions. If any of these checks pass, then `target` can be obtained by rotating `mat`.

## Algorithm
1. **Initialize flags**: Create four boolean flags, `r0`, `r1`, `r2`, and `r3`, all initialized to `true`. These flags will track if the `target` matrix matches `mat` in its original orientation, 90-degree rotation, 180-degree rotation, and 270-degree rotation, respectively.
2. **Get matrix dimension**: Determine the size `n` of the square matrix (assuming `mat` and `target` are `n x n`).
3. **Iterate through matrix elements**: Use nested loops to iterate through each element `mat[i][j]` of the `mat` matrix, where `i` is the row index and `j` is the column index.
4. **Check against target orientations**: For each element `mat[i][j]`:
    * **Original (0-degree)**: Compare `mat[i][j]` with `target[i][j]`. If they are not equal, set `r0` to `false`.
    * **90-degree clockwise rotation**: The element at `mat[i][j]` in the original matrix moves to `target[j][n-1-i]` after a 90-degree clockwise rotation. Compare `mat[i][j]` with `target[j][n-1-i]`. If they are not equal, set `r2` to `false`. (Note: The provided solution uses `target[n-1-j][i]` for 90 degrees, which is a 90-degree counter-clockwise rotation. For consistency with standard clockwise rotation, `target[j][n-1-i]` is correct. The provided solution's `r2` and `r3` logic seems to be swapped or using a different convention. Let's stick to the standard clockwise rotation mapping for clarity in explanation.)
    * **180-degree clockwise rotation**: The element at `mat[i][j]` moves to `target[n-1-i][n-1-j]`. Compare `mat[i][j]` with `target[n-1-i][n-1-j]`. If they are not equal, set `r1` to `false`.
    * **270-degree clockwise rotation**: The element at `mat[i][j]` moves to `target[n-1-j][i]`. Compare `mat[i][j]` with `target[n-1-j][i]`. If they are not equal, set `r3` to `false`. (Again, this mapping is for 270-degree clockwise. The provided solution's `r2` and `r3` might be interpreted differently.)

    *Correction based on the provided solution's logic:*
    The provided solution uses:
    - `r0`: `mat[i][j] != target[i][j]` (0-degree)
    - `r1`: `mat[i][j] != target[n - 1 - i][n-1-j]` (180-degree)
    - `r2`: `mat[i][j] != target[n-1-j][i]` (This corresponds to 270-degree clockwise rotation, or 90-degree counter-clockwise rotation)
    - `r3`: `mat[i][j] != target[j][n-1-i]` (This corresponds to 90-degree clockwise rotation)

    So, the algorithm should follow the provided solution's mapping:
    For each element `mat[i][j]`:
    * **0-degree**: Compare `mat[i][j]` with `target[i][j]`. If not equal, `r0 = false`.
    * **180-degree**: Compare `mat[i][j]` with `target[n-1-i][n-1-j]`. If not equal, `r1 = false`.
    * **270-degree clockwise (or 90-degree counter-clockwise)**: Compare `mat[i][j]` with `target[n-1-j][i]`. If not equal, `r2 = false`.
    * **90-degree clockwise**: Compare `mat[i][j]` with `target[j][n-1-i]`. If not equal, `r3 = false`.

5. **Return result**: After iterating through all elements, return `true` if any of the flags (`r0`, `r1`, `r2`, or `r3`) is still `true`. Otherwise, return `false`.

## Concept to Remember
*   **Matrix Rotations**: Understanding how elements shift positions during 90, 180, and 270-degree rotations.
*   **Coordinate Transformation**: Mapping original coordinates `(i, j)` to new coordinates after rotation.
*   **Boolean Logic**: Using flags to track multiple conditions and combining them with OR.
*   **In-place Comparison**: Avoiding explicit matrix creation for rotated versions by comparing elements directly.

## Common Mistakes
*   **Incorrect Rotation Mapping**: Miscalculating the new coordinates for elements after rotation, especially for 90 and 270 degrees.
*   **Off-by-One Errors**: Errors in index calculations, particularly with `n-1-i` and `n-1-j`.
*   **Early Exit Logic**: If using separate loops for each rotation, failing to reset the `found` flag or not breaking out of inner loops correctly. The provided single-loop solution is more efficient.
*   **Not Checking All Rotations**: Forgetting to check all four possible orientations (0, 90, 180, 270 degrees).
*   **Assuming Square Matrices**: The problem statement implies square matrices, but in a real interview, clarifying this assumption is good practice.

## Complexity Analysis
- **Time**: O(N^2) - reason: We iterate through each element of the N x N matrix exactly once. The operations inside the loop are constant time.
- **Space**: O(1) - reason: We only use a few boolean flags and an integer for the dimension, which is constant extra space regardless of the input matrix size.

## Commented Code
```java
class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        // Initialize a boolean flag to track if the matrices match in the original orientation.
        boolean r0 = true;
        // Initialize a boolean flag to track if the matrices match after a 180-degree rotation.
        boolean r1 = true;
        // Initialize a boolean flag to track if the matrices match after a 270-degree clockwise rotation (or 90-degree counter-clockwise).
        boolean r2 = true;
        // Initialize a boolean flag to track if the matrices match after a 90-degree clockwise rotation.
        boolean r3 = true;
        
        // Get the dimension of the square matrix.
        int n = mat.length;
        
        // Iterate through each row of the matrix.
        for (int i = 0; i < n; i++) {
            // Iterate through each column of the matrix.
            for (int j = 0; j < n; j++) {
                // Check for 0-degree rotation: mat[i][j] should match target[i][j].
                // If they don't match, set r0 to false, indicating this orientation is not a match.
                if (mat[i][j] != target[i][j]) r0 = false;
                
                // Check for 180-degree rotation: mat[i][j] should match target[n - 1 - i][n - 1 - j].
                // The element at (i, j) in mat moves to (n-1-i, n-1-j) in target after 180-degree rotation.
                // If they don't match, set r1 to false.
                if (mat[i][j] != target[n - 1 - i][n - 1 - j]) r1 = false;
                
                // Check for 270-degree clockwise rotation: mat[i][j] should match target[n - 1 - j][i].
                // The element at (i, j) in mat moves to (n-1-j, i) in target after 270-degree clockwise rotation.
                // If they don't match, set r2 to false.
                if (mat[i][j] != target[n - 1 - j][i]) r2 = false;
                
                // Check for 90-degree clockwise rotation: mat[i][j] should match target[j][n - 1 - i].
                // The element at (i, j) in mat moves to (j, n-1-i) in target after 90-degree clockwise rotation.
                // If they don't match, set r3 to false.
                if (mat[i][j] != target[j][n - 1 - i]) r3 = false;
            }
        }
        
        // Return true if any of the rotation checks passed (i.e., at least one flag is still true).
        // This means the target matrix can be obtained by rotating the mat matrix.
        return r0 || r1 || r2 || r3;
    }
}
```

## Interview Tips
1.  **Clarify Assumptions**: Ask if the matrices are guaranteed to be square and of the same dimensions.
2.  **Explain Rotation Logic**: Clearly articulate how the coordinates change for each rotation (0, 90, 180, 270 degrees). For example, "A 90-degree clockwise rotation maps `(i, j)` to `(j, n-1-i)`."
3.  **Discuss Trade-offs**: Mention that while this approach avoids creating new matrices, an alternative could be to implement a `rotateMatrix` helper function and call it up to three times, comparing the result with `target` each time. Discuss why the current approach is more efficient in terms of space and potentially time (single pass).
4.  **Edge Cases**: Consider `n=1` matrix. The code should handle this correctly.

## Revision Checklist
- [ ] Understand the problem: Can `target` be formed by rotating `mat`?
- [ ] Identify the four possible orientations: 0, 90, 180, 270 degrees.
- [ ] Derive the coordinate transformation for each rotation.
- [ ] Implement a single pass to check all orientations simultaneously using boolean flags.
- [ ] Ensure correct index calculations (`n-1-i`, `n-1-j`).
- [ ] Combine results using logical OR.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Rotate Image (LeetCode 48) - Involves rotating a matrix in-place.
*   Matrix Diagonal Traverse (LeetCode 498) - Traverses a matrix in a specific diagonal pattern.

## Tags
`Array` `Matrix` `Simulation`
