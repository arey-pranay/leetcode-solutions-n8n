# Subrectangle Queries

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Design` `Matrix`  
**Time:** O(1)  
**Space:** O(N \* M + U)

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
The problem is to design a class that can update and query values in a 2D matrix (subrectangle) with batch operations.
This solution uses a combination of arrays and dynamic updates to achieve O(1) or O(U) time complexity.

## Intuition
The key insight here is to use the concept of "latest valid update" to efficiently store and retrieve subrectangle values. By storing updates in reverse order, we can quickly determine the value for any cell by iterating through the latest updates that affect it.

## Algorithm
1. Initialize a 2D array `rectangle` and an empty list `updates`.
2. In the constructor, set up the initial state of the class with the given rectangle.
3. The `updateSubrectangle` method adds an update to the end of the `updates` list with its values.
4. The `getValue` method iterates through the `updates` list in reverse order, returning the value for a cell as soon as it finds an update that affects it.

## Concept to Remember
* **Batch updates**: storing multiple operations at once to reduce time complexity
* **Latest valid update**: finding the most recent update that affects a cell's value
* **Dynamic updates**: efficiently updating values in a data structure with batch operations

## Common Mistakes
* Failing to iterate through updates in reverse order, leading to incorrect results or excessive computation.
* Not properly storing and retrieving subrectangle values, resulting in incorrect answers.

## Complexity Analysis
- Time: O(1) for `updateSubrectangle` / O(U) for `getValue`
- Space: O(N \* M + U), where N is the number of rows, M is the number of columns, and U is the number of updates

## Commented Code
```java
class SubrectangleQueries {
    private int[][] rectangle; // Initial 2D array representing the subrectangle
    private List<int[]> updates; // List to store updates in reverse order

    public SubrectangleQueries(int[][] rectangle) {
        this.rectangle = rectangle;
        this.updates = new ArrayList<>();
    }

    // O(1) Time Complexity: Adding an update to the end of the list
    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        updates.add(new int[]{row1, col1, row2, col2, newValue});
    }

    // O(U) Time Complexity: Iterating through latest updates that affect a cell
    public int getValue(int row, int col) {
        for (int i = updates.size() - 1; i >= 0; i--) {
            int[] update = updates.get(i);
            if (row >= update[0] && row <= update[2] && col >= update[1] && col <= update[3]) {
                return update[4]; // Return the value from the latest valid update
            }
        }
        return rectangle[row][col]; // If no updates affect this cell, return original value
    }
}
```

## Interview Tips
* Practice explaining batch operations and dynamic updates.
* Review how to implement efficient iteration through a list in reverse order.
* Be prepared to discuss trade-offs between time and space complexity.

## Revision Checklist
- [ ] Understand the concept of "latest valid update" for efficient subrectangle queries
- [ ] Review implementing batch updates with dynamic updates
- [ ] Practice explaining the solution's key insights and design decisions

## Similar Problems
* LeetCode: 560. Subarray Sum Equals K (similar use of prefix sums and dynamic updates)
* LeetCode: 1221. Split a String in Balanced Strings (utilizes similar concepts of batch operations and dynamic updates)
