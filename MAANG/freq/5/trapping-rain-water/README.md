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
Given an array representing elevation map, calculate how much water it can trap.
We can solve this by finding the maximum height to the left and right of each bar.

## Intuition
The amount of water a bar can hold is determined by the minimum of the tallest bar to its left and the tallest bar to its right, minus its own height. If this difference is negative, it means the bar is taller than or equal to its surrounding maximums, so it can't hold water. We can precompute these maximums for all bars to efficiently calculate the trapped water.

## Algorithm
1. Initialize an array `pre` of the same size as `height` to store the maximum height to the left of each bar (inclusive).
2. Initialize `pre[0]` with `height[0]`.
3. Iterate from the second element (`i = 1`) to the end of `height`: `pre[i]` will be the maximum of `pre[i-1]` and `height[i]`.
4. Initialize an array `suf` of the same size as `height` to store the maximum height to the right of each bar (inclusive).
5. Initialize `suf[n-1]` with `height[n-1]`, where `n` is the length of `height`.
6. Iterate from the second to last element (`i = n-2`) down to the beginning of `height`: `suf[i]` will be the maximum of `suf[i+1]` and `height[i]`.
7. Initialize a variable `ans` to 0, which will store the total trapped water.
8. Iterate through the `height` array from `i = 0` to `n-1`:
    a. For each bar `i`, get its left maximum `lMax = pre[i]` and its right maximum `rMax = suf[i]`.
    b. Calculate the water trapped above the current bar: `Math.min(lMax, rMax) - height[i]`.
    c. Add this calculated water to `ans`.
9. Return `ans`.

## Concept to Remember
*   **Prefix and Suffix Maximums:** Efficiently calculating cumulative maximums from both ends of an array.
*   **Two Pointers (Implicit):** While not explicitly using two pointers moving towards each other, the precomputation step effectively considers the "boundaries" for each element.
*   **Greedy Approach:** At each position, we make the locally optimal choice (calculating water based on immediate surrounding maximums) which leads to the globally optimal solution.

## Common Mistakes
*   **Incorrectly calculating `pre` and `suf`:** Forgetting to include the current element's height when calculating the maximum.
*   **Off-by-one errors:** In loop bounds or array indexing when calculating prefix/suffix maximums.
*   **Not handling cases where `min(lMax, rMax) - height[i]` is negative:** This should result in 0 water trapped, not a negative value.
*   **Recomputing maximums repeatedly:** Instead of precomputing, recalculating `lMax` and `rMax` for each bar in the final loop, leading to O(n^2) time complexity.

## Complexity Analysis
- Time: O(n) - We iterate through the `height` array three times: once for `pre`, once for `suf`, and once to calculate the total trapped water. Each iteration takes O(n) time.
- Space: O(n) - We use two additional arrays, `pre` and `suf`, each of size `n`, to store the prefix and suffix maximums.

## Commented Code
```java
class Solution {
    public int trap(int[] height) {
      // Get the total number of bars in the elevation map.
      int n = height.length;
      // Create an array to store the maximum height to the left of each bar (inclusive).
      int[] pre = new int[n];
      // Create an array to store the maximum height to the right of each bar (inclusive).
      int[] suf = new int[n];

      // The maximum height to the left of the first bar is just its own height.
      pre[0] = height[0];
      // Iterate from the second bar to the end to calculate prefix maximums.
      for(int i=1;i<n;i++) {
        // The maximum height to the left of the current bar is the max of the previous prefix max and the current bar's height.
        pre[i]= Math.max(pre[i-1],height[i]);
      }

      // The maximum height to the right of the last bar is just its own height.
      suf[n-1] = height[n-1];
      // Iterate from the second to last bar backwards to calculate suffix maximums.
      for(int i=n-2;i>=0;i--) {
        // The maximum height to the right of the current bar is the max of the next suffix max and the current bar's height.
        suf[i]= Math.max(suf[i+1],height[i]);
      }

      // Initialize the total trapped water to 0.
      int ans = 0;
      // Iterate through each bar to calculate the water trapped above it.
      for(int i=0;i<n;i++){
        // Get the maximum height to the left of the current bar.
        int lMax = pre[i];
        // Get the maximum height to the right of the current bar.
        int rMax = suf[i];
        // The water trapped above the current bar is the minimum of the left and right maximums, minus the current bar's height.
        // If this value is negative, it means the bar is taller than or equal to its boundaries, so no water is trapped.
        ans += Math.min(lMax,rMax)-height[i];
      }
      // Return the total trapped water.
      return ans;
    }
}
```

## Interview Tips
*   **Explain the intuition first:** Before diving into code, clearly articulate why the `min(left_max, right_max) - current_height` formula works.
*   **Discuss trade-offs:** Mention that this O(n) space solution is one way, and briefly hint at or discuss the O(1) space two-pointer approach if time permits or if asked.
*   **Edge cases:** Be prepared to discuss what happens with an empty array, an array with one element, or an array where all elements are the same height.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Grasp the intuition behind using left and right maximums.
- [ ] Implement prefix maximum calculation correctly.
- [ ] Implement suffix maximum calculation correctly.
- [ ] Combine prefix/suffix maximums to calculate trapped water.
- [ ] Handle cases where no water is trapped at a bar.
- [ ] Analyze time and space complexity.
- [ ] Consider alternative approaches (e.g., two pointers).

## Similar Problems
*   Container With Most Water (LeetCode 11)
*   Largest Rectangle in Histogram (LeetCode 84)

## Tags
`Array` `Dynamic Programming` `Two Pointers`
