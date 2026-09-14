# Rectangle Overlap

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Math` `Geometry`  
**Time:** O(1)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public boolean isRectangleOverlap(int[] r1, int[] r2) {
        int x1 = r1[0], y1 = r1[1], x2 = r1[2], y2 = r1[3];
        int X1 = r2[0], Y1 = r2[1], X2 = r2[2], Y2 = r2[3];

        return x1<X2 && X1<x2 && y1<Y2 && Y1<y2;

        //1 < 2 of opposite sides
    }

}

//    .
//   .
//  .  
// .   
```

---

---
## Quick Revision
Given two rectangles, determine if they overlap.
Overlap occurs if their horizontal and vertical projections both overlap.

## Intuition
The core idea is to think about when two rectangles *do not* overlap. If we can define the conditions for no overlap, then any other scenario implies overlap. Two rectangles do not overlap if one is entirely to the left of the other, entirely to the right, entirely below, or entirely above. If none of these non-overlapping conditions are met, then they must overlap.

Alternatively, we can directly check for overlap. For overlap to occur, the right edge of one rectangle must be to the right of the left edge of the other, AND the left edge of the first must be to the left of the right edge of the other. This must hold true for both the x-axis and the y-axis.

## Algorithm
1.  Define the coordinates for the first rectangle: `x1, y1, x2, y2` (bottom-left and top-right).
2.  Define the coordinates for the second rectangle: `X1, Y1, X2, Y2` (bottom-left and top-right).
3.  Check for overlap on the x-axis: The interval `[x1, x2]` and `[X1, X2]` overlap if `x1 < X2` AND `X1 < x2`.
4.  Check for overlap on the y-axis: The interval `[y1, y2]` and `[Y1, Y2]` overlap if `y1 < Y2` AND `Y1 < y2`.
5.  The rectangles overlap if and only if both the x-axis and y-axis intervals overlap. Return `(x1 < X2 && X1 < x2) && (y1 < Y2 && Y1 < y2)`.

## Concept to Remember
*   **Interval Overlap:** The fundamental concept is how to determine if two 1D intervals `[a, b]` and `[c, d]` overlap. They overlap if `a < d` and `c < b`.
*   **Geometric Decomposition:** Breaking down a 2D problem (rectangle overlap) into two independent 1D problems (interval overlap on x and y axes).
*   **Coordinate Systems:** Understanding how to represent geometric shapes using coordinate pairs.

## Common Mistakes
*   **Confusing "overlap" with "touching":** The problem typically implies strict overlap (area > 0). If rectangles only touch at an edge or a corner, they might not be considered overlapping depending on the exact problem statement (though this LeetCode problem considers touching as non-overlapping).
*   **Incorrectly defining non-overlap conditions:** It's easy to miss a case or state the conditions for non-overlap incorrectly (e.g., `x1 >= X2` instead of `x1 >= X2` or `x2 <= X1`).
*   **Handling degenerate rectangles:** Rectangles with zero width or height (lines or points) might require special consideration if the problem statement allows them. This problem statement implies non-degenerate rectangles.
*   **Off-by-one errors in comparisons:** Using `<=` or `>=` when `<` or `>` is needed, or vice-versa, leading to incorrect overlap detection.

## Complexity Analysis
*   Time: O(1) - The solution involves a fixed number of comparisons and arithmetic operations, regardless of the input size.
*   Space: O(1) - The solution uses a constant amount of extra space to store variables for coordinates.

## Commented Code
```java
class Solution {
    // This method checks if two rectangles overlap.
    public boolean isRectangleOverlap(int[] r1, int[] r2) {
        // Extract coordinates for the first rectangle: [x1, y1, x2, y2]
        // x1: bottom-left x, y1: bottom-left y, x2: top-right x, y2: top-right y
        int x1 = r1[0]; // Left edge of rectangle 1
        int y1 = r1[1]; // Bottom edge of rectangle 1
        int x2 = r1[2]; // Right edge of rectangle 1
        int y2 = r1[3]; // Top edge of rectangle 1

        // Extract coordinates for the second rectangle: [X1, Y1, X2, Y2]
        // X1: bottom-left x, Y1: bottom-left y, X2: top-right x, Y2: top-right y
        int X1 = r2[0]; // Left edge of rectangle 2
        int Y1 = r2[1]; // Bottom edge of rectangle 2
        int X2 = r2[2]; // Right edge of rectangle 2
        int Y2 = r2[3]; // Top edge of rectangle 2

        // Check for overlap on the x-axis.
        // For overlap, the left edge of one must be to the left of the right edge of the other,
        // AND the left edge of the other must be to the left of the right edge of the first.
        // This ensures their horizontal projections intersect.
        boolean xOverlap = x1 < X2 && X1 < x2;

        // Check for overlap on the y-axis.
        // Similar logic as x-axis: the bottom edge of one must be below the top edge of the other,
        // AND the bottom edge of the other must be below the top edge of the first.
        // This ensures their vertical projections intersect.
        boolean yOverlap = y1 < Y2 && Y1 < y2;

        // Rectangles overlap if and only if both their x-axis and y-axis projections overlap.
        return xOverlap && yOverlap;

        // The comment "//1 < 2 of opposite sides" is a concise way to remember the condition:
        // The left edge of rect1 (x1) must be less than the right edge of rect2 (X2), AND
        // the left edge of rect2 (X1) must be less than the right edge of rect1 (x2).
        // This logic is applied similarly for the y-coordinates.
    }
}
```

## Interview Tips
*   **Explain the "no overlap" logic first:** It's often easier to explain why rectangles *don't* overlap (one is entirely left, right, above, or below the other). Then, state that overlap is the negation of all these non-overlap conditions.
*   **Visualize the intervals:** Draw two intervals on a number line to explain the `a < d && c < b` condition for overlap. Then extend this to 2D.
*   **Clarify edge cases:** Ask the interviewer if rectangles with zero width or height are possible and how they should be handled. Also, clarify if touching edges/corners count as overlap.
*   **Consider the alternative approach:** Briefly mention the "no overlap" approach as a way to confirm your understanding and show you've thought about different perspectives.

## Revision Checklist
- [ ] Understand the input format: `[x1, y1, x2, y2]` representing bottom-left and top-right corners.
- [ ] Define overlap conditions for 1D intervals.
- [ ] Apply 1D overlap logic to both x and y dimensions.
- [ ] Combine x and y overlap conditions using logical AND.
- [ ] Consider the case where rectangles might only touch (and if that counts as overlap).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Line Reflection
*   Maximal Rectangle
*   Rectangle Area

## Tags
`Array` `Math`

## My Notes
1 < 2 of opposite sides
