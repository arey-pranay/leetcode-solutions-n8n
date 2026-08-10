# Merge K Sorted Lists

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Linked List` `Divide and Conquer` `Heap (Priority Queue)` `Merge Sort` `Tournament Sort`  
**Time:** O(N log K)  
**Space:** O(K)

---

## Solution (java)

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
      if(lists.length==0 ) return null;
      PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b)->a.val-b.val);
      for(ListNode l : lists) if(l!=null) pq.add(l);
      ListNode head = new ListNode();
      ListNode tail=head;
      while(!pq.isEmpty()){
        ListNode temp = pq.poll();
        tail.next = temp;
        if(temp.next!=null)pq.add(temp.next);
        temp.next=null;
        tail=tail.next;
      }
      return head.next;
    }
}
```

---

---
## Quick Revision
Merge k sorted linked lists into a single sorted linked list.
This is efficiently solved using a min-heap (PriorityQueue) to keep track of the smallest elements from each list.

## Intuition
The core idea is to always pick the smallest available node from all the k lists. A min-heap is the perfect data structure for this because it allows us to efficiently retrieve the minimum element among a collection of elements. We can imagine having k pointers, one for each list, and at each step, we want to advance the pointer pointing to the smallest value. A min-heap automates this selection process.

## Algorithm
1. Initialize a min-heap (PriorityQueue) that stores ListNode objects. The comparator should order nodes based on their `val` attribute.
2. Iterate through the input array `lists`. For each non-null ListNode in `lists`, add it to the min-heap.
3. Create a dummy head node for the merged list and a `tail` pointer initialized to this dummy head. This simplifies handling the first node.
4. While the min-heap is not empty:
    a. Extract the node with the smallest value from the min-heap (let's call it `currentMinNode`).
    b. Append `currentMinNode` to the merged list by setting `tail.next = currentMinNode`.
    c. Move the `tail` pointer forward: `tail = tail.next`.
    d. If `currentMinNode` has a next node, add that next node to the min-heap. This ensures we always have the next smallest element from that list available.
    e. Crucially, set `currentMinNode.next = null` to detach it from its original list and prevent cycles or incorrect list construction.
5. Return `dummyHead.next` (the actual head of the merged sorted list).

## Concept to Remember
*   **Priority Queue (Min-Heap):** Efficiently retrieving the minimum element from a dynamic set.
*   **Linked List Manipulation:** Understanding how to traverse, append, and modify `next` pointers.
*   **Dummy Head Node:** A common technique to simplify edge cases in linked list operations, especially when building a new list.
*   **Iterative Merging:** Combining multiple sorted sequences into one.

## Common Mistakes
*   **Not handling empty input `lists` array:** The code should gracefully return `null` if `lists` is empty.
*   **Not handling `null` lists within the input array:** The initial population of the priority queue must check for `null` list heads.
*   **Forgetting to add the `next` node to the priority queue:** If you only poll and append without adding the next element, you'll only merge the first elements of each list.
*   **Not detaching the polled node:** Failing to set `temp.next = null` after appending it to the result list can lead to incorrect list structures or infinite loops if not careful.
*   **Incorrectly initializing the dummy head or returning `head` instead of `head.next`:** This is a common off-by-one error.

## Complexity Analysis
*   **Time:** O(N log K) - where N is the total number of nodes across all lists, and K is the number of lists. Each node is inserted into and extracted from the priority queue once. Priority queue operations (add/poll) take O(log K) time.
*   **Space:** O(K) - The space complexity is dominated by the priority queue, which stores at most K nodes (one from each list) at any given time.

## Commented Code
```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
      // Handle the edge case where the input array of lists is empty.
      if(lists.length==0 ) return null;

      // Initialize a min-priority queue to store ListNode objects.
      // The lambda expression (a,b)->a.val-b.val defines the comparator:
      // it orders nodes based on their 'val' attribute in ascending order.
      PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b)->a.val-b.val);

      // Iterate through each linked list in the input array.
      for(ListNode l : lists) {
        // If a list is not null (i.e., it has a head node), add its head to the priority queue.
        if(l!=null) pq.add(l);
      }

      // Create a dummy head node for the merged list. This simplifies the logic for appending nodes.
      ListNode head = new ListNode();
      // Initialize a 'tail' pointer to the dummy head. This pointer will always point to the last node of the merged list.
      ListNode tail=head;

      // Continue as long as there are nodes in the priority queue.
      while(!pq.isEmpty()){
        // Extract the node with the smallest value from the priority queue.
        ListNode temp = pq.poll();

        // Append this smallest node to the end of our merged list.
        tail.next = temp;

        // If the extracted node has a next node in its original list, add that next node to the priority queue.
        // This ensures we always consider the next smallest element from each list.
        if(temp.next!=null)pq.add(temp.next);

        // Detach the 'temp' node from its original list by setting its 'next' to null.
        // This is important to prevent cycles and ensure the merged list is correctly formed.
        temp.next=null;

        // Move the 'tail' pointer forward to the newly appended node.
        tail=tail.next;
      }

      // The merged list starts after the dummy head node.
      return head.next;
    }
}
```

## Interview Tips
*   **Explain the Priority Queue choice:** Clearly articulate why a min-heap is suitable for this problem (efficiently finding the minimum among multiple sources).
*   **Walk through an example:** Use a small example with 2-3 lists to demonstrate how the priority queue is populated and how nodes are extracted and appended.
*   **Discuss edge cases:** Mention handling an empty input array, empty lists within the array, and lists with only one node.
*   **Clarify the dummy node's purpose:** Explain why a dummy head simplifies the code, especially for the first node insertion.
*   **Be prepared to discuss alternative approaches:** Briefly mention a divide-and-conquer approach (merging pairs of lists recursively) and its complexity trade-offs.

## Revision Checklist
- [ ] Understand the problem: merging k sorted linked lists.
- [ ] Identify the core challenge: efficiently finding the minimum element across k lists.
- [ ] Recognize the suitability of a min-heap (PriorityQueue).
- [ ] Implement the PriorityQueue initialization with a custom comparator.
- [ ] Handle initial population of the PQ with heads of non-null lists.
- [ ] Use a dummy head node for simplified list construction.
- [ ] Implement the main loop: poll min, append to result, add next to PQ, detach polled node.
- [ ] Ensure correct return value (`head.next`).
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases: empty input, empty lists.

## Similar Problems
*   Merge Two Sorted Lists
*   Merge Sorted Array
*   Kth Smallest Element in a Sorted Matrix
*   Find Median from Data Stream

## Tags
`Heap` `Linked List` `Priority Queue` `Divide and Conquer`
