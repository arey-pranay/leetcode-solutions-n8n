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
        suf[n-1] = height[n-1];
        int ans = 0;
        for(int i=1;i<n;i++) pre[i] = Math.max(pre[i-1],height[i]);
        for(int i=n-2;i>=0;i--) suf[i] = Math.max(suf[i+1],height[i]);
        for(int i=0;i<n;i++) ans += Math.min(pre[i],suf[i]) - height[i];
        return ans;        
    }
}
```

---

---
## Quick Revision
Given an elevation map represented by an array of non-negative integers, calculate how much water it can trap after raining.
This is solved by finding the maximum height to the left and right of each bar, and calculating the water trapped above it.

## Intuition
The amount of water trapped above any bar is determined by the minimum of the tallest bar to its left and the tallest bar to its right, minus the height of the current bar itself. If this difference is negative, it means no water can be trapped above this bar. We can precompute these maximums for all bars to efficiently calculate the total trapped water.

## Algorithm
1. Initialize an array `pre` of the same size as `height` to store the maximum height to the left of each bar (inclusive).
2. Initialize an array `suf` of the same size as `height` to store the maximum height to the right of each bar (inclusive).
3. Set `pre[0]` to `height[0]`.
4. Set `suf[n-1]` to `height[n-1]`, where `n` is the length of `height`.
5. Iterate from the second element (`i = 1`) to the end of `height`: `pre[i] = Math.max(pre[i-1], height[i])`. This populates `pre` with the running maximum from the left.
6. Iterate from the second-to-last element (`i = n-2`) down to the beginning of `height`: `suf[i] = Math.max(suf[i+1], height[i])`. This populates `suf` with the running maximum from the right.
7. Initialize a variable `ans` to 0, which will store the total trapped water.
8. Iterate through the `height` array from `i = 0` to `n-1`:
   a. Calculate the water trapped above the current bar: `Math.min(pre[i], suf[i]) - height[i]`.
   b. Add this amount to `ans`.
9. Return `ans`.

## Concept to Remember
*   Dynamic Programming: Using precomputed values (maximums from left/right) to solve subproblems efficiently.
*   Two Pointers (Implicit): While not explicitly using two pointers moving towards each other, the precomputation of left and right maximums effectively considers the boundaries for each element.
*   Array Manipulation: Efficiently storing and retrieving precomputed information.

## Common Mistakes
*   Forgetting to handle edge cases: The first and last bars cannot trap water.
*   Incorrectly calculating the water trapped: Using `max(pre[i], suf[i])` instead of `min(pre[i], suf[i])`.
*   Off-by-one errors in loop boundaries or array indexing when calculating `pre` and `suf` arrays.
*   Not subtracting the current bar's height: The water level is determined by the surrounding walls, but the water itself sits *above* the current bar.

## Complexity Analysis
- Time: O(n) - The algorithm involves three separate passes through the `height` array (one for `pre`, one for `suf`, and one for calculating `ans`), each taking O(n) time.
- Space: O(n) - Two additional arrays, `pre` and `suf`, of size `n` are used to store the maximum heights from the left and right, respectively.

## Commented Code
```java
class Solution {
    public int trap(int[] height) {
        // Get the total number of bars in the elevation map.
        int n = height.length;
        // Initialize an array to store the maximum height to the left of each bar (inclusive).
        int[] pre = new int[n];
        // Initialize an array to store the maximum height to the right of each bar (inclusive).
        int[] suf = new int[n];
        // The maximum height to the left of the first bar is just its own height.
        pre[0] = height[0];
        // The maximum height to the right of the last bar is just its own height.
        suf[n-1] = height[n-1];
        // Initialize a variable to accumulate the total trapped water.
        int ans = 0;

        // Populate the 'pre' array: iterate from the second bar to the end.
        for(int i = 1; i < n; i++) {
            // The maximum height to the left of the current bar is the maximum of
            // the previous bar's maximum left height and the current bar's height.
            pre[i] = Math.max(pre[i-1], height[i]);
        }

        // Populate the 'suf' array: iterate from the second-to-last bar to the beginning.
        for(int i = n - 2; i >= 0; i--) {
            // The maximum height to the right of the current bar is the maximum of
            // the next bar's maximum right height and the current bar's height.
            suf[i] = Math.max(suf[i+1], height[i]);
        }

        // Calculate the trapped water: iterate through each bar.
        for(int i = 0; i < n; i++) {
            // The amount of water trapped above the current bar is determined by the
            // minimum of the tallest bar to its left and the tallest bar to its right,
            // minus the height of the current bar itself.
            // If this value is negative, it means no water is trapped, and Math.min will ensure it's not added.
            ans += Math.min(pre[i], suf[i]) - height[i];
        }

        // Return the total accumulated trapped water.
        return ans;
    }
}
```

## Interview Tips
*   Explain the intuition clearly: Start by describing how water is trapped based on surrounding walls.
*   Discuss space optimization: Mention that this O(n) space solution can be optimized to O(1) space using the two-pointer approach.
*   Walk through an example: Use a small `height` array (e.g., `[0,1,0,2,1,0,1,3,2,1,2,1]`) to trace the `pre`, `suf`, and `ans` calculations.
*   Be prepared to implement the O(1) space solution if asked.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Grasp the intuition of water trapping based on surrounding maximums.
- [ ] Implement the precomputation of left and right maximums.
- [ ] Correctly calculate water trapped for each bar.
- [ ] Handle edge cases (empty array, single element array).
- [ ] Analyze time and space complexity.
- [ ] Consider alternative solutions (e.g., two-pointer).

## Similar Problems
*   Container With Most Water (LeetCode 11)
*   The Skyline Problem (LeetCode 218)
*   Largest Rectangle in Histogram (LeetCode 84)

## Tags
`Array` `Dynamic Programming` `Two Pointers`
