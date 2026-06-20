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
The problem asks for the maximum possible height of the tallest building in a city of `n` buildings, given some height restrictions at specific building positions.
We solve this by normalizing the restrictions and then finding the maximum height achievable between consecutive normalized restrictions.

## Intuition
The core idea is that the height difference between any two buildings `i` and `j` cannot exceed their distance `abs(i - j)`. This is because each building can only increase its height by at most 1 compared to its adjacent building. If we have restrictions, these restrictions impose upper bounds on building heights.

The "aha moment" comes from realizing that we can "normalize" the restrictions. For any two adjacent restrictions `(pos1, h1)` and `(pos2, h2)`, the maximum height at `pos2` is limited by `h1 + (pos2 - pos1)`. Similarly, the maximum height at `pos1` is limited by `h2 + (pos2 - pos1)`. By iterating from left to right and then right to left, we ensure that each restriction's height is the minimum of its original height and the height imposed by its neighbors. After this normalization, the problem reduces to finding the maximum height between any two consecutive normalized restrictions (including the implicit restrictions at building 1 with height 0 and building `n` with height `n-1`). The maximum height between two points `(p1, h1)` and `(p2, h2)` occurs at the midpoint, and its value is `(h1 + h2 + (p2 - p1)) / 2`.

## Algorithm
1. **Sort Restrictions:** Sort the `restrictions` array based on the building position (`restrictions[i][0]`) in ascending order. This is crucial for processing them sequentially.
2. **Handle Empty Restrictions:** If there are no restrictions, the maximum building height is simply `n-1` (building `n` can be height `n-1`).
3. **Left-to-Right Normalization:**
    * Initialize `prevPos = 1` and `prevH = 0` (representing the implicit restriction at building 1).
    * Iterate through the sorted `restrictions`:
        * Calculate the maximum allowed height at the current restriction's position based on the previous restriction: `min(currentH, prevH + (currentPos - prevPos))`. Update the current restriction's height with this minimum.
        * Update `prevPos` and `prevH` to the current restriction's position and normalized height.
4. **Right-to-Left Normalization:**
    * Initialize `prevPos = n` and `prevH = n - 1` (representing the implicit restriction at building `n`).
    * Iterate through the `restrictions` array in reverse order:
        * Calculate the maximum allowed height at the current restriction's position based on the next restriction (from the right): `min(currentH, prevH + (prevPos - currentPos))`. Update the current restriction's height with this minimum.
        * Update `prevPos` and `prevH` to the current restriction's position and normalized height.
5. **Calculate Maximum Height Between Restrictions:**
    * Initialize `leftPos = 1`, `leftH = 0`, and `ans = 0`.
    * Iterate through the normalized `restrictions`:
        * For each restriction `(rightPos, rightH)`:
            * Calculate the maximum height achievable between `(leftPos, leftH)` and `(rightPos, rightH)`. This occurs at the midpoint and is `(leftH + rightH + (rightPos - leftPos)) / 2`.
            * Update `ans` with the maximum of `ans` and this calculated height.
            * Update `leftPos` and `leftH` to `rightPos` and `rightH` for the next iteration.
6. **Calculate Maximum Height for the Last Segment:**
    * After the loop, consider the segment from the last restriction to building `n`.
    * The maximum height in this segment is `leftH + (n - leftPos)`.
    * Update `ans` with the maximum of `ans` and this height.
7. **Return `ans`:** This is the overall maximum building height.

## Concept to Remember
*   **Greedy Approach:** At each step, we make a locally optimal choice (normalizing heights based on immediate neighbors) that leads to a globally optimal solution.
*   **Constraint Propagation:** The restrictions propagate constraints on heights. Normalization effectively propagates these constraints from both ends.
*   **Arithmetic Progression:** The height difference between adjacent buildings is at most 1, forming an arithmetic progression. The maximum height between two points is found by averaging their heights and considering the distance.

## Common Mistakes
*   **Not Sorting Restrictions:** Failing to sort the restrictions will lead to incorrect normalization as the left-to-right and right-to-left passes assume ordered positions.
*   **Incorrect Normalization Logic:** Miscalculating the `min` condition during normalization, especially with the distance calculation.
*   **Forgetting Implicit Restrictions:** Not considering the implicit restrictions at building 1 (height 0) and building `n` (height `n-1`) can lead to an incorrect final answer.
*   **Integer Division:** Incorrectly handling integer division when calculating the maximum height between two points, especially when the sum of heights and distance is odd. The formula `(h1 + h2 + d) / 2` correctly handles this for integer heights.

## Complexity Analysis
- Time: O(N log N) - due to sorting the restrictions, where N is the number of restrictions. The subsequent passes are O(N).
- Space: O(1) - if we modify the input array in-place. If a copy is made, it would be O(N).

## Commented Code
```java
class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        // Sort restrictions by building position to process them in order.
        Arrays.sort(restrictions, (a, b) -> a[0] - b[0]);

        // Get the number of restrictions.
        int m = restrictions.length;

        // If there are no restrictions, the tallest building can be at position n with height n-1.
        if(m == 0) return n - 1;

        // --- Left-to-Right Normalization ---
        // Initialize previous position and height. Building 1 has position 1 and can have height 0.
        int prevPos = 1;
        int prevH = 0;
        // Iterate through each restriction from left to right.
        for (int i = 0; i < m; i++) {
            // Calculate the distance between the current restriction and the previous one.
            int dist = restrictions[i][0] - prevPos;
            // The height at the current restriction's position cannot exceed its original height
            // AND it cannot exceed the height of the previous restriction plus the distance.
            // This ensures the height difference between adjacent buildings is at most 1.
            restrictions[i][1] = Math.min(restrictions[i][1], prevH + dist);
            // Update previous position and height for the next iteration.
            prevPos = restrictions[i][0];
            prevH = restrictions[i][1];
        }

        // --- Right-to-Left Normalization ---
        // Initialize previous position and height for the right-to-left pass.
        // Building n has position n and can have height n-1.
        prevPos = n;
        prevH = n - 1;
        // Iterate through each restriction from right to left.
        for (int i = m - 1; i >= 0; i--) {
            // Calculate the distance between the current restriction and the next one (from the right).
            int dist = prevPos - restrictions[i][0];
            // The height at the current restriction's position cannot exceed its current normalized height
            // AND it cannot exceed the height of the next restriction (from right) plus the distance.
            // This further refines the height constraints.
            restrictions[i][1] = Math.min(restrictions[i][1], prevH + dist);
            // Update previous position and height for the next iteration.
            prevPos = restrictions[i][0];
            prevH = restrictions[i][1];
        }

        // --- Calculate Maximum Height Between Consecutive Normalized Restrictions ---
        // Initialize the starting point for calculating segments. Building 1 has position 1 and height 0.
        int leftPos = 1;
        int leftH = 0;
        // Initialize the maximum building height found so far.
        int ans = 0;
        // Iterate through the normalized restrictions.
        for (int[] temp : restrictions) {
            // Current restriction's position and normalized height.
            int rightPos = temp[0];
            int rightH = temp[1];

            // Calculate the distance between the current segment's start and end.
            int d = rightPos - leftPos;
            // The maximum height in a segment between (leftPos, leftH) and (rightPos, rightH)
            // occurs at the midpoint. The height at the midpoint is (leftH + rightH + d) / 2.
            // This formula comes from averaging the heights and considering the distance.
            // For example, if h1=0, h2=2, d=2, max height is (0+2+2)/2 = 2.
            // If h1=1, h2=3, d=2, max height is (1+3+2)/2 = 3.
            ans = Math.max(ans, (leftH + rightH + d) / 2);

            // Update the start of the next segment to the current restriction's position and height.
            leftPos = rightPos;
            leftH = rightH;
        }

        // --- Calculate Maximum Height for the Last Segment (from last restriction to building n) ---
        // Calculate the distance from the last processed restriction to building n.
        int d = n - leftPos;
        // The maximum height in this last segment is the height of the last restriction plus the remaining distance.
        // This assumes the building height can increase linearly up to building n.
        int rightH = leftH + d;
        // Update the overall maximum height if this last segment yields a greater height.
        return Math.max(ans, rightH);
    }
}
```

## Interview Tips
*   **Explain the Constraint:** Clearly articulate the `abs(h1 - h2) <= abs(p1 - p2)` constraint and how it drives the normalization process.
*   **Walk Through Normalization:** Verbally walk through the left-to-right and right-to-left normalization passes with a small example to demonstrate understanding.
*   **Derive the Midpoint Formula:** Explain how `(h1 + h2 + d) / 2` is derived for the maximum height between two points. This shows a deeper understanding of the problem's geometry.
*   **Edge Cases:** Be prepared to discuss edge cases like no restrictions, one restriction, or restrictions at the boundaries (building 1 or `n`).

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the core constraint: height difference <= position difference.
- [ ] Implement sorting of restrictions.
- [ ] Implement left-to-right normalization.
- [ ] Implement right-to-left normalization.
- [ ] Implement calculation of max height between normalized restrictions.
- [ ] Handle the final segment from the last restriction to building `n`.
- [ ] Consider edge cases (no restrictions).
- [ ] Analyze time and space complexity.

## Similar Problems
*   [1846. Maximum Element After Decreasing and Rearranging](https://leetcode.com/problems/maximum-element-after-decreasing-and-rearranging/)
*   [1642. Furthest Building You Can Reach](https://leetcode.com/problems/furthest-building-you-can-reach/)
*   [1764. Form Array by Concatenating Subarrays of the Another Array](https://leetcode.com/problems/form-array-by-concatenating-subarrays-of-the-another-array/) (Conceptually related to ordering)

## Tags
`Array` `Sorting` `Greedy` `Two Pointers`
