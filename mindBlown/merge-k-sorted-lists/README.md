# Merge K Sorted Lists

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Linked List` `Divide and Conquer` `Heap (Priority Queue)` `Merge Sort`  
**Time:** O(n log k)  
**Space:** O(1)

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
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0) return null;
        int j=0;
        while(j<lists.length && lists[j]==null) j++;
        if(j==lists.length) return null;
        ListNode a = lists[j];
        for(int i=j+1;i<lists.length;i++){
            ListNode b = lists[i];
            a = merge2Lists(a,b);
        }
        return a;
    }
    
    public ListNode merge2Lists(ListNode a, ListNode b){
        ListNode headA = a;
        ListNode headB = b;
        
        ListNode head = new ListNode(-1);
        ListNode dummy = head;
        
        while(headA!=null && headB!=null){
            if(headA.val<headB.val){
                dummy.next = headA;
                headA = headA.next;
            } else{
                dummy.next = headB;
                headB = headB.next;
            }
            dummy = dummy.next;
        }
        
        while(headA != null){dummy.next = headA; dummy = dummy.next; headA=headA.next;}
        while(headB != null){dummy.next = headB; dummy = dummy.next; headB=headB.next;}
        
        return head.next;
    }
}
```

---

---
## Quick Revision
Merging multiple sorted linked lists into a single sorted linked list.
This problem can be solved by using a divide-and-conquer approach, where we merge two lists at a time until all lists are merged.

## Intuition
The key insight is to realize that the first non-empty list will always have the smallest value. We can use this fact to build up the final sorted linked list by merging each pair of lists in a round-robin fashion.

## Algorithm
1. Check if the input array is empty and return null if it is.
2. Find the first non-empty list in the array using a while loop.
3. Merge all other lists with the first non-empty list.
4. Use a recursive function `merge2Lists` to merge two sorted linked lists.

## Concept to Remember
* Divide-and-conquer approach for solving complex problems
* Using a round-robin merging strategy to combine multiple sorted lists

## Common Mistakes
* Not checking if the input array is empty before trying to access its elements.
* Incorrectly implementing the `merge2Lists` function, resulting in an unsorted output.
* Failing to handle cases where one or more of the input lists are null.

## Complexity Analysis
- Time: O(n log k) - where n is the total number of nodes across all lists and k is the number of lists. This is because we use a recursive approach with a time complexity of O(log k) for each merge operation, and there are n nodes to be merged.
- Space: O(1) - We only use a constant amount of extra space to store temporary variables.

## Commented Code
```java
public ListNode mergeKLists(ListNode[] lists) {
    // Check if input array is empty and return null if it is
    if (lists.length == 0) return null;
    
    int j = 0; // Initialize index to find first non-empty list
    
    // Find first non-empty list in the array
    while (j < lists.length && lists[j] == null) j++;
    
    // If all lists are empty, return null
    if (j == lists.length) return null;
    
    ListNode a = lists[j]; // First non-empty list
    
    // Merge all other lists with the first non-empty list
    for (int i = j + 1; i < lists.length; i++) {
        ListNode b = lists[i];
        a = merge2Lists(a, b);
    }
    
    return a;
}

public ListNode merge2Lists(ListNode a, ListNode b) {
    // Initialize dummy node and head of merged list
    ListNode headA = a;
    ListNode headB = b;
    ListNode head = new ListNode(-1); // Dummy node for merging
    ListNode dummy = head;
    
    while (headA != null && headB != null) {
        // Merge smaller nodes into the result list
        if (headA.val < headB.val) {
            dummy.next = headA; // Link current smallest node to result
            headA = headA.next; // Move to next node in first list
        } else {
            dummy.next = headB; // Link current smallest node to result
            headB = headB.next; // Move to next node in second list
        }
        dummy = dummy.next; // Move to next position for merging
    }
    
    // Merge remaining nodes (if any)
    while (headA != null) {
        dummy.next = headA;
        dummy = dummy.next;
        headA = headA.next;
    }
    while (headB != null) {
        dummy.next = headB;
        dummy = dummy.next;
        headB = headB.next;
    }
    
    // Return the merged list, skipping the dummy node
    return head.next;
}
```

## Interview Tips
* Make sure to clearly explain your approach and how it ensures a correct solution.
* Practice solving this problem on a whiteboard or with a friend to improve your communication skills.
* Be prepared to answer follow-up questions about edge cases (e.g., empty input lists, duplicate values).

## Revision Checklist
- [ ] Check if input array is empty before trying to access its elements.
- [ ] Implement `merge2Lists` function correctly for merging two sorted linked lists.
- [ ] Handle edge cases where one or more of the input lists are null.

## Similar Problems
* Merge k Sorted Arrays (LeetCode #563)
* Meeting Rooms II (LeetCode #611)
