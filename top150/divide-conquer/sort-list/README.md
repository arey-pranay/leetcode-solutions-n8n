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
The problem asks us to sort a linked list. Standard sorting algorithms like quicksort or mergesort come to mind. For linked lists, mergesort is often preferred because it doesn't require random access and can be implemented efficiently. The core idea is to recursively divide the list into smaller halves, sort each half, and then merge the sorted halves.

## Algorithm
1. **Count the number of nodes (n)**: Traverse the list to determine its length. This is needed to find the middle point for splitting.
2. **Recursive Division (divide function)**:
    a. **Base Case**: If the number of nodes (n) is 1, the sublist is already sorted. Set `start.next` to `null` to properly terminate this sublist and return `start`.
    b. **Find Middle**: Traverse `n/2 - 1` steps from `start` to find the node just before the middle (`mid`).
    c. **Split List**: The second half starts at `mid.next`. Set `mid.next` to `null` to split the list into two halves.
    d. **Recursive Calls**: Recursively call `divide` on the first half (`start`, `midCount`) and the second half (`head2`, `n - midCount`).
    e. **Merge**: Merge the two sorted halves returned by the recursive calls using the `merge` function.
3. **Merge Sorted Lists (merge function)**:
    a. Create a dummy head node (`head`) and a tail pointer (`tail`) initialized to `head`.
    b. While both `left` and `right` lists have nodes:
        i. Compare the values of the current nodes in `left` and `right`.
        ii. Append the smaller node to `tail.next`.
        iii. Advance the pointer of the list from which the node was taken.
        iv. Advance `tail` to the newly appended node.
    c. After one list is exhausted, append the remaining nodes of the other list to `tail.next`.
    d. Return `head.next` (skipping the dummy head).

## Concept to Remember
*   **Merge Sort**: A divide-and-conquer sorting algorithm that recursively divides the list into halves, sorts them, and then merges them.
*   **Linked List Manipulation**: Efficiently splitting and merging linked lists by manipulating `next` pointers.
*   **Recursion**: Breaking down a problem into smaller, self-similar subproblems.
*   **Dummy Nodes**: Useful for simplifying list operations, especially at the head.

## Common Mistakes
*   Incorrectly calculating the middle point for splitting, especially for even/odd length lists.
*   Not properly null-terminating the first half of the list after splitting, leading to infinite loops or incorrect merging.
*   Off-by-one errors when iterating to find the middle node.
*   Forgetting to handle the case where one of the sublists is empty during the merge process.
*   Modifying the original list structure incorrectly during recursive calls.

## Complexity Analysis
- Time: O(n log n) - The list is divided logarithmically (log n levels of recursion), and at each level, we traverse all n nodes for merging.
- Space: O(log n) - Due to the recursion stack depth, which is proportional to the height of the recursion tree (log n for a balanced split).

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
        // Initialize a counter for the number of nodes.
        int n = 0;
        // Use a temporary pointer 'end' to traverse the list.
        ListNode end = head;
        // If the list is empty, return null immediately.
        if(head==null) return null;
        // Traverse the list to count the total number of nodes.
        while(end!=null){
            // Increment the count for each node.
            n++;
            // Move to the next node.
            end = end.next;
        }
        // Call the recursive divide function to sort the list.
        // 'head' is the starting node, 'n' is the total number of nodes.
        return divide(head,n);
    }

    // Recursive function to divide the list into halves and sort them.
    public ListNode divide(ListNode start, int n){
        // Base case: If the sublist has only one node, it's already sorted.
        if(n == 1){
            // Ensure the single node is properly terminated.
            start.next=null;
            // Return the single node as a sorted sublist.
            return start;
        }
        // Initialize 'mid' pointer to the start of the current sublist.
        ListNode mid = start;
        // Calculate the number of nodes in the first half.
        int midCount = n/2;
        // Traverse 'midCount - 1' steps to reach the node just before the middle.
        for(int i=0;i<midCount-1;i++) mid = mid.next;
        // 'head2' points to the start of the second half of the list.
        ListNode head2 = mid.next;
        // Split the list by setting the 'next' of the middle node to null.
        mid.next=null;
        // Recursively sort the first half of the list.
        ListNode left = divide(start,midCount);
        // Recursively sort the second half of the list.
        ListNode right = divide(head2,n-midCount);
        // Merge the two sorted halves and return the merged list.
        return merge(left,right);
    }

    // Function to merge two sorted linked lists.
    public ListNode merge(ListNode left ,ListNode right){
        // Create a dummy head node to simplify the merge process.
        ListNode head = new ListNode(0);
        // 'tail' pointer will be used to build the merged list.
        ListNode tail = head;
        // Iterate while both 'left' and 'right' lists have nodes.
        while(left!=null && right!=null){
            // Compare the values of the current nodes in 'left' and 'right'.
            if(left.val < right.val){
                // If 'left' node is smaller, append it to the merged list.
                tail.next = left;
                // Move 'left' pointer to the next node.
                left = left.next;
            }
            else{
                // If 'right' node is smaller or equal, append it to the merged list.
                tail.next=right;
                // Move 'right' pointer to the next node.
                right = right.next;
            }
            // Move the 'tail' pointer to the newly added node.
            tail = tail.next;
        }
        // If the 'left' list is exhausted, append the remaining nodes of 'right'.
        if(left == null) tail.next = right;
        // If the 'right' list is exhausted, append the remaining nodes of 'left'.
        else tail.next = left;
        // Return the head of the merged list (skipping the dummy node).
        return head.next;
    }
}
```

## Interview Tips
*   Clearly explain the merge sort approach and why it's suitable for linked lists.
*   Walk through the `divide` and `merge` functions step-by-step with a small example.
*   Pay close attention to edge cases: empty list, single-node list, and how `mid.next = null` correctly splits the list.
*   Discuss the time and space complexity trade-offs.

## Revision Checklist
- [ ] Understand the problem: Sort a linked list.
- [ ] Recall merge sort as a suitable algorithm for linked lists.
- [ ] Implement the `sortList` function to get list length and initiate sorting.
- [ ] Implement the `divide` function for recursive splitting.
- [ ] Implement the `merge` function for combining sorted sublists.
- [ ] Handle base cases in recursion (n=1).
- [ ] Correctly find and use the middle node for splitting.
- [ ] Ensure proper null termination after splitting.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (empty list, single node).

## Similar Problems
*   Merge Two Sorted Lists
*   Sort Colors
*   Kth Smallest Element in a Sorted Matrix
*   Merge K Sorted Lists

## Tags
`Linked List` `Recursion` `Divide and Conquer` `Merge Sort`
