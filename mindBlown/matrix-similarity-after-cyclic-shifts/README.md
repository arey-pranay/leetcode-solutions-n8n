# Matrix Similarity After Cyclic Shifts

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Math` `Matrix` `Simulation`  
**Time:** O(m * n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        k %= n; // ki effectively kitna ghooma
        for(int i=0;i<m;i++){ // i me kuch nhi krna kyuki rows rotate ho rahi hai
            for(int j=0; j<n; j++){
                int J = j;
                if(i%2 == 0) J -= k;
                else J += k;
                J = J>=n ? J-n : J; // agar size se 1 aage chle gye mtlb 0th index
                J = J<0 ? n+J : J; // agr -1 aagya J mtlb right se last
                if(mat[i][j] != mat[i][J]) return false;
            }
        }
        return true;
    }
}
```

---

---
## Quick Revision
This problem asks if a matrix can be made identical to another matrix by cyclically shifting its rows. We solve this by checking if each row in the first matrix can be transformed into the corresponding row in the second matrix via cyclic shifts.

## Intuition
The core idea is that if two matrices are similar after cyclic shifts, then each row in the first matrix must be a cyclic shift of the corresponding row in the second matrix. Since the shifts are applied independently to each row, we can check this row by row. For even-indexed rows, the shift is to the left, and for odd-indexed rows, it's to the right. The `k` value tells us the magnitude of the shift.

## Algorithm
1. Normalize `k`: Since cyclic shifts repeat every `n` (number of columns), take `k` modulo `n`.
2. Iterate through each row of the matrix (`i` from 0 to `m-1`).
3. For each row, iterate through each column (`j` from 0 to `n-1`).
4. Calculate the target index `J` in the same row that `mat[i][j]` should correspond to after the shift.
   - If the row index `i` is even, the shift is to the left, so the original element at `J` should move to `j`. This means `j = (J - k + n) % n`. Rearranging for `J`, we get `J = (j + k) % n`. However, the provided code calculates the *destination* index for `mat[i][j]`. If `mat[i][j]` is the element at index `j`, and it's supposed to match an element that *was* at index `J` before the shift, then for even rows (left shift), `j = (J + k) % n`. So, `J = (j - k + n) % n`.
   - If the row index `i` is odd, the shift is to the right, so the original element at `J` should move to `j`. This means `j = (J + k) % n`. Rearranging for `J`, we get `J = (j - k + n) % n`. However, the provided code calculates the *destination* index for `mat[i][j]`. If `mat[i][j]` is the element at index `j`, and it's supposed to match an element that *was* at index `J` before the shift, then for odd rows (right shift), `j = (J - k + n) % n`. So, `J = (j + k) % n`.
   - The code's logic:
     - `J = j`: Start with the current column index.
     - `if(i%2 == 0) J -= k; else J += k;`: Apply the shift based on row parity. For even rows (left shift), the element at `j` *came from* `j+k`. For odd rows (right shift), the element at `j` *came from* `j-k`. The code is calculating where the element *at* `mat[i][j]` *should have come from* to match the original matrix.
     - `J = J>=n ? J-n : J;`: Handle wrap-around for positive indices (e.g., if `j+k` exceeds `n-1`).
     - `J = J<0 ? n+J : J;`: Handle wrap-around for negative indices (e.g., if `j-k` becomes negative).
5. Compare `mat[i][j]` with `mat[i][J]`. If they are not equal, the matrices are not similar, so return `false`.
6. If all elements match after checking all rows and columns, return `true`.

## Concept to Remember
*   **Cyclic Shifts:** Understanding how elements move in a circular array or list.
*   **Modulo Arithmetic:** Essential for handling wrap-around behavior in cyclic shifts.
*   **Conditional Logic:** Applying different rules based on row index parity.

## Common Mistakes
*   **Incorrect Modulo Arithmetic:** Errors in handling negative results from subtractions or ensuring indices stay within bounds.
*   **Confusing Shift Direction:** Mixing up left and right shifts for even and odd rows.
*   **Off-by-One Errors:** Incorrectly calculating the target index `J` or handling boundary conditions.
*   **Not Normalizing `k`:** Failing to take `k % n` can lead to unnecessary computations or incorrect results if `k` is larger than `n`.

## Complexity Analysis
- Time: O(m * n) - reason: We iterate through each element of the matrix exactly once to perform the comparison.
- Space: O(1) - reason: We are only using a few extra variables to store indices and the dimensions, which does not depend on the input size.

## Commented Code
```java
class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length; // Get the number of rows in the matrix.
        int n = mat[0].length; // Get the number of columns in the matrix.
        k %= n; // Normalize k by taking modulo n, as shifts repeat every n columns.

        // Iterate through each row of the matrix.
        for(int i = 0; i < m; i++){
            // Iterate through each column of the current row.
            for(int j = 0; j < n; j++){
                int J = j; // Initialize J with the current column index.

                // Determine the original index J from which the element at mat[i][j] should have come.
                // If the row index i is even, it's a left shift, so the element at j came from j+k.
                // If the row index i is odd, it's a right shift, so the element at j came from j-k.
                if(i % 2 == 0) { // For even rows (0, 2, 4, ...), the shift is to the left.
                    J -= k; // The element at mat[i][j] was originally at index (j + k) % n.
                           // So, to find the original index, we subtract k from j.
                } else { // For odd rows (1, 3, 5, ...), the shift is to the right.
                    J += k; // The element at mat[i][j] was originally at index (j - k + n) % n.
                           // So, to find the original index, we add k to j.
                }

                // Handle wrap-around for positive indices: if J goes beyond the last column index (n-1).
                // For example, if n=5 and j=3, k=3, then J becomes 6. 6 % 5 = 1.
                // The code uses J = J >= n ? J - n : J; which is equivalent to J %= n for positive J.
                J = J >= n ? J - n : J;

                // Handle wrap-around for negative indices: if J becomes negative.
                // For example, if n=5 and j=1, k=3, then J becomes -2. -2 + 5 = 3.
                // The code uses J = J < 0 ? n + J : J; which is equivalent to J = (J % n + n) % n for any J.
                J = J < 0 ? n + J : J;

                // Compare the element at the current position mat[i][j] with the element at the calculated original position mat[i][J].
                // If they are not equal, the matrices are not similar after cyclic shifts.
                if(mat[i][j] != mat[i][J]) {
                    return false; // Immediately return false if a mismatch is found.
                }
            }
        }
        // If the loop completes without finding any mismatches, it means all rows are cyclic shifts of each other.
        return true; // Return true indicating the matrices are similar.
    }
}
```

## Interview Tips
*   **Clarify Shift Direction:** Explicitly ask the interviewer about the direction of shifts for even and odd rows if not clearly stated.
*   **Explain Modulo Arithmetic:** Be prepared to explain how you handle wrap-around using the modulo operator, especially for negative results.
*   **Trace an Example:** Walk through a small 2x2 or 2x3 matrix with a given `k` to demonstrate your understanding of the algorithm.
*   **Edge Cases:** Consider cases where `k=0`, `k=n`, or `k > n`.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Implement cyclic shift logic correctly for both left and right shifts.
- [ ] Handle index wrap-around using modulo arithmetic.
- [ ] Normalize `k` by taking `k % n`.
- [ ] Test with edge cases like `k=0` or empty matrices.

## Similar Problems
*   Rotate Array
*   Circular Array Loop
*   Array Rotation

## Tags
`Array` `Matrix` `Simulation`
