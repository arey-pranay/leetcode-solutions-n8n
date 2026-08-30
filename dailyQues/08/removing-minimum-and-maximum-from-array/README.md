# Removing Minimum And Maximum From Array

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Greedy`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int minimumDeletions(int[] nums) {
        int n= nums.length;
        int min = 100001;
        int max = -100001;
        int minI = -1;
        int maxI = -1;
        for(int i =0 ; i<n;i++){
            if(min>nums[i]){
                min = nums[i];
                minI = i;
            }
            if(max<nums[i]){
                max = nums[i];
                maxI = i;
            }
        }
    
    
        if(minI > maxI){
            int temp = minI;
            minI = maxI;
            maxI=temp;
        }
        
        
        //Now we know that, maxI > minI (minI left me hai, maxI right me)

        int a = maxI+1; //starting se hataye
        int b = minI+1 + n-maxI; // aage wale ko aage se hataya, pichhe wale ko pichhe se
        int c = n-minI; // dono ko pichhe se hataya
        return Math.min(a,Math.min(b,c));
    }
}
```

---

---
## Quick Revision
This problem asks for the minimum number of deletions to remove both the minimum and maximum elements from an array.
We solve it by finding the indices of the min and max elements and considering three deletion strategies.

## Intuition
The core idea is that we want to remove both the minimum and maximum elements. We can achieve this by deleting elements from the left, from the right, or a combination. The "aha moment" comes from realizing that the optimal strategy will involve removing elements up to the *closer* of the two target elements (min or max) from one end, and then removing elements up to the *closer* of the two target elements from the *other* end. This leads to three distinct scenarios to consider.

## Algorithm
1. Initialize `minVal` to a very large number and `maxVal` to a very small number.
2. Initialize `minIndex` and `maxIndex` to -1.
3. Iterate through the input array `nums`:
    a. If the current element `nums[i]` is smaller than `minVal`, update `minVal` to `nums[i]` and `minIndex` to `i`.
    b. If the current element `nums[i]` is larger than `maxVal`, update `maxVal` to `nums[i]` and `maxIndex` to `i`.
4. Ensure `minIndex` is less than or equal to `maxIndex` by swapping them if `minIndex > maxIndex`. This simplifies the subsequent calculations.
5. Calculate the number of deletions for three possible strategies:
    a. Delete from the left up to the maximum index: `maxIndex + 1`.
    b. Delete from the left up to the minimum index, and from the right up to the maximum index: `(minIndex + 1) + (n - maxIndex)`.
    c. Delete from the right up to the minimum index: `n - minIndex`.
6. Return the minimum of these three calculated deletion counts.

## Concept to Remember
*   Finding Minimum/Maximum: Efficiently identifying extreme values in an array.
*   Index Tracking: Keeping track of the positions of specific elements.
*   Case Analysis/Optimization: Breaking down a problem into distinct scenarios and finding the best among them.
*   Array Traversal: Iterating through array elements to gather information.

## Common Mistakes
*   Not handling the case where the minimum and maximum elements are the same.
*   Incorrectly calculating the number of deletions from the right end of the array.
*   Forgetting to sort or normalize the `minIndex` and `maxIndex` before calculating deletion counts, leading to logical errors.
*   Off-by-one errors when calculating the number of elements to delete from either end.
*   Assuming the min and max are always at the ends of the array.

## Complexity Analysis
- Time: O(n) - reason: We iterate through the array once to find the minimum and maximum elements and their indices. The subsequent calculations are constant time.
- Space: O(1) - reason: We only use a few extra variables to store the minimum/maximum values and their indices, regardless of the input array size.

## Commented Code
```java
class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length; // Get the total number of elements in the array.
        int minVal = 100001; // Initialize minVal to a value larger than any possible element in nums.
        int maxVal = -100001; // Initialize maxVal to a value smaller than any possible element in nums.
        int minIndex = -1; // Initialize the index of the minimum element to -1.
        int maxIndex = -1; // Initialize the index of the maximum element to -1.

        // Iterate through the array to find the minimum and maximum values and their indices.
        for (int i = 0; i < n; i++) {
            // If the current element is smaller than the current minimum value found so far.
            if (minVal > nums[i]) {
                minVal = nums[i]; // Update the minimum value.
                minIndex = i; // Update the index of the minimum value.
            }
            // If the current element is larger than the current maximum value found so far.
            if (maxVal < nums[i]) {
                maxVal = nums[i]; // Update the maximum value.
                maxIndex = i; // Update the index of the maximum value.
            }
        }

        // Ensure that minIndex is always less than or equal to maxIndex for simpler calculations.
        // If minIndex is greater than maxIndex, swap them.
        if (minIndex > maxIndex) {
            int temp = minIndex; // Store minIndex temporarily.
            minIndex = maxIndex; // Assign maxIndex to minIndex.
            maxIndex = temp; // Assign the temporary value (original minIndex) to maxIndex.
        }

        // Now we know that, maxIndex >= minIndex.
        // We have three possible strategies to remove both min and max elements:

        // Strategy 1: Remove both from the left side.
        // This requires deleting all elements up to and including the maximum element's index.
        // The number of deletions is maxIndex + 1 (since indices are 0-based).
        int deletionsFromLeftToMax = maxIndex + 1;

        // Strategy 2: Remove the minimum from the left and the maximum from the right.
        // This requires deleting elements from the left up to the minimum element's index (minIndex + 1 deletions).
        // And deleting elements from the right up to the maximum element's index (n - maxIndex deletions).
        int deletionsMinLeftMaxRight = (minIndex + 1) + (n - maxIndex);

        // Strategy 3: Remove both from the right side.
        // This requires deleting all elements from the right up to and including the minimum element's index.
        // The number of deletions is n - minIndex (elements from minIndex to n-1).
        int deletionsFromRightToMin = n - minIndex;

        // Return the minimum of the three calculated deletion counts.
        return Math.min(deletionsFromLeftToMax, Math.min(deletionsMinLeftMaxRight, deletionsFromRightToMin));
    }
}
```

## Interview Tips
*   Clearly articulate the three possible deletion strategies (all left, all right, mixed ends) before diving into code.
*   Explain the logic behind swapping `minIndex` and `maxIndex` to simplify calculations.
*   Walk through an example array to demonstrate how the three deletion counts are derived.
*   Be prepared to discuss edge cases like an array with all identical elements or an array of size 1 or 2.

## Revision Checklist
- [ ] Understand the problem statement: minimum deletions to remove min and max.
- [ ] Identify min and max values and their indices.
- [ ] Consider the three main deletion strategies:
    - [ ] Delete from left up to max index.
    - [ ] Delete from right up to min index.
    - [ ] Delete from left up to min index AND from right up to max index.
- [ ] Handle index ordering (ensure minIndex <= maxIndex).
- [ ] Correctly calculate deletions for each strategy.
- [ ] Implement the logic efficiently (single pass).
- [ ] Test with edge cases (e.g., all same elements, small arrays).

## Similar Problems
*   [1672. Richest Customer Wealth](https://leetcode.com/problems/richest-customer-wealth/) (Different problem, but involves array traversal and finding max)
*   [217. Contains Duplicate](https://leetcode.com/problems/contains-duplicate/) (Array traversal, identifying properties)
*   [153. Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) (Finding minimum, but with binary search)

## Tags
`Array` `Two Pointers` `Greedy`
