# Maximum Building Height

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Math` `Sorting`  
**Time:** O(N log N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        Arrays.sort(restrictions, (a, b) -> a[0] - b[0]);
        int m = restrictions.length;
        if(m==0) return n-1;
        //left to right norm
        int prevPos = 1;
        int prevH = 0;
        for (int i=0; i<m; i++) {
            int dist = restrictions[i][0] - prevPos;
            restrictions[i][1] = Math.min(restrictions[i][1], prevH + dist);
            prevPos = restrictions[i][0];
            prevH = restrictions[i][1];
        }
        //right to left norm
        prevPos = n;
        prevH = n - 1;
        for (int i = m - 1; i >= 0; i--) {
            int dist = prevPos - restrictions[i][0];
            restrictions[i][1] = Math.min(restrictions[i][1], prevH + dist);
            prevPos = restrictions[i][0];
            prevH = restrictions[i][1];
        }
        //max distance between every pair of restrictions
        int leftPos = 1;
        int leftH = 0;
        int ans = 0;
        for (int[] temp : restrictions) {
            int rightPos = temp[0];
            int rightH = temp[1];

            int d = rightPos - leftPos;
            ans = Math.max(ans, (leftH + rightH + d) / 2);

            leftPos = rightPos;
            leftH = rightH;
        }
        int d = n-leftPos;
        int rightH = leftH+d;
        return Math.max(ans, rightH);
    }
}
```

---

---
## Quick Revision
The problem asks for the maximum possible height of the tallest building in a city of `n` buildings, given some restrictions on building heights at specific positions.
We solve this by first normalizing the restrictions to find the maximum possible height at each restricted position, considering constraints from both left and right. Then, we find the maximum height achievable between consecutive normalized restrictions.

## Intuition
The core idea is that the height difference between any two buildings cannot exceed the distance between them. If we have two buildings at positions `p1` and `p2` with heights `h1` and `h2` respectively, then `abs(h1 - h2) <= abs(p1 - p2)`. This implies `h2 <= h1 + abs(p1 - p2)` and `h1 <= h2 + abs(p1 - p2)`.

The restrictions given are *upper bounds*. However, a restriction at `(pos, h)` means the building at `pos` can be *at most* `h`. This restriction can propagate. If a building at `pos1` has a maximum height `h1`, then a building at `pos2` (where `pos2 > pos1`) can have a maximum height of `h1 + (pos2 - pos1)`. Similarly, if `pos2 < pos1`, `h2` can be at most `h1 + (pos1 - pos2)`.

The problem can be broken down into finding the maximum possible height at each restricted building, and then finding the maximum height between these restricted buildings.

The normalization step is crucial. For a restriction `(pos, h)`, its actual maximum possible height is limited by:
1. Its own given height `h`.
2. The maximum height of the building to its left plus the distance.
3. The maximum height of the building to its right plus the distance.

After normalizing, we have a set of effective maximum heights at specific positions. The maximum height between two consecutive normalized restrictions `(p1, h1)` and `(p2, h2)` (where `p1 < p2`) can be visualized as a triangle. The peak of this triangle will be at `(p1 + (p2 - p1 + h2 - h1) / 2)`. The height at this peak will be `h1 + (p2 - p1 + h2 - h1) / 2`. This simplifies to `(h1 + h2 + p2 - p1) / 2`. This is the average of the heights plus half the distance.

We also need to consider the space before the first restriction and after the last restriction.

## Algorithm
1. **Sort Restrictions:** Sort the `restrictions` array based on building position (`restrictions[i][0]`). This is essential for processing constraints from left to right and right to left.
2. **Handle Empty Restrictions:** If there are no restrictions, the maximum height is `n-1` (building at position `n-1` can be `n-1` height, assuming 0-indexed positions and 0 height for building 0).
3. **Left-to-Right Normalization:**
    * Initialize `prevPos = 1` and `prevH = 0` (representing the ground floor at position 1).
    * Iterate through the sorted restrictions from left to right.
    * For each restriction `(currentPos, currentH)`:
        * Calculate the maximum possible height at `currentPos` considering the `prevH` at `prevPos`: `currentH = min(currentH, prevH + (currentPos - prevPos))`.
        * Update `prevPos = currentPos` and `prevH = currentH`.
4. **Right-to-Left Normalization:**
    * Initialize `prevPos = n` and `prevH = n - 1` (representing the maximum possible height at the last building).
    * Iterate through the normalized restrictions from right to left.
    * For each restriction `(currentPos, currentH)`:
        * Calculate the maximum possible height at `currentPos` considering the `prevH` at `prevPos`: `currentH = min(currentH, prevH + (prevPos - currentPos))`.
        * Update `prevPos = currentPos` and `prevH = currentH`.
5. **Calculate Maximum Height Between Restrictions:**
    * Initialize `leftPos = 1`, `leftH = 0`, and `ans = 0`.
    * Iterate through the now fully normalized restrictions.
    * For each restriction `(rightPos, rightH)`:
        * Calculate the maximum height achievable between `(leftPos, leftH)` and `(rightPos, rightH)`. The peak height is `(leftH + rightH + (rightPos - leftPos)) / 2`.
        * Update `ans = max(ans, (leftH + rightH + (rightPos - leftPos)) / 2)`.
        * Update `leftPos = rightPos` and `leftH = rightH`.
6. **Calculate Maximum Height After Last Restriction:**
    * Calculate the maximum height achievable from the last normalized restriction `(leftPos, leftH)` to the end of the city (`n`). The maximum height at `n` would be `leftH + (n - leftPos)`.
    * Update `ans = max(ans, leftH + (n - leftPos))`.
7. **Return `ans`**.

## Concept to Remember
*   **Greedy Approach:** At each step, we make a locally optimal choice (normalizing heights and then finding the max between points) that leads to a globally optimal solution.
*   **Constraint Propagation:** Restrictions on building heights can influence the maximum possible heights of adjacent buildings.
*   **Geometric Interpretation:** The problem can be visualized as finding the highest point within a series of trapezoids or triangles formed by the normalized restrictions.
*   **Two-Pass Processing:** Using two passes (left-to-right and right-to-left) is a common technique to handle constraints that propagate from both directions.

## Common Mistakes
*   **Not Sorting Restrictions:** Failing to sort the restrictions by position will lead to incorrect normalization.
*   **Incorrect Base Cases:** Not handling the case of no restrictions or incorrectly setting initial `prevH` and `prevPos` for normalization.
*   **Integer Division:** Mishandling the calculation of the peak height between two points, especially with integer division. The formula `(h1 + h2 + dist) / 2` correctly handles this for the peak.
*   **Forgetting the Last Segment:** Not calculating the maximum height achievable from the last restriction to the end of the city (`n`).
*   **Off-by-One Errors:** Miscalculating distances or positions, especially when dealing with 1-based vs. 0-based indexing implicitly.

## Complexity Analysis
- Time: O(N log N) - due to sorting the `restrictions` array. The subsequent passes are O(N) where N is the number of restrictions.
- Space: O(1) - if we modify the input array in-place. If a copy is made, it would be O(N).

## Commented Code
```java
class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        // Sort restrictions by building position to process them in order.
        Arrays.sort(restrictions, (a, b) -> a[0] - b[0]);

        // Get the number of restrictions.
        int m = restrictions.length;

        // If there are no restrictions, the tallest building can be at position n-1 with height n-1.
        // Assuming building 0 has height 0.
        if(m==0) return n-1;

        // --- Left-to-Right Normalization ---
        // Initialize previous position and height. Building at position 1 can have height 0.
        int prevPos = 1;
        int prevH = 0;
        // Iterate through restrictions from left to right.
        for (int i = 0; i < m; i++) {
            // Calculate the maximum allowed height at the current restriction's position based on the previous restriction.
            // The height cannot exceed the previous height plus the distance between positions.
            int dist = restrictions[i][0] - prevPos;
            restrictions[i][1] = Math.min(restrictions[i][1], prevH + dist);

            // Update previous position and height for the next iteration.
            prevPos = restrictions[i][0];
            prevH = restrictions[i][1];
        }

        // --- Right-to-Left Normalization ---
        // Initialize previous position and height for right-to-left pass.
        // The building at position n can have height n-1.
        prevPos = n;
        prevH = n - 1;
        // Iterate through restrictions from right to left.
        for (int i = m - 1; i >= 0; i--) {
            // Calculate the maximum allowed height at the current restriction's position based on the restriction to its right.
            // The height cannot exceed the height to the right plus the distance between positions.
            int dist = prevPos - restrictions[i][0];
            restrictions[i][1] = Math.min(restrictions[i][1], prevH + dist);

            // Update previous position and height for the next iteration.
            prevPos = restrictions[i][0];
            prevH = restrictions[i][1];
        }

        // --- Calculate Maximum Height Between Restrictions ---
        // Initialize the starting point for calculating segments. Building at position 1 has height 0.
        int leftPos = 1;
        int leftH = 0;
        // Initialize the maximum building height found so far.
        int ans = 0;
        // Iterate through the normalized restrictions.
        for (int[] temp : restrictions) {
            // Current restriction's position and normalized height.
            int rightPos = temp[0];
            int rightH = temp[1];

            // Calculate the distance between the current segment's left and right boundaries.
            int d = rightPos - leftPos;
            // The maximum height in this segment forms a triangle. The peak height is the average of the two boundary heights plus half the distance.
            // This formula (leftH + rightH + d) / 2 correctly calculates the peak height.
            ans = Math.max(ans, (leftH + rightH + d) / 2);

            // Update the left boundary for the next segment.
            leftPos = rightPos;
            leftH = rightH;
        }

        // --- Calculate Maximum Height After Last Restriction ---
        // Calculate the maximum height achievable from the last restriction to the end of the city (position n).
        // The height at position n would be the height of the last restriction plus the distance to n.
        int d = n - leftPos;
        int rightH = leftH + d; // This is the maximum possible height at position n.
        // Update the overall maximum height if this last segment yields a greater height.
        return Math.max(ans, rightH);
    }
}
```

## Interview Tips
*   **Explain the Constraint:** Clearly articulate the `abs(h1 - h2) <= abs(p1 - p2)` constraint and how it implies `h_i <= h_j + abs(p_i - p_j)`.
*   **Visualize Normalization:** Use a whiteboard or drawing to explain how the left-to-right and right-to-left passes "tighten" the maximum possible height at each restricted position.
*   **Derive the Peak Formula:** Walk through the derivation of `(h1 + h2 + dist) / 2` for the maximum height between two points. Explain it as the peak of an isosceles triangle or a trapezoid.
*   **Edge Cases:** Be prepared to discuss edge cases like no restrictions, only one restriction, or restrictions at the boundaries (1 and `n`).

## Revision Checklist
- [ ] Understand the core constraint: height difference <= position difference.
- [ ] Implement sorting of restrictions.
- [ ] Implement left-to-right normalization.
- [ ] Implement right-to-left normalization.
- [ ] Implement calculation of max height between normalized restrictions.
- [ ] Implement calculation of max height after the last restriction.
- [ ] Handle the edge case of no restrictions.
- [ ] Verify integer division logic for peak height calculation.

## Similar Problems
*   1846. Maximum Element After Decreasing and Rearranging
*   1642. Furthest Building You Can Reach
*   1768. Merge Strings Alternatively (different concept, but involves processing two sequences)

## Tags
`Array` `Sorting` `Greedy` `Two Pointers`
