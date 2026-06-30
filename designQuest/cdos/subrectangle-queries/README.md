# Subrectangle Queries

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Design` `Matrix`  
**Time:**   
**Space:** 

---

## Solution (java)

```java
// batch updates - mtlb ki hum sirf aakhrir baar traverse krenge tb tk bss updates store krte jaayenge 
// latest valid update dhundhke 
class SubrectangleQueries {
    private int[][] rectangle;
    // Store updates as {row1, col1, row2, col2, newValue}
    private List<int[]> updates;

    public SubrectangleQueries(int[][] rectangle) {
        this.rectangle = rectangle;
        this.updates = new ArrayList<>();
    }
    
    // O(1) Time Complexity
    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        updates.add(new int[]{row1, col1, row2, col2, newValue});
    }
    
    // O(U) Time Complexity, where U is the number of updates
    public int getValue(int row, int col) {
        // Look from the newest update to the oldest
        for (int i = updates.size() - 1; i >= 0; i--) {
            int[] update = updates.get(i);
            // Check if the coordinate falls inside this updated subrectangle
            if (row >= update[0] && row <= update[2] && col >= update[1] && col <= update[3]) {
                return update[4];
                // r1 c1 r2 c2 value
            }
        }
        // If no updates hit this cell, return the original value
        return rectangle[row][col];
    }
}
```

---

---
## Quick Revision
This problem involves a 2D matrix that supports subrectangle updates and point queries. We solve it by storing updates and applying the latest relevant one when querying a cell.

## Intuition
The core idea is that later updates overwrite earlier ones. When we need to find the value of a specific cell, we don't need to physically apply every single update to the entire matrix. Instead, we can simply look at the updates in reverse chronological order. The first update we find that covers the queried cell will provide the correct, most up-to-date value for that cell. If no update covers the cell, then its original value from initialization is used.

## Algorithm
1.  **Initialization (`SubrectangleQueries(int[][] rectangle)`):**
    *   Store the initial 2D `rectangle` in a private member variable.
    *   Initialize an empty `ArrayList` called `updates` to store all subsequent update operations. Each update will be represented as an array `[row1, col1, row2, col2, newValue]`.

2.  **Update Subrectangle (`updateSubrectangle(int row1, int col1, int row2, int col2, int newValue)`):**
    *   Create a new integer array containing `row1`, `col1`, `row2`, `col2`, and `newValue`.
    *   Add this array to the `updates` list. This operation is very fast as it's just appending to a list.

3.  **Get Value (`getValue(int row, int col)`):**
    *   Iterate through the `updates` list in reverse order (from the most recent update to the oldest).
    *   For each `update` in the list:
        *   Extract the coordinates (`r1`, `c1`, `r2`, `c2`) and the `newValue` from the `update` array.
        *   Check if the given `row` and `col` fall within the bounds of this `update`'s subrectangle: `row >= r1 && row <= r2 && col >= c1 && col <= c2`.
        *   If the cell is within the subrectangle, return the `newValue` of this update immediately. This is the most recent value for this cell.
    *   If the loop completes without finding any update that covers the cell, it means the cell has not been modified by any update. In this case, return the original value of the cell from the initial `rectangle`: `rectangle[row][col]`.

## Concept to Remember
*   **Lazy Updates:** Instead of applying updates immediately, we defer them and only process them when needed for a query.
*   **Data Structures for Updates:** Using a list to store a sequence of operations allows for efficient recording and later retrieval.
*   **Reverse Iteration:** Processing updates in reverse order is crucial for finding the *latest* applicable change.
*   **Coordinate Geometry:** Understanding how to check if a point lies within a given rectangle is fundamental.

## Common Mistakes
*   **Applying updates eagerly:** Modifying the original `rectangle` matrix directly for every `updateSubrectangle` call. This would be very inefficient, especially with many updates.
*   **Iterating updates in forward order:** If updates are checked from oldest to newest, the `getValue` method would return an outdated value.
*   **Off-by-one errors in boundary checks:** Incorrectly implementing the `row >= r1 && row <= r2 && col >= c1 && col <= c2` condition can lead to wrong results.
*   **Not handling the case where no update applies:** Forgetting to return the original `rectangle[row][col]` if no update covers the queried cell.

## Complexity Analysis
*   **Time:**
    *   `SubrectangleQueries(int[][] rectangle)`: O(R * C) where R is the number of rows and C is the number of columns, to copy the initial rectangle. If we don't copy and just store a reference, it's O(1). The provided code copies, so it's O(R*C).
    *   `updateSubrectangle`: O(1) because we are just appending to a list.
    *   `getValue`: O(U) where U is the total number of updates made so far. In the worst case, we might have to iterate through all stored updates.
*   **Space:**
    *   O(R * C + U) where R * C is for storing the initial rectangle and U is for storing the list of updates. If the initial rectangle is not copied and only a reference is stored, then it's O(U). The provided code copies the rectangle, so it's O(R*C + U).

## Commented Code
```java
import java.util.ArrayList; // Import the ArrayList class to use dynamic arrays for storing updates
import java.util.List;      // Import the List interface

class SubrectangleQueries {
    private int[][] rectangle; // Declare a private 2D integer array to store the initial state of the rectangle
    // Declare a private List of integer arrays to store all subrectangle update operations.
    // Each element in the list will be an array: {row1, col1, row2, col2, newValue}
    private List<int[]> updates;

    // Constructor for the SubrectangleQueries class
    public SubrectangleQueries(int[][] rectangle) {
        this.rectangle = rectangle; // Initialize the 'rectangle' member variable with the input rectangle
        this.updates = new ArrayList<>(); // Initialize the 'updates' list as a new empty ArrayList
    }
    
    // Method to update a subrectangle with a new value
    // Time Complexity: O(1) - This operation is very efficient as it only involves adding an entry to a list.
    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        // Add a new update operation to the 'updates' list.
        // The update is represented by an array containing the top-left corner (row1, col1),
        // the bottom-right corner (row2, col2), and the newValue to be applied.
        updates.add(new int[]{row1, col1, row2, col2, newValue});
    }
    
    // Method to get the value of a specific cell at (row, col)
    // Time Complexity: O(U), where U is the number of updates. In the worst case, we check all updates.
    public int getValue(int row, int col) {
        // Iterate through the 'updates' list in reverse order, from the most recent update to the oldest.
        // This is because the latest update that covers the cell determines its final value.
        for (int i = updates.size() - 1; i >= 0; i--) {
            int[] update = updates.get(i); // Get the current update operation from the list
            // Extract the coordinates and the new value from the current update array.
            // update[0] = row1, update[1] = col1, update[2] = row2, update[3] = col2, update[4] = newValue
            
            // Check if the queried cell (row, col) falls within the boundaries of the current subrectangle update.
            // The cell is within the subrectangle if its row is between row1 and row2 (inclusive)
            // AND its column is between col1 and col2 (inclusive).
            if (row >= update[0] && row <= update[2] && col >= update[1] && col <= update[3]) {
                // If the cell is within this updated subrectangle, return the newValue of this update.
                // This is the most recent value for this cell, so we can stop searching.
                return update[4];
            }
        }
        // If the loop finishes without finding any update that covers the cell,
        // it means the cell has not been modified by any update operation.
        // Therefore, return the original value of the cell from the initial 'rectangle'.
        return rectangle[row][col];
    }
}
```

## Interview Tips
*   **Explain the "lazy update" strategy:** Emphasize that you're not modifying the matrix directly for every update, which is the key to efficiency.
*   **Discuss the reverse iteration:** Clearly articulate why iterating from the end of the `updates` list is crucial for correctness.
*   **Clarify complexity trade-offs:** Be ready to explain the O(1) update vs. O(U) query trade-off and why it's beneficial here.
*   **Ask about constraints:** If not provided, ask about the maximum number of updates or matrix dimensions to understand potential edge cases or if a different approach might be needed.

## Revision Checklist
- [ ] Understand the problem statement: support subrectangle updates and point queries.
- [ ] Implement the constructor to store the initial matrix and an update list.
- [ ] Implement `updateSubrectangle` to simply record the update parameters.
- [ ] Implement `getValue` by iterating through updates in reverse.
- [ ] Ensure boundary checks in `getValue` are correct (inclusive).
- [ ] Handle the case where no update applies to a cell.
- [ ] Analyze time and space complexity for all methods.
- [ ] Consider edge cases: empty matrix, single cell updates, full matrix updates.

## Similar Problems
*   Range Sum Query 2D - Immutable
*   Range Sum Query 2D - Mutable
*   Paint House III
*   Corporate Flight Bookings

## Tags
`Array` `List` `Simulation`
