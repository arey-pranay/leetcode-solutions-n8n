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
Remove duplicates from a sorted array such that each element appears at most twice.
Use a two-pointer approach to overwrite elements in-place.

## Intuition
Since the array is sorted, duplicates will be adjacent. We want to keep elements that are not the third (or more) occurrence of a number. The key insight is that if we are considering `nums[i]`, we only need to compare it with the element two positions behind the *current write position* (`j-2`). If `nums[i]` is different from `nums[j-2]`, it means `nums[i]` is either a new number or the second occurrence of the previous number, and thus it's safe to keep.

## Algorithm
1. Initialize a "write pointer" `j` to 2. This pointer indicates the next position in the array where a valid element should be placed. We start at 2 because the first two elements are always valid (as they can be the first or second occurrence of a number).
2. Iterate through the array with a "read pointer" `i` starting from index 2.
3. For each element `nums[i]`, compare it with the element at `nums[j-2]`.
4. If `nums[i]` is *not equal* to `nums[j-2]`, it means `nums[i]` is a valid element to keep (either a new number or the second occurrence of the previous number).
5. In this case, copy `nums[i]` to `nums[j]` and increment `j`.
6. If `nums[i]` *is equal* to `nums[j-2]`, it means `nums[i]` is the third or more occurrence of the current number, so we skip it and do not increment `j`.
7. After the loop finishes, `j` will represent the length of the modified array with duplicates removed according to the rule. Return `j`.

## Concept to Remember
*   **Two Pointers:** Efficiently traversing and manipulating arrays by using two indices that move at different rates.
*   **In-place Modification:** Modifying the input array directly without using extra space for a new array.
*   **Sorted Array Properties:** Leveraging the sorted nature of the input to group identical elements together.

## Common Mistakes
*   Comparing `nums[i]` with `nums[j-1]` instead of `nums[j-2]`, which would incorrectly remove the second occurrence of an element.
*   Forgetting to handle the base cases where the array has fewer than 3 elements (though the provided solution implicitly handles this by starting `j` at 2 and the loop at 2).
*   Incorrectly initializing the write pointer `j`, leading to off-by-one errors or incorrect overwrites.
*   Not returning the correct length (`j`) at the end, but rather the original length or an incorrect index.

## Complexity Analysis
*   Time: O(n) - The algorithm iterates through the array once with the `i` pointer.
*   Space: O(1) - The algorithm modifies the array in-place and uses only a constant amount of extra space for variables like `j`.

## Commented Code
```java
class Solution {
    public int removeDuplicates(int[] nums) {
        // Get the total number of elements in the input array.
        int n = nums.length;
        // Initialize 'j' as the write pointer. It starts at 2 because the first two elements
        // are always allowed to be kept, as they can be the first or second occurrence of a number.
        // 'j' also represents the length of the valid subarray being formed.
        int j = 2;
        // Iterate through the array starting from the third element (index 2) using 'i' as the read pointer.
        for(int i = 2; i<n; i++) {
            // Check if the current element nums[i] is different from the element two positions
            // behind the write pointer (nums[j-2]). This condition ensures that we are not
            // keeping a third or subsequent occurrence of any number.
            // If nums[i] is different from nums[j-2], it means nums[i] is either a new number
            // or the second occurrence of the previous number, and thus it's a valid element to keep.
            if(nums[i] != nums[j-2]) {
                // If the element is valid, copy it to the position indicated by the write pointer 'j'.
                nums[j] = nums[i];
                // Increment the write pointer 'j' to point to the next available slot for a valid element.
                j++;
            }
            // If nums[i] == nums[j-2], it means nums[i] is a duplicate beyond the allowed two occurrences,
            // so we simply skip it by not copying it and not incrementing 'j'.
        }
        // After the loop, 'j' holds the count of valid elements, which is also the length of the modified array.
        return j;
    }
}
```

## Interview Tips
*   Clearly explain the two-pointer approach and the logic behind comparing `nums[i]` with `nums[j-2]`.
*   Walk through an example on a whiteboard or paper to demonstrate how the pointers move and how the array is modified.
*   Be prepared to discuss edge cases like empty arrays or arrays with fewer than 3 elements.
*   Emphasize the in-place modification and constant space complexity.

## Revision Checklist
- [ ] Understand the problem statement: "at most twice".
- [ ] Identify the sorted nature of the input.
- [ ] Grasp the two-pointer strategy.
- [ ] Understand the `nums[i] != nums[j-2]` condition.
- [ ] Trace execution with an example.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (empty, small arrays).

## Similar Problems
Remove Duplicates From Sorted Array
Sort Colors
Remove Element

## Tags
`Array` `Two Pointers` `In-place`
