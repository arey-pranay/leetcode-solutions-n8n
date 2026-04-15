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
        int[] lmax = new int[n];
        int[] rmax = new int[n];
        
        lmax[0] = height[0];
        for(int i=1;i<n;i++) lmax[i] = Math.max(lmax[i-1],height[i-1]);
        rmax[n-1] = height[n-1];
        for(int i=n-2;i>=0;i--) rmax[i] = Math.max(rmax[i+1],height[i+1]);
        
        
        int ans = 0;
        for(int i =0; i<n;i++){
            if(lmax[i] <= height[i] || rmax[i] <= height[i]) continue;
            int canStore = Math.min(lmax[i],rmax[i]);
            ans += canStore-height[i];
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
The amount of water a bar can hold is determined by the *shorter* of the tallest bar to its left and the tallest bar to its right. If the current bar is taller than or equal to either of these maximums, it cannot hold water. Otherwise, the water trapped above it is the difference between the minimum of the left and right maximums and its own height.

## Algorithm
1. Initialize two arrays, `lmax` and `rmax`, of the same size as the input `height` array. These will store the maximum height to the left and right of each position, respectively.
2. Populate `lmax`:
   - `lmax[0]` is `height[0]`.
   - For each subsequent index `i` from 1 to `n-1`, `lmax[i]` is the maximum of `lmax[i-1]` and `height[i-1]`. This means `lmax[i]` stores the maximum height encountered *before* index `i`.
3. Populate `rmax`:
   - `rmax[n-1]` is `height[n-1]`.
   - For each preceding index `i` from `n-2` down to 0, `rmax[i]` is the maximum of `rmax[i+1]` and `height[i+1]`. This means `rmax[i]` stores the maximum height encountered *after* index `i`.
4. Initialize a variable `ans` to 0, which will store the total trapped water.
5. Iterate through the `height` array from index 0 to `n-1`.
6. For each index `i`:
   - If `lmax[i]` is less than or equal to `height[i]` OR `rmax[i]` is less than or equal to `height[i]`, this bar cannot trap water, so continue to the next iteration.
   - Otherwise, calculate the water that can be stored above this bar: `canStore = Math.min(lmax[i], rmax[i])`.
   - Add the trapped water for this bar to the total: `ans += canStore - height[i]`.
7. Return `ans`.

## Concept to Remember
*   **Prefix/Suffix Maximums:** Pre-calculating maximums from both ends of an array is a common pattern for problems involving ranges or boundaries.
*   **Two Pointers (Implicit):** While this solution uses auxiliary arrays, the core idea of considering boundaries from both sides hints at potential two-pointer optimizations.
*   **Water Level Determination:** The water level at any point is limited by the minimum of the highest walls on either side.

## Common Mistakes
*   **Off-by-one errors in `lmax` and `rmax` calculation:** Incorrectly including the current bar's height when calculating the maximums for the *other* side. The `lmax[i]` should be the max *before* `i`, and `rmax[i]` should be the max *after* `i`.
*   **Not handling edge cases:** Forgetting that bars at the very beginning or end of the array cannot trap water.
*   **Incorrectly calculating trapped water:** Using `Math.max` instead of `Math.min` for the bounding walls, or not subtracting the current bar's height.
*   **Overwriting `height` array:** Modifying the input array if it's not allowed or if it complicates logic.

## Complexity Analysis
- Time: O(n) - We iterate through the array three times: once to calculate `lmax`, once for `rmax`, and once to calculate the total trapped water. Each iteration takes linear time.
- Space: O(n) - We use two auxiliary arrays, `lmax` and `rmax`, each of size `n`, to store the pre-calculated maximums.

## Commented Code
```java
class Solution {
    public int trap(int[] height) {
        // Get the total number of bars in the elevation map.
        int n = height.length;
        
        // Initialize an array to store the maximum height to the left of each bar.
        int[] lmax = new int[n];
        // Initialize an array to store the maximum height to the right of each bar.
        int[] rmax = new int[n];
        
        // The maximum height to the left of the first bar is just its own height.
        lmax[0] = height[0];
        // Iterate from the second bar to the end to calculate left maximums.
        for(int i = 1; i < n; i++) {
            // The left maximum at index i is the maximum of the left maximum at the previous index (i-1)
            // and the height of the previous bar (height[i-1]). This ensures lmax[i] stores the max *before* index i.
            lmax[i] = Math.max(lmax[i-1], height[i-1]);
        }
        
        // The maximum height to the right of the last bar is just its own height.
        rmax[n-1] = height[n-1];
        // Iterate from the second to last bar backwards to the beginning to calculate right maximums.
        for(int i = n - 2; i >= 0; i--) {
            // The right maximum at index i is the maximum of the right maximum at the next index (i+1)
            // and the height of the next bar (height[i+1]). This ensures rmax[i] stores the max *after* index i.
            rmax[i] = Math.max(rmax[i+1], height[i+1]);
        }
        
        // Initialize a variable to accumulate the total trapped water.
        int ans = 0;
        // Iterate through each bar in the elevation map.
        for(int i = 0; i < n; i++) {
            // If the current bar's height is greater than or equal to the maximum height to its left,
            // OR if its height is greater than or equal to the maximum height to its right,
            // then this bar cannot trap any water, so we skip it.
            if(lmax[i] <= height[i] || rmax[i] <= height[i]) {
                continue;
            }
            // If the bar can trap water, the water level above it is determined by the shorter of the two bounding walls.
            int canStore = Math.min(lmax[i], rmax[i]);
            // The amount of water trapped above the current bar is the difference between the water level and the bar's height.
            ans += canStore - height[i];
        }
        // Return the total accumulated trapped water.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the intuition first:** Clearly articulate why the minimum of left and right maximums is the key.
*   **Discuss space optimization:** Mention that this O(n) space solution can be optimized to O(1) using a two-pointer approach.
*   **Walk through an example:** Use a small array like `[0,1,0,2,1,0,1,3,2,1,2,1]` to trace the `lmax`, `rmax`, and `ans` calculations.
*   **Clarify array indexing:** Be precise about whether `lmax[i]` refers to the max *up to and including* `i` or *before* `i`. The provided solution correctly uses the "before" definition.

## Revision Checklist
- [ ] Understand the problem statement clearly.
- [ ] Identify the bounding walls for water trapping.
- [ ] Implement prefix maximum calculation correctly.
- [ ] Implement suffix maximum calculation correctly.
- [ ] Calculate trapped water for each bar.
- [ ] Sum up the total trapped water.
- [ ] Consider edge cases (empty array, single bar).
- [ ] Analyze time and space complexity.
- [ ] Be prepared to discuss O(1) space optimization.

## Similar Problems
*   Container With Most Water (LeetCode 11)
*   Rain Water Trapping II (LeetCode 407) - 2D version

## Tags
`Array` `Two Pointers` `Dynamic Programming` `Stack`
