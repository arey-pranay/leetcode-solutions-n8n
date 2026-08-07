# Swap Nodes In Pairs

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Linked List` `Recursion`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public ListNode swapPairs(ListNode head) {
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode curr = dummy;
      while(curr!=null){
          if(curr.next==null) break;
          curr.next = swapPair(curr.next,curr.next.next);
          curr = curr.next.next;
      }
      return dummy.next;
    }
    public ListNode swapPair(ListNode left , ListNode right){
      if(left==null || right==null) return left;
      ListNode temp = right.next;
      right.next = left;
      left.next= temp;
      return right;
    }
}
```

---

---
## Quick Revision
Swap adjacent nodes in a linked list to form pairs.
To solve this, recursively swap each pair of nodes while iterating through the list.

## Intuition
The key insight is that we can use recursion to handle each pair of nodes independently. By keeping track of the current node and its next two nodes (left, right), we can swap them in constant time using a helper function `swapPair`.

## Algorithm

1. Initialize a dummy node with `next` pointer pointing to the head of the list.
2. Use a loop to iterate through the list until there are no more pairs left (i.e., only one or two nodes remaining).
3. For each pair, call the `swapPair` function on the current node's next two nodes (`curr.next` and `curr.next.next`) and update `curr` to point to the new last node of the swapped pair.
4. Return the `next` pointer of the dummy node, which points to the first node of the modified list.

## Concept to Remember
* Recursion can be used to simplify complex problems by breaking them down into smaller sub-problems.
* Linked lists require careful handling of pointers to avoid losing nodes or introducing cycles.

## Common Mistakes

* Not initializing a dummy node and directly modifying the head pointer, which would lead to incorrect results.
* Failing to handle edge cases where there are an odd number of nodes (e.g., only one node left).
* Not updating `curr` correctly after swapping each pair, resulting in incorrect iteration.

## Complexity Analysis
- Time: O(n) - reason: We iterate through the list once and perform constant-time operations for each pair.
- Space: O(1) - reason: We use a fixed amount of extra space to store recursive call stack frames and dummy node pointers.

## Commented Code

```java
// Initialize a dummy node with next pointer pointing to head
class Solution {
    public ListNode swapPairs(ListNode head) {
        // Create dummy node for iteration
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // Iterate through list and swap pairs
        ListNode curr = dummy; // keep track of current node
        while (curr != null) {
            if (curr.next == null) break; // stop when no more pairs left
            
            // Swap pair using helper function
            curr.next = swapPair(curr.next, curr.next.next);
            
            // Move to next non-swapped node
            curr = curr.next.next;
        }
        
        return dummy.next; // Return first node of modified list
    }

    public ListNode swapPair(ListNode left, ListNode right) {
        if (left == null || right == null) return left; // handle edge case
        
        // Store next pointer and swap nodes
        ListNode temp = right.next;
        right.next = left;
        left.next = temp;
        
        return right;
    }
}
```

## Interview Tips

* Be prepared to explain the recursive approach and how it simplifies the problem.
* Emphasize the importance of handling edge cases correctly (e.g., odd number of nodes).
* Show that you understand the trade-offs between using recursion vs. iteration for solving this type of problem.

## Revision Checklist
- [ ] Review linked list basics and common operations.
- [ ] Understand how recursion can be used to simplify complex problems.
- [ ] Practice handling edge cases and testing corner scenarios.

## Similar Problems

* Swap Linked List Nodes in Groups of Three (LC 328)
* Reverse Linked List II (LC 92)
