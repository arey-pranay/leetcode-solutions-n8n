# Subrectangle Queries

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Design` `Matrix`  
**Time:** O(m*n)  
**Space:** O(1)

---

## Solution (java)

```java
class SubrectangleQueries {
    int[][] rectangle; 
    public SubrectangleQueries(int[][] rectangle) {
        this.rectangle = rectangle;
    }
    
    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        for(int i =row1;i<=row2;i++) for(int j =col1;j<=col2;j++) rectangle[i][j] = newValue;
    }  
    public int getValue(int row, int col) {
        return rectangle[row][col];
    }
}
// 4x3 
// 0,0 - 100,100

// 5 1 5
// 4 3 4
// 3 2 1
// 1 1 1

// 5 5 5
// 5 5 5
// 5 5 5
// 10 10 10
```

---

---

## Quick Revision
The problem is about implementing a class that allows querying and updating sub-rectangles of a given 2D array. We solve it by using nested loops to update the sub-rectangle and direct array access to query its value.

## Intuition
This approach works because we can treat the 2D array as a flat table, where each element represents a cell in the rectangle. By updating all cells within the specified sub-rectangle boundaries, we effectively modify the entire rectangle's values at once. When querying a specific cell, we directly access its value using the given row and column indices.

## Algorithm
1. Initialize an instance variable `rectangle` to store the input 2D array.
2. In the constructor, assign the input `rectangle` to the instance variable.
3. In `updateSubrectangle`, iterate through each cell in the specified sub-rectangle (rows `row1` to `row2` and columns `col1` to `col2`) and update its value to `newValue`.
4. In `getValue`, directly return the value of the cell at row `row` and column `col`.

## Concept to Remember
* **Matrix representation**: Treating a 2D array as a flat table.
* **Range updates**: Updating values within a specified range or interval.
* **Direct array access**: Accessing elements in an array using their indices.

## Common Mistakes
* Forgetting to update the entire sub-rectangle, leading to incorrect results.
* Using incorrect bounds when updating or querying the rectangle (e.g., accessing outside the bounds).
* Not initializing the instance variable properly, causing unexpected behavior.

## Complexity Analysis
- Time: O(m*n) - where m is `row2 - row1 + 1` and n is `col2 - col1 + 1`, because we're iterating through all cells in the sub-rectangle.
- Space: O(1) - our space complexity is constant, as we're only using a few instance variables.

## Commented Code
```java
class SubrectangleQueries {
    int[][] rectangle; // Store the input 2D array

    public SubrectangleQueries(int[][] rectangle) {
        this.rectangle = rectangle; // Assign input to instance variable
    }

    /**
     * Updates all cells in the specified sub-rectangle with newValue.
     */
    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        for (int i = row1; i <= row2; i++) { // Iterate through each cell in sub-rect
            for (int j = col1; j <= col2; j++) {
                rectangle[i][j] = newValue; // Update value at current position
            }
        }
    }

    /**
     * Returns the value of the cell at row and column.
     */
    public int getValue(int row, int col) {
        return rectangle[row][col]; // Directly access value using indices
    }
}
```

## Interview Tips
* Be sure to understand the problem requirements clearly before diving into implementation details.
* Use a systematic approach when updating sub-rectangles to avoid mistakes.
* Pay attention to edge cases, such as invalid input ranges or indices.

## Revision Checklist
- [ ] Understand matrix representation and range updates.
- [ ] Implement direct array access correctly.
- [ ] Ensure correct bounds when updating or querying the rectangle.
- [ ] Review time and space complexity analysis.

## Similar Problems
* LeetCode: 1684. Count the Number of Consistent Strings
* LeetCode: 1380. Kth Largest Sum of a Pair
* LeetCode: 1629. Maximize the Confidence Value

## Tags
`Array` `Hash Map`
