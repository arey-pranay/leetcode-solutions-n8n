# Container With Most Water

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Two Pointers` `Greedy`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int maxArea(int[] height) {
        int ans = 0;
        int n = height.length;
        int i=0, j=n-1;
        while(i<j){
            ans = Math.max(ans,Math.min(height[i],height[j]) * (j-i));
            if(height[i]<height[j]) i++; else j--;
        }
        return ans;
    }
}

//kitne paani ki guarantee hai abhi -> Math.min(height[i],height[j]) * (j-i)
```

---

---
## Quick Revision
Given an array of non-negative integers representing heights of vertical lines, find two lines that, together with the x-axis, form a container that holds the most water.
This is solved using a two-pointer approach, moving the pointers inward based on the shorter height.

## Intuition
The area of a container is determined by its width and its height. The height is limited by the shorter of the two lines forming the container. To maximize the area, we want to maximize both width and height.
Initially, we have the maximum possible width (between the leftmost and rightmost lines). If we move the pointer associated with the taller line inward, the width decreases, and the height might also decrease or stay the same (if the new line is still taller than the other). This will never increase the area. However, if we move the pointer associated with the *shorter* line inward, the width decreases, but there's a *chance* that the new line is taller, potentially increasing the height and thus the overall area. This is the core idea: always try to improve the limiting factor (the shorter line).

## Algorithm
1. Initialize two pointers, `left` at the beginning of the `height` array (index 0) and `right` at the end of the `height` array (index `n-1`).
2. Initialize a variable `maxArea` to 0 to store the maximum area found so far.
3. While `left` is less than `right`:
    a. Calculate the current area: `currentArea = min(height[left], height[right]) * (right - left)`.
    b. Update `maxArea`: `maxArea = max(maxArea, currentArea)`.
    c. If `height[left]` is less than `height[right]`, increment `left` by 1. This is because moving the shorter line has the potential to increase the height.
    d. Otherwise (if `height[right]` is less than or equal to `height[left]`), decrement `right` by 1.
4. Return `maxArea`.

## Concept to Remember
*   **Two-Pointer Technique:** Efficiently traversing or searching in a sorted or partially ordered data structure by using two pointers that move towards each other.
*   **Greedy Approach:** Making locally optimal choices at each step with the hope of finding a global optimum. In this case, we greedily try to improve the area by moving the shorter pointer.
*   **Area Calculation:** Understanding that the area of a rectangle (or container) is width multiplied by height, and in this problem, the height is constrained by the shorter of the two vertical lines.

## Common Mistakes
*   **Brute-Force Approach:** Trying all possible pairs of lines, which leads to an O(n^2) time complexity, unnecessarily.
*   **Incorrect Pointer Movement:** Moving the pointer of the taller line inward, which guarantees a decrease in width and no guaranteed increase in height, thus never improving the area.
*   **Off-by-One Errors:** Incorrectly calculating the width (`right - left + 1` instead of `right - left`) or loop conditions.
*   **Integer Overflow:** While less likely with typical LeetCode constraints for this problem, in general, be mindful of potential overflows when calculating areas with large numbers.

## Complexity Analysis
*   **Time:** O(n) - The two pointers start at opposite ends and move towards each other, with each pointer traversing the array at most once.
*   **Space:** O(1) - We only use a few extra variables to store pointers and the maximum area, regardless of the input size.

## Commented Code
```java
class Solution {
    public int maxArea(int[] height) {
        // Initialize the maximum area found so far to 0.
        int ans = 0;
        // Get the total number of lines (elements in the height array).
        int n = height.length;
        // Initialize the left pointer to the beginning of the array.
        int i = 0;
        // Initialize the right pointer to the end of the array.
        int j = n - 1;

        // Continue as long as the left pointer is to the left of the right pointer.
        while (i < j) {
            // Calculate the current area.
            // The height of the container is limited by the shorter of the two lines.
            // The width of the container is the distance between the two pointers.
            int currentArea = Math.min(height[i], height[j]) * (j - i);
            // Update the maximum area if the current area is greater.
            ans = Math.max(ans, currentArea);

            // Move the pointer that points to the shorter line inward.
            // This is because moving the shorter line has the potential to increase the container's height,
            // which is the limiting factor for the area. Moving the taller line would only decrease the width
            // without a guaranteed increase in height.
            if (height[i] < height[j]) {
                // If the left line is shorter, move the left pointer to the right.
                i++;
            } else {
                // If the right line is shorter or equal, move the right pointer to the left.
                j--;
            }
        }
        // Return the maximum area found.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the Two-Pointer Logic:** Clearly articulate *why* you are moving the shorter pointer. This is the most crucial part of the explanation.
*   **Walk Through an Example:** Use a small example array (e.g., `[1,8,6,2,5,4,8,3,7]`) and trace the algorithm's execution step-by-step, showing how `left`, `right`, and `ans` change.
*   **Discuss Trade-offs:** Briefly mention why a brute-force O(n^2) approach is inefficient and why the two-pointer O(n) approach is optimal.
*   **Consider Edge Cases:** Think about arrays with only two elements, arrays with all elements the same height, or arrays with very large or very small heights.

## Revision Checklist
- [ ] Understand the problem statement: finding max area between two lines.
- [ ] Recall the area formula: `min(h1, h2) * width`.
- [ ] Remember the two-pointer strategy: start at ends, move inward.
- [ ] Know *which* pointer to move: the one with the shorter height.
- [ ] Be able to explain the reasoning for pointer movement.
- [ ] Analyze time and space complexity.
- [ ] Practice coding the solution from scratch.

## Similar Problems
*   3Sum
*   Trapping Rain Water
*   Two Sum
*   Valid Triangle Number

## Tags
`Array` `Two Pointers` `Greedy`
