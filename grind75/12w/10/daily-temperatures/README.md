# Daily Temperatures

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Stack` `Monotonic Stack`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public int[] dailyTemperatures(int[] t) {
        int n = t.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        for(int i = n-1;i>=0;i--){
            while(!st.isEmpty() && t[st.peek()]<=t[i]) st.pop();
            if(st.isEmpty()) ans[i]=0;
            else ans[i] = st.peek()-i;
            st.push(i);
        }
        return ans;
    }
}

```

---

---
## Quick Revision
Given an array of daily temperatures, return an array such that each element indicates the number of days you have to wait until a warmer temperature.
This problem can be efficiently solved using a monotonic stack.

## Intuition
The core idea is to find, for each day, the *next* warmer day. If we process the temperatures from right to left, we can maintain a data structure that helps us quickly find the nearest warmer temperature to the right. A stack is ideal for this. As we iterate backward, if the current temperature is warmer than the top of the stack, it means the days represented by the stack elements are not the *next* warmer day for any future (to the left) days. We can pop them. If the stack is not empty after popping, the element at the top of the stack represents the index of the next warmer day.

## Algorithm
1. Initialize an integer array `ans` of the same size as the input temperature array `t` to store the results.
2. Initialize an empty stack `st` to store indices of the temperatures.
3. Iterate through the temperature array `t` from right to left (from index `n-1` down to `0`).
4. For each current index `i`:
    a. While the stack is not empty AND the temperature at the index at the top of the stack (`t[st.peek()]`) is less than or equal to the current temperature (`t[i]`):
        i. Pop the index from the stack. This is because the temperature at the popped index is not warmer than the current temperature, so it cannot be the "next warmer day" for any day to its left.
    b. If the stack is now empty:
        i. Set `ans[i]` to `0`. This means there is no warmer temperature to the right.
    c. Else (if the stack is not empty):
        i. Set `ans[i]` to `st.peek() - i`. The difference between the index at the top of the stack (the next warmer day) and the current index `i` gives the number of days to wait.
    d. Push the current index `i` onto the stack. This index might be the "next warmer day" for days to its left.
5. Return the `ans` array.

## Concept to Remember
*   **Monotonic Stack:** A stack where elements are maintained in a specific order (either strictly increasing or decreasing). In this case, we use a decreasing monotonic stack (of temperatures, implicitly by storing indices).
*   **Right-to-Left Traversal:** Processing an array from the end to the beginning can simplify finding the "next" element with a certain property.
*   **Stack for Nearest Greater Element:** Stacks are a common data structure for problems involving finding the nearest element (to the left or right) that satisfies a condition (e.g., greater, smaller).

## Common Mistakes
*   **Processing Left-to-Right:** While possible, a left-to-right approach for finding the *next* warmer day is more complex and often requires a different stack management strategy or additional data structures. The right-to-left approach is more intuitive here.
*   **Storing Temperatures Instead of Indices:** Storing the actual temperatures in the stack makes it harder to calculate the difference in days. Storing indices allows direct calculation of the distance.
*   **Incorrect Stack Condition:** Forgetting to pop elements that are less than or equal to the current temperature will lead to incorrect "next warmer day" calculations.
*   **Off-by-One Errors:** Miscalculating the difference `st.peek() - i` or handling the `ans[i] = 0` case incorrectly.

## Complexity Analysis
*   Time: O(n) - Each element is pushed onto and popped from the stack at most once. The loop iterates through the array once.
*   Space: O(n) - In the worst case (e.g., temperatures are strictly decreasing), the stack can hold all `n` indices.

## Commented Code
```java
class Solution {
    public int[] dailyTemperatures(int[] t) {
        int n = t.length; // Get the number of days (length of the temperature array).
        int[] ans = new int[n]; // Initialize an array to store the result, same size as input.
        Stack<Integer> st = new Stack<>(); // Initialize a stack to store indices of temperatures.

        // Iterate through the temperature array from right to left.
        // This allows us to easily find the *next* warmer day to the right.
        for(int i = n - 1; i >= 0; i--) {
            // While the stack is not empty AND the temperature at the index on top of the stack
            // is less than or equal to the current temperature t[i].
            // We pop because these temperatures are not warmer than the current one,
            // so they cannot be the "next warmer day" for any day to the left of 'i'.
            while(!st.isEmpty() && t[st.peek()] <= t[i]) {
                st.pop(); // Remove indices of non-warmer temperatures from the stack.
            }

            // After popping, if the stack is empty, it means there's no warmer temperature to the right of index 'i'.
            if(st.isEmpty()) {
                ans[i] = 0; // Set the waiting days to 0.
            } else {
                // If the stack is not empty, the index at the top of the stack (st.peek())
                // is the index of the next warmer temperature to the right of 'i'.
                // The difference st.peek() - i gives the number of days to wait.
                ans[i] = st.peek() - i; // Calculate and store the number of days to wait.
            }

            // Push the current index 'i' onto the stack.
            // This index might be the "next warmer day" for days to its left.
            st.push(i);
        }

        return ans; // Return the array containing the number of days to wait for a warmer temperature.
    }
}
```

## Interview Tips
*   **Explain the Monotonic Stack:** Clearly articulate why a monotonic stack is suitable and how it helps maintain the "next greater element" property efficiently.
*   **Trace with an Example:** Walk through a small example (e.g., `[73, 74, 75, 71, 69, 72, 76, 73]`) to demonstrate the stack's behavior and how the `ans` array is populated.
*   **Discuss Trade-offs:** Briefly mention why a brute-force O(n^2) approach would be too slow and why the stack approach achieves O(n).
*   **Consider Edge Cases:** Think about arrays with all increasing temperatures, all decreasing temperatures, or all same temperatures.

## Revision Checklist
- [ ] Understand the problem: find days until next warmer temp.
- [ ] Recognize the "next greater element" pattern.
- [ ] Implement a monotonic stack (decreasing).
- [ ] Choose the correct traversal direction (right-to-left).
- [ ] Store indices in the stack, not values.
- [ ] Correctly calculate the difference in indices.
- [ ] Handle the case where no warmer day exists.
- [ ] Analyze time and space complexity.

## Similar Problems
*   84. Largest Rectangle in Histogram (uses monotonic stack)
*   496. Next Greater Element I
*   503. Next Greater Element II
*   739. Daily Temperatures (this problem)

## Tags
`Array` `Stack` `Monotonic Stack`
