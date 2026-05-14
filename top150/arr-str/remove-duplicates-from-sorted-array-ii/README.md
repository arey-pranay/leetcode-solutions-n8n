# Remove Duplicates From Sorted Array Ii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Two Pointers`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        // if(nums[j] == nums[j-2]) // mtlb yahan pe chain bni hui hai, is j ke liye nayi value dhundhni hogi
        // we need to replace when nums[i] != nums[j-2]
        // so
        int j = 2;
        for(int i = 2; i<n; i++)if(nums[i] != nums[j-2]) nums[j++] = nums[i];
        return j;
    }
}
// 001011122

// 0001111222333344
// 0011111222333344
// 0011222222333344
// 0011223333333344
// 0011223344 444444


```

---

---
## Quick Revision
Given a sorted array `nums`, remove duplicates such that each element appears at most twice.
The relative order of the elements should be kept the same.

## Intuition
Since the array is sorted, duplicates will always be adjacent. We can use a two-pointer approach. One pointer (`i`) iterates through the array, and another pointer (`j`) keeps track of the position where the next valid element should be placed. The key insight is that an element is valid if it's different from the element two positions behind the `j` pointer. This ensures we don't have more than two occurrences of any number.

## Algorithm
1. Initialize a pointer `j` to 2. This pointer will represent the index where the next non-duplicate element should be placed. We start at 2 because the first two elements are always considered valid (as they can appear at most twice).
2. Iterate through the array with a pointer `i` starting from index 2.
3. For each element `nums[i]`, compare it with the element at `nums[j-2]`.
4. If `nums[i]` is *not* equal to `nums[j-2]`, it means `nums[i]` is a valid element to keep (either it's a new number or it's the second occurrence of a number that's already been placed twice).
5. In this case, copy `nums[i]` to `nums[j]` and increment `j`.
6. After the loop finishes, `j` will be the length of the modified array. Return `j`.

## Concept to Remember
*   **Two Pointers:** Efficiently traversing and manipulating arrays by using multiple pointers.
*   **In-place modification:** Modifying the array directly without using extra space for a new array.
*   **Sorted Array Properties:** Leveraging the ordered nature of the input to simplify duplicate detection.

## Common Mistakes
*   Incorrectly initializing the `j` pointer (e.g., starting at 0 or 1).
*   Comparing `nums[i]` with `nums[j-1]` instead of `nums[j-2]`, which would allow more than two duplicates.
*   Forgetting to increment `j` after placing a valid element.
*   Not handling edge cases like arrays with fewer than 2 elements (though the provided solution implicitly handles this by the loop condition).

## Complexity Analysis
*   Time: O(n) - reason: We iterate through the array once with the `i` pointer.
*   Space: O(1) - reason: We are modifying the array in-place and only using a few extra variables.

## Commented Code
```java
class Solution {
    public int removeDuplicates(int[] nums) {
        // Get the total number of elements in the input array.
        int n = nums.length;
        // Initialize 'j' to 2. 'j' is the pointer for the next position to place a valid element.
        // The first two elements are always considered valid as per the problem statement (at most twice).
        int j = 2;
        // Iterate through the array starting from the third element (index 2).
        for(int i = 2; i < n; i++) {
            // Check if the current element 'nums[i]' is different from the element two positions behind 'j'.
            // 'nums[j-2]' represents the element that was placed two steps ago.
            // If 'nums[i]' is different, it means it's either a new number or the second occurrence of a number
            // that has already been placed twice (because if it were the third occurrence, it would be equal to nums[j-2]).
            if(nums[i] != nums[j-2]) {
                // If it's a valid element, place it at the 'j' position.
                nums[j] = nums[i];
                // Increment 'j' to point to the next available position for a valid element.
                j++;
            }
        }
        // 'j' now represents the length of the modified array with duplicates removed (at most twice).
        return j;
    }
}
```

## Interview Tips
*   Clearly explain the two-pointer strategy and why `j-2` is the crucial comparison point.
*   Walk through an example on the whiteboard to demonstrate how the pointers move and how the array is modified.
*   Discuss edge cases like empty arrays or arrays with only one or two elements.
*   Be prepared to explain the time and space complexity of your solution.

## Revision Checklist
- [ ] Understand the problem statement: "at most twice" is key.
- [ ] Recognize the sorted nature of the array.
- [ ] Implement the two-pointer approach correctly.
- [ ] Ensure the comparison `nums[i] != nums[j-2]` is used.
- [ ] Verify the base cases and pointer initializations.
- [ ] Analyze time and space complexity.

## Similar Problems
Remove Duplicates From Sorted Array
Remove Element

## Tags
`Array` `Two Pointers` `In-place`
