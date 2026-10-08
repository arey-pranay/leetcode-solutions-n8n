# Largest Rectangle In Histogram

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Stack` `Monotonic Stack` `Range Minimum/Maximum Query`  
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
            int currH = i==n ? 0: heights[i];
            while(!st.isEmpty() && currH < heights[st.peek()]){
                int peekI = st.pop();
                int height = heights[peekI];
                int width = st.isEmpty() ? i : i-st.peek()-1;
                int area = height*width;
                // System.out.println(area + " = " + height + " * " +width);
                ans = Math.max(ans, area);
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
Find the largest rectangular area in a histogram represented by an array of bar heights.
This is solved efficiently using a monotonic stack to track potential rectangle boundaries.

## Intuition
The core idea is that for any bar `h` at index `i`, the largest rectangle with `h` as its height will extend as far left and right as possible until it encounters a bar shorter than `h`. A monotonic increasing stack helps us find these boundaries efficiently. When we encounter a bar shorter than the top of the stack, it means the bar at the top of the stack can no longer extend to the right. We then pop it, calculate its maximum possible rectangle area using the current bar as the right boundary and the previous element in the stack (or the beginning of the array) as the left boundary, and update our overall maximum.

## Algorithm
1. Initialize an empty stack `st` to store indices of bars.
2. Initialize `ans` to 0, which will store the maximum area found so far.
3. Get the length of the `heights` array, `n`.
4. Iterate through the `heights` array from index `i = 0` to `n` (inclusive). The `i = n` case is a sentinel to process any remaining bars in the stack.
5. Inside the loop, determine the current height `currH`. If `i` is `n`, `currH` is 0; otherwise, it's `heights[i]`.
6. While the stack is not empty AND `currH` is less than the height of the bar at the index at the top of the stack (`heights[st.peek()]`):
    a. Pop the index `peekI` from the stack. This `peekI` represents a bar whose maximum rectangle we can now calculate.
    b. Get the `height` of this popped bar: `heights[peekI]`.
    c. Calculate the `width` of the rectangle. If the stack is now empty, it means this bar could extend all the way to the beginning of the histogram, so the width is `i`. Otherwise, the width is `i - st.peek() - 1` (the current index `i` is the right boundary, and `st.peek()` is the index of the first bar to its left that is shorter).
    d. Calculate the `area` for this bar: `height * width`.
    e. Update `ans` with the maximum of `ans` and `area`.
7. Push the current index `i` onto the stack.
8. After the loop finishes, return `ans`.

## Concept to Remember
*   **Monotonic Stack:** A stack where elements are always in a specific order (e.g., increasing or decreasing). It's useful for problems involving finding the next/previous greater/smaller element or range queries.
*   **Stack for Range Queries:** The stack helps maintain a sequence of indices that define potential boundaries for rectangles.
*   **Sentinel Value:** Using `i = n` with `currH = 0` acts as a sentinel to ensure all bars remaining in the stack are processed at the end.

## Common Mistakes
*   **Off-by-one errors in width calculation:** Incorrectly calculating the width when the stack becomes empty or when determining the left boundary.
*   **Not handling the end of the array:** Failing to process bars remaining in the stack after iterating through all actual heights.
*   **Incorrectly comparing heights:** Comparing `currH` with `heights[i]` instead of `heights[st.peek()]` inside the `while` loop.
*   **Stack storing heights instead of indices:** The stack should store indices to correctly calculate widths.

## Complexity Analysis
*   Time: O(n) - Each bar is pushed onto and popped from the stack at most once. The loop runs `n+1` times.
*   Space: O(n) - In the worst case (e.g., a strictly increasing histogram), the stack can store all `n` indices.

## Commented Code
```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        // Initialize a stack to store indices of bars.
        // This stack will maintain indices of bars in increasing order of height.
        Stack<Integer> st = new Stack<>();
        // Initialize 'ans' to store the maximum rectangular area found so far.
        int ans = 0;
        // Get the number of bars in the histogram.
        int n = heights.length;

        // Iterate through the bars. The loop goes up to 'n' (inclusive) to handle
        // any remaining bars in the stack after processing all actual heights.
        for(int i = 0; i <= n; i++) {
            // Determine the current height. If 'i' is 'n', it means we've reached
            // the end, so we use a height of 0 to force processing of remaining stack elements.
            int currH = (i == n) ? 0 : heights[i];

            // While the stack is not empty AND the current height is less than the
            // height of the bar at the index on top of the stack:
            // This means the bar at st.peek() can no longer extend to the right.
            while(!st.isEmpty() && currH < heights[st.peek()]) {
                // Pop the index of the bar that is taller than the current bar.
                int peekI = st.pop();
                // Get the height of the popped bar. This is the height of the rectangle we're calculating.
                int height = heights[peekI];
                // Calculate the width of the rectangle.
                // If the stack is empty after popping, it means the popped bar was the shortest
                // so far, and its rectangle extends from the beginning (index 0) up to 'i'.
                // So, width is 'i'.
                // If the stack is not empty, the left boundary is the index of the next
                // shorter bar to the left (st.peek()), and the right boundary is 'i'.
                // The width is 'i - st.peek() - 1'.
                int width = st.isEmpty() ? i : i - st.peek() - 1;
                // Calculate the area of the rectangle with 'height' and 'width'.
                int area = height * width;
                // Update 'ans' if the current area is larger.
                ans = Math.max(ans, area);
            }
            // Push the current index 'i' onto the stack. This maintains the
            // monotonic increasing property of the stack (by height).
            st.push(i);
        }
        // Return the maximum area found.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the Monotonic Stack:** Clearly articulate why a monotonic stack is suitable for this problem and how it helps find the left and right boundaries efficiently.
*   **Trace with an Example:** Walk through a small example (e.g., `[2,1,5,6,2,3]`) to demonstrate how the stack operates and how areas are calculated.
*   **Discuss Edge Cases:** Mention how the sentinel value (`i=n`, `currH=0`) handles the end of the array and ensures all bars are processed. Also, consider an empty input array.
*   **Clarify Width Calculation:** Be precise when explaining the width calculation, especially the `i - st.peek() - 1` part.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Grasp the intuition behind using a monotonic stack.
- [ ] Implement the algorithm step-by-step.
- [ ] Correctly calculate the width of the rectangle.
- [ ] Handle the end-of-array condition using a sentinel.
- [ ] Analyze time and space complexity.
- [ ] Practice tracing the algorithm with different test cases.

## Similar Problems
*   Trapping Rain Water
*   Maximal Rectangle
*   Sliding Window Maximum

## Tags
`Array` `Stack` `Monotonic Stack`
