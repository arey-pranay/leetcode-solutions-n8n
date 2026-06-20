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
We solve this by normalizing the restrictions and then finding the maximum height achievable between adjacent valid building positions.

## Intuition
The core idea is that the height difference between any two buildings is limited by their distance. If building A is at position `p1` with height `h1` and building B is at `p2` with height `h2`, then `abs(h1 - h2) <= abs(p1 - p2)`. This means `h2 <= h1 + abs(p1 - p2)` and `h1 <= h2 + abs(p1 - p2)`.

The restrictions given are *upper bounds*. However, a restriction at `(pos, h)` means the building at `pos` *cannot exceed* height `h`. This implies that any building at `pos` must have a height `h_actual <= h`.

Consider two adjacent restrictions `(p1, h1)` and `(p2, h2)` where `p1 < p2`.
From the perspective of `(p1, h1)` moving towards `p2`, the building at `p2` cannot exceed `h1 + (p2 - p1)`. So, the effective height at `p2` is `min(h2, h1 + (p2 - p1))`.
Similarly, from the perspective of `(p2, h2)` moving towards `p1`, the building at `p1` cannot exceed `h2 + (p2 - p1)`. So, the effective height at `p1` is `min(h1, h2 + (p2 - p1))`.

By performing these two passes (left-to-right and right-to-left), we normalize the restrictions to their tightest possible upper bounds, considering the distance constraints.

After normalization, we have a set of effective restrictions `(pos_i, h_i)`. For any two adjacent effective restrictions `(p1, h1)` and `(p2, h2)` (including implicit restrictions at `(1, 0)` and `(n, n-1)`), the maximum height achievable *between* them occurs when the height profile is a triangle. The peak of this triangle will be at the midpoint (or close to it) and its height will be `(h1 + h2 + (p2 - p1)) / 2`. This is because the height increases by 1 for every 1 unit of distance from `p1` and decreases by 1 for every 1 unit of distance from `p2`. The sum of these two linear functions is maximized at the point where their slopes cancel out, which is related to the midpoint. The formula `(h1 + h2 + distance) / 2` arises from considering the sum of heights at the two ends and the distance between them.

Finally, we also need to consider the maximum height achievable from the last restriction to the end of the city (`n`).

## Algorithm
1. **Sort Restrictions:** Sort the `restrictions` array based on the building position (`restrictions[i][0]`) in ascending order. This is crucial for processing them sequentially.
2. **Handle Empty Restrictions:** If there are no restrictions, the maximum height is `n-1` (building at position `n` can be `n-1` height).
3. **Left-to-Right Normalization:**
    * Initialize `prevPos = 1` and `prevH = 0` (representing an implicit building at position 1 with height 0).
    * Iterate through the sorted `restrictions`:
        * For each restriction `(currentPos, currentH)`:
            * Calculate the maximum allowed height at `currentPos` based on `prevH` and the distance: `maxAllowedH = prevH + (currentPos - prevPos)`.
            * Update the restriction's height to be the minimum of its original height and `maxAllowedH`: `restrictions[i][1] = Math.min(currentH, maxAllowedH)`.
            * Update `prevPos = currentPos` and `prevH = restrictions[i][1]` for the next iteration.
4. **Right-to-Left Normalization:**
    * Initialize `prevPos = n` and `prevH = n - 1` (representing an implicit building at position `n` with height `n-1`).
    * Iterate through the `restrictions` array in reverse order (from `m-1` down to `0`):
        * For each restriction `(currentPos, currentH)`:
            * Calculate the maximum allowed height at `currentPos` based on `prevH` and the distance: `maxAllowedH = prevH + (prevPos - currentPos)`.
            * Update the restriction's height to be the minimum of its current (already left-to-right normalized) height and `maxAllowedH`: `restrictions[i][1] = Math.min(currentH, maxAllowedH)`.
            * Update `prevPos = currentPos` and `prevH = restrictions[i][1]` for the next iteration.
5. **Calculate Maximum Height Between Restrictions:**
    * Initialize `leftPos = 1`, `leftH = 0` (representing the implicit building at position 1 with height 0).
    * Initialize `ans = 0` to store the maximum building height found so far.
    * Iterate through the normalized `restrictions`:
        * For each restriction `(rightPos, rightH)`:
            * Calculate the distance `d = rightPos - leftPos`.
            * The maximum height achievable between `(leftPos, leftH)` and `(rightPos, rightH)` is `(leftH + rightH + d) / 2`. Update `ans = Math.max(ans, (leftH + rightH + d) / 2)`.
            * Update `leftPos = rightPos` and `leftH = rightH` for the next interval.
6. **Calculate Maximum Height to the End:**
    * After the loop, consider the interval from the last restriction's position (`leftPos`) to the end of the city (`n`).
    * The implicit height at `n` would be `leftH + (n - leftPos)`.
    * Update `ans = Math.max(ans, leftH + (n - leftPos))`.
7. **Return `ans`**.

## Concept to Remember
*   **Greedy Approach:** Normalizing restrictions greedily ensures we find the tightest possible bounds.
*   **Two-Pointer/Sliding Window (Implicit):** The normalization passes and the final calculation pass effectively use a sliding window or two-pointer approach to consider adjacent elements.
*   **Geometric Interpretation:** The height constraint `abs(h1 - h2) <= abs(p1 - p2)` implies a triangular shape for maximum height between two points.
*   **Edge Cases:** Handling no restrictions and the interval from the last restriction to `n` is important.

## Common Mistakes
*   **Not Sorting Restrictions:** Processing restrictions in an arbitrary order will lead to incorrect normalization.
*   **Incorrect Normalization Logic:** Forgetting to consider both left-to-right and right-to-left constraints, or miscalculating the `min` condition.
*   **Off-by-One Errors:** Incorrectly handling the implicit buildings at position 1 (height 0) and position `n` (height `n-1`).
*   **Integer Division:** Using `(leftH + rightH + d) / 2` directly might truncate if the sum is odd, but this is the correct way to find the peak height in this context (integer heights). The formula correctly represents the peak of a triangle with base `d` and heights `leftH` and `rightH` at its ends.
*   **Forgetting the Last Interval:** Not calculating the maximum height from the last restriction to the end of the city (`n`).

## Complexity Analysis
- Time: O(N log N) - due to sorting the `restrictions` array. The subsequent passes are O(N) where N is the number of restrictions.
- Space: O(1) - if we modify the input `restrictions` array in-place. If a copy is made, it would be O(N).

## Commented Code
```java
class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        // Sort restrictions by position to process them in order.
        Arrays.sort(restrictions, (a, b) -> a[0] - b[0]);

        // Get the number of restrictions.
        int m = restrictions.length;

        // If there are no restrictions, the tallest building can be at position n with height n-1.
        if(m == 0) return n - 1;

        // --- Left-to-Right Normalization ---
        // Initialize previous position and height. Building at position 1 has height 0.
        int prevPos = 1;
        int prevH = 0;
        // Iterate through restrictions from left to right.
        for (int i = 0; i < m; i++) {
            // Calculate the distance from the previous restriction/point.
            int dist = restrictions[i][0] - prevPos;
            // The current restriction's height cannot exceed its original height OR
            // the height achievable by increasing from prevH by 'dist'.
            restrictions[i][1] = Math.min(restrictions[i][1], prevH + dist);
            // Update previous position and height for the next iteration.
            prevPos = restrictions[i][0];
            prevH = restrictions[i][1];
        }

        // --- Right-to-Left Normalization ---
        // Initialize previous position and height for the right-to-left pass.
        // Building at position n has height n-1.
        prevPos = n;
        prevH = n - 1;
        // Iterate through restrictions from right to left.
        for (int i = m - 1; i >= 0; i--) {
            // Calculate the distance from the previous restriction/point (moving leftwards).
            int dist = prevPos - restrictions[i][0];
            // The current restriction's height cannot exceed its already normalized height (from L-R pass) OR
            // the height achievable by increasing from prevH (moving left) by 'dist'.
            restrictions[i][1] = Math.min(restrictions[i][1], prevH + dist);
            // Update previous position and height for the next iteration.
            prevPos = restrictions[i][0];
            prevH = restrictions[i][1];
        }

        // --- Calculate Maximum Height Between Adjacent Normalized Restrictions ---
        // Initialize the start of the interval. Building at position 1 has height 0.
        int leftPos = 1;
        int leftH = 0;
        // Initialize the maximum building height found so far.
        int ans = 0;
        // Iterate through the normalized restrictions.
        for (int[] temp : restrictions) {
            // Current restriction's position and height.
            int rightPos = temp[0];
            int rightH = temp[1];

            // Calculate the distance between the current interval's start and end.
            int d = rightPos - leftPos;
            // The maximum height achievable in this interval forms a triangle.
            // The peak height is (leftH + rightH + distance) / 2.
            // This formula arises from h_peak = leftH + (peak_pos - leftPos) and h_peak = rightH + (rightPos - peak_pos),
            // where peak_pos is the position of maximum height. Solving for h_peak gives the formula.
            ans = Math.max(ans, (leftH + rightH + d) / 2);

            // Move the start of the interval to the current restriction's position and height.
            leftPos = rightPos;
            leftH = rightH;
        }

        // --- Calculate Maximum Height from Last Restriction to the End of the City ---
        // Calculate the distance from the last processed restriction to the end of the city (n).
        int d = n - leftPos;
        // The maximum height achievable in this final segment is simply extending the last height linearly.
        // The height at position 'n' would be leftH + distance.
        int finalSegmentMaxHeight = leftH + d;
        // Update the overall maximum height.
        return Math.max(ans, finalSegmentMaxHeight);
    }
}
```

## Interview Tips
1.  **Explain the Constraint:** Clearly articulate the `abs(h1 - h2) <= abs(p1 - p2)` constraint and how it limits height differences. This is the foundation of the solution.
2.  **Justify Normalization:** Explain *why* the two-pass normalization is necessary. Emphasize that a restriction `(pos, h)` is an *upper bound*, and the distance constraint can further reduce this effective upper bound from both sides.
3.  **Visualize the Triangle:** When discussing the calculation between two normalized restrictions, describe the triangular height profile. This helps explain the `(h1 + h2 + d) / 2` formula intuitively.
4.  **Handle Edge Cases:** Be prepared to discuss what happens with zero restrictions, or when restrictions are at the very beginning or end of the city.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Recognize the height difference constraint: `abs(h1 - h2) <= abs(p1 - p2)`.
- [ ] Implement sorting of restrictions.
- [ ] Implement the left-to-right normalization pass.
- [ ] Implement the right-to-left normalization pass.
- [ ] Implement the calculation of max height between adjacent normalized points.
- [ ] Correctly handle the implicit points (1, 0) and (n, n-1).
- [ ] Handle the final segment from the last restriction to `n`.
- [ ] Consider time and space complexity.

## Similar Problems
*   [1846. Maximum Element After Decreasing and Rearranging](https://leetcode.com/problems/maximum-element-after-decreasing-and-rearranging/) (Similar greedy approach with sorting and constraints)
*   [1696. Jump Game VI](https://leetcode.com/problems/jump-game-vi/) (Dynamic programming with sliding window optimization, related to maximizing values over intervals)
*   [84. Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) (Finding maximums over intervals, though different constraint)

## Tags
`Array` `Greedy` `Sorting` `Two Pointers`
