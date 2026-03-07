# Add Two Numbers

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Linked List` `Math` `Recursion`  
**Time:** See complexity section  
**Space:** See complexity section

---

## Solution (java)

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        ListNode temp1 = l1;
        ListNode temp2 = l2;
        int carry = 0;
        
        while (temp1 != null || temp2 != null) {
            int val1 = (temp1 != null) ? temp1.val : 0; //if temp 1 is null then value is 0 otherwise node val
            int val2 = (temp2 != null) ? temp2.val : 0;
            int sum = val1 + val2 + carry;
            carry = sum / 10;
            int digit = sum % 10;
            
            current.next = new ListNode(digit);
            current = current.next;
            
            if (temp1 != null) temp1 = temp1.next;
            if (temp2 != null) temp2 = temp2.next;
        }
        
        // If there's a carry left after the final addition
        if (carry > 0) {
            current.next = new ListNode(carry);
        }
        
        return dummy.next;
    }
}
```

---

---
## Problem Summary
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in such a way that the least significant digit is at the head of the list, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list. You may assume the two numbers do not contain any leading zero, except the number 0 itself.

## Approach and Intuition
The problem asks us to add two numbers represented by linked lists. Since the digits are stored in reverse order (least significant digit first), we can iterate through both lists simultaneously, adding the corresponding digits along with any carry from the previous addition.

The intuition is to simulate the manual process of adding two numbers column by column. We'll maintain a `carry` variable to handle sums greater than 9.

1.  **Initialization**:
    *   Create a `dummy` head node for the result list. This simplifies handling the head of the new list.
    *   Initialize a `current` pointer to the `dummy` node. This pointer will traverse and build the result list.
    *   Initialize `temp1` and `temp2` to point to the heads of the input lists `l1` and `l2` respectively.
    *   Initialize `carry` to 0.

2.  **Iteration**:
    *   Loop as long as either `temp1` or `temp2` is not null, or there's a `carry` to process.
    *   Inside the loop, get the values from the current nodes of `l1` and `l2`. If a list has been exhausted (its pointer is null), treat its value as 0.
    *   Calculate the `sum` of the two values and the `carry`.
    *   Update the `carry` for the next iteration: `carry = sum / 10`.
    *   Determine the `digit` to be added to the result list: `digit = sum % 10`.
    *   Create a new `ListNode` with this `digit` and append it to the `current.next`.
    *   Move the `current` pointer forward: `current = current.next`.
    *   Advance `temp1` and `temp2` to their next nodes if they are not null.

3.  **Final Carry**:
    *   After the loop finishes, if there's still a `carry` greater than 0, it means the sum resulted in an extra digit at the most significant end. Create a new `ListNode` for this remaining `carry` and append it to the result list.

4.  **Return**:
    *   Return `dummy.next`, which is the head of the newly formed sum linked list.

## Complexity Analysis
- Time: O(max(N, M)) - where N is the length of l1 and M is the length of l2. We iterate through the linked lists once. In the worst case, we might iterate up to the length of the longer list, plus one extra step if there's a final carry.
- Space: O(max(N, M)) - The space complexity is determined by the length of the resulting linked list, which can be at most one element longer than the longer of the two input lists.

## Code Walkthrough
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 1. Initialization
        ListNode dummy = new ListNode(-1); // Dummy head for the result list
        ListNode current = dummy;         // Pointer to build the result list
        ListNode temp1 = l1;              // Pointer for list l1
        ListNode temp2 = l2;              // Pointer for list l2
        int carry = 0;                    // Carry-over from previous addition

        // 2. Iteration: Loop while there are nodes in either list or a carry exists
        while (temp1 != null || temp2 != null) {
            // Get values from current nodes, or 0 if the list is exhausted
            int val1 = (temp1 != null) ? temp1.val : 0;
            int val2 = (temp2 != null) ? temp2.val : 0;

            // Calculate sum of current digits and carry
            int sum = val1 + val2 + carry;

            // Update carry for the next iteration
            carry = sum / 10;
            // Get the digit for the current node in the result list
            int digit = sum % 10;

            // Create a new node with the digit and append it to the result list
            current.next = new ListNode(digit);
            // Move the current pointer forward
            current = current.next;

            // Advance pointers for l1 and l2 if they are not null
            if (temp1 != null) temp1 = temp1.next;
            if (temp2 != null) temp2 = temp2.next;
        }

        // 3. Final Carry: If there's a carry left after the loop, add it as a new node
        if (carry > 0) {
            current.next = new ListNode(carry);
        }

        // 4. Return: Return the head of the result list (skipping the dummy node)
        return dummy.next;
    }
}
```

## Interview Tips
*   **Edge Cases**: Always consider edge cases:
    *   One list is much longer than the other.
    *   Both lists are empty (though the problem statement says non-empty).
    *   The sum results in a carry at the very end (e.g., 99 + 1).
    *   Input lists containing only zeros.
*   **Dummy Node**: Using a dummy head node is a common and effective technique for simplifying linked list manipulations, especially when dealing with insertions at the head or building a new list. It avoids special handling for the first node.
*   **Clarity of Variables**: Use descriptive variable names (`current`, `carry`, `val1`, `val2`, `digit`).
*   **Step-by-Step Logic**: Explain your logic clearly, breaking it down into initialization, iteration, and handling the final carry.
*   **Complexity Explanation**: Be prepared to explain the time and space complexity with clear reasoning.

## Optimization and Alternatives
*   **In-Place Modification (Not Applicable Here)**: For some linked list problems, in-place modification is a goal. However, here we are creating a new linked list for the sum, so in-place modification of the input lists is not relevant or desired.
*   **Recursive Approach**: While an iterative approach is generally preferred for its clarity and avoidance of stack overflow issues with very long lists, a recursive solution is also possible. It would involve passing the carry and the next nodes to the recursive calls. However, the iterative approach is more straightforward for this problem.
*   **Converting to Integers (Not Recommended)**: One might consider converting the linked lists to integers, adding them, and then converting the sum back to a linked list. However, this approach is problematic because the integers could become extremely large, potentially exceeding the limits of standard integer types (like `int` or `long` in Java). The linked list representation is designed to handle arbitrarily large numbers.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Identify the core operation: digit-by-digit addition with carry.
- [ ] Choose an appropriate data structure for the result (a new linked list).
- [ ] Implement the iteration logic correctly, handling null pointers for list exhaustion.
- [ ] Manage the `carry` variable accurately.
- [ ] Ensure the final `carry` is handled.
- [ ] Use a dummy node to simplify list construction.
- [ ] Analyze time and space complexity.
- [ ] Test with various edge cases.

## Similar Problems
*   [2. Add Two Numbers](https://leetcode.com/problems/add-two-numbers/) (This is the problem itself)
*   [445. Add Two Numbers II](https://leetcode.com/problems/add-two-numbers-ii/) (Adds numbers where digits are stored in normal order, requiring reversal or stacks)
*   [19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) (Involves list traversal and pointer manipulation)
*   [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/) (A fundamental linked list operation)

## Tags
`Linked List` `Math`

## My Notes
A nice question
