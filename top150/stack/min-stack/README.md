# Min Stack

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Stack` `Design`  
**Time:** O(1)  
**Space:** O(N)

---

## Solution (java)

```java
class MinStack {
    ListNode head;
    int min;
    public MinStack() {
        head=null;
        min=Integer.MAX_VALUE;
    }
    
    public void push(int val) {
        if(min>val){
            min=val;
        }
        head=new ListNode(val,min,head);
    }
    
    public void pop() {
        head=head.next;
        if(head==null){
            min=Integer.MAX_VALUE;
        }else{
            min=head.currentMin;
        }
    }
    
    public int top() {
        return head.val;
    }
    
    public int getMin() {
        return head.currentMin;
    }
}

class ListNode{
    int val;
    int currentMin;
    ListNode next;
    ListNode(){

    }
    ListNode(int val){
        this.val=val;
    }
    ListNode(int val,int currMin,ListNode next){
        this.val=val;
        this.currentMin=currMin;
        this.next=next;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```

---

---
## Quick Revision
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
This is achieved by storing the minimum element alongside each node in the stack.

## Intuition
The core challenge is to retrieve the minimum element in O(1) time. A naive approach of iterating through the stack for `getMin()` would be O(N). To achieve O(1), we need to have the minimum value readily available. The "aha moment" is realizing that if we store the minimum value *up to that point* with each element pushed onto the stack, then the minimum of the entire stack will always be the minimum stored with the top element.

## Algorithm
1.  **Data Structure:** Use a linked list where each node stores its value, the minimum value encountered *up to and including* this node, and a pointer to the next node.
2.  **`MinStack()` Constructor:** Initialize the `head` of the linked list to `null` and `min` to `Integer.MAX_VALUE` (or any value larger than any possible input).
3.  **`push(int val)`:**
    *   If the new `val` is less than the current `min`, update `min` to `val`.
    *   Create a new `ListNode` with `val`, the *current* `min` (which might have just been updated), and the current `head` as its `next` pointer.
    *   Set the `head` of the stack to this new node.
4.  **`pop()`:**
    *   Move the `head` pointer to the next node (`head.next`).
    *   If the stack becomes empty (`head` is `null`), reset `min` to `Integer.MAX_VALUE`.
    *   Otherwise, update `min` to the `currentMin` value stored in the new `head` node.
5.  **`top()`:** Return the `val` of the `head` node.
6.  **`getMin()`:** Return the `currentMin` value stored in the `head` node.

## Concept to Remember
*   **Linked Lists:** Understanding how to implement and traverse linked lists is fundamental.
*   **Auxiliary Data:** Using extra space to store pre-computed information for faster retrieval.
*   **Constant Time Operations:** Designing data structures to achieve O(1) for critical operations.
*   **State Management:** Keeping track of the minimum value as the stack changes.

## Common Mistakes
*   **Not storing the minimum with each node:** Simply having a global `min` variable that is updated only on `push` will fail if the minimum element is popped.
*   **Incorrectly updating `min` on `pop`:** Forgetting to re-evaluate the minimum after removing the top element, especially when the stack becomes empty.
*   **Off-by-one errors in linked list manipulation:** Incorrectly updating `head` or `next` pointers.
*   **Handling empty stack edge cases:** Not considering what happens when `pop` or `top` is called on an empty stack.

## Complexity Analysis
*   **Time:** O(1) - All operations (`push`, `pop`, `top`, `getMin`) involve a fixed number of steps, regardless of the stack size.
*   **Space:** O(N) - In the worst case, we store N nodes in the linked list, and each node stores its value and the minimum value up to that point.

## Commented Code
```java
// Define a helper class for the nodes in our linked list
class ListNode {
    int val; // The actual value of the node
    int currentMin; // The minimum value in the stack from the bottom up to this node
    ListNode next; // Pointer to the next node in the stack (the one below it)

    // Default constructor (not used in this specific implementation but good practice)
    ListNode() {
    }

    // Constructor for a node with just a value
    ListNode(int val) {
        this.val = val;
    }

    // Constructor for a node with value, current minimum, and next node
    ListNode(int val, int currMin, ListNode next) {
        this.val = val; // Set the node's value
        this.currentMin = currMin; // Set the minimum value up to this point
        this.next = next; // Link this node to the previous top of the stack
    }
}

// The main MinStack class
class MinStack {
    ListNode head; // The head of our linked list, representing the top of the stack
    int min; // Stores the current minimum value in the entire stack

    // Constructor for MinStack
    public MinStack() {
        head = null; // Initialize the stack as empty
        min = Integer.MAX_VALUE; // Initialize min to a very large value, so any push will be smaller
    }

    // Pushes an element onto the stack
    public void push(int val) {
        // If the new value is smaller than the current minimum, update the minimum
        if (min > val) {
            min = val;
        }
        // Create a new node: its value is 'val', its 'currentMin' is the *updated* minimum,
        // and its 'next' points to the current head (making it the new top)
        head = new ListNode(val, min, head);
    }

    // Removes the element on top of the stack
    public void pop() {
        // Move the head pointer to the next node, effectively removing the current top
        head = head.next;
        // If the stack is now empty after popping
        if (head == null) {
            // Reset the minimum to its initial large value
            min = Integer.MAX_VALUE;
        } else {
            // Otherwise, update the global minimum to the 'currentMin' of the new top node
            min = head.currentMin;
        }
    }

    // Gets the top element of the stack
    public int top() {
        // Return the value of the current head node
        return head.val;
    }

    // Retrieves the minimum element in the stack
    public int getMin() {
        // Return the 'currentMin' value stored in the head node, which is the overall minimum
        return head.currentMin;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```

## Interview Tips
*   **Clarify Constraints:** Ask about the range of integer values, potential for duplicate minimums, and the maximum number of operations.
*   **Explain the Trade-off:** Discuss why storing the minimum with each node is necessary for O(1) `getMin` and the resulting O(N) space complexity.
*   **Edge Cases:** Be prepared to discuss how you handle an empty stack for `pop`, `top`, and `getMin`.
*   **Alternative Approaches:** Briefly mention other ways to solve this (e.g., using two stacks) and why your chosen method is suitable.

## Revision Checklist
- [ ] Understand the problem statement for Min Stack.
- [ ] Implement a stack with `push`, `pop`, `top`, and `getMin` in O(1) time.
- [ ] Use a linked list where each node stores its value and the minimum up to that point.
- [ ] Handle the `min` variable correctly during `push` and `pop`.
- [ ] Consider edge cases like an empty stack.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Implement Stack using Queues
*   Implement Queue using Stacks
*   Sliding Window Maximum

## Tags
`Stack` `Linked List` `Design`
