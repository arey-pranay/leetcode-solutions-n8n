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
Overlap occurs if the projection of the rectangles onto both the x-axis and y-axis overlap.

## Intuition
The core idea is to think about when two rectangles *do not* overlap. If we can define the conditions for no overlap, then any other case must be an overlap. Two rectangles do not overlap if one is entirely to the left of the other, entirely to the right, entirely below, or entirely above. If none of these "no overlap" conditions are met, then they must overlap.

Alternatively, we can directly check for overlap. For overlap to occur, the horizontal segments of the rectangles must overlap, AND the vertical segments must overlap.

## Algorithm
1.  Represent the first rectangle `r1` with its bottom-left corner `(x1, y1)` and top-right corner `(x2, y2)`.
2.  Represent the second rectangle `r2` with its bottom-left corner `(X1, Y1)` and top-right corner `(X2, Y2)`.
3.  Check for overlap on the x-axis: The left edge of `r1` must be to the left of the right edge of `r2` (`x1 < X2`), AND the left edge of `r2` must be to the left of the right edge of `r1` (`X1 < x2`).
4.  Check for overlap on the y-axis: The bottom edge of `r1` must be below the top edge of `r2` (`y1 < Y2`), AND the bottom edge of `r2` must be below the top edge of `r1` (`Y1 < y2`).
5.  If both x-axis overlap and y-axis overlap conditions are true, the rectangles overlap. Otherwise, they do not.

## Concept to Remember
*   **Geometric Intersection:** Understanding how to define and check for overlap between geometric shapes.
*   **Axis Projection:** The problem can be simplified by considering the overlap of the rectangles' projections onto the x and y axes independently.
*   **Complementary Conditions:** Sometimes it's easier to define the conditions for *no* overlap and negate them to find the conditions for overlap.

## Common Mistakes
*   **Confusing coordinates:** Mixing up `x1, y1, x2, y2` with `X1, Y1, X2, Y2` or misinterpreting which coordinate represents which edge.
*   **Edge cases with lines/points:** Not considering that rectangles are defined by `x1 < x2` and `y1 < y2`. If `x1 == x2` or `y1 == y2`, it's a line or point, not a rectangle, and thus cannot overlap in a meaningful area. The problem statement implies non-degenerate rectangles.
*   **Only checking one axis:** Forgetting that overlap requires intersection on *both* the x and y axes.
*   **Incorrectly defining non-overlap:** Errors in formulating the conditions for when rectangles are completely separate.

## Complexity Analysis
*   Time: O(1) - The solution involves a fixed number of comparisons and arithmetic operations, regardless of the input size.
*   Space: O(1) - No additional data structures are used that grow with the input size. Only a few variables are used to store coordinates.

## Commented Code
```java
class Solution {
    public boolean isRectangleOverlap(int[] r1, int[] r2) {
        // Extract coordinates for the first rectangle (r1)
        int x1 = r1[0]; // bottom-left x-coordinate of r1
        int y1 = r1[1]; // bottom-left y-coordinate of r1
        int x2 = r1[2]; // top-right x-coordinate of r1
        int y2 = r1[3]; // top-right y-coordinate of r1

        // Extract coordinates for the second rectangle (r2)
        int X1 = r2[0]; // bottom-left x-coordinate of r2
        int Y1 = r2[1]; // bottom-left y-coordinate of r2
        int X2 = r2[2]; // top-right x-coordinate of r2
        int Y2 = r2[3]; // top-right y-coordinate of r2

        // Check for overlap on the x-axis:
        // The left edge of r1 must be to the left of the right edge of r2 (x1 < X2)
        // AND the left edge of r2 must be to the left of the right edge of r1 (X1 < x2)
        boolean xOverlap = x1 < X2 && X1 < x2;

        // Check for overlap on the y-axis:
        // The bottom edge of r1 must be below the top edge of r2 (y1 < Y2)
        // AND the bottom edge of r2 must be below the top edge of r1 (Y1 < y2)
        boolean yOverlap = y1 < Y2 && Y1 < y2;

        // Rectangles overlap if and only if they overlap on both axes
        return xOverlap && yOverlap;
    }
}
```

## Interview Tips
*   **Clarify Input:** Ask about the format of the input arrays and what each index represents (e.g., `[x1, y1, x2, y2]`). Confirm if rectangles can be lines or points (degenerate cases).
*   **Explain the Logic:** Clearly articulate the conditions for overlap, perhaps by first explaining the conditions for *no* overlap (one rectangle is entirely to the left, right, above, or below the other).
*   **Draw it Out:** If you get stuck, sketch two rectangles on a whiteboard or paper to visualize the overlap and non-overlap scenarios. This can help solidify the coordinate comparisons.
*   **Consider Edge Cases:** Briefly mention how degenerate rectangles (lines or points) would be handled if they were allowed, and how the current solution implicitly handles them by requiring strict inequalities.

## Revision Checklist
- [ ] Understand the problem statement: determine if two rectangles overlap.
- [ ] Recognize that overlap requires intersection on both x and y axes.
- [ ] Formulate the conditions for x-axis overlap: `r1.x1 < r2.x2` AND `r2.x1 < r1.x2`.
- [ ] Formulate the conditions for y-axis overlap: `r1.y1 < r2.y2` AND `r2.y1 < r1.y2`.
- [ ] Combine these conditions with a logical AND.
- [ ] Consider the case where rectangles might be lines or points (degenerate).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Rectangle Area
*   Largest Rectangle in Histogram
*   Maximal Rectangle

## Tags
`Array` `Math`
