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
The core idea is that for any bar `h` at index `i`, the largest rectangle with `h` as its height will extend as far left and right as possible until it encounters a bar shorter than `h`. A monotonic increasing stack helps us find these boundaries efficiently. When we encounter a bar shorter than the top of the stack, it means the bar at the top of the stack can no longer extend to the right. We then pop it, calculate its maximum possible rectangle area (using the current bar as the right boundary and the previous element in the stack as the left boundary), and update our overall maximum.

## Algorithm
1. Initialize an empty stack `st` to store indices of bars.
2. Initialize `ans` to 0, which will store the maximum rectangle area found so far.
3. Get the length of the `heights` array, `n`.
4. Iterate from `i = 0` to `n` (inclusive). The `i = n` case is a sentinel to process any remaining bars in the stack.
5. Inside the loop, determine the current height `currH`. If `i == n`, `currH` is 0; otherwise, it's `heights[i]`.
6. While the stack is not empty AND `currH` is less than the height of the bar at the index `st.peek()`:
    a. Pop the index `peekI` from the stack. This is the bar whose maximum rectangle area we will now calculate.
    b. Get the `height` of this popped bar: `heights[peekI]`.
    c. Calculate the `width` of the rectangle. If the stack is now empty, it means the popped bar was the shortest so far, and its rectangle extends from the beginning of the histogram up to `i-1`. So, `width = i`. If the stack is not empty, the left boundary is the index of the bar just below the popped one in the stack (`st.peek()`), and the right boundary is `i-1`. Thus, `width = i - st.peek() - 1`.
    d. Calculate the `area` = `height * width`.
    e. Update `ans = Math.max(ans, area)`.
7. Push the current index `i` onto the stack.
8. After the loop finishes, return `ans`.

## Concept to Remember
*   **Monotonic Stack:** A stack where elements are always in increasing or decreasing order. This problem uses a monotonically increasing stack to find the nearest smaller elements to the left and right.
*   **Stack for Range Queries:** Stacks are excellent for problems that involve finding the "next greater/smaller element" or determining boundaries for a current element.
*   **Sentinel Value:** Using a sentinel value (like `i=n` with `currH=0`) simplifies the logic by ensuring all elements remaining in the stack are processed at the end.

## Common Mistakes
*   **Off-by-one errors in width calculation:** Incorrectly calculating the width when the stack becomes empty or when determining the left boundary.
*   **Not handling the end of the array:** Forgetting to process any bars left in the stack after the loop finishes, which can be solved by the sentinel value.
*   **Incorrectly comparing heights:** Comparing `currH` with `heights[i]` instead of `heights[st.peek()]` inside the `while` loop.
*   **Stack storing heights instead of indices:** Storing indices is crucial for calculating the width correctly.

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
        // Initialize 'ans' to store the maximum rectangle area found so far.
        int ans = 0;
        // Get the number of bars in the histogram.
        int n = heights.length;

        // Iterate through the bars. The loop goes up to 'n' (inclusive) to act as a sentinel.
        // When i == n, we use a current height of 0 to force processing of any remaining bars in the stack.
        for(int i = 0; i <= n; i++) {
            // Determine the current height. If i is n, it's a sentinel 0 height.
            int currH = (i == n) ? 0 : heights[i];

            // While the stack is not empty AND the current height is less than the height of the bar at the top of the stack:
            // This means the bar at the top of the stack cannot extend further to the right (because currH is shorter).
            while(!st.isEmpty() && currH < heights[st.peek()]) {
                // Pop the index of the bar from the stack. This is the bar whose maximum rectangle area we will calculate.
                int peekI = st.pop();
                // Get the height of the popped bar.
                int height = heights[peekI];
                // Calculate the width of the rectangle.
                // If the stack is empty after popping, it means the popped bar was the shortest so far,
                // and its rectangle extends from the beginning of the histogram up to the current index 'i' (exclusive).
                // So, the width is 'i'.
                // If the stack is not empty, the left boundary is the index of the bar just below the popped one in the stack (st.peek()),
                // and the right boundary is the current index 'i' (exclusive).
                // Thus, the width is i - st.peek() - 1.
                int width = st.isEmpty() ? i : i - st.peek() - 1;
                // Calculate the area of the rectangle with the popped bar's height and calculated width.
                int area = height * width;
                // Update the maximum area found so far.
                ans = Math.max(ans, area);
            }
            // Push the current index 'i' onto the stack.
            // This maintains the monotonic increasing property of the stack (by height).
            st.push(i);
        }
        // Return the maximum rectangle area found.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the Monotonic Stack:** Clearly articulate why a monotonic stack is suitable for this problem and how it helps find the nearest smaller elements.
*   **Walk Through an Example:** Use a small example array (e.g., `[2,1,5,6,2,3]`) and trace the stack's state and calculations step-by-step.
*   **Discuss Edge Cases:** Mention how the sentinel value (`i=n`, `currH=0`) handles remaining elements in the stack and how an empty input array would be handled (though the code implicitly handles it with `n=0`).
*   **Clarify Width Calculation:** Be precise when explaining how the width is determined, especially the `i - st.peek() - 1` part.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Grasp the intuition behind using a monotonic stack.
- [ ] Implement the algorithm with a stack storing indices.
- [ ] Correctly calculate the width of rectangles when popping from the stack.
- [ ] Handle the end of the array using a sentinel value or a separate loop.
- [ ] Analyze time and space complexity.
- [ ] Practice tracing the algorithm with different examples.

## Similar Problems
*   Trapping Rain Water
*   Maximal Rectangle
*   Shortest Subarray with Sum at Least K
*   Daily Temperatures

## Tags
`Array` `Stack` `Monotonic Stack`
