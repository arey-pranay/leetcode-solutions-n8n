# Min Stack

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Stack` `Design`  
**Time:** See complexity section  
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
The core challenge is to retrieve the minimum element in O(1) time. A naive approach of iterating through the stack every time `getMin` is called would be O(n). To achieve O(1), we need to have the minimum value readily available. The "aha moment" is realizing that when we push a new element, the new minimum will either be the new element itself or the previous minimum. We can store this information alongside the element.

## Algorithm
1.  Initialize an empty data structure to represent the stack. A `List` of `int[]` arrays is suitable, where each `int[]` will store `{value, current_minimum}`.
2.  **`push(val)`**:
    *   If the stack is empty, the current minimum is `val`. Push `{val, val}` onto the stack.
    *   If the stack is not empty, get the `current_minimum` from the top element of the stack.
    *   Calculate the new minimum: `new_min = min(val, current_minimum)`.
    *   Push `{val, new_min}` onto the stack.
3.  **`pop()`**:
    *   Remove the top element from the stack.
4.  **`top()`**:
    *   If the stack is empty, return a sentinel value (e.g., -1 or throw an exception).
    *   Otherwise, return the `value` (the first element) of the top `int[]` on the stack.
5.  **`getMin()`**:
    *   If the stack is empty, return a sentinel value (e.g., -1 or throw an exception).
    *   Otherwise, return the `current_minimum` (the second element) of the top `int[]` on the stack.

## Concept to Remember
*   **Stack Data Structure**: Understanding LIFO (Last-In, First-Out) behavior and its operations (`push`, `pop`, `top`).
*   **Constant Time Operations**: The requirement to perform `getMin` in O(1) time necessitates storing auxiliary information.
*   **Auxiliary Data**: Using extra space to store derived information (like the minimum) to optimize retrieval time.
*   **Edge Cases**: Handling empty stack scenarios gracefully.

## Common Mistakes
*   **Not storing the minimum with each element**: Trying to find the minimum by iterating through the stack on `getMin` calls, leading to O(n) time complexity.
*   **Incorrectly updating the minimum on `push`**: Forgetting to compare the new value with the *previous* minimum.
*   **Handling empty stack**: Not returning a sensible value or throwing an exception when `top()` or `getMin()` is called on an empty stack.
*   **Modifying the original minimum logic**: If using two stacks, incorrectly popping from the minimum stack when the popped element is not the current minimum.

## Complexity Analysis
*   **Time**:
    *   `push(val)`: O(1) - We perform a constant number of operations (checking emptiness, getting top, comparison, adding to list).
    *   `pop()`: O(1) - Removing the last element from an `ArrayList` is O(1) on average.
    *   `top()`: O(1) - Accessing the last element of an `ArrayList` is O(1).
    *   `getMin()`: O(1) - Accessing the last element of an `ArrayList` is O(1).
*   **Space**: O(n) - Where n is the number of elements in the stack. We store an `int[]` of size 2 for each element pushed onto the stack.

## Commented Code
```java
import java.util.ArrayList; // Import the ArrayList class for dynamic array implementation.
import java.util.List; // Import the List interface.

class MinStack {
    private List<int[]> st; // Declare a private List to store elements. Each element will be an int array of size 2: {value, current_minimum}.

    public MinStack() {
        st = new ArrayList<>(); // Initialize the ArrayList when a new MinStack object is created.
    }
    
    public void push(int val) {
        // Get the top element's data if the stack is not empty.
        // If empty, use a default int array where both value and min are 'val' itself.
        int[] top = st.isEmpty() ? new int[]{val, val} : st.get(st.size() - 1); 
        
        // Extract the current minimum from the top element.
        int min_val = top[1]; 
        
        // If the new value is smaller than the current minimum, update min_val.
        if (min_val > val) { 
            min_val = val; // The new minimum is the value itself.
        }
        
        // Add a new int array to the stack: {the pushed value, the updated minimum up to this point}.
        st.add(new int[]{val, min_val});        
    }
    
    public void pop() {
        // Remove the last element from the list, effectively popping from the stack.
        // This also removes the associated minimum value for that state.
        st.remove(st.size() - 1); 
    }
    
    public int top() {
        // If the stack is empty, return -1 (or throw an exception) as a sentinel value.
        // Otherwise, return the actual value (first element of the int array) from the top of the stack.
        return st.isEmpty() ? -1 : st.get(st.size() - 1)[0]; 
    }
    
    public int getMin() {
        // If the stack is empty, return -1 (or throw an exception) as a sentinel value.
        // Otherwise, return the stored minimum value (second element of the int array) from the top of the stack.
        return st.isEmpty() ? -1 : st.get(st.size() - 1)[1]; 
    }
}
```

## Interview Tips
1.  **Clarify Constraints**: Ask about the range of integer values and potential for duplicates. This can influence sentinel values or if specific data types are needed.
2.  **Explain the O(1) `getMin`**: Emphasize how storing the minimum with each element is the key to achieving constant time retrieval.
3.  **Discuss Space-Time Tradeoff**: Acknowledge that this solution uses O(n) space to achieve O(1) time for `getMin`, which is a common tradeoff in algorithm design.
4.  **Consider Alternatives (Briefly)**: You might briefly mention a two-stack approach (one for values, one for minimums) and its potential pitfalls (e.g., handling duplicate minimums).

## Revision Checklist
- [ ] Understand the core requirement: O(1) `getMin`.
- [ ] Implement `push` to store both value and current minimum.
- [ ] Implement `pop` to correctly remove the top element.
- [ ] Implement `top` to return the actual value.
- [ ] Implement `getMin` to return the stored minimum.
- [ ] Handle empty stack edge cases for `top` and `getMin`.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the intuition and algorithm clearly.

## Similar Problems
*   Implement Queue using Stacks
*   Sliding Window Maximum
*   Shortest Subarray with Sum at Least K

## Tags
`Stack` `Design` `Array` `List`
