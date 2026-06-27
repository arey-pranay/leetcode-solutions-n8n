# Palindrome Linked List

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Linked List` `Two Pointers` `Stack` `Recursion`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    ListNode left;

    public boolean isPalindrome(ListNode head) {
        left = head;
        return check(head);
    }

    private boolean check(ListNode right) {
        if (right == null)
            return true;

        if (!check(right.next))
            return false;

        if (left.val != right.val)
            return false;

        left = left.next;

        return true;
    }
}
```

---

---

## Quick Revision
Determine whether a singly linked list is a palindrome.
Solve it by using a two-pointer approach, one starting from the head and another from the end of the list.

## Intuition
The key insight here is that we can solve this problem by comparing the values at each corresponding position in the first half and second half of the linked list. This works because if the entire list is a palindrome, then any prefix of it will also be a palindrome.

## Algorithm
1. Initialize two pointers, `left` and `right`, to the head of the linked list.
2. Move the `right` pointer to the end of the first half of the list using a while loop.
3. Compare the values at each corresponding position in the first half and second half by recursively calling the `check` method.
4. If any pair of nodes has different values, return false.

## Concept to Remember
* Singly linked lists
* Two-pointer technique for solving problems involving linked lists

## Common Mistakes
* Not initializing both pointers correctly at the beginning of the algorithm.
* Forgetting to move the `left` pointer forward in each recursive call.
* Not handling the case where the linked list has an odd number of nodes.

## Complexity Analysis
- Time: O(n) - reason: we only traverse the linked list once.
- Space: O(1) - reason: we use a constant amount of space to store the two pointers.

## Commented Code
```java
class Solution {
    ListNode left; // pointer for the first half

    public boolean isPalindrome(ListNode head) {
        // initialize both pointers at the beginning
        left = head;
        return check(head); // start checking from the end of the first half
    }

    private boolean check(ListNode right) {
        if (right == null) { // if we've reached the end, it's a palindrome
            return true;
        }

        if (!check(right.next)) { // recursively check the rest of the second half
            return false; // if any pair has different values, it's not a palindrome
        }

        if (left.val != right.val) { // compare the current pair of nodes
            return false; // if they're different, it's not a palindrome
        }

        left = left.next; // move the 'left' pointer forward

        return true;
    }
}
```

## Interview Tips
* Make sure to initialize both pointers correctly at the beginning.
* Don't forget to move the `left` pointer forward in each recursive call.
* Pay attention to the case where the linked list has an odd number of nodes.

## Revision Checklist
- [ ] Review two-pointer technique for solving problems involving linked lists.
- [ ] Practice handling cases with odd-length linked lists.
- [ ] Test code on edge cases, such as empty or single-node linked lists.

## Similar Problems
* Palindrome String (LeetCode 5)
* Valid Palindrome (LeetCode 680)

## Tags
`Linked List` `Palindrome` `Two-Pointer Technique`
