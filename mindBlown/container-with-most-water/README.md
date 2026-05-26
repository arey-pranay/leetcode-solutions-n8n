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
        int n = height.length;
        int i=0;
        int j = n-1;
        int max = Integer.MIN_VALUE; 
        while(i<j){
            max = Math.max(max,Math.min(height[i],height[j])*(j-i));
            if(height[i] < height[j]) i++; else j--;
        }
        return max;
    }
}

```

---

---
## Quick Revision
Given an array of non-negative integers representing heights of vertical lines, find two lines that form a container with the most water.
This is solved using a two-pointer approach, moving the pointer of the shorter line inwards.

## Intuition
The area of a container is determined by its width and its height. The height is limited by the shorter of the two lines. To maximize the area, we want to maximize both width and height.
Initially, we have the maximum possible width (between the first and last line). If we move the pointer of the taller line inwards, the width decreases, and the height is still limited by the shorter line, so the area can only decrease or stay the same. However, if we move the pointer of the *shorter* line inwards, we might find a taller line, which *could* compensate for the reduced width and potentially lead to a larger area. This greedy approach of always trying to improve the limiting factor (the shorter line) is key.

## Algorithm
1. Initialize two pointers, `left` at the beginning of the `height` array (index 0) and `right` at the end of the `height` array (index `n-1`).
2. Initialize a variable `maxArea` to 0 (or `Integer.MIN_VALUE` if negative heights were possible, but they are non-negative here).
3. While `left` is less than `right`:
    a. Calculate the current width: `width = right - left`.
    b. Determine the current height: `currentHeight = min(height[left], height[right])`.
    c. Calculate the current area: `currentArea = width * currentHeight`.
    d. Update `maxArea`: `maxArea = max(maxArea, currentArea)`.
    e. Move the pointer that points to the shorter line inwards:
        i. If `height[left] < height[right]`, increment `left`.
        ii. Otherwise (if `height[right] <= height[left]`), decrement `right`.
4. Return `maxArea`.

## Concept to Remember
*   **Two-Pointer Technique:** Efficiently traversing or searching in a sorted or partially ordered data structure by using two pointers moving towards each other.
*   **Greedy Approach:** Making locally optimal choices at each step with the hope of finding a global optimum.
*   **Area Calculation:** Understanding how to calculate the area of a rectangle formed by two vertical lines and the x-axis.

## Common Mistakes
*   **Brute Force:** Trying all possible pairs of lines, which leads to an O(n^2) time complexity.
*   **Incorrect Pointer Movement:** Moving the taller pointer instead of the shorter one, which might miss the optimal solution.
*   **Off-by-One Errors:** Incorrectly calculating width or loop conditions.
*   **Integer Overflow:** While not an issue with typical LeetCode constraints for this problem, in general, be mindful of potential overflows when calculating areas with large dimensions.

## Complexity Analysis
- Time: O(n) - The two pointers start at opposite ends and move towards each other, with each pointer traversing the array at most once.
- Space: O(1) - Only a few variables are used to store pointers and the maximum area, regardless of the input size.

## Commented Code
```java
class Solution {
    public int maxArea(int[] height) {
        // Get the total number of vertical lines.
        int n = height.length;
        // Initialize the left pointer to the beginning of the array.
        int i = 0;
        // Initialize the right pointer to the end of the array.
        int j = n - 1;
        // Initialize maxArea to the smallest possible integer value to ensure any valid area will be greater.
        int max = Integer.MIN_VALUE; 

        // Continue as long as the left pointer is to the left of the right pointer.
        while (i < j) {
            // Calculate the width of the current container.
            int width = j - i;
            // Determine the height of the current container, which is limited by the shorter of the two lines.
            int currentHeight = Math.min(height[i], height[j]);
            // Calculate the area of the current container.
            int currentArea = currentHeight * width;
            // Update maxArea if the current area is larger.
            max = Math.max(max, currentArea);

            // Move the pointer that points to the shorter line inwards.
            // This is because moving the shorter line has the potential to increase the height,
            // while moving the taller line would only decrease the width without guaranteeing a height increase.
            if (height[i] < height[j]) {
                // If the left line is shorter, move the left pointer one step to the right.
                i++;
            } else {
                // If the right line is shorter or equal, move the right pointer one step to the left.
                j--;
            }
        }
        // Return the maximum area found.
        return max;
    }
}
```

## Interview Tips
*   **Explain the Two-Pointer Logic:** Clearly articulate *why* moving the shorter pointer is the correct greedy choice. This is the core of the solution.
*   **Walk Through an Example:** Use a small `height` array (e.g., `[1, 8, 6, 2, 5, 4, 8, 3, 7]`) and trace the pointers, width, height, and `maxArea` updates.
*   **Discuss Time/Space Complexity:** Be ready to justify why the solution is O(n) time and O(1) space.
*   **Consider Edge Cases:** What if the array is empty or has only one element? (The problem constraints usually handle this, but it's good to think about).

## Revision Checklist
- [ ] Understand the problem statement.
- [ ] Recall the two-pointer approach.
- [ ] Implement the pointer movement logic correctly.
- [ ] Calculate area using `min(height[left], height[right]) * (right - left)`.
- [ ] Track the maximum area found.
- [ ] Analyze time and space complexity.

## Similar Problems
*   3Sum
*   Trapping Rain Water
*   3Sum Closest
*   Container With Most Water II (a harder variation)

## Tags
`Array` `Two Pointers` `Greedy`
