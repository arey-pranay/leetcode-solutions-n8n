# Merge K Sorted Lists

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Linked List` `Divide and Conquer` `Heap (Priority Queue)` `Merge Sort`  
**Time:** O(N*K)  
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
        ListNode h = new ListNode(0);
        ListNode tail = new ListNode(0);
        h.next = tail;
        
        int minIndex = -1;
        while (minIndex != -2) {
            ListNode minNode = new ListNode(Integer.MAX_VALUE);
            minIndex = -2;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null && lists[i].val < minNode.val) {
                    minNode = lists[i];
                    minIndex = i;
                }
            }
            if(minIndex == -2)break;
            
            lists[minIndex] = minNode.next
            
            minNode.next = null;
            tail.next = minNode;
            tail = tail.next;
        }
        return h.next.next;
    }
}
```

---

---
## Quick Revision
Given an array of k sorted linked lists, merge them into one sorted linked list.
This problem can be solved by iteratively finding the smallest element across all lists and appending it to the result.

## Intuition
The core idea is to repeatedly find the smallest element among the heads of all the k sorted lists. Once we find the smallest element, we append it to our merged list and advance the pointer of the list from which we took the element. We continue this process until all lists are exhausted. A dummy head node simplifies the construction of the new merged list.

## Algorithm
1. Initialize a dummy head node `h` and a `tail` pointer for the merged list. Set `h.next = tail`.
2. Initialize `minIndex` to -1. This variable will track the index of the list containing the current minimum element.
3. Enter a loop that continues as long as `minIndex` is not -2 (a sentinel value indicating all lists are empty).
4. Inside the loop, initialize `minNode` to a node with `Integer.MAX_VALUE` and `minIndex` to -2. This is to find the smallest element in the current iteration.
5. Iterate through each of the `k` linked lists in the input array `lists`.
6. For each list, if it's not null and its current node's value is less than `minNode.val`, update `minNode` to the current node and `minIndex` to the current list's index.
7. If after checking all lists, `minIndex` is still -2, it means all lists are empty, so break the loop.
8. Otherwise, advance the pointer of the list from which `minNode` was taken: `lists[minIndex] = minNode.next`.
9. Detach `minNode` from its original list by setting `minNode.next = null`.
10. Append `minNode` to the merged list: `tail.next = minNode`.
11. Move the `tail` pointer forward: `tail = tail.next`.
12. After the loop finishes, return `h.next.next` (skipping the dummy head).

## Concept to Remember
*   **Linked List Manipulation**: Understanding how to traverse, insert, and modify nodes in a linked list.
*   **Iterative Merging**: The strategy of repeatedly combining smaller sorted structures into a larger one.
*   **Finding Minimum in Multiple Sources**: Efficiently identifying the smallest element across several collections.
*   **Dummy Head Node**: A common technique to simplify linked list operations, especially at the beginning.

## Common Mistakes
*   **Incorrectly handling empty lists**: Not checking if `lists[i]` is null before accessing `lists[i].val`.
*   **Modifying the original lists incorrectly**: Forgetting to advance the pointer of the list from which an element was taken.
*   **Off-by-one errors with dummy nodes**: Returning `h` or `h.next` instead of `h.next.next`.
*   **Infinite loops**: Not having a proper termination condition for the main loop, especially when all lists become empty.
*   **Not detaching the chosen node**: Forgetting to set `minNode.next = null` before appending it, which can lead to cycles or incorrect merging.

## Complexity Analysis
- Time: O(N*K) - where N is the total number of nodes across all lists and K is the number of lists. In each iteration of the outer `while` loop, we iterate through all K lists to find the minimum. This happens N times in total (once for each node).
- Space: O(1) - We are only using a few extra pointers and variables, not proportional to the input size. The merged list is built in-place by rearranging existing nodes.

## Commented Code
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val; // The value of the node
 *     ListNode next; // Pointer to the next node in the list
 *     ListNode() {} // Default constructor
 *     ListNode(int val) { this.val = val; } // Constructor with value
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; } // Constructor with value and next node
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // Create a dummy head node for the merged list. This simplifies insertion at the beginning.
        ListNode h = new ListNode(0);
        // Create a tail pointer to keep track of the last node in the merged list.
        ListNode tail = new ListNode(0);
        // Link the dummy head to the tail initially.
        h.next = tail;
        
        // Initialize minIndex to -1. This will store the index of the list containing the minimum element.
        int minIndex = -1;
        // The loop continues as long as we find a minimum element (minIndex != -2).
        // -2 is a sentinel value indicating all lists are exhausted.
        while (minIndex != -2) {
            // Initialize minNode to a node with the maximum possible integer value.
            // This ensures that the first valid node encountered will be smaller.
            ListNode minNode = new ListNode(Integer.MAX_VALUE);
            // Reset minIndex to -2 for this iteration. If no node is found, it remains -2.
            minIndex = -2;
            // Iterate through all the k linked lists.
            for (int i = 0; i < lists.length; i++) {
                // Check if the current list is not null and its current node's value is less than the current minimum.
                if (lists[i] != null && lists[i].val < minNode.val) {
                    // Update minNode to the current node.
                    minNode = lists[i];
                    // Update minIndex to the index of the current list.
                    minIndex = i;
                }
            }
            // If minIndex is still -2 after checking all lists, it means all lists are empty. Break the loop.
            if(minIndex == -2)break;
            
            // Advance the pointer of the list from which the minimum node was taken.
            lists[minIndex] = minNode.next
            
            // Detach the minNode from its original list by setting its next pointer to null.
            minNode.next = null;
            // Append the minNode to the merged list.
            tail.next = minNode;
            // Move the tail pointer to the newly added node.
            tail = tail.next;
        }
        // Return the head of the merged list, skipping the dummy head node.
        return h.next.next;
    }
}
```

## Interview Tips
1.  **Clarify Constraints**: Ask about the number of lists (k) and the total number of nodes (N). This helps in choosing the most efficient approach.
2.  **Explain the Brute-Force First**: Briefly mention a naive approach (e.g., concatenating all lists and then sorting) to show you've considered simpler, less optimal solutions.
3.  **Discuss Alternatives**: Mention that a min-heap (PriorityQueue in Java) is a more efficient approach with O(N log K) time complexity. Explain why it's better for larger K.
4.  **Edge Cases**: Be prepared to discuss handling empty input arrays (`lists` is empty), arrays with empty lists, and arrays with only one list.
5.  **Code Walkthrough**: After writing the code, walk through it with a small example to demonstrate your understanding.

## Revision Checklist
- [ ] Understand the problem: merging k sorted linked lists.
- [ ] Implement the iterative minimum finding approach.
- [ ] Use a dummy head node for easier list construction.
- [ ] Correctly handle null lists and empty lists.
- [ ] Ensure the loop terminates correctly.
- [ ] Analyze time and space complexity.
- [ ] Consider the min-heap (PriorityQueue) alternative.

## Similar Problems
*   Merge Two Sorted Lists
*   Merge Sorted Array
*   Kth Smallest Element in a Sorted Matrix
*   Find Median from Data Stream

## Tags
`LinkedList` `Heap` `PriorityQueue` `Divide and Conquer`
