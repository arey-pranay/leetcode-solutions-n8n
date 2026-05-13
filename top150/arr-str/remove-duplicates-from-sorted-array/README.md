# Remove Duplicates From Sorted Array

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Two Pointers`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int done = 0;
        int checking = 1;
        while(checking<nums.length){
            if(nums[done]!=nums[checking]){
                int temp = nums[done+1];
                nums[done+1] = nums[checking];
                nums[checking] = temp;
                done++;
            }
            checking++;
        }
        return done+1;
    }
}
```

---

---
## Quick Revision
Given a sorted array, remove duplicates in-place such that each unique element appears only once.
Use two pointers to iterate and overwrite duplicates.

## Intuition
Since the array is sorted, all duplicate elements will be adjacent to each other. We can use two pointers: one to keep track of the position where the next unique element should be placed (`done`), and another to iterate through the array and find unique elements (`checking`). When we find a unique element (i.e., `nums[checking]` is different from `nums[done]`), we place it at the `done + 1` position and advance `done`.

## Algorithm
1. Initialize two pointers, `done` to 0 and `checking` to 1. `done` will point to the last unique element found so far, and `checking` will scan the array.
2. Iterate while `checking` is less than the length of the array.
3. Inside the loop, compare `nums[done]` and `nums[checking]`.
4. If `nums[done]` is not equal to `nums[checking]`, it means `nums[checking]` is a new unique element.
5. To place this unique element at the correct position, increment `done` first, then assign `nums[checking]` to `nums[done]`.
6. Regardless of whether a duplicate was found or not, increment `checking` to move to the next element.
7. After the loop finishes, `done` will be the index of the last unique element. The number of unique elements is `done + 1`. Return `done + 1`.

## Concept to Remember
*   In-place modification: Modifying the array directly without using extra space.
*   Two-pointer technique: Using multiple pointers to traverse and manipulate data structures efficiently.
*   Sorted array properties: Leveraging the ordered nature of the input for efficient processing.

## Common Mistakes
*   Forgetting to handle the base case where the array is empty or has only one element.
*   Incorrectly updating the `done` pointer (e.g., incrementing it before assigning the new element).
*   Not understanding that the problem requires in-place modification and returning the *length* of the modified array, not the modified array itself.
*   Using extra space (like a Set or another array) when the problem explicitly asks for an in-place solution.

## Complexity Analysis
*   Time: O(n) - reason: We iterate through the array once with the `checking` pointer.
*   Space: O(1) - reason: We are modifying the array in-place and not using any auxiliary data structures that grow with input size.

## Commented Code
```java
class Solution {
    public int removeDuplicates(int[] nums) {
        // 'done' pointer tracks the index of the last unique element found.
        // It also implicitly indicates the position where the next unique element should be placed.
        int done = 0;
        // 'checking' pointer iterates through the array to find unique elements.
        // It starts from the second element because the first element is always unique initially.
        int checking = 1;

        // Loop continues as long as 'checking' pointer is within the array bounds.
        while(checking < nums.length){
            // If the element at 'checking' is different from the element at 'done',
            // it means we've found a new unique element.
            if(nums[done] != nums[checking]){
                // Increment 'done' to move to the next position for the unique element.
                done++;
                // Place the unique element found at 'checking' into the position indicated by 'done'.
                nums[done] = nums[checking];
            }
            // Move the 'checking' pointer to the next element to continue scanning.
            checking++;
        }
        // The number of unique elements is 'done' (the last index of a unique element) + 1.
        // This is because 'done' is 0-indexed.
        return done + 1;
    }
}
```

## Interview Tips
*   Clearly explain your two-pointer strategy before coding.
*   Walk through an example array step-by-step to demonstrate your logic.
*   Emphasize the "in-place" requirement and how your solution achieves it.
*   Be prepared to discuss the time and space complexity.

## Revision Checklist
- [ ] Understand the problem statement: remove duplicates from a sorted array in-place.
- [ ] Identify the two-pointer approach.
- [ ] Implement the logic for advancing `done` and `checking` pointers.
- [ ] Handle the comparison `nums[done] != nums[checking]`.
- [ ] Correctly assign `nums[done] = nums[checking]`.
- [ ] Return the correct count of unique elements (`done + 1`).
- [ ] Consider edge cases (empty array, single element array).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Remove Duplicates from Sorted List
*   Remove Element
*   Move Zeroes
*   Sort Colors

## Tags
`Array` `Two Pointers`
