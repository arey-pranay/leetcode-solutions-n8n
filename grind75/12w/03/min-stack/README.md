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
    Stack<Integer> st1;
    Stack<Integer> st2;
    public MinStack() {
        st1 = new Stack<>();
        st2 = new Stack<>();
    }
    
    public void push(int value) {
        st1.push(value);
        if(st2.isEmpty() || value <= st2.peek()) st2.push(value);
    }
    
    public void pop() {
        int temp = st1.pop();
        if(!st2.isEmpty() && st2.peek()==temp) st2.pop();
    }
    
    public int top() {
        return st1.peek();
    }
    
    public int getMin() {
        return st2.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```

---

---

## Quick Revision
Implement a stack that supports adding elements, removing elements, and getting the minimum value of the stack. This is achieved by using two stacks: one for storing elements and another for keeping track of the minimum value.

## Intuition
The key insight here is to use an auxiliary stack (`st2`) to keep track of the minimum values seen so far. Whenever we push a new element onto `st1`, we also check if it's smaller than or equal to the top of `st2`. If it is, we push it onto `st2` as well. This way, `st2` always contains the most recent minimum value.

## Algorithm
1. Initialize two stacks: `st1` for storing elements and `st2` for keeping track of minimum values.
2. When pushing an element onto the stack:
	* Push it onto `st1`.
	* If `st2` is empty or the new element is smaller than or equal to the top of `st2`, push it onto `st2`.
3. When popping an element from the stack:
	* Pop it from `st1`.
	* If the popped element is equal to the top of `st2`, pop it from `st2` as well.
4. The minimum value can be retrieved by peeking at the top of `st2`.

## Concept to Remember
• **Stack**: A last-in, first-out (LIFO) data structure.
• **Auxiliary Data Structure**: Using a secondary data structure (`st2`) to simplify problem-solving.

## Common Mistakes
• Forgetting to update both stacks when pushing or popping elements.
• Not initializing the auxiliary stack correctly.
• Misunderstanding the purpose of the auxiliary stack.

## Complexity Analysis
- Time: O(1) / reason: All operations (push, pop, top, getMin) take constant time.
- Space: O(n) / reason: In the worst case, both stacks grow to n elements.

## Commented Code
```java
class MinStack {
    // Stack for storing elements
    Stack<Integer> st1;
    // Auxiliary stack for keeping track of minimum values
    Stack<Integer> st2;

    public MinStack() {
        st1 = new Stack<>();
        st2 = new Stack<>();
    }

    /**
     * Pushes an element onto the stack and updates both stacks if necessary.
     */
    public void push(int value) {
        // Store element on primary stack
        st1.push(value);
        // If secondary stack is empty or new element <= top of secondary stack, update it
        if (st2.isEmpty() || value <= st2.peek()) st2.push(value);
    }

    /**
     * Pops an element from the stack and updates both stacks if necessary.
     */
    public void pop() {
        // Pop element from primary stack
        int temp = st1.pop();
        // If popped element is equal to top of secondary stack, update it
        if (!st2.isEmpty() && st2.peek() == temp) st2.pop();
    }

    /**
     * Returns the top element of the stack.
     */
    public int top() {
        return st1.peek();
    }

    /**
     * Returns the minimum value of the stack.
     */
    public int getMin() {
        return st2.peek();
    }
}
```

## Interview Tips
• Make sure to thoroughly understand the problem requirements before starting to code.
• Use an auxiliary data structure when necessary to simplify problem-solving.
• Pay attention to edge cases and corner scenarios.

## Revision Checklist
- [ ] Understand the problem statement and requirements.
- [ ] Recognize the need for an auxiliary stack.
- [ ] Implement push, pop, top, and getMin operations correctly.
- [ ] Test code thoroughly for edge cases and corner scenarios.
