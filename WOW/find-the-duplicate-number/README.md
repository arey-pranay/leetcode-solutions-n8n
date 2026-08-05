# Find The Duplicate Number

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Two Pointers` `Binary Search` `Bit Manipulation` `Pigeonhole Principle` `Floyd's Cycle Finding Algorithm`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow; //or nums[fast]; 
    }

    // 1 3 4 2 2
    // 0->1->3->2->4->2

    // 3 1 3 4 2
    // 0 -> 3 -> 4 -> 2 -> 3 
}

```

---

---
## Quick Revision
The problem is to find a duplicate number in an array where numbers are indices of other elements.
We solve it by using Floyd's Tortoise and Hare algorithm (slow and fast pointers) to detect the cycle.

## Intuition
The "aha moment" comes from realizing that if there's a duplicate number, it must be part of a cycle in the array because each number points to another index. We can use this cycle detection property to find the duplicate.

## Algorithm
1. Initialize two pointers `slow` and `fast`, both pointing to the first element.
2. Move `slow` one step at a time, moving `fast` two steps at a time.
3. When `slow` and `fast` meet, it means there's a cycle (duplicate number).
4. Reset `slow` back to the start.
5. Both `slow` and `fast` move one step at a time; when they meet again, this is the duplicate number.

## Concept to Remember
* Cycles in graphs can be detected using Floyd's Tortoise and Hare algorithm.
* Array indices can represent graph edges.

## Common Mistakes
* Not resetting `slow` back to the start after detecting a cycle.
* Assuming the duplicate is the last element reached by both pointers.
* Not realizing that each number points to another index, creating a graph-like structure.

## Complexity Analysis
- Time: O(n) - traverse the array once / 
- Space: O(1) - constant space used for pointers.

## Commented Code
```java
class Solution {
    public int findDuplicate(int[] nums) {
        // Initialize slow and fast pointers
        int slow = 0; // Start at index 0
        int fast = 0; // Start at index 0

        do {
            // Move slow one step forward
            slow = nums[slow];
            // Move fast two steps forward
            fast = nums[nums[fast]];
        } while (slow != fast); // Detect cycle and break loop

        // Reset slow to start, both pointers move one step at a time
        slow = 0;
        while (slow != fast) {
            // Move slow one step forward
            slow = nums[slow];
            // Move fast one step forward
            fast = nums[fast];
        }

        return slow; // Return duplicate number
    }
}
```

## Interview Tips
* Practice explaining Floyd's Tortoise and Hare algorithm.
* Pay attention to resetting `slow` back to the start after detecting a cycle.
* Recognize the graph structure represented by array indices.

## Revision Checklist
- [ ] Can explain Floyd's Tortoise and Hare algorithm.
- [ ] Reset `slow` back to the start after detecting a cycle.
- [ ] Realize the graph structure represented by array indices.

## Similar Problems
* 142. Linked List Cycle II (detecting cycles in linked lists)
* 287. Find the Duplicate Number in an Array (find duplicate in array using Floyd's algorithm)

## Tags
`Array` `Hash Map` `Cycle Detection`
