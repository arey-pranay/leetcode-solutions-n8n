# Number Of Visible People In A Queue

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Stack` `Monotonic Stack`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        // har ek "i" ke baad ascending array me kitni dal skte hai wo hai ans[i];
        // Arrays.fill(ans,0);
        //monotonic stack
        for(int i=n-1; i>=0; i--){
            while(!st.isEmpty() && st.peek() < heights[i]){
                st.pop();
                ans[i]++;
            }
            if(!st.isEmpty()) ans[i]++;
            st.push(heights[i]); 
        }
        return ans;
    }
}

```

---

---
## Quick Revision
Given an array of heights representing people in a queue, find how many people each person can see to their right.
This is solved using a monotonic decreasing stack from right to left.

## Intuition
Imagine standing at position `i` and looking to your right. You can see a person if they are taller than everyone between you and them, or if they are the first person taller than you. This suggests a "visibility" problem that can be efficiently solved by considering elements from right to left. A monotonic stack is ideal here because it helps us quickly discard people who are shorter than someone to their right and thus blocked.

## Algorithm
1. Initialize an integer array `ans` of the same size as `heights` to store the counts of visible people for each person.
2. Initialize an empty stack `st` to store heights of people encountered so far (from right to left). This stack will be monotonic decreasing.
3. Iterate through the `heights` array from right to left (from index `n-1` down to `0`).
4. For each person at index `i` with height `heights[i]`:
    a. While the stack is not empty AND the height of the person at the top of the stack (`st.peek()`) is less than `heights[i]`:
        i. Pop the person from the stack. This person is visible to `heights[i]` because `heights[i]` is taller.
        ii. Increment `ans[i]` because `heights[i]` can see this popped person.
    b. If the stack is not empty after the while loop, it means there's a person to the right of `heights[i]` who is taller than or equal to `heights[i]`. This person is also visible to `heights[i]`.
        i. Increment `ans[i]`.
    c. Push `heights[i]` onto the stack. This maintains the monotonic decreasing property of the stack.
5. Return the `ans` array.

## Concept to Remember
*   **Monotonic Stack:** A stack where elements are always in a specific order (increasing or decreasing). Useful for problems involving finding the next greater/smaller element or visibility.
*   **Right-to-Left Traversal:** Processing an array from the end to the beginning can simplify problems where visibility or relationships depend on elements to the right.
*   **Stack Operations:** Understanding `push`, `pop`, `peek`, and `isEmpty` is crucial for implementing stack-based algorithms.

## Common Mistakes
*   **Incorrect Stack Monotonicity:** Failing to maintain a strictly decreasing or increasing stack, leading to incorrect visibility counts.
*   **Off-by-One Errors:** Miscounting visible people, especially when considering the first taller person to the right.
*   **Forgetting to Push Current Element:** Not pushing the current person's height onto the stack after processing them, breaking the logic for subsequent elements.
*   **Handling Empty Stack:** Not correctly checking if the stack is empty before peeking or popping.

## Complexity Analysis
*   Time: O(n) - reason: Each element is pushed onto and popped from the stack at most once. The loop iterates through the array once.
*   Space: O(n) - reason: In the worst case, the stack can store all elements of the `heights` array (e.g., if the array is strictly increasing).

## Commented Code
```java
class Solution {
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length; // Get the total number of people in the queue.
        int[] ans = new int[n]; // Initialize an array to store the count of visible persons for each person.
        Stack<Integer> st = new Stack<>(); // Initialize a stack to keep track of heights of people to the right, maintaining a monotonic decreasing order.

        // Iterate through the people from right to left.
        for(int i = n - 1; i >= 0; i--) {
            // While the stack is not empty and the person at the top of the stack is shorter than the current person (heights[i]).
            while(!st.isEmpty() && st.peek() < heights[i]) {
                st.pop(); // This shorter person is blocked by heights[i], so remove them from consideration for people further left.
                ans[i]++; // The current person (heights[i]) can see this popped person.
            }
            // If the stack is not empty after popping shorter people, it means there's a person to the right who is taller than or equal to heights[i].
            if(!st.isEmpty()) {
                ans[i]++; // The current person (heights[i]) can see this taller person.
            }
            // Push the current person's height onto the stack. This maintains the monotonic decreasing property.
            st.push(heights[i]);
        }
        return ans; // Return the array containing the counts of visible persons for each person.
    }
}
```

## Interview Tips
*   Clearly explain the monotonic stack approach and why it's suitable for this problem.
*   Walk through an example manually to demonstrate how the stack and `ans` array are updated.
*   Discuss the time and space complexity and justify them.
*   Be prepared to explain what happens if the input array is empty or has only one element.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the core challenge: visibility to the right.
- [ ] Recognize the utility of a monotonic stack.
- [ ] Implement the right-to-left traversal correctly.
- [ ] Handle the conditions for incrementing `ans[i]` accurately.
- [ ] Ensure the stack maintains its monotonic property.
- [ ] Analyze time and space complexity.

## Similar Problems
*   1944. Number of Visible People In A Queue (This problem)
*   496. Next Greater Element I
*   503. Next Greater Element II
*   739. Daily Temperatures
*   84. Largest Rectangle in Histogram

## Tags
`Array` `Stack` `Monotonic Stack`

## My Notes
Hard Question. Monotonic Stack Concept Used.
