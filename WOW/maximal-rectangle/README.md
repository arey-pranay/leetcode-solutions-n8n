# Maximal Rectangle

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Stack` `Matrix` `Monotonic Stack`  
**Time:** O(m * n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    int m,n;
    int[] heights, leftBound, rightBound;
    public int maximalRectangle(char[][] matrix) {
        m = matrix.length; //rows
        n = matrix[0].length; //cols
        
        //har ek col ke liye h,lb,rb
        heights = new int[n];
        leftBound = new int[n];
        rightBound = new int[n];
        Arrays.fill(rightBound,n);
        int ans = Integer.MIN_VALUE;
        for(int row=0;row<m;row++){
            updateHeight(matrix[row]); // current number ki height
            updateLeftBound(matrix[row]); // wo height left me kahan tk chlegi
            updateRightBound(matrix[row]); // wo height right me kahan tk chlegi
            ans = Math.max(ans,findMaxArea()); // calculate for the rows covered, then cover the next row and calculate again
            // printArr(heights);
            // printArr(leftBound);
            // printArr(rightBound);
            // System.out.println();
        }
        
        return ans;
    }
    
    public void updateHeight(char[] row){
        for(int col=0;col<n;col++){
            if(row[col] == '1') heights[col]++;
            else heights[col] = 0;
        }
    }
    public void updateLeftBound(char[] row){
        int left=0;
        for(int col=0;col<n;col++){
            if(row[col] == '1') leftBound[col]=Math.max(leftBound[col],left);
            else {
                leftBound[col] = 0;
                left=col+1;
            }
        }    
    }
    
    public void updateRightBound(char[] row){
        int right=n;
        for(int col=n-1;col>=0;col--){
            if(row[col] == '1') rightBound[col]=Math.min(rightBound[col],right);
            else {
                rightBound[col] = n;
                right=col;
            }
        }    
    }
    public int findMaxArea(){
        int area = 0;
        for(int i=0;i<n;i++){
            int width = rightBound[i]-leftBound[i];
            area = Math.max(area,heights[i] * width);
        }
        return area;
    }
    
    public void printArr(int[] arr){
        for(int num : arr) System.out.print(num+" ");
        System.out.println();
    }
}
```

---

---
## Quick Revision
This problem asks for the largest rectangular area of '1's in a 2D binary matrix.
It can be solved by reducing it to the "Largest Rectangle in Histogram" problem for each row.

## Intuition
The core idea is to transform the 2D matrix problem into a series of 1D histogram problems. For each row, we can consider it as the "bottom" of potential rectangles. The height of a bar in the histogram at a specific column will be the number of consecutive '1's ending at that cell in that column. Once we have these histograms for each row, we can apply the standard "Largest Rectangle in Histogram" algorithm to find the maximum area for that row's histogram. The overall maximum area will be the maximum among all rows.

## Algorithm
1. Initialize `m` (number of rows) and `n` (number of columns) from the input `matrix`.
2. Create three arrays of size `n`: `heights`, `leftBound`, and `rightBound`.
   - `heights[j]` will store the current height of consecutive '1's ending at `matrix[i][j]`.
   - `leftBound[j]` will store the leftmost boundary for a rectangle of height `heights[j]` ending at `matrix[i][j]`.
   - `rightBound[j]` will store the rightmost boundary for a rectangle of height `heights[j]` ending at `matrix[i][j]`.
3. Initialize `rightBound` with `n` for all elements. This signifies that initially, the right boundary extends to the end of the row.
4. Initialize `ans` to `Integer.MIN_VALUE` to store the maximum area found so far.
5. Iterate through each `row` from `0` to `m-1`:
   a. Call `updateHeight(matrix[row])` to update the `heights` array based on the current row. If `matrix[i][j]` is '1', increment `heights[j]`; otherwise, reset `heights[j]` to 0.
   b. Call `updateLeftBound(matrix[row])` to update the `leftBound` array. For each column `j`, if `matrix[i][j]` is '1', `leftBound[j]` is the maximum of its current value and the `left` pointer (which tracks the start of a contiguous '1' sequence). If `matrix[i][j]` is '0', reset `leftBound[j]` to 0 and update `left` to `j+1`.
   c. Call `updateRightBound(matrix[row])` to update the `rightBound` array. Iterate from right to left. For each column `j`, if `matrix[i][j]` is '1', `rightBound[j]` is the minimum of its current value and the `right` pointer (which tracks the end of a contiguous '1' sequence). If `matrix[i][j]` is '0', reset `rightBound[j]` to `n` and update `right` to `j`.
   d. Call `findMaxArea()` to calculate the maximum rectangle area for the current row's histogram and update `ans` if a larger area is found.
6. Return `ans`.

The `findMaxArea()` function iterates through each column `i`, calculates the `width` as `rightBound[i] - leftBound[i]`, and updates the maximum `area` with `heights[i] * width`.

## Concept to Remember
*   **Dynamic Programming:** The `heights` array effectively uses DP, where the height at `(i, j)` depends on the height at `(i-1, j)`.
*   **Reduction to 1D Problem:** Transforming a 2D problem into a series of 1D problems is a common and powerful technique.
*   **Largest Rectangle in Histogram:** Understanding and being able to solve this subproblem is crucial.
*   **Two Pointers/Sliding Window (Implicit):** The `left` and `right` pointers in `updateLeftBound` and `updateRightBound` implicitly define boundaries for potential rectangles.

## Common Mistakes
*   **Incorrectly updating `heights`:** Forgetting to reset height to 0 when a '0' is encountered.
*   **Off-by-one errors in `leftBound` and `rightBound`:** Miscalculating the boundaries, especially when dealing with '0's.
*   **Not handling edge cases:** Forgetting to initialize `rightBound` correctly or not considering empty matrices.
*   **Reinventing the wheel:** Trying to solve the 2D problem directly without recognizing the connection to the histogram problem.
*   **Inefficient `findMaxArea`:** If `findMaxArea` itself is not O(n), the overall complexity will be higher. The provided solution's `findMaxArea` is O(n), which is correct.

## Complexity Analysis
*   **Time:** O(m * n) - We iterate through each cell of the matrix once to update heights, left bounds, and right bounds. The `findMaxArea` function takes O(n) time for each row. Since there are `m` rows, the total time complexity is O(m * n).
*   **Space:** O(n) - We use three auxiliary arrays (`heights`, `leftBound`, `rightBound`), each of size `n`, to store intermediate results.

## Commented Code
```java
class Solution {
    int m,n; // Declare instance variables for number of rows (m) and columns (n)
    int[] heights, leftBound, rightBound; // Declare arrays to store heights, left boundaries, and right boundaries for each column

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) { // Handle edge case: empty or null matrix
            return 0; // If matrix is empty, no rectangle can be formed, return 0
        }
        m = matrix.length; // Get the number of rows
        n = matrix[0].length; // Get the number of columns

        // Initialize arrays for each column. These will be updated row by row.
        heights = new int[n]; // heights[j] will store the height of consecutive '1's ending at current row, column j
        leftBound = new int[n]; // leftBound[j] will store the leftmost boundary for a rectangle of height heights[j]
        rightBound = new int[n]; // rightBound[j] will store the rightmost boundary for a rectangle of height heights[j]

        // Initialize rightBound array. Initially, assume the right boundary extends to the end of the matrix (n).
        Arrays.fill(rightBound,n);

        int ans = Integer.MIN_VALUE; // Initialize the maximum area found so far to the smallest possible integer value

        // Iterate through each row of the matrix
        for(int row=0;row<m;row++){
            updateHeight(matrix[row]); // Update the heights array based on the current row's '1's and '0's
            updateLeftBound(matrix[row]); // Update the left boundaries for potential rectangles ending at this row
            updateRightBound(matrix[row]); // Update the right boundaries for potential rectangles ending at this row

            // After updating heights, leftBound, and rightBound for the current row,
            // find the maximum rectangle area that can be formed using this row as the base.
            // This is equivalent to finding the largest rectangle in the histogram represented by heights,
            // constrained by leftBound and rightBound.
            ans = Math.max(ans,findMaxArea());
        }

        return ans; // Return the overall maximum area found across all rows
    }

    // Updates the heights array for the current row.
    // If matrix[row][col] is '1', increment the height for that column.
    // If matrix[row][col] is '0', reset the height for that column to 0.
    public void updateHeight(char[] row){
        for(int col=0;col<n;col++){ // Iterate through each column in the current row
            if(row[col] == '1') heights[col]++; // If the current cell is '1', increase the height of the bar in the histogram
            else heights[col] = 0; // If the current cell is '0', the consecutive sequence of '1's is broken, so reset height to 0
        }
    }

    // Updates the leftBound array for the current row.
    // leftBound[col] stores the index of the first '0' to the left of col (exclusive)
    // or the start of the row if all preceding cells are '1'.
    public void updateLeftBound(char[] row){
        int left=0; // 'left' pointer tracks the start of a contiguous sequence of '1's in the current row
        for(int col=0;col<n;col++){ // Iterate through each column from left to right
            if(row[col] == '1') { // If the current cell is '1'
                // The left boundary for a rectangle of height heights[col] is at least 'left'.
                // We take the maximum because a previous '0' might have set a tighter left bound.
                leftBound[col]=Math.max(leftBound[col],left);
            } else { // If the current cell is '0'
                leftBound[col] = 0; // A '0' breaks any rectangle, so its left boundary is effectively 0 (or irrelevant for future '1's)
                left=col+1; // The next potential sequence of '1's starts after this '0'
            }
        }
    }

    // Updates the rightBound array for the current row.
    // rightBound[col] stores the index of the first '0' to the right of col (exclusive)
    // or the end of the row if all succeeding cells are '1'.
    public void updateRightBound(char[] row){
        int right=n; // 'right' pointer tracks the end of a contiguous sequence of '1's in the current row, initialized to the end of the row
        for(int col=n-1;col>=0;col--){ // Iterate through each column from right to left
            if(row[col] == '1') { // If the current cell is '1'
                // The right boundary for a rectangle of height heights[col] is at most 'right'.
                // We take the minimum because a previous '0' (from the right) might have set a tighter right bound.
                rightBound[col]=Math.min(rightBound[col],right);
            } else { // If the current cell is '0'
                rightBound[col] = n; // A '0' breaks any rectangle, so its right boundary is effectively 'n' (or irrelevant for future '1's)
                right=col; // The next potential sequence of '1's (from the right) ends before this '0'
            }
        }
    }

    // Calculates the maximum rectangle area for the histogram represented by the current heights,
    // considering the constraints imposed by leftBound and rightBound.
    public int findMaxArea(){
        int area = 0; // Initialize the maximum area for this row's histogram
        for(int i=0;i<n;i++){ // Iterate through each column (bar in the histogram)
            // The width of the rectangle for the current bar is determined by the difference between its right and left boundaries.
            int width = rightBound[i]-leftBound[i];
            // Calculate the area of the rectangle with height heights[i] and calculated width.
            // Update the maximum area found for this row if the current rectangle's area is larger.
            area = Math.max(area,heights[i] * width);
        }
        return area; // Return the maximum area found for this row's histogram
    }

    // Helper function to print an array (useful for debugging)
    public void printArr(int[] arr){
        for(int num : arr) System.out.print(num+" "); // Print each element of the array
        System.out.println(); // Print a newline character after the array
    }
}
```

## Interview Tips
1.  **Explain the Reduction:** Clearly articulate how you're transforming the 2D problem into multiple 1D "Largest Rectangle in Histogram" problems. This shows you can break down complex problems.
2.  **Walk Through `updateLeftBound` and `updateRightBound`:** These are the trickiest parts. Explain the logic of the `left` and `right` pointers and why `Math.max` and `Math.min` are used. Use a small example to illustrate.
3.  **Discuss the Histogram Subproblem:** Be prepared to explain how you'd solve "Largest Rectangle in Histogram" if asked. The stack-based approach is common, but the current solution uses a simpler O(n) approach for `findMaxArea` by pre-calculating bounds, which is also valid and efficient here.
4.  **Edge Cases:** Mention handling empty matrices or matrices with zero rows/columns.

## Revision Checklist
- [ ] Understand the problem statement: find the largest rectangle of '1's.
- [ ] Recognize the connection to "Largest Rectangle in Histogram".
- [ ] Implement `updateHeight` correctly.
- [ ] Implement `updateLeftBound` correctly, understanding the `left` pointer and `Math.max`.
- [ ] Implement `updateRightBound` correctly, understanding the `right` pointer and `Math.min`.
- [ ] Implement `findMaxArea` to calculate area using `heights`, `leftBound`, and `rightBound`.
- [ ] Handle edge cases (empty matrix).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Largest Rectangle in Histogram (LeetCode 84)
*   Maximal Square (LeetCode 221)
*   Submatrix Sum Equals K (LeetCode 363) - Similar reduction idea.

## Tags
`Array` `Dynamic Programming` `Stack` `Matrix`
