# Remove Element

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Two Pointers`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int s = 0;
        int e = nums.length-1;
        while(s<=e){
            if(nums[e] == val){
                e--;
                continue;
            }
            if(nums[s] == val){
                int temp = nums[s];
                nums[s] = nums[e];
                nums[e] = temp;
            } 
            s++;
        }
        return s;
    }
}
```

---

---
## Quick Revision
Remove a specified value from an array in-place. This problem can be solved by using two pointers, one starting from the beginning and one from the end of the array.

## Intuition
The "aha moment" comes from realizing that we don't need to physically remove the elements from the array, but rather keep track of the position where the next non-target element should be placed. This is a classic example of in-place algorithm design.

## Algorithm

1. Initialize two pointers, `s` (start) and `e` (end), to the beginning and end of the array respectively.
2. Start iterating from both ends towards the center of the array.
3. If the element at the end (`nums[e]`) is equal to the target value, move `e` one step back.
4. If the element at the start (`nums[s]`) is equal to the target value, swap it with the element at the current end position (`nums[e]`) and then move both `s` and `e` one step forward.
5. Continue iterating until `s` meets or crosses `e`.
6. The final value of `s` represents the new length of the array without the target elements.

## Concept to Remember
* In-place algorithms: modifying input data structures in place rather than creating new ones.
* Two-pointer technique: using two pointers to traverse and manipulate an array efficiently.
* Array manipulation: understanding how to swap, insert, or remove elements from an array.

## Common Mistakes
* Failing to consider the edge cases where `s` meets or crosses `e`.
* Misunderstanding the role of the end pointer (`e`) in swapping elements.
* Forgetting to return the new length of the array instead of just the value of `s`.

## Complexity Analysis
- Time: O(n) - reason: Iterating through the array once with a time complexity of n.
- Space: O(1) - reason: Modifying the input array in place without using any additional space.

## Commented Code

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        // Initialize two pointers, s and e, to the beginning and end of the array.
        int s = 0;
        int e = nums.length - 1;

        // Iterate from both ends towards the center of the array.
        while (s <= e) {
            // If the element at the end is equal to the target value, move e one step back.
            if (nums[e] == val) {
                e--;
                continue;
            }
            // If the element at the start is equal to the target value, swap it with the element at the current end position and then move both s and e one step forward.
            if (nums[s] == val) {
                int temp = nums[s];
                nums[s] = nums[e];
                nums[e] = temp;
            }
            // Move the start pointer forward regardless of whether an element was swapped or not.
            s++;
        }
        // Return the new length of the array without the target elements.
        return s;
    }
}
```

## Interview Tips
* Practice explaining your thought process behind using two pointers for in-place algorithm design.
* Be prepared to handle edge cases and corner scenarios during the interview.
* Show examples of how this problem can be modified or extended, such as removing duplicates from an array.

## Revision Checklist
- [ ] Understand the concept of in-place algorithms and their applications.
- [ ] Practice solving similar problems with arrays and two-pointer techniques.
- [ ] Review edge cases for iterating pointers, especially when `s` meets or crosses `e`.

## Similar Problems
* Remove Duplicates from Sorted Array
* Remove Nth Node From End of List
* Move Zeroes to the End of an Array

## Tags
`Array`, `Hash Map`, `Two Pointers`, `In-place Algorithm`, `Remove Elements`
