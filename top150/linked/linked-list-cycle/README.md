# Linked List Cycle

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Hash Table` `Linked List` `Two Pointers`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(slow!=null && fast !=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast) return true;
        }
        return false;
    }
}
```

---

---
## Quick Revision
Detect if a singly-linked list contains a cycle.
Use the fast and slow pointer approach to detect a cycle.

## Intuition
Imagine two runners on a circular track. If one runner is faster than the other, they will eventually meet if there's a cycle. In a linked list, the "runners" are pointers. If a cycle exists, the faster pointer will eventually "lap" the slower pointer, meaning they will point to the same node. If the fast pointer reaches the end of the list (null), there's no cycle.

## Algorithm
1. Initialize two pointers, `slow` and `fast`, both pointing to the `head` of the linked list.
2. Iterate through the list as long as `fast` and `fast.next` are not null.
3. In each iteration, move `slow` one step forward (`slow = slow.next`).
4. In each iteration, move `fast` two steps forward (`fast = fast.next.next`).
5. After moving the pointers, check if `slow` and `fast` are pointing to the same node. If they are, a cycle is detected, and return `true`.
6. If the loop finishes without `slow` and `fast` meeting (meaning `fast` or `fast.next` became null), it indicates the end of the list was reached, so no cycle exists. Return `false`.

## Concept to Remember
*   **Pointers:** Understanding how to manipulate and advance pointers in a linked list.
*   **Two-Pointer Technique:** Using multiple pointers to traverse a data structure, often with different speeds or purposes.
*   **Cycle Detection:** A common pattern for identifying loops in data structures.

## Common Mistakes
*   Not handling the edge case where the list is empty (`head == null`).
*   Incorrectly advancing the `fast` pointer (e.g., only `fast.next` instead of `fast.next.next`).
*   Not checking `fast.next != null` before accessing `fast.next.next`, which can lead to a `NullPointerException`.
*   Returning `true` prematurely without ensuring `slow` and `fast` have actually met.

## Complexity Analysis
- Time: O(N) - In the worst case, the fast pointer traverses the list twice. If there's a cycle, the pointers will meet within a number of steps proportional to the list length.
- Space: O(1) - We only use a constant amount of extra space for the two pointers (`slow` and `fast`).

## Commented Code
```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        // Initialize the slow pointer to the head of the list.
        ListNode slow = head;
        // Initialize the fast pointer to the head of the list.
        ListNode fast = head;

        // Continue as long as the fast pointer and its next node are not null.
        // This ensures we don't encounter a NullPointerException when advancing fast.
        while(slow!=null && fast !=null && fast.next!=null){
            // Move the slow pointer one step forward.
            slow = slow.next;
            // Move the fast pointer two steps forward.
            fast = fast.next.next;
            // If the slow and fast pointers meet, it means there's a cycle.
            if(slow==fast) return true;
        }
        // If the loop terminates, it means the fast pointer reached the end of the list (null),
        // indicating no cycle was found.
        return false;
    }
}
```

## Interview Tips
*   Clearly explain the "two runners on a track" analogy to illustrate the intuition.
*   Walk through an example with a cycle and without a cycle, showing how the pointers move.
*   Be prepared to discuss the time and space complexity and justify it.
*   Mention the alternative approach using a HashSet (and its space complexity trade-off) if asked for other solutions.

## Revision Checklist
- [ ] Understand the problem statement for linked list cycles.
- [ ] Implement the fast and slow pointer approach correctly.
- [ ] Handle edge cases like an empty list.
- [ ] Correctly advance the fast pointer by two steps.
- [ ] Analyze time and space complexity.
- [ ] Explain the intuition clearly.

## Similar Problems
Linked List Cycle II
Remove Nth Node From End of List
Merge Two Sorted Lists
Palindrome Linked List

## Tags
`Linked List` `Two Pointers`
