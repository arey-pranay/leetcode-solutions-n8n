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
This problem is solved using a two-pointer approach, moving the pointers inwards based on the shorter line.

## Intuition
The goal is to maximize the area, which is determined by the width between two lines and the height of the shorter line. If we fix one line and move the other, the width decreases. To potentially increase the area, we must find a taller line. The intuition is that if we have two pointers, one at the beginning and one at the end, the widest possible container is formed. To find a larger area, we need to move the pointer pointing to the *shorter* line inwards. Why? Because moving the taller line inwards will *always* result in a smaller or equal area (width decreases, and the height is still limited by the shorter line). Moving the shorter line inwards gives us a *chance* to find a taller line that could compensate for the reduced width.

## Algorithm
1. Initialize two pointers, `left` at the beginning of the array (index 0) and `right` at the end of the array (index `n-1`).
2. Initialize a variable `maxArea` to 0 (or `Integer.MIN_VALUE` if negative heights were possible, but they are non-negative here).
3. While `left` is less than `right`:
    a. Calculate the current area: `currentArea = min(height[left], height[right]) * (right - left)`.
    b. Update `maxArea = max(maxArea, currentArea)`.
    c. If `height[left]` is less than `height[right]`, increment `left` to explore a potentially taller line on the left.
    d. Otherwise (if `height[right]` is less than or equal to `height[left]`), decrement `right` to explore a potentially taller line on the right.
4. Return `maxArea`.

## Concept to Remember
*   **Two-Pointer Technique:** Efficiently traversing or searching in sorted or partially sorted data structures by using two pointers that move towards each other.
*   **Greedy Approach:** Making locally optimal choices at each step with the hope of finding a global optimum. In this case, always moving the shorter pointer is the greedy choice.
*   **Area Calculation:** Understanding that area is `width * height`, and for a container, the height is limited by the shorter of the two bounding lines.

## Common Mistakes
*   **Brute Force (O(n^2)):** Trying every possible pair of lines, which is too slow for larger inputs.
*   **Incorrect Pointer Movement:** Moving the pointer associated with the taller line instead of the shorter one, which misses potential larger areas.
*   **Off-by-One Errors:** Incorrectly calculating the width (`right - left + 1` instead of `right - left`).
*   **Integer Overflow:** While unlikely with typical LeetCode constraints for this problem, it's good practice to consider if intermediate calculations could exceed integer limits.

## Complexity Analysis
*   **Time:** O(n) - The two pointers start at opposite ends and move towards each other. In the worst case, each pointer traverses the array once.
*   **Space:** O(1) - We only use a few extra variables to store pointers and the maximum area, regardless of the input size.

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
        // Initialize maxArea to the smallest possible integer value to ensure any valid area will be larger.
        int max = Integer.MIN_VALUE; 

        // Continue as long as the left pointer is to the left of the right pointer.
        while (i < j) {
            // Calculate the current area:
            // The height of the container is limited by the shorter of the two lines (height[i] or height[j]).
            // The width of the container is the distance between the two pointers (j - i).
            int currentArea = Math.min(height[i], height[j]) * (j - i);
            // Update maxArea if the currentArea is greater than the previously recorded maximum area.
            max = Math.max(max, currentArea);

            // Move the pointer that points to the shorter line inwards.
            // This is the greedy step: moving the shorter line gives us a chance to find a taller line,
            // which might compensate for the reduced width and potentially yield a larger area.
            // Moving the taller line would guarantee a smaller or equal area because the height is still limited by the shorter line.
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
*   **Explain the Two-Pointer Logic:** Clearly articulate *why* you are moving the shorter pointer. This is the core of the solution and demonstrates understanding.
*   **Walk Through an Example:** Use a small array (e.g., `[1,8,6,2,5,4,8,3,7]`) and trace the pointer movements and area calculations to show your thought process.
*   **Discuss Edge Cases:** Consider what happens with an empty array (though constraints usually prevent this), an array with two elements, or an array where all heights are the same.
*   **Mention Brute Force First (Briefly):** Acknowledge the naive O(n^2) approach and then explain why the O(n) two-pointer solution is superior.

## Revision Checklist
- [ ] Understand the problem statement: find max area between two vertical lines.
- [ ] Recall the area formula: `min(h1, h2) * width`.
- [ ] Implement the two-pointer approach.
- [ ] Correctly initialize `left`, `right`, and `maxArea`.
- [ ] Implement the `while (left < right)` loop.
- [ ] Calculate `currentArea` accurately.
- [ ] Update `maxArea` using `Math.max`.
- [ ] Implement the correct pointer movement logic (`if (height[left] < height[right]) left++; else right--;`).
- [ ] Return `maxArea`.
- [ ] Analyze time and space complexity.

## Similar Problems
*   3Sum
*   Container With Most Water II (a harder variation)
*   Trapping Rain Water (different problem, but also involves heights and water levels)

## Tags
`Array` `Two Pointers` `Greedy`
