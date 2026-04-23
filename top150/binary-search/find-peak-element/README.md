# Find Peak Element

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Binary Search`  
**Time:** O(log n)  
**Space:** O(log n)

---

## Solution (java)

```java
class Solution {
    public int findPeakElement(int[] nums){
        int left = 0;
        int right = nums.length-1;
        while(left < right){
            int mid = left + (right-left)/2;
            if(nums[mid] < nums[mid+1]) left = mid+1;
            else right = mid;
        }
        return left;
        //agar array bdhti hi rahi to last element hai peak. agr bdhti nhi rahi to peak beech me aayega mid aur right ke
        //agar array kam hoti hi rahi to first element hai peak. agr kamti nhi rahi to peak beech me aayega mid aur left ke
    }
}
//  [1,2,1,3,5,6,4,2] 
```

---

---
## Quick Revision
Given an integer array `nums` where `nums[i] != nums[i+1]`, find a peak element.
A peak element is an element that is strictly greater than its neighbors.

## Intuition
The problem guarantees that `nums[i] != nums[i+1]`, which means there are no plateaus. This property is crucial. If we are at an element `nums[mid]` and `nums[mid] < nums[mid+1]`, it implies that there *must* be a peak to the right of `mid` (including `mid+1` itself, or further to the right). Why? Because the array eventually has to go down (or reach the end, which is considered a peak if it's greater than its only neighbor). Conversely, if `nums[mid] > nums[mid+1]`, there *must* be a peak to the left of or at `mid`. This observation strongly suggests a binary search approach. We can eliminate half of the search space in each step.

## Algorithm
1. Initialize two pointers, `left` to the start of the array (index 0) and `right` to the end of the array (index `nums.length - 1`).
2. While `left` is less than `right`:
   a. Calculate the middle index: `mid = left + (right - left) / 2`. This prevents potential integer overflow.
   b. Compare `nums[mid]` with its right neighbor `nums[mid + 1]`.
   c. If `nums[mid] < nums[mid + 1]`: This means the peak must be to the right of `mid` (or `mid + 1` itself). So, update `left = mid + 1`.
   d. If `nums[mid] > nums[mid + 1]`: This means `mid` could be a peak, or the peak is to its left. So, update `right = mid`.
3. When the loop terminates, `left` will be equal to `right`. This index points to a peak element. Return `left`.

## Concept to Remember
*   **Binary Search:** Efficiently searching a sorted or partially ordered data structure by repeatedly dividing the search interval in half.
*   **Monotonicity:** The problem's constraint `nums[i] != nums[i+1]` implies local monotonicity, which is key for binary search.
*   **Edge Cases:** Handling array boundaries and the definition of a peak at the edges.

## Common Mistakes
*   **Incorrectly handling `mid+1`:** Accessing `nums[mid+1]` when `mid` is the last element can lead to an `ArrayIndexOutOfBoundsException`. The binary search condition `left < right` and the update `right = mid` prevent this.
*   **Not considering the problem constraints:** Assuming the array is fully sorted when it's not. The problem only guarantees local non-equality.
*   **Off-by-one errors in pointer updates:** Incorrectly updating `left` or `right` can lead to infinite loops or missing the peak.
*   **Returning the wrong index:** Ensuring the final `left` (or `right`) index correctly identifies a peak.

## Complexity Analysis
*   **Time:** O(log n) - The algorithm uses binary search, which halves the search space in each iteration.
*   **Space:** O(1) - The algorithm uses a constant amount of extra space for variables like `left`, `right`, and `mid`.

## Commented Code
```java
class Solution {
    public int findPeakElement(int[] nums){
        // Initialize the left pointer to the start of the array.
        int left = 0;
        // Initialize the right pointer to the end of the array.
        int right = nums.length - 1;

        // Continue the binary search as long as the left pointer is less than the right pointer.
        // This ensures we are always working with a valid search space of at least two elements.
        while(left < right){
            // Calculate the middle index. Using (right - left) / 2 prevents potential integer overflow.
            int mid = left + (right - left) / 2;

            // Compare the middle element with its right neighbor.
            // If nums[mid] is less than nums[mid+1], it means the peak must be to the right of mid.
            // This is because the array is increasing at this point, and it must eventually decrease or end.
            if(nums[mid] < nums[mid+1]){
                // Move the left pointer to mid + 1, effectively discarding the left half including mid.
                left = mid + 1;
            }
            // If nums[mid] is greater than or equal to nums[mid+1] (since nums[i] != nums[i+1], it's strictly greater),
            // it means mid could be a peak, or the peak is to its left.
            else {
                // Move the right pointer to mid. We keep mid in the search space because it might be the peak.
                right = mid;
            }
        }
        // When the loop terminates, left == right. This index is guaranteed to be a peak element.
        // If the array was strictly increasing, 'left' would end up at the last element.
        // If the array was strictly decreasing, 'left' would end up at the first element.
        // In mixed cases, it converges to a peak.
        return left;
    }
}
```

## Interview Tips
*   **Explain the binary search logic clearly:** Emphasize why `nums[mid] < nums[mid+1]` implies a peak to the right and `nums[mid] > nums[mid+1]` implies a peak to the left or at `mid`.
*   **Discuss the `nums[i] != nums[i+1]` constraint:** Highlight how this simplifies the problem and guarantees a peak exists and allows for binary search.
*   **Walk through an example:** Use `[1,2,1,3,5,6,4,2]` to show how `left` and `right` pointers move and converge.
*   **Consider edge cases:** Briefly mention what happens if the array has only one element (it's a peak) or if the peak is at the beginning or end.

## Revision Checklist
- [ ] Understand the definition of a peak element.
- [ ] Recognize the applicability of binary search due to `nums[i] != nums[i+1]`.
- [ ] Implement the binary search correctly with `left < right` condition.
- [ ] Correctly update `left` and `right` pointers based on `nums[mid]` vs `nums[mid+1]`.
- [ ] Handle potential integer overflow in `mid` calculation.
- [ ] Verify the return value is the index of a peak element.

## Similar Problems
*   Search in Rotated Sorted Array
*   Find Minimum in Rotated Sorted Array
*   Median of Two Sorted Arrays

## Tags
`Array` `Binary Search`
