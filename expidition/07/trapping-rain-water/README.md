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
Given an elevation map represented by an array of non-negative integers, calculate how much water it can trap.
We can solve this by finding the maximum height to the left and right of each bar, and then calculating the water trapped above it.

## Intuition
The amount of water a bar can hold is determined by the shorter of the two highest bars surrounding it (one to its left, one to its right). If a bar is taller than or equal to either of its surrounding maximums, it cannot hold water at that position. The water trapped above a bar is `min(left_max, right_max) - current_height`. Summing this up for all bars gives the total trapped water.

## Algorithm
1. Initialize two arrays, `lmax` and `rmax`, of the same size as the input `height` array. These will store the maximum height to the left and right of each bar, respectively.
2. Populate `lmax`:
   - `lmax[0]` is `height[0]`.
   - For each subsequent index `i` from 1 to `n-1`, `lmax[i]` is the maximum of `lmax[i-1]` and `height[i-1]`. This means `lmax[i]` stores the maximum height encountered *before* index `i`.
3. Populate `rmax`:
   - `rmax[n-1]` is `height[n-1]`.
   - For each preceding index `i` from `n-2` down to 0, `rmax[i]` is the maximum of `rmax[i+1]` and `height[i+1]`. This means `rmax[i]` stores the maximum height encountered *after* index `i`.
4. Initialize a variable `ans` to 0, which will store the total trapped water.
5. Iterate through the `height` array from index 0 to `n-1`:
   - For each bar at index `i`, check if `lmax[i]` is less than or equal to `height[i]` OR `rmax[i]` is less than or equal to `height[i]`. If either is true, this bar cannot trap water, so `continue` to the next iteration.
   - If the bar can trap water, calculate the water level: `canStore = Math.min(lmax[i], rmax[i])`.
   - The amount of water trapped above the current bar is `canStore - height[i]`. Add this to `ans`.
6. Return `ans`.

## Concept to Remember
*   **Prefix/Suffix Maximums:** Calculating maximums from left-to-right and right-to-left is a common pattern for problems involving ranges or boundaries.
*   **Two Pointers (Implicit):** While this solution uses auxiliary arrays, the core idea of finding boundaries relates to how two-pointer approaches work by maintaining left and right boundaries.
*   **Water Trapping Logic:** The fundamental principle that water level is dictated by the minimum of the surrounding maximum heights.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly calculating `lmax` or `rmax` by including the current element or missing the boundary element.
*   **Misunderstanding `lmax`/`rmax` definition:** Confusing `lmax[i]` to mean max up to *and including* `i`, versus max *before* `i`. The provided solution correctly uses max *before* `i` for `lmax[i]` and max *after* `i` for `rmax[i]`.
*   **Not handling edge cases:** Forgetting that bars at the ends of the array cannot trap water.
*   **Incorrect water calculation:** Using `max(lmax[i], rmax[i])` instead of `min(lmax[i], rmax[i])` to determine the water level.

## Complexity Analysis
- Time: O(n) - reason: We iterate through the array three times: once to compute `lmax`, once to compute `rmax`, and once to calculate the trapped water. Each iteration takes linear time with respect to the input array size `n`.
- Space: O(n) - reason: We use two auxiliary arrays, `lmax` and `rmax`, each of size `n`, to store the maximum heights to the left and right of each bar.

## Commented Code
```java
class Solution {
    public int trap(int[] height) {
        // Get the total number of bars in the elevation map.
        int n = height.length;
        
        // Create an array to store the maximum height to the left of each bar.
        int[] lmax = new int[n];
        // Create an array to store the maximum height to the right of each bar.
        int[] rmax = new int[n];
        
        // Initialize the maximum height to the left of the first bar.
        // It's just the height of the first bar itself.
        lmax[0] = height[0];
        // Iterate from the second bar to the end to compute left maximums.
        for(int i = 1; i < n; i++) {
            // The maximum height to the left of the current bar (i) is the maximum
            // of the previous bar's left maximum (lmax[i-1]) and the height of the previous bar (height[i-1]).
            // Note: We use height[i-1] because lmax[i] should represent the max *before* index i.
            lmax[i] = Math.max(lmax[i-1], height[i-1]);
        }
        
        // Initialize the maximum height to the right of the last bar.
        // It's just the height of the last bar itself.
        rmax[n-1] = height[n-1];
        // Iterate from the second to last bar backwards to the beginning to compute right maximums.
        for(int i = n - 2; i >= 0; i--) {
            // The maximum height to the right of the current bar (i) is the maximum
            // of the next bar's right maximum (rmax[i+1]) and the height of the next bar (height[i+1]).
            // Note: We use height[i+1] because rmax[i] should represent the max *after* index i.
            rmax[i] = Math.max(rmax[i+1], height[i+1]);
        }
        
        // Initialize the total trapped water to 0.
        int ans = 0;
        // Iterate through each bar to calculate the water it can trap.
        for(int i = 0; i < n; i++) {
            // If the current bar's height is greater than or equal to the maximum height
            // to its left OR greater than or equal to the maximum height to its right,
            // it cannot trap any water at this position. So, skip to the next bar.
            if(lmax[i] <= height[i] || rmax[i] <= height[i]) {
                continue;
            }
            
            // The water level above the current bar is determined by the shorter of the two surrounding maximums.
            int canStore = Math.min(lmax[i], rmax[i]);
            
            // The amount of water trapped above the current bar is the water level minus the bar's height.
            // Add this amount to the total trapped water.
            ans += canStore - height[i];
        }
        
        // Return the total amount of trapped water.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the core logic first:** Before diving into code, clearly articulate that water trapped at a position depends on the minimum of the highest bar to its left and the highest bar to its right.
*   **Discuss space-time trade-offs:** Mention that this O(n) space solution is straightforward, but hint at or discuss the O(1) space two-pointer approach as a follow-up.
*   **Clarify array indexing:** Be precise about whether `lmax[i]` refers to the maximum *up to and including* index `i` or *before* index `i`. The provided solution uses the latter, which is crucial for correctness.
*   **Walk through an example:** Use a small array like `[0,1,0,2,1,0,1,3,2,1,2,1]` to trace the `lmax`, `rmax`, and `ans` calculations.

## Revision Checklist
- [ ] Understand the problem: calculate trapped water based on elevation map.
- [ ] Identify the key constraint: water level is limited by the shorter of the two surrounding maximums.
- [ ] Implement prefix maximums (`lmax`).
- [ ] Implement suffix maximums (`rmax`).
- [ ] Correctly calculate water trapped per bar: `min(lmax[i], rmax[i]) - height[i]`.
- [ ] Handle cases where a bar cannot trap water.
- [ ] Sum up all trapped water.
- [ ] Analyze time and space complexity.
- [ ] Consider alternative approaches (e.g., two pointers).

## Similar Problems
*   Container With Most Water (LeetCode 11)
*   Largest Rectangle in Histogram (LeetCode 84)
*   Rain Water Trapping II (LeetCode 407) - 3D version

## Tags
`Array` `Dynamic Programming` `Two Pointers`
