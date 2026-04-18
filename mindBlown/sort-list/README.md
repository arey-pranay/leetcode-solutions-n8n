# Sort List

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Linked List` `Two Pointers` `Divide and Conquer` `Sorting` `Merge Sort`  
**Time:** O(n log n)  
**Space:** O(log n)

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
    public ListNode sortList(ListNode head) {
        int n = 0;
        ListNode end = head;
        if(head==null) return null;
        while(end!=null){
            n++;
            end = end.next;
        }
        return divide(head,n);
    }
    public ListNode divide(ListNode start, int n){
        if(n == 1){
            start.next=null;
            return start;
        }
        ListNode mid = start;
        int midCount = n/2;
        for(int i=0;i<midCount-1;i++) mid = mid.next;
        ListNode head2 = mid.next;
        mid.next=null;
        ListNode left = divide(start,midCount);
        ListNode right = divide(head2,n-midCount);
        return merge(left,right);
    }
    public ListNode merge(ListNode left ,ListNode right){
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while(left!=null && right!=null){
            if(left.val < right.val){
                tail.next = left;
                left = left.next;
            }
            else{
                tail.next=right;
                right = right.next;
            }
            tail = tail.next;
        }
        if(left == null) tail.next = right;
        else tail.next = left;
        return head.next;
    }
}
```

---

---
## Quick Revision
Sort a singly-linked list in O(n log n) time and O(log n) space.
This is achieved using a top-down merge sort approach.

## Intuition
The core idea is to break down the problem into smaller, manageable subproblems. If we can sort two halves of a linked list, we can then merge them efficiently to get a sorted whole. This recursive decomposition naturally leads to a merge sort algorithm. The base case is a list of size 1, which is already sorted.

## Algorithm
1.  **Count Nodes:** Traverse the linked list to determine its total number of nodes, `n`.
2.  **Recursive Division (divide function):**
    *   **Base Case:** If `n` is 1, the list is already sorted. Set `start.next` to `null` to isolate this single node and return `start`.
    *   **Find Midpoint:** Traverse `n/2 - 1` steps from `start` to find the node just before the middle. Let this be `mid`.
    *   **Split List:** The second half of the list starts at `mid.next`. Store this in `head2`. Set `mid.next` to `null` to split the list into two halves.
    *   **Recursive Calls:** Recursively call `divide` on the first half (`start`, `midCount`) and the second half (`head2`, `n - midCount`).
    *   **Merge:** Merge the two sorted sub-lists returned by the recursive calls using the `merge` function.
3.  **Merge Sorted Lists (merge function):**
    *   Create a dummy head node (`head`) and a `tail` pointer initialized to `head`.
    *   Iterate while both `left` and `right` lists have nodes:
        *   Compare the values of the current nodes in `left` and `right`.
        *   Append the smaller node to `tail.next`.
        *   Advance the pointer of the list from which the node was taken.
        *   Advance `tail` to the newly appended node.
    *   After the loop, one of the lists might still have remaining nodes. Append the rest of the non-null list to `tail.next`.
    *   Return `head.next` (skipping the dummy head).

## Concept to Remember
*   **Merge Sort:** A divide-and-conquer sorting algorithm that recursively divides the list into halves, sorts each half, and then merges them.
*   **Linked List Manipulation:** Efficiently splitting and merging linked lists by manipulating `next` pointers.
*   **Recursion:** Using recursive calls to break down a problem into smaller, identical subproblems.
*   **Dummy Nodes:** Useful for simplifying list operations, especially at the head, by providing a consistent starting point for modifications.

## Common Mistakes
*   **Incorrect Midpoint Calculation:** Off-by-one errors when calculating the midpoint, leading to uneven splits or incorrect list segmentation.
*   **Not Handling Empty Lists:** Failing to check for `null` `head` or empty sub-lists during recursion or merging.
*   **Modifying Pointers Incorrectly:** Incorrectly setting `next` pointers during splitting or merging, leading to broken lists or infinite loops.
*   **Stack Overflow:** For very large lists, a purely recursive approach without tail call optimization (not standard in Java) could theoretically lead to stack overflow, though typically LeetCode constraints prevent this. The space complexity analysis accounts for the recursion depth.

## Complexity Analysis
- Time: O(n log n) - The list is divided logarithmically (log n levels of recursion), and at each level, we traverse all `n` nodes during the merge operation.
- Space: O(log n) - This is due to the recursion depth. In the worst case, the recursion stack will hold `log n` function calls.

## Commented Code
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
    // Main function to sort the linked list.
    public ListNode sortList(ListNode head) {
        int n = 0; // Initialize a counter for the number of nodes.
        ListNode end = head; // Use 'end' to traverse the list and count nodes.
        // Handle the edge case where the list is empty.
        if(head==null) return null;
        // Traverse the list to count the total number of nodes.
        while(end!=null){
            n++; // Increment the count for each node.
            end = end.next; // Move to the next node.
        }
        // Call the recursive divide function to sort the list.
        // 'head' is the starting node, and 'n' is the total number of nodes.
        return divide(head,n);
    }

    // Recursive function to divide the list into halves and sort them.
    public ListNode divide(ListNode start, int n){
        // Base case: If the sublist has only one node, it's already sorted.
        if(n == 1){
            start.next=null; // Ensure the single node is properly terminated.
            return start; // Return the single node as a sorted list.
        }
        ListNode mid = start; // Initialize 'mid' pointer to the start of the current sublist.
        int midCount = n/2; // Calculate the number of nodes in the first half.
        // Traverse to find the node just before the middle of the sublist.
        // We need to stop at midCount-1 to split the list correctly.
        for(int i=0;i<midCount-1;i++) mid = mid.next;
        ListNode head2 = mid.next; // 'head2' points to the start of the second half.
        mid.next=null; // Split the list by setting the next of the middle node to null.
        // Recursively sort the left half.
        ListNode left = divide(start,midCount);
        // Recursively sort the right half.
        ListNode right = divide(head2,n-midCount);
        // Merge the two sorted halves.
        return merge(left,right);
    }

    // Function to merge two sorted linked lists.
    public ListNode merge(ListNode left ,ListNode right){
        ListNode head = new ListNode(0); // Create a dummy head node to simplify merging.
        ListNode tail = head; // 'tail' pointer will build the merged list.
        // Iterate while both left and right lists have nodes.
        while(left!=null && right!=null){
            // Compare the values of the current nodes in left and right lists.
            if(left.val < right.val){
                tail.next = left; // Append the smaller node from 'left' to the merged list.
                left = left.next; // Move 'left' pointer to the next node.
            }
            else{
                tail.next=right; // Append the smaller node from 'right' to the merged list.
                right = right.next; // Move 'right' pointer to the next node.
            }
            tail = tail.next; // Move 'tail' pointer to the newly added node.
        }
        // If the left list is exhausted, append the remaining nodes from the right list.
        if(left == null) tail.next = right;
        // If the right list is exhausted, append the remaining nodes from the left list.
        else tail.next = left;
        // Return the head of the merged list, skipping the dummy node.
        return head.next;
    }
}
```

## Interview Tips
*   **Explain Merge Sort:** Clearly articulate the divide-and-conquer strategy of merge sort and why it's suitable for linked lists.
*   **Handle Edge Cases:** Be prepared to discuss how you handle an empty list (`head == null`) and lists with one or two nodes.
*   **Pointer Manipulation:** Demonstrate a strong understanding of how to correctly manipulate `next` pointers to split and merge lists without losing nodes.
*   **Complexity Justification:** Be ready to explain the time and space complexity derivation, especially the `O(log n)` space for recursion.

## Revision Checklist
- [ ] Understand the problem: Sort a linked list.
- [ ] Recall Merge Sort algorithm.
- [ ] Implement list traversal to count nodes.
- [ ] Implement recursive `divide` function with base case.
- [ ] Implement midpoint finding and list splitting.
- [ ] Implement `merge` function for two sorted lists.
- [ ] Use a dummy node for the `merge` function.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (empty list, single node list).

## Similar Problems
*   Merge Two Sorted Lists
*   Sort Colors
*   Merge K Sorted Lists

## Tags
`Linked List` `Recursion` `Divide and Conquer` `Merge Sort`
