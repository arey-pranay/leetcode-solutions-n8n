# Remove Nth Node From End Of List

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Linked List` `Two Pointers`  
**Time:** O(L)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        func(dummy,n,0);
        return dummy.next;
    }
    public int func(ListNode head, int n, int curr){
        if(head==null) return 0;
        curr = 1 + func(head.next,n,curr);
        if(curr==n+1) head.next=head.next.next;
        return curr;
    }
}
```

---

---
## Quick Revision
Remove the nth node from the end of a singly linked list.
This problem can be solved by using two pointers, one of which is ahead of the other by n nodes. We then move both pointers forward until the ahead pointer reaches the end of the list. At this point, we know that the current position of the behind pointer is the nth node from the end.

## Intuition
The idea here is to use a dummy node at the beginning of the linked list and two pointers, one "behind" the other by n nodes. As we traverse the list, if the ahead pointer reaches the end, it means that the current position of the behind pointer is the nth node from the end.

## Algorithm
1. Create a dummy node and set its next pointer to the head of the linked list.
2. Initialize two pointers, both pointing to the dummy node.
3. Move the ahead pointer n steps forward by calling the func function recursively with the updated position.
4. Move both pointers one step at a time until the ahead pointer reaches the end of the list (head == null).
5. When the ahead pointer reaches the end, it means that the current position of the behind pointer is the nth node from the end. Remove this node by setting its next pointer to the node after it.

## Concept to Remember
* Two pointers technique: used to solve problems involving linked lists.
* Dummy nodes: used as a placeholder at the beginning of the linked list for convenience.

## Common Mistakes
* Not initializing the dummy node properly.
* Not moving both pointers correctly (either too many or too few steps).
* Not removing the correct node when the ahead pointer reaches the end.

## Complexity Analysis
- Time: O(L) - where L is the length of the linked list, as we traverse it once.
- Space: O(1) - constant space usage, excluding the input list.

## Commented Code

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Create a dummy node and set its next pointer to the head of the linked list
        ListNode dummy = new ListNode();
        dummy.next = head;
        
        // Initialize two pointers, both pointing to the dummy node
        func(dummy,n,0);
        
        // Return the next node of the dummy node (the actual head of the modified list)
        return dummy.next;
    }
    
    public int func(ListNode head, int n, int curr){
        // Base case: if we've reached the end of the linked list, return 0
        if(head==null) return 0;
        
        // Move both pointers one step at a time by recursively calling the func function
        curr = 1 + func(head.next,n,curr);
        
        // If the current position is n+1 (i.e., we've reached the nth node from the end), remove it
        if(curr==n+1) head.next=head.next.next;
        
        // Return the updated position of the behind pointer
        return curr;
    }
}
```

## Interview Tips
* Use a dummy node to simplify the problem.
* Two pointers technique can be used to solve this and similar problems.
* Be careful when moving both pointers, as it's easy to make mistakes.

## Revision Checklist
- [ ] Review the two pointers technique and its application in linked lists.
- [ ] Practice removing nodes from the end of a linked list using the two pointers approach.
- [ ] Make sure to initialize the dummy node correctly.

## Similar Problems
* Remove Duplicates from Sorted List
* Merge k Sorted Lists

## Tags
`Array` `Hash Map` `Two Pointers` `Linked List` `Remove Node`
