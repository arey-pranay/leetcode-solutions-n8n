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
Given an array of integers representing daily temperatures, find the number of days you have to wait until a warmer temperature.
Solve using a monotonic stack to efficiently find the next greater element for each day.

## Intuition
The core idea is to find, for each day, the *next* day with a *warmer* temperature. If we process the temperatures from right to left, we can maintain a data structure that stores potential "warmer" days encountered so far. A stack is ideal for this because it naturally keeps track of elements in a way that allows us to quickly find the most recent (and thus closest) warmer day. As we iterate backward, if the current day's temperature is warmer than the top of the stack, it means the top of the stack is a candidate for a warmer day for previous days. If it's not warmer, we pop it because it can never be the *next* warmer day for any day to its left (since the current day is closer and colder or equal).

## Algorithm
1. Initialize an integer array `ans` of the same size as the input temperature array `t` to store the results.
2. Initialize an empty stack `st` to store indices of the temperatures.
3. Iterate through the temperature array `t` from right to left (from index `n-1` down to `0`).
4. For each current index `i`:
    a. While the stack is not empty AND the temperature at the index at the top of the stack (`t[st.peek()]`) is less than or equal to the current temperature (`t[i]`):
        i. Pop the index from the stack. This is because the element at the top of the stack can never be the "next warmer temperature" for any day to its left, as the current day `i` is closer and has a temperature greater than or equal to it.
    b. If the stack is now empty:
        i. Set `ans[i]` to `0`. This means there is no warmer temperature to the right of the current day.
    c. Else (if the stack is not empty):
        i. Set `ans[i]` to `st.peek() - i`. The index at the top of the stack `st.peek()` is the index of the next warmer day, and `st.peek() - i` is the number of days to wait.
    d. Push the current index `i` onto the stack. This index is now a candidate for being the "next warmer temperature" for days to its left.
5. Return the `ans` array.

## Concept to Remember
*   **Monotonic Stack:** A stack where elements are always in a specific order (e.g., strictly increasing or decreasing). In this case, we maintain a decreasing stack of indices based on their corresponding temperatures.
*   **Next Greater Element (NGE) Pattern:** This problem is a variation of finding the "next greater element" for each element in an array. The monotonic stack is a standard technique for solving NGE problems efficiently.
*   **Right-to-Left Traversal:** Processing the array from right to left simplifies finding the *next* warmer day, as elements to the right are encountered first.

## Common Mistakes
*   **Processing Left-to-Right:** Attempting to solve this by iterating from left to right makes it harder to find the *next* warmer day without additional data structures or multiple passes.
*   **Storing Temperatures Instead of Indices:** Storing the actual temperatures on the stack instead of their indices makes it impossible to calculate the difference in days.
*   **Incorrect Stack Condition:** Using `t[st.peek()] < t[i]` instead of `t[st.peek()] <= t[i]` in the `while` loop can lead to incorrect results when there are equal temperatures.
*   **Forgetting to Push Current Index:** Failing to push the current index `i` onto the stack means it won't be considered as a potential warmer day for elements to its left.

## Complexity Analysis
*   Time: O(n) - Each element is pushed onto and popped from the stack at most once. The loop runs `n` times.
*   Space: O(n) - In the worst case (e.g., temperatures are strictly decreasing), the stack can store all `n` indices.

## Commented Code
```java
class Solution {
    public int[] dailyTemperatures(int[] t) {
        // Get the number of days (length of the temperature array).
        int n = t.length;
        // Initialize an array to store the result, where ans[i] will be the number of days to wait for a warmer temperature.
        int[] ans = new int[n];
        // Initialize a stack to store indices of days. This stack will help us find the next warmer day efficiently.
        Stack<Integer> st = new Stack<>();

        // Iterate through the temperatures from right to left. This allows us to easily find the *next* warmer day.
        for(int i = n - 1; i >= 0; i--) {
            // While the stack is not empty AND the temperature at the index on top of the stack is less than or equal to the current day's temperature:
            // This condition means the day at st.peek() is NOT warmer than the current day 'i'.
            // Since we are iterating from right to left, any day to the left of 'i' that is looking for a warmer day will find 'i' first if 'i' is warmer.
            // Therefore, the day at st.peek() can never be the *next* warmer day for any day to the left of 'i'.
            while(!st.isEmpty() && t[st.peek()] <= t[i]) {
                // Pop the index from the stack because it's no longer a candidate for being the *next* warmer day for any preceding day.
                st.pop();
            }

            // After the while loop, if the stack is empty, it means there are no warmer temperatures to the right of the current day 'i'.
            if(st.isEmpty()) {
                // So, the number of days to wait is 0.
                ans[i] = 0;
            } else {
                // If the stack is not empty, the index at the top of the stack (st.peek()) is the index of the *next* warmer day.
                // The number of days to wait is the difference between the index of the warmer day and the current day's index.
                ans[i] = st.peek() - i;
            }

            // Push the current day's index 'i' onto the stack. This index is now a potential candidate for being the *next* warmer day for days to its left.
            st.push(i);
        }
        // Return the array containing the number of days to wait for a warmer temperature for each day.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the Monotonic Stack:** Clearly articulate why a monotonic stack is suitable and how it helps maintain the "next greater element" property.
*   **Trace with an Example:** Walk through a small example (e.g., `[73, 74, 75, 71, 69, 72, 76, 73]`) to demonstrate the stack's behavior and how the `ans` array is populated.
*   **Discuss Edge Cases:** Consider cases like an empty input array, an array with all increasing temperatures, or an array with all decreasing temperatures.
*   **Clarify Right-to-Left vs. Left-to-Right:** Be prepared to explain why the right-to-left approach is more straightforward for this specific problem.

## Revision Checklist
- [ ] Understand the problem statement: find days to wait for a warmer temperature.
- [ ] Recognize the "Next Greater Element" pattern.
- [ ] Implement a monotonic stack (decreasing order of temperatures).
- [ ] Process the array from right to left.
- [ ] Correctly handle stack operations (push, pop, peek).
- [ ] Calculate the difference in indices for the waiting days.
- [ ] Handle the case where no warmer day exists.
- [ ] Analyze time and space complexity.

## Similar Problems
*   84. Largest Rectangle in Histogram
*   496. Next Greater Element I
*   503. Next Greater Element II
*   739. Daily Temperatures (This is the same problem)
*   150. Evaluate Reverse Polish Notation

## Tags
`Array` `Stack` `Monotonic Stack`
