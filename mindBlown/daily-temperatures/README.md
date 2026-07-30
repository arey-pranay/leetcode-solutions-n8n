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
Given an array of daily temperatures, find out the next day that will be warmer than today. The solution uses a stack to track the days with increasing temperatures.

## Intuition
The key insight here is to use a stack to store the indices of the days with decreasing temperatures. When we encounter a new day with higher temperature than the top element on the stack, we know it's the first day that will be warmer than today, so we pop all elements from the stack and update their corresponding answer arrays.

## Algorithm
1. Initialize an empty stack to store indices of days with increasing temperatures.
2. Iterate over the input array from right to left (last element to first).
3. For each element, while the top element on the stack has a temperature less than or equal to the current element, pop it from the stack.
4. If the stack is empty after popping all elements, push the current index onto the stack and mark the answer for this day as 0 (no warmer days found).
5. Otherwise, push the current index onto the stack and update the answer for this day with the difference between its index and the top element on the stack.
6. After iterating over all elements, return the answer array.

## Concept to Remember
*   **Stack**: used to track indices of days with increasing temperatures.
*   **Monotonicity**: we use a monotonic decrease in temperature to trigger updates in our answer array.
*   **Dynamic Programming/Array Processing**: we process the input array in a single pass and update an array as we go.

## Common Mistakes
*   Using a different data structure (e.g. queue) instead of stack, leading to incorrect results.
*   Not initializing the stack or answer array properly before iteration starts.
*   Forgetting to handle edge cases (e.g., when input array is empty).

## Complexity Analysis
- Time: O(n) - reason: single pass through input array
- Space: O(n) - reason: maximum size of the stack

## Commented Code
```java
class Solution {
    public int[] dailyTemperatures(int[] t) {
        // Initialize variables and data structures
        int n = t.length;
        int[] ans = new int[n]; // answer array to store differences
        Stack<Integer> st = new Stack<>(); // stack for days with increasing temps

        // Iterate over input array from right to left
        for (int i = n - 1; i >= 0; i--) {
            // While top element on stack has temperature <= current, pop it
            while (!st.isEmpty() && t[st.peek()] <= t[i]) st.pop();
            
            // If stack is empty after popping all elements, mark answer as 0
            if (st.isEmpty()) ans[i] = 0;
            else {
                // Otherwise, update answer with difference between indices
                ans[i] = st.peek() - i;
            }
            
            // Push current index onto stack
            st.push(i);
        }

        return ans; // return the final answer array
    }
}
```

## Interview Tips
*   Always consider how to optimize your solution for large inputs (in this case, O(n) time complexity).
*   Don't be afraid to use standard data structures like stacks and arrays when they're a good fit for the problem.
*   Pay attention to initialization of variables and data structures before iteration starts.

## Revision Checklist
- [ ] Understand how the stack is used to store indices of days with increasing temperatures.
- [ ] Verify that the while loop pop condition is correct.
- [ ] Ensure proper handling of edge cases (e.g., when input array is empty).
- [ ] Confirm time and space complexity analysis matches actual performance.

## Similar Problems
*   LeetCode 739: Daily Temperatures (similar problem, but with JavaScript)
*   LeetCode 496: Next Greater Element I (uses similar stack-based approach)

## Tags
`Array` `Hash Map` `Stack`

## My Notes
// start from behind
// jbtk chhote wale hai tbtk st.pop() krne hai
// empty hai to 0 value nhi to st.peek()-i value
// st.push()
