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
Given an array of k sorted linked lists, merge them into one sorted linked list.
This is efficiently solved using a min-heap (PriorityQueue) to keep track of the smallest elements from each list.

## Intuition
The core idea is to always pick the smallest available node from *any* of the k lists. If we can efficiently find the minimum element across all lists at any given time, we can build the merged list incrementally. A min-heap is the perfect data structure for this, as it allows us to extract the minimum element in logarithmic time.

## Algorithm
1. Initialize a min-heap (PriorityQueue) to store ListNode objects. The comparator should order nodes by their `val` attribute.
2. Iterate through the input array `lists`. For each non-null ListNode in `lists`, add it to the min-heap.
3. Create a dummy head node (`head`) and a `tail` pointer, both initialized to `head`. This dummy node simplifies the process of building the new list.
4. While the min-heap is not empty:
    a. Extract the node with the smallest value from the min-heap (let's call it `temp`).
    b. Append `temp` to the merged list by setting `tail.next = temp`.
    c. If `temp` has a next node (`temp.next` is not null), add `temp.next` to the min-heap. This ensures we always have the next smallest element from that particular list available.
    d. Crucially, set `temp.next = null` to detach it from its original list and prevent cycles or incorrect connections in the merged list.
    e. Move the `tail` pointer forward: `tail = tail.next`.
5. Return `head.next`, which is the actual head of the merged sorted linked list.

## Concept to Remember
*   **Priority Queue (Min-Heap):** Efficiently finding and extracting the minimum element from a collection.
*   **Linked List Manipulation:** Understanding how to traverse, append, and modify nodes in a linked list.
*   **Dummy Head Node:** A common technique to simplify linked list operations, especially when dealing with insertions at the beginning or building a new list.
*   **Iterative Merging:** Building the final structure by repeatedly taking the smallest element from available sources.

## Common Mistakes
*   **Not handling empty input `lists` array:** The code should gracefully return `null` if `lists` is empty.
*   **Not adding the `next` node to the priority queue:** Forgetting to add `temp.next` after polling `temp` means you won't consider subsequent elements from that list.
*   **Not detaching the polled node:** Failing to set `temp.next = null` can lead to incorrect list structures or infinite loops if not handled carefully.
*   **Incorrectly initializing the dummy head/tail:** Mismanaging the pointers for the new list can result in an empty or malformed output.
*   **Comparator issues:** If the priority queue's comparator is not set up correctly, it won't maintain the min-heap property based on node values.

## Complexity Analysis
*   **Time:** O(N log K) - where N is the total number of nodes across all k lists, and K is the number of lists. Each node is inserted into and extracted from the priority queue once. Insertion and extraction take O(log K) time because the priority queue can hold at most K elements (one from each list).
*   **Space:** O(K) - The space complexity is dominated by the priority queue, which stores at most one node from each of the K linked lists.

## Commented Code
```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
      // Handle the edge case where the input array of lists is empty.
      if(lists.length==0 ) return null;

      // Initialize a min-priority queue. The lambda expression defines the comparator
      // to order ListNodes based on their 'val' attribute in ascending order.
      PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b)->a.val-b.val);

      // Iterate through each linked list in the input array.
      for(ListNode l : lists) {
        // If a list is not null, add its head node to the priority queue.
        if(l!=null) pq.add(l);
      }

      // Create a dummy head node for the merged list. This simplifies appending nodes.
      ListNode head = new ListNode();
      // Initialize a tail pointer to the dummy head. This pointer will always point
      // to the last node added to the merged list.
      ListNode tail=head;

      // Continue as long as there are nodes in the priority queue.
      while(!pq.isEmpty()){
        // Extract the node with the smallest value from the priority queue.
        ListNode temp = pq.poll();

        // Append this smallest node to the merged list.
        tail.next = temp;

        // If the extracted node has a next node, add it to the priority queue.
        // This ensures we always consider the next element from the list that 'temp' came from.
        if(temp.next!=null)pq.add(temp.next);

        // Detach the 'temp' node from its original list by setting its next pointer to null.
        // This is crucial to avoid creating cycles or incorrect list structures.
        temp.next=null;

        // Move the tail pointer forward to the newly added node.
        tail=tail.next;
      }

      // The merged list starts after the dummy head node.
      return head.next;
    }
}
```

## Interview Tips
*   **Explain the Priority Queue choice:** Clearly articulate why a min-heap is the optimal data structure for this problem, emphasizing its ability to efficiently find the minimum across multiple sources.
*   **Walk through an example:** Use a small example with 2-3 lists to demonstrate how the priority queue is populated and how nodes are extracted and appended.
*   **Discuss edge cases:** Be prepared to talk about handling an empty input array, lists with only one node, or lists that are already empty.
*   **Clarify the `temp.next = null` step:** This is a subtle but important detail. Explain why it's necessary to break the original list's chain.
*   **Consider alternative approaches (briefly):** You might briefly mention a divide-and-conquer approach (merging pairs of lists recursively) and compare its complexity to the priority queue method.

## Revision Checklist
- [ ] Understand the problem statement: merging k sorted linked lists.
- [ ] Identify the need for an efficient way to find the minimum element across all lists.
- [ ] Implement a min-priority queue (min-heap).
- [ ] Correctly configure the priority queue's comparator for ListNode values.
- [ ] Handle the initial population of the priority queue with the heads of all lists.
- [ ] Use a dummy head node to simplify building the merged list.
- [ ] Implement the main loop: poll min, append to merged list, add next element to PQ.
- [ ] Ensure `temp.next` is set to `null` after polling.
- [ ] Correctly advance the `tail` pointer.
- [ ] Return `head.next` to exclude the dummy node.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases: empty input array, empty lists.

## Similar Problems
*   Merge Two Sorted Lists
*   Merge Sorted Array
*   Kth Smallest Element in a Sorted Matrix
*   Smallest Range Covering Elements from K Lists

## Tags
`Heap` `PriorityQueue` `LinkedList` `Divide and Conquer`
