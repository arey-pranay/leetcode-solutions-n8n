# Min Stack

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Stack` `Design`  
**Time:** O(1)  
**Space:** O(n)

---

## Solution (java)

```java
class MinStack {
    private List<int[]> st;

    public MinStack() {
        st = new ArrayList<>();
    }
    
    public void push(int val) {
        int[] top = st.isEmpty() ? new int[]{val, val} : st.get(st.size() - 1);
        int min_val = top[1];
        if (min_val > val) {
            min_val = val;
        }
        st.add(new int[]{val, min_val});        
    }
    
    public void pop() {
        st.remove(st.size() - 1);
    }
    
    public int top() {
        return st.isEmpty() ? -1 : st.get(st.size() - 1)[0];
    }
    
    public int getMin() {
        return st.isEmpty() ? -1 : st.get(st.size() - 1)[1];
    }
}
```

---

---
## Quick Revision
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
This is achieved by storing the current minimum along with each element pushed onto the stack.

## Intuition
The core challenge is to retrieve the minimum element in O(1) time. A naive approach of iterating through the stack every time `getMin()` is called would be O(n). To achieve O(1), we need to have the minimum value readily available. The "aha moment" is realizing that when we push a new element, the new minimum will either be the new element itself or the previous minimum. We can store this information alongside the element.

## Algorithm
1.  **Initialization**: Create a data structure (e.g., a `List` or `Stack`) to store elements. Each element stored will be a pair: the actual value and the minimum value encountered up to that point.
2.  **`push(val)`**:
    *   If the stack is empty, push a pair `[val, val]` (both the value and the minimum are `val`).
    *   If the stack is not empty, get the minimum value from the top element of the stack.
    *   Calculate the new minimum: `new_min = min(val, current_minimum)`.
    *   Push the pair `[val, new_min]` onto the stack.
3.  **`pop()`**: Remove the top element from the stack.
4.  **`top()`**: Return the actual value of the top element (the first element of the pair).
5.  **`getMin()`**: Return the minimum value stored in the top element (the second element of the pair).

## Concept to Remember
*   **Stack Data Structure**: Understanding LIFO (Last-In, First-Out) behavior and its operations (`push`, `pop`, `top`).
*   **Constant Time Operations**: The requirement to perform `getMin` in O(1) time necessitates pre-computation or auxiliary data structures.
*   **Auxiliary Space**: Using extra space to optimize time complexity.
*   **State Management**: Maintaining the minimum value across stack operations.

## Common Mistakes
*   **Not handling empty stack**: Forgetting to check if the stack is empty before accessing `top()` or `getMin()`, leading to errors.
*   **Incorrectly updating minimum**: Only storing the current minimum and not associating it with each element, making `pop` operations lose track of the previous minimum.
*   **Using a separate min variable**: A single variable to track the minimum won't work because when that minimum element is popped, the stack loses track of the *next* minimum.
*   **Inefficient `getMin`**: Implementing `getMin` by iterating through the stack, failing the O(1) time complexity requirement.

## Complexity Analysis
*   **Time**: O(1) - Each operation (`push`, `pop`, `top`, `getMin`) involves a constant number of operations on the underlying list/stack.
*   **Space**: O(n) - In the worst case, we store an additional integer (the minimum) for each element pushed onto the stack, where 'n' is the number of elements.

## Commented Code
```java
import java.util.ArrayList; // Import the ArrayList class for dynamic array functionality.
import java.util.List;      // Import the List interface.

class MinStack {
    // Declare a private List of integer arrays. Each array will store [value, current_minimum].
    private List<int[]> st;

    // Constructor for the MinStack class.
    public MinStack() {
        // Initialize the ArrayList to store the stack elements.
        st = new ArrayList<>();
    }
    
    // Method to push an element onto the stack.
    public void push(int val) {
        // Get the top element's data if the stack is not empty, otherwise use a default [val, val] for the first element.
        // top[0] is the actual value, top[1] is the minimum value up to that point.
        int[] top = st.isEmpty() ? new int[]{val, val} : st.get(st.size() - 1);
        
        // Get the minimum value from the previous top element.
        int min_val = top[1];
        
        // If the new value is smaller than the current minimum, update the minimum.
        if (min_val > val) {
            min_val = val;
        }
        
        // Add a new integer array to the list: [the pushed value, the updated minimum].
        st.add(new int[]{val, min_val});        
    }
    
    // Method to remove the top element from the stack.
    public void pop() {
        // Remove the last element from the ArrayList, effectively popping from the stack.
        // This is O(1) for ArrayList if removing from the end.
        st.remove(st.size() - 1);
    }
    
    // Method to get the top element's value.
    public int top() {
        // If the stack is empty, return -1 (or throw an exception, depending on requirements).
        // Otherwise, return the actual value (the first element of the pair) from the top of the stack.
        return st.isEmpty() ? -1 : st.get(st.size() - 1)[0];
    }
    
    // Method to get the minimum element currently in the stack.
    public int getMin() {
        // If the stack is empty, return -1 (or throw an exception).
        // Otherwise, return the stored minimum value (the second element of the pair) from the top of the stack.
        return st.isEmpty() ? -1 : st.get(st.size() - 1)[1];
    }
}
```

## Interview Tips
*   **Clarify Edge Cases**: Ask about behavior for an empty stack (e.g., what to return for `top()` or `getMin()`). The provided solution returns -1, but throwing an exception might be preferred.
*   **Explain the Trade-off**: Clearly articulate why you're using extra space (to achieve O(1) time for `getMin`).
*   **Walk Through Examples**: Use a simple example like `push(5), push(2), push(3), getMin(), pop(), getMin()` to demonstrate how your data structure tracks the minimum.
*   **Consider Alternatives**: Briefly mention other approaches (like using two stacks) and why this approach is efficient.

## Revision Checklist
- [ ] Understand the core requirement: O(1) `getMin`.
- [ ] Implement `push` to store both value and current minimum.
- [ ] Implement `pop` to correctly remove the top element.
- [ ] Implement `top` to return the actual value.
- [ ] Implement `getMin` to return the stored minimum.
- [ ] Handle empty stack scenarios.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Implement Queue using Stacks
*   Sliding Window Maximum
*   Shortest Subarray with Sum at Least K

## Tags
`Stack` `Design` `Array` `List`

## My Notes
just adding for examples.
