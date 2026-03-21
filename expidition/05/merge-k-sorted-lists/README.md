# Merge K Sorted Lists

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Linked List` `Divide and Conquer` `Heap (Priority Queue)` `Merge Sort`  
**Time:** O(N log K)  
**Space:** O(K)

---

## Solution (java)

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq =
            new PriorityQueue<>((a, b) -> a.val - b.val);

        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = tail.next;

            if (minNode.next != null) {
                pq.add(minNode.next);
            }
        }

        return dummy.next;
    }
    
    //     public ListNode mergeKLists(ListNode[] lists) {
    //     ListNode h = new ListNode(0);
    //     ListNode tail = new ListNode(0);
    //     h.next = tail;
        
    //     int minIndex = -1;
    //     while (minIndex != -2) {
    //         ListNode minNode = new ListNode(Integer.MAX_VALUE);
    //         minIndex = -2;
    //         for (int i = 0; i < lists.length; i++) {
    //             if (lists[i] != null && lists[i].val < minNode.val) {
    //                 minNode = lists[i];
    //                 minIndex = i;
    //             }
    //         }
    //         if(minIndex == -2)break;
    //         ListNode temp = minNode.next;
    //         lists[minIndex] = temp;
    //         tail.next = minNode;
    //         tail = tail.next;
    //         minNode.next = null;
    //     }
    //     return h.next.next;
    // }
}

```

---

---
## Quick Revision
Merge k sorted linked lists into a single sorted linked list.
This is efficiently solved using a min-heap (Priority Queue) to keep track of the smallest element across all lists.

## Intuition
Imagine you have k sorted piles of cards, and you want to create one giant sorted pile. At each step, you'd look at the top card of each pile and pick the smallest one to add to your new pile. You repeat this until all piles are empty. A min-heap is the perfect data structure to efficiently find the smallest element among the current "top" elements of all k lists.

## Algorithm
1. Initialize a min-priority queue. The priority queue will store `ListNode` objects and order them based on their `val` attribute.
2. Iterate through the input array `lists`. For each non-null `ListNode` in `lists`, add it to the priority queue.
3. Create a dummy `ListNode` (e.g., `dummy = new ListNode(0)`) to serve as the head of the merged list. Initialize a `tail` pointer to `dummy`.
4. While the priority queue is not empty:
    a. Extract the node with the minimum value from the priority queue (`minNode = pq.poll()`).
    b. Append `minNode` to the merged list by setting `tail.next = minNode`.
    c. Move the `tail` pointer forward: `tail = tail.next`.
    d. If `minNode` has a next node (`minNode.next != null`), add `minNode.next` to the priority queue. This ensures we always consider the next smallest element from the list that `minNode` came from.
5. Return `dummy.next`, which is the head of the fully merged sorted list.

## Concept to Remember
*   **Linked Lists:** Understanding node manipulation, pointers, and traversal.
*   **Priority Queues (Min-Heap):** Efficiently retrieving the minimum element from a collection.
*   **Dummy Nodes:** Simplifying edge cases, especially for list manipulation (insertion/deletion at the head).
*   **Divide and Conquer (Alternative Approach):** Merging lists pairwise can also be a valid strategy.

## Common Mistakes
*   **Not handling empty lists:** Forgetting to check if a `ListNode` in the input array is `null` before adding it to the priority queue.
*   **Incorrectly updating the tail pointer:** Failing to advance the `tail` pointer after appending a node, leading to an incorrectly formed list.
*   **Not adding the next node to the PQ:** Forgetting to add `minNode.next` to the priority queue after extracting `minNode`, which would stop the merging process prematurely.
*   **Off-by-one errors with dummy nodes:** Incorrectly returning `dummy` instead of `dummy.next`.
*   **Inefficient comparison in PQ:** If using a custom comparator, ensuring it correctly compares `ListNode` values.

## Complexity Analysis
*   **Time:** O(N log K) - where N is the total number of nodes across all k lists, and K is the number of lists.
    *   Adding the initial K nodes to the priority queue takes O(K log K).
    *   Each of the N nodes will be inserted into and extracted from the priority queue once. Each operation takes O(log K) time.
    *   Therefore, the total time complexity is O(K log K + N log K), which simplifies to O(N log K) as N is typically much larger than K.
*   **Space:** O(K) - The space complexity is dominated by the priority queue, which can store up to K nodes (one from each list) at any given time.

## Commented Code
```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // Initialize a min-priority queue.
        // The comparator (a, b) -> a.val - b.val ensures that nodes are ordered by their 'val' in ascending order.
        PriorityQueue<ListNode> pq =
            new PriorityQueue<>((a, b) -> a.val - b.val);

        // Iterate through each linked list in the input array.
        for (ListNode node : lists) {
            // If the current list is not empty (i.e., the head node is not null),
            // add its head node to the priority queue.
            if (node != null) {
                pq.add(node);
            }
        }

        // Create a dummy node. This simplifies the process of building the new merged list,
        // as we don't need to handle the special case of inserting the first node.
        ListNode dummy = new ListNode(0);
        // Initialize a 'tail' pointer to the dummy node. This pointer will always point to the last node added to the merged list.
        ListNode tail = dummy;

        // Continue as long as there are nodes in the priority queue.
        while (!pq.isEmpty()) {
            // Extract the node with the smallest value from the priority queue.
            ListNode minNode = pq.poll();
            // Append this smallest node to the end of our merged list.
            tail.next = minNode;
            // Move the 'tail' pointer forward to the newly added node.
            tail = tail.next;

            // If the extracted node has a next node in its original list,
            // add that next node to the priority queue. This ensures we always have the next smallest element from that list available.
            if (minNode.next != null) {
                pq.add(minNode.next);
            }
        }

        // The merged list starts after the dummy node.
        return dummy.next;
    }
}
```

## Interview Tips
1.  **Explain the Priority Queue approach first:** This is the most common and efficient solution. Clearly articulate why a min-heap is suitable.
2.  **Discuss alternative approaches:** Briefly mention other methods like pairwise merging (divide and conquer) and their trade-offs in terms of complexity.
3.  **Walk through an example:** Use a small example with 2-3 lists to trace the execution of your algorithm, showing how the priority queue evolves and how the merged list is built.
4.  **Handle edge cases:** Be prepared to discuss what happens if the input `lists` array is empty, or if all lists within it are empty. The dummy node approach handles many of these gracefully.
5.  **Clarify constraints:** Ask about the maximum number of lists (K) and the maximum total number of nodes (N), as this can influence the choice of algorithm if K is very small or very large relative to N.

## Revision Checklist
- [ ] Understand the problem: merging k sorted linked lists.
- [ ] Identify the core challenge: efficiently finding the minimum element across k lists.
- [ ] Recall or derive the Priority Queue (Min-Heap) solution.
- [ ] Implement the Priority Queue solution with a dummy node.
- [ ] Analyze Time Complexity: O(N log K).
- [ ] Analyze Space Complexity: O(K).
- [ ] Consider alternative solutions (e.g., pairwise merge).
- [ ] Practice coding the solution without looking at the code.
- [ ] Be ready to explain the intuition and trade-offs.

## Similar Problems
*   Merge Two Sorted Lists (LeetCode 21)
*   Merge Sorted Array (LeetCode 88)
*   Kth Smallest Element in a Sorted Matrix (LeetCode 378)
*   Find Median from Data Stream (LeetCode 295)

## Tags
`Heap` `Priority Queue` `Linked List` `Divide and Conquer`

## My Notes
Used priority queue, and even without the prioirtyqueue written above.
