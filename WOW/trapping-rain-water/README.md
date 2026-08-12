# Trapping Rain Water

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Two Pointers` `Dynamic Programming` `Stack` `Monotonic Stack`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public int trap(int[] height) {
      int n = height.length;
      int[] pre = new int[n];
      int[] suf = new int[n];
      pre[0] = height[0];
      for(int i=1;i<n;i++) pre[i]= Math.max(pre[i-1],height[i]);
      suf[n-1] = height[n-1];
      for(int i=n-2;i>=0;i--) suf[i]= Math.max(suf[i+1],height[i]);
      int ans = 0;
      for(int i=0;i<n;i++){
        int lMax = pre[i];
        int rMax = suf[i];
        ans += Math.min(lMax,rMax)-height[i];
      }
      return ans;
    }
}

```

---

---
## Quick Revision
Given an array representing the height of bars, calculate how much water can be trapped between them.
This is solved by finding the maximum height to the left and right of each bar and using the minimum of these to determine water level.

## Intuition
The amount of water trapped above a bar at a specific index `i` is determined by the height of the shortest of the two tallest bars surrounding it (one to its left and one to its right). If the current bar's height is less than this minimum bounding height, the difference represents the water trapped above it. We need to sum this difference for all bars.

## Algorithm
1. Initialize an array `pre` of the same size as `height` to store the maximum height to the left of each bar (inclusive).
2. Initialize `pre[0]` with `height[0]`.
3. Iterate from the second element (`i = 1`) to the end of `height`: `pre[i]` will be the maximum of `pre[i-1]` and `height[i]`.
4. Initialize an array `suf` of the same size as `height` to store the maximum height to the right of each bar (inclusive).
5. Initialize `suf[n-1]` with `height[n-1]`.
6. Iterate from the second to last element (`i = n-2`) down to the beginning of `height`: `suf[i]` will be the maximum of `suf[i+1]` and `height[i]`.
7. Initialize a variable `ans` to 0, which will store the total trapped water.
8. Iterate through the `height` array from `i = 0` to `n-1`:
    a. Get the maximum height to the left: `lMax = pre[i]`.
    b. Get the maximum height to the right: `rMax = suf[i]`.
    c. Calculate the water trapped at index `i`: `Math.min(lMax, rMax) - height[i]`. This value is only positive if `Math.min(lMax, rMax)` is greater than `height[i]`.
    d. Add this calculated water to `ans`.
9. Return `ans`.

## Concept to Remember
*   **Prefix and Suffix Maximums:** Efficiently pre-calculating maximums from both directions allows for O(1) lookup of bounding heights.
*   **Water Level Determination:** The water level at any point is limited by the shorter of the two surrounding maximum heights.
*   **Array Traversal and Accumulation:** Iterating through the array and accumulating results based on calculated bounds.

## Common Mistakes
*   **Incorrectly calculating `pre` and `suf`:** Forgetting to include the current bar's height in the maximum calculation for `pre` and `suf`.
*   **Off-by-one errors in loops:** Incorrect loop bounds when calculating prefix/suffix maximums or when calculating trapped water.
*   **Not handling edge cases:** Forgetting that bars at the ends of the array cannot trap water.
*   **Calculating water for negative values:** If `height[i]` is greater than `Math.min(lMax, rMax)`, the result of subtraction would be negative, which should not be added to the total trapped water. The `Math.min(lMax, rMax) - height[i]` naturally handles this as it will be 0 or positive.

## Complexity Analysis
- Time: O(n) - We iterate through the `height` array three times: once for `pre`, once for `suf`, and once to calculate the trapped water. Each iteration takes O(n) time.
- Space: O(n) - We use two additional arrays, `pre` and `suf`, each of size n, to store prefix and suffix maximums.

## Commented Code
```java
class Solution {
    public int trap(int[] height) {
      // Get the total number of bars in the height array.
      int n = height.length;
      // Create an array to store the maximum height to the left of each bar (inclusive).
      int[] pre = new int[n];
      // Create an array to store the maximum height to the right of each bar (inclusive).
      int[] suf = new int[n];

      // The maximum height to the left of the first bar is just its own height.
      pre[0] = height[0];
      // Iterate from the second bar to the end to calculate prefix maximums.
      for(int i=1;i<n;i++) {
        // The max height to the left of bar 'i' is the max of the previous max height and the current bar's height.
        pre[i]= Math.max(pre[i-1],height[i]);
      }

      // The maximum height to the right of the last bar is just its own height.
      suf[n-1] = height[n-1];
      // Iterate from the second to last bar backwards to calculate suffix maximums.
      for(int i=n-2;i>=0;i--) {
        // The max height to the right of bar 'i' is the max of the next max height and the current bar's height.
        suf[i]= Math.max(suf[i+1],height[i]);
      }

      // Initialize the total trapped water to 0.
      int ans = 0;
      // Iterate through each bar to calculate the water trapped above it.
      for(int i=0;i<n;i++){
        // The maximum height to the left of the current bar.
        int lMax = pre[i];
        // The maximum height to the right of the current bar.
        int rMax = suf[i];
        // The water trapped above the current bar is the minimum of the left and right max heights,
        // minus the current bar's height. This difference will be 0 or positive.
        ans += Math.min(lMax,rMax)-height[i];
      }
      // Return the total accumulated trapped water.
      return ans;
    }
}
```

## Interview Tips
*   **Explain the intuition clearly:** Start by explaining how water is trapped based on surrounding walls.
*   **Discuss trade-offs:** Mention that this O(n) space solution is straightforward, but there are O(1) space solutions (e.g., two pointers) that are more complex to implement.
*   **Walk through an example:** Use a small `height` array (e.g., `[0,1,0,2,1,0,1,3,2,1,2,1]`) to trace the `pre`, `suf` arrays and the final calculation.
*   **Ask clarifying questions:** If unsure about constraints or edge cases, ask the interviewer.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Grasp the intuition behind using surrounding maximums.
- [ ] Implement prefix maximum calculation correctly.
- [ ] Implement suffix maximum calculation correctly.
- [ ] Combine prefix/suffix maximums to find trapped water per bar.
- [ ] Sum up the trapped water for all bars.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (empty array, single bar, all same height).

## Similar Problems
*   Container With Most Water (LeetCode 11)
*   Largest Rectangle in Histogram (LeetCode 84)

## Tags
`Array` `Dynamic Programming` `Two Pointers`
