# Add Two Numbers

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Linked List` `Math` `Recursion`  
**Time:** O(max(N, M)  
**Space:** O(max(N, M)

---

## Solution (java)

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean carry = false;
        ListNode head = new ListNode(-1);
        ListNode ans = head;
        while(l1 != null || l2 != null){
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            int sum = val1 + val2;
            
            if(carry) sum++; carry = sum > 9; if(carry) sum -= 10;
            
            head.next = new ListNode(sum);
            head = head.next;
            if(l1!=null)l1 = l1.next;
            if(l2!=null)l2 = l2.next;
        }
        if(carry)head.next = new ListNode(1);
        return ans.next;
    }
}
```

---

---
## Quick Revision
Given two non-empty linked lists representing two non-negative integers, add them and return the sum as a linked list.
The digits are stored in reverse order, and each of their nodes contains a single digit.

## Intuition
The problem asks us to add two numbers represented by linked lists, where digits are in reverse order. This is analogous to how we perform addition manually, starting from the least significant digit (which is the head of the list). We can iterate through both lists simultaneously, summing the corresponding digits along with any carry-over from the previous step. A new linked list can be built to store the result.

## Algorithm
1. Initialize a `carry` variable to `false` (or 0).
2. Create a dummy `head` node for the result list (e.g., `ListNode(-1)`). This simplifies handling the first node.
3. Create a pointer `current` (or `ans`) that initially points to `head`. This pointer will traverse and build the result list.
4. Iterate while either `l1` or `l2` is not `null`:
    a. Get the value of the current node in `l1`, or 0 if `l1` is `null`.
    b. Get the value of the current node in `l2`, or 0 if `l2` is `null`.
    c. Calculate the `sum` of these two values.
    d. If `carry` is `true`, increment `sum` by 1.
    e. Update `carry`: set `carry` to `true` if `sum` is greater than 9, otherwise `false`.
    f. If `carry` is `true` (after the update), subtract 10 from `sum` to get the digit for the current node.
    g. Create a new `ListNode` with the calculated `sum` and append it to the `current` node's `next`.
    h. Move `current` to the newly created node (`current = current.next`).
    i. Move `l1` to its next node if `l1` is not `null`.
    j. Move `l2` to its next node if `l2` is not `null`.
5. After the loop, if `carry` is still `true`, append a new `ListNode(1)` to the end of the result list.
6. Return `head.next` (skipping the dummy head).

## Concept to Remember
*   **Linked List Traversal:** Iterating through nodes of a linked list using pointers.
*   **Handling Carry-Over:** Simulating manual addition by managing a carry value.
*   **Dummy Head Node:** A common technique to simplify linked list manipulation, especially for insertion at the beginning or handling empty lists.
*   **Edge Cases:** Considering scenarios like lists of different lengths and a final carry-over.

## Common Mistakes
*   **Incorrect Carry Handling:** Forgetting to add the carry to the sum or incorrectly updating the carry flag.
*   **Off-by-One Errors:** Incorrectly handling the loop termination or the final carry-over.
*   **Modifying Input Lists:** Accidentally modifying the original `l1` or `l2` pointers in a way that breaks the logic (though in this specific solution, it's intentional and correct).
*   **Not Handling Null Lists:** Failing to check if `l1` or `l2` is `null` before accessing their `val` or `next` properties.
*   **Forgetting the Dummy Node:** Not using a dummy head can make handling the first node insertion more complex.

## Complexity Analysis
*   **Time:** O(max(N, M)) - where N and M are the lengths of the two linked lists. We iterate through each list at most once.
*   **Space:** O(max(N, M)) - The space complexity is determined by the length of the resulting linked list, which can be at most one longer than the longer of the two input lists.

## Commented Code
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val; // Stores the digit value of the node.
 *     ListNode next; // Pointer to the next node in the list.
 *     ListNode() {} // Default constructor.
 *     ListNode(int val) { this.val = val; } // Constructor with value.
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; } // Constructor with value and next node.
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean carry = false; // Initialize a boolean flag to track if there's a carry-over from the previous digit addition.
        ListNode head = new ListNode(-1); // Create a dummy head node for the result list. This simplifies appending nodes.
        ListNode ans = head; // Initialize a pointer 'ans' to the dummy head. This pointer will be used to build the result list.

        // Loop continues as long as there are digits in either list or there's a carry-over.
        while(l1 != null || l2 != null){
            // Get the value of the current node in l1, or 0 if l1 is null.
            int val1 = l1 == null ? 0 : l1.val;
            // Get the value of the current node in l2, or 0 if l2 is null.
            int val2 = l2 == null ? 0 : l2.val;
            // Calculate the sum of the current digits from both lists.
            int sum = val1 + val2;

            // If there was a carry from the previous step, add it to the current sum.
            if(carry) sum++;
            // Update the carry flag: if the sum is greater than 9, there will be a carry to the next digit.
            carry = sum > 9;
            // If there is a carry, subtract 10 from the sum to get the correct digit for the current node.
            if(carry) sum -= 10;

            // Create a new node with the calculated sum and append it to the result list.
            head.next = new ListNode(sum);
            // Move the 'head' pointer to the newly created node, so it's ready for the next append.
            head = head.next;

            // Move to the next node in l1 if l1 is not null.
            if(l1!=null)l1 = l1.next;
            // Move to the next node in l2 if l2 is not null.
            if(l2!=null)l2 = l2.next;
        }
        // After the loop, if there's still a carry-over, append a new node with value 1 to the result list.
        if(carry)head.next = new ListNode(1);
        // Return the head of the result list, skipping the dummy head node.
        return ans.next;
    }
}
```

## Interview Tips
*   **Explain the Carry Logic:** Clearly articulate how you handle the `carry` variable, as it's the core of the addition process.
*   **Use a Dummy Node:** Mention the benefit of using a dummy head node for simplifying list construction.
*   **Handle Different Lengths:** Emphasize how your loop condition (`l1 != null || l2 != null`) and the ternary operator (`l1 == null ? 0 : l1.val`) correctly manage lists of unequal lengths.
*   **Consider the Final Carry:** Discuss the importance of checking for a final carry-over after the loop finishes.

## Revision Checklist
- [ ] Understand the problem statement: digits in reverse order, non-negative integers.
- [ ] Implement linked list node structure.
- [ ] Initialize carry and result list (dummy head).
- [ ] Loop through both lists simultaneously.
- [ ] Handle null lists gracefully (use 0 for missing digits).
- [ ] Correctly calculate sum with carry.
- [ ] Update carry flag.
- [ ] Adjust sum if carry exists.
- [ ] Create new node for result list.
- [ ] Advance pointers for input lists.
- [ ] Handle final carry-over after loop.
- [ ] Return the correct head of the result list.

## Similar Problems
*   [Add Binary](https://leetcode.com/problems/add-binary/)
*   [Multiply Strings](https://leetcode.com/problems/multiply-strings/)
*   [Plus One](https://leetcode.com/problems/plus-one/)

## Tags
`Linked List` `Math` `Recursion`
