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
The core idea is to transform the 2D matrix problem into a series of 1D histogram problems. For each row, we can consider it as the "bottom" of potential rectangles. The height of a bar at a specific column in this histogram will be the number of consecutive '1's upwards from that cell in the original matrix. Once we have this histogram for a row, we can use a standard algorithm (like the one for "Largest Rectangle in Histogram") to find the maximum rectangle area ending at that row. We do this for every row and take the maximum among them.

The provided solution takes a slightly different but related approach. Instead of directly calculating heights and then using a stack-based histogram algorithm, it maintains `heights`, `leftBound`, and `rightBound` arrays for each row.
- `heights[j]` stores the number of consecutive '1's ending at `matrix[i][j]` and going upwards.
- `leftBound[j]` stores the leftmost column index `k` such that all cells `matrix[i][k...j]` are '1's, considering the current row `i` as the bottom.
- `rightBound[j]` stores the rightmost column index `k` such that all cells `matrix[i][j...k]` are '1's, considering the current row `i` as the bottom.

For each row, it updates these three arrays. Then, for each column `j`, it calculates the potential rectangle area using `heights[j] * (rightBound[j] - leftBound[j])`. This effectively finds the largest rectangle whose bottom-right corner is at `matrix[i][j]` and whose height is determined by `heights[j]`. The maximum of these areas across all columns and all rows is the answer.

## Algorithm
1. Initialize `m` (number of rows) and `n` (number of columns) from the input `matrix`.
2. Initialize three integer arrays of size `n`: `heights`, `leftBound`, and `rightBound`.
3. Initialize `rightBound` with `n` for all elements. This signifies that initially, a '1' can extend to the rightmost boundary.
4. Initialize `ans` to `Integer.MIN_VALUE` to store the maximum area found so far.
5. Iterate through each `row` from `0` to `m-1`:
    a. Call `updateHeight(matrix[row])` to update the `heights` array. For each column `col`: if `matrix[row][col]` is '1', increment `heights[col]`; otherwise, reset `heights[col]` to 0.
    b. Call `updateLeftBound(matrix[row])` to update the `leftBound` array. For each column `col`:
        - If `matrix[row][col]` is '1', `leftBound[col]` is the maximum of its current value and the `left` pointer (which tracks the start of a contiguous '1' sequence).
        - If `matrix[row][col]` is '0', reset `leftBound[col]` to 0 and update `left` to `col + 1`.
    c. Call `updateRightBound(matrix[row])` to update the `rightBound` array. For each column `col` from right to left:
        - If `matrix[row][col]` is '1', `rightBound[col]` is the minimum of its current value and the `right` pointer (which tracks the end of a contiguous '1' sequence).
        - If `matrix[row][col]` is '0', reset `rightBound[col]` to `n` and update `right` to `col`.
    d. Call `findMaxArea()` to calculate the maximum area for the current row's histogram configuration and update `ans` if a larger area is found.
6. Return `ans`.

The `findMaxArea()` function iterates through each column `i` and calculates the area as `heights[i] * (rightBound[i] - leftBound[i])`.

## Concept to Remember
*   **Dynamic Programming:** The `heights` array is updated row by row, using information from the previous row, which is a form of DP.
*   **Reduction to 1D Problem:** Transforming a 2D problem into multiple 1D subproblems is a common and powerful technique.
*   **Two Pointers/Sliding Window (Implicit):** The `left` and `right` pointers in `updateLeftBound` and `updateRightBound` effectively define the boundaries of contiguous '1's, similar to a sliding window concept.

## Common Mistakes
*   **Incorrectly calculating heights:** Forgetting to reset height to 0 when a '0' is encountered.
*   **Off-by-one errors in bounds:** Miscalculating `leftBound` and `rightBound`, especially at the edges of the matrix or when encountering '0's.
*   **Not handling empty matrix:** The code assumes `matrix` and `matrix[0]` are not empty.
*   **Confusing with "Maximal Square":** This problem is about rectangles, not squares, so the width calculation is different.
*   **Inefficiently calculating bounds:** The provided solution's `updateLeftBound` and `updateRightBound` are O(N) per row, which is efficient. A naive approach might be O(N^2) per row.

## Complexity Analysis
- Time: O(m * n) - The outer loop iterates `m` times (for each row). Inside the loop, `updateHeight`, `updateLeftBound`, `updateRightBound`, and `findMaxArea` all take O(n) time. Thus, the total time complexity is O(m * n).
- Space: O(n) - We use three auxiliary arrays (`heights`, `leftBound`, `rightBound`), each of size `n`, to store intermediate results.

## Commented Code
```java
class Solution {
    int m,n; // Declare instance variables for number of rows (m) and columns (n)
    int[] heights, leftBound, rightBound; // Declare arrays to store heights, left boundaries, and right boundaries for each column

    public int maximalRectangle(char[][] matrix) {
        // Check for empty matrix to avoid NullPointerException or ArrayIndexOutOfBoundsException
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0; // If the matrix is empty, the maximal rectangle area is 0
        }

        m = matrix.length; // Get the number of rows from the matrix
        n = matrix[0].length; // Get the number of columns from the matrix

        // Initialize the auxiliary arrays. These will be reused for each row.
        heights = new int[n]; // Array to store the height of consecutive '1's ending at the current row for each column
        leftBound = new int[n]; // Array to store the leftmost boundary of a rectangle of '1's ending at the current row for each column
        rightBound = new int[n]; // Array to store the rightmost boundary of a rectangle of '1's ending at the current row for each column

        // Initialize rightBound array. Initially, a '1' can extend to the very right edge of the matrix.
        Arrays.fill(rightBound,n);

        int ans = Integer.MIN_VALUE; // Initialize the maximum area found so far to the smallest possible integer value

        // Iterate through each row of the matrix
        for(int row=0;row<m;row++){
            // Update the heights array based on the current row.
            // If matrix[row][col] is '1', increment the height; otherwise, reset to 0.
            updateHeight(matrix[row]);

            // Update the leftBound array. This finds the start of contiguous '1's for the current row.
            updateLeftBound(matrix[row]);

            // Update the rightBound array. This finds the end of contiguous '1's for the current row.
            updateRightBound(matrix[row]);

            // Calculate the maximum area for the current row's configuration and update the overall maximum area.
            // This step effectively treats the current row as the base of potential rectangles.
            ans = Math.max(ans,findMaxArea());
        }

        return ans; // Return the overall maximum rectangular area found in the matrix
    }

    // Updates the heights array for the current row.
    public void updateHeight(char[] row){
        // Iterate through each column in the current row
        for(int col=0;col<n;col++){
            // If the current cell is '1', increment the height for this column.
            // This means the column of '1's continues upwards.
            if(row[col] == '1') heights[col]++;
            // If the current cell is '0', the sequence of '1's is broken, so reset height to 0.
            else heights[col] = 0;
        }
    }

    // Updates the leftBound array for the current row.
    public void updateLeftBound(char[] row){
        int left = 0; // Initialize a pointer 'left' to track the start of a contiguous sequence of '1's.
        // Iterate through each column from left to right
        for(int col=0;col<n;col++){
            // If the current cell is '1':
            if(row[col] == '1') {
                // The left boundary for this column is the maximum of its current value and the 'left' pointer.
                // This ensures that the left boundary doesn't extend beyond a previous '0' encountered.
                leftBound[col] = Math.max(leftBound[col], left);
            }
            // If the current cell is '0':
            else {
                // The left boundary for this column is 0, as a '0' breaks any contiguous '1' sequence.
                leftBound[col] = 0;
                // Update the 'left' pointer to the next column, as this '0' marks the end of a potential '1' sequence.
                left = col + 1;
            }
        }
    }

    // Updates the rightBound array for the current row.
    public void updateRightBound(char[] row){
        int right = n; // Initialize a pointer 'right' to track the end of a contiguous sequence of '1's. Start from the rightmost boundary.
        // Iterate through each column from right to left
        for(int col=n-1;col>=0;col--){
            // If the current cell is '1':
            if(row[col] == '1') {
                // The right boundary for this column is the minimum of its current value and the 'right' pointer.
                // This ensures that the right boundary doesn't extend beyond a previous '0' encountered from the right.
                rightBound[col] = Math.min(rightBound[col], right);
            }
            // If the current cell is '0':
            else {
                // The right boundary for this column is 'n', as a '0' breaks any contiguous '1' sequence.
                rightBound[col] = n;
                // Update the 'right' pointer to the current column, as this '0' marks the start of a potential '1' sequence from the right.
                right = col;
            }
        }
    }

    // Calculates the maximum area for the current row's histogram configuration.
    public int findMaxArea(){
        int area = 0; // Initialize the maximum area for this row to 0.
        // Iterate through each column
        for(int i=0;i<n;i++){
            // Calculate the width of the rectangle for the current column.
            // The width is determined by the difference between the right and left boundaries.
            int width = rightBound[i] - leftBound[i];
            // Calculate the area of the rectangle with height heights[i] and calculated width.
            // Update the maximum area found for this row if the current area is larger.
            area = Math.max(area, heights[i] * width);
        }
        return area; // Return the maximum area found for this row.
    }

    // Helper function to print an integer array (useful for debugging).
    public void printArr(int[] arr){
        for(int num : arr) System.out.print(num+" "); // Print each number in the array followed by a space.
        System.out.println(); // Print a newline character after the array.
    }
}
```

## Interview Tips
1.  **Explain the Reduction:** Clearly articulate how you're reducing the 2D problem to multiple 1D "Largest Rectangle in Histogram" problems. This is the key insight.
2.  **Walk Through the State Updates:** Explain how `heights`, `leftBound`, and `rightBound` are updated row by row. Emphasize why these updates are correct and how they capture the necessary information.
3.  **Discuss Edge Cases:** Be prepared to discuss what happens with an empty matrix, a matrix with all '0's, or a matrix with all '1's.
4.  **Consider Alternatives:** Briefly mention that the "Largest Rectangle in Histogram" subproblem can be solved using a stack in O(N) time, and how that would integrate here if the provided solution's `findMaxArea` was replaced. (Though the provided solution's `findMaxArea` is also O(N)).

## Revision Checklist
- [ ] Understand the problem statement: Find the largest rectangle of '1's.
- [ ] Recognize the connection to "Largest Rectangle in Histogram".
- [ ] Implement the `heights` update correctly (increment on '1', reset on '0').
- [ ] Implement `leftBound` update: track contiguous '1's from left.
- [ ] Implement `rightBound` update: track contiguous '1's from right.
- [ ] Calculate area using `height * (rightBound - leftBound)`.
- [ ] Iterate through all rows and keep track of the maximum area.
- [ ] Handle edge cases (empty matrix).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Largest Rectangle in Histogram (LeetCode 84)
*   Maximal Square (LeetCode 221)
*   Submatrix Sum Equals K (LeetCode 363) - Similar reduction to 1D.

## Tags
`Array` `Dynamic Programming` `Stack`
