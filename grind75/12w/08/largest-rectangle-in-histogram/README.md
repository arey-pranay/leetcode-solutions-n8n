# Largest Rectangle In Histogram

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Stack` `Monotonic Stack`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public int largestRectangleArea(int[] heights) {        
        Stack<Integer> st = new Stack<>();
        int ans = 0;
        int n = heights.length;
        for(int i=0;i<=n;i++){
            int h = i==n ? 0 :heights[i];
            while(!st.isEmpty() && h<heights[st.peek()]){
                int height = heights[st.pop()];
                int width = st.isEmpty() ? i : i-st.peek()-1;//2
                ans = Math.max(ans,height*width);
            }
            st.push(i);
        }
        return ans;
    }
}
```

---

---
## Quick Revision
Given an array of integers representing the heights of bars in a histogram, find the area of the largest rectangle.
This is solved efficiently using a monotonic stack to track potential rectangle boundaries.

## Intuition
The core idea is that for any bar, the largest rectangle it can be a part of is limited by the first shorter bar to its left and the first shorter bar to its right. If we can efficiently find these boundaries for every bar, we can calculate the maximum area. A monotonic stack (specifically, an increasing one) helps us find these boundaries. When we encounter a bar shorter than the top of the stack, it means the bar at the top of the stack can no longer extend to the right. We then pop it, calculate its maximum possible rectangle area using the current bar as the right boundary and the element below it in the stack as the left boundary, and update our overall maximum.

## Algorithm
1. Initialize an empty stack `st` to store indices of bars.
2. Initialize `ans` to 0, which will store the maximum rectangle area found so far.
3. Get the length of the `heights` array, `n`.
4. Iterate through the `heights` array from `i = 0` to `n` (inclusive). The `i == n` case is a sentinel to process any remaining bars in the stack.
5. Inside the loop, determine the current height `h`. If `i == n`, `h` is 0; otherwise, `h` is `heights[i]`.
6. While the stack is not empty AND the current height `h` is less than the height of the bar at the index at the top of the stack (`heights[st.peek()]`):
    a. Pop the index from the stack. Let this popped index be `top_index`.
    b. Get the `height` of the popped bar: `heights[top_index]`.
    c. Calculate the `width` of the rectangle. If the stack is now empty, it means the popped bar was the shortest so far, and its rectangle extends from the beginning of the histogram up to the current index `i`. So, `width = i`. If the stack is not empty, the left boundary is the index of the bar just below the popped one in the stack (`st.peek()`), and the right boundary is the current index `i`. The width is `i - st.peek() - 1`.
    d. Update `ans` with the maximum of `ans` and `height * width`.
7. Push the current index `i` onto the stack.
8. After the loop finishes, return `ans`.

## Concept to Remember
*   **Monotonic Stack:** A stack where elements are in a strictly increasing or decreasing order. It's useful for finding the "next greater/smaller element" or "previous greater/smaller element" efficiently.
*   **Stack for Range Queries:** Using a stack to maintain a sequence of indices that define potential boundaries for calculations over a range.
*   **Sentinel Value:** Using a special value (like 0 height at `i=n`) to simplify loop termination and ensure all elements are processed.

## Common Mistakes
*   **Off-by-one errors in width calculation:** Incorrectly calculating the width when the stack becomes empty or when determining the left boundary.
*   **Not handling the end of the array:** Failing to process bars remaining in the stack after the loop finishes, which can be done using a sentinel height of 0.
*   **Incorrect stack condition:** Using `h <= heights[st.peek()]` instead of `h < heights[st.peek()]` can lead to incorrect width calculations for bars of equal height.
*   **Stack storing heights instead of indices:** The stack should store indices to correctly calculate the width based on positions.

## Complexity Analysis
*   Time: O(n) - Each bar is pushed onto and popped from the stack at most once. The loop runs `n+1` times.
*   Space: O(n) - In the worst case (e.g., a strictly increasing histogram), the stack can store all `n` indices.

## Commented Code
```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        // Initialize a stack to store indices of bars.
        // This stack will maintain indices of bars in increasing order of their heights.
        Stack<Integer> st = new Stack<>();
        
        // Initialize the maximum area found so far to 0.
        int ans = 0;
        
        // Get the number of bars in the histogram.
        int n = heights.length;
        
        // Iterate through the bars. We iterate up to n (inclusive) to handle the case
        // where remaining bars in the stack need to be processed.
        for(int i=0; i<=n; i++){
            // Determine the current height. If i == n, we use a height of 0 (sentinel)
            // to ensure all bars remaining in the stack are processed.
            int h = i==n ? 0 : heights[i];
            
            // While the stack is not empty AND the current height 'h' is less than
            // the height of the bar at the index at the top of the stack:
            // This means the bar at st.peek() can no longer extend to the right
            // because the current bar 'h' is shorter.
            while(!st.isEmpty() && h < heights[st.peek()]){
                // Pop the index of the bar that is taller than the current bar.
                // This bar's maximum rectangle area needs to be calculated now.
                int height = heights[st.pop()];
                
                // Calculate the width of the rectangle for the popped bar.
                // If the stack is empty after popping, it means the popped bar was the
                // shortest so far, and its rectangle extends from the beginning (index 0)
                // up to the current index 'i'. So, width is 'i'.
                // If the stack is not empty, the left boundary of the rectangle is the
                // index of the bar just below the popped one in the stack (st.peek()),
                // and the right boundary is the current index 'i'.
                // The width is then (current_index - index_of_previous_shorter_bar - 1).
                int width = st.isEmpty() ? i : i - st.peek() - 1;
                
                // Update the maximum area found so far.
                ans = Math.max(ans, height * width);
            }
            
            // Push the current index 'i' onto the stack. This maintains the
            // increasing order of heights in the stack.
            st.push(i);
        }
        
        // Return the maximum rectangle area found.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the Monotonic Stack:** Clearly articulate why a monotonic stack is suitable for this problem and how it helps find the left and right boundaries efficiently.
*   **Walk Through an Example:** Use a small example array (e.g., `[2,1,5,6,2,3]`) and trace the stack's state and `ans` updates step-by-step.
*   **Handle Edge Cases:** Discuss how the sentinel value (height 0 at `i=n`) simplifies the logic for processing remaining stack elements.
*   **Clarify Width Calculation:** Be precise when explaining how the `width` is calculated, especially the `i - st.peek() - 1` part.

## Revision Checklist
- [ ] Understand the problem statement clearly.
- [ ] Grasp the intuition behind using a monotonic stack.
- [ ] Implement the algorithm with a stack storing indices.
- [ ] Correctly calculate the width for popped elements.
- [ ] Handle the end of the array using a sentinel value.
- [ ] Analyze time and space complexity.
- [ ] Practice tracing the algorithm with examples.

## Similar Problems
*   Trapping Rain Water
*   Maximal Rectangle
*   Daily Temperatures
*   Next Greater Element I/II

## Tags
`Array` `Stack` `Monotonic Stack`
