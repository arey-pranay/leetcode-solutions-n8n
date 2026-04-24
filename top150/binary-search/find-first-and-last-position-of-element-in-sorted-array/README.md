# Find First And Last Position Of Element In Sorted Array

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Binary Search`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    int[] ans = new int[]{-1,-1};
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        if(n==0) return ans;
        bs(0,n-1,nums,target);
        return ans;
    }
    public void bs(int start, int end , int[]nums, int target){
        if(start > end) return;
        int mid = start + (end-start)/2;
        
        if(nums[mid]==target){
            
            if(ans[0]==-1 || ans[0] > mid){
                // System.out.println("Found smaller start");
                ans[0] = mid;
            }
            if(ans[1]==-1 || ans[1] < mid){
                // System.out.println("Found larger end");
                ans[1] = mid;
            }
            bs(start,mid-1,nums,target);
            bs(mid+1,end,nums,target);
            return;
        }
        if(nums[mid]<target) bs(mid+1,end,nums,target);
        else bs(start,mid-1,nums,target);
        return; 
    }
} 

```

---

---
## Quick Revision
Given a sorted array, find the starting and ending position of a given target value.
This is solved by modifying binary search to find the leftmost and rightmost occurrences.

## Intuition
The core idea is that since the array is sorted, all occurrences of the target value will be contiguous. A standard binary search finds *an* occurrence, but we need the *first* and *last*. We can adapt binary search. When we find the target, instead of stopping, we can try to search further left for an earlier occurrence and further right for a later occurrence. This suggests a recursive or iterative approach that explores both directions from a found target.

## Algorithm
1. Initialize a result array `ans` of size 2 with `{-1, -1}`.
2. Handle the edge case: if the input array `nums` is empty, return `ans`.
3. Define a recursive helper function `bs(start, end, nums, target)`:
    a. Base case: If `start > end`, return (the search range is invalid).
    b. Calculate the middle index: `mid = start + (end - start) / 2`.
    c. If `nums[mid] == target`:
        i. Update `ans[0]` (first occurrence): If `ans[0]` is still `-1` or `mid` is smaller than the current `ans[0]`, set `ans[0] = mid`.
        ii. Update `ans[1]` (last occurrence): If `ans[1]` is still `-1` or `mid` is larger than the current `ans[1]`, set `ans[1] = mid`.
        iii. Recursively search the left half: `bs(start, mid - 1, nums, target)`.
        iv. Recursively search the right half: `bs(mid + 1, end, nums, target)`.
        v. Return from this call.
    d. If `nums[mid] < target`: Recursively search the right half: `bs(mid + 1, end, nums, target)`.
    e. If `nums[mid] > target`: Recursively search the left half: `bs(start, mid - 1, nums, target)`.
4. Call the helper function `bs(0, n - 1, nums, target)` to initiate the search.
5. Return the `ans` array.

## Concept to Remember
*   **Binary Search:** Efficiently searching sorted data structures by repeatedly dividing the search interval in half.
*   **Recursion:** A programming technique where a function calls itself to solve smaller instances of the same problem.
*   **Array Traversal:** Iterating through elements of an array to perform operations.
*   **Edge Case Handling:** Properly addressing scenarios like empty arrays or targets not present.

## Common Mistakes
*   **Not handling the empty array case:** This can lead to index out of bounds errors.
*   **Stopping binary search immediately after finding *any* target:** The problem requires the *first* and *last* occurrences, not just one.
*   **Incorrectly updating `ans[0]` and `ans[1]`:** Forgetting to check if the new `mid` is indeed a better candidate for the first or last occurrence.
*   **Infinite recursion:** If the recursive calls are not properly bounded or the base cases are missed, the recursion might not terminate.
*   **Integer overflow for `mid` calculation:** Using `start + (end - start) / 2` is safer than `(start + end) / 2`.

## Complexity Analysis
- Time: O(N) - In the worst case, if all elements are the target, the recursive calls will explore almost every element. While it uses binary search logic, the branching nature when `nums[mid] == target` can lead to visiting many nodes. A more optimized binary search approach would be O(log N).
- Space: O(N) - Due to the recursive call stack. In the worst case, the depth of recursion can be proportional to the number of elements. An iterative approach would reduce this to O(1).

## Commented Code
```java
class Solution {
    // Initialize the result array to store the first and last positions, default to -1.
    int[] ans = new int[]{-1,-1};
    
    // Main function to find the range of the target element.
    public int[] searchRange(int[] nums, int target) {
        // Get the length of the input array.
        int n = nums.length;
        // If the array is empty, return the default result.
        if(n==0) return ans;
        // Start the binary search process on the entire array.
        bs(0,n-1,nums,target);
        // Return the found range.
        return ans;
    }
    
    // Recursive binary search helper function.
    public void bs(int start, int end , int[]nums, int target){
        // Base case: if the start index exceeds the end index, the range is invalid, so return.
        if(start > end) return;
        
        // Calculate the middle index to avoid potential integer overflow.
        int mid = start + (end-start)/2;
        
        // If the middle element is the target.
        if(nums[mid]==target){
            
            // Check if this is the first occurrence found so far.
            // If ans[0] is -1 (meaning no target found yet) or if the current mid is smaller than the current ans[0], update ans[0].
            if(ans[0]==-1 || ans[0] > mid){
                // System.out.println("Found smaller start"); // Debugging line
                ans[0] = mid; // Update the first occurrence index.
            }
            // Check if this is the last occurrence found so far.
            // If ans[1] is -1 (meaning no target found yet) or if the current mid is larger than the current ans[1], update ans[1].
            if(ans[1]==-1 || ans[1] < mid){
                // System.out.println("Found larger end"); // Debugging line
                ans[1] = mid; // Update the last occurrence index.
            }
            
            // Crucially, we don't stop here. We need to find the *absolute* first and last.
            // Recursively search the left half for an even earlier occurrence.
            bs(start,mid-1,nums,target);
            // Recursively search the right half for an even later occurrence.
            bs(mid+1,end,nums,target);
            // Return after exploring both halves.
            return;
        }
        // If the middle element is less than the target, search in the right half.
        if(nums[mid]<target) bs(mid+1,end,nums,target);
        // If the middle element is greater than the target, search in the left half.
        else bs(start,mid-1,nums,target);
        
        // Return from the recursive call.
        return; 
    }
}
```

## Interview Tips
1.  **Clarify Requirements:** Ask if the array is guaranteed to be sorted and what to return if the target is not found (e.g., `[-1, -1]`).
2.  **Explain the Binary Search Adaptation:** Clearly articulate how you're modifying binary search to find the *boundaries* rather than just *an* element. Discuss the recursive calls to the left and right.
3.  **Discuss Time Complexity Trade-offs:** While the provided solution is O(N) in the worst case due to its branching, mention that a more optimized O(log N) solution exists by performing two separate binary searches (one for the first occurrence, one for the last).
4.  **Consider Edge Cases:** Be prepared to discuss how you handle empty arrays, arrays with a single element, and targets at the beginning or end of the array.

## Revision Checklist
- [ ] Understand the problem: find first and last index of target in sorted array.
- [ ] Handle empty array case.
- [ ] Implement binary search logic.
- [ ] Adapt binary search to find the leftmost occurrence.
- [ ] Adapt binary search to find the rightmost occurrence.
- [ ] Combine results into `[first, last]`.
- [ ] Analyze time and space complexity.
- [ ] Consider alternative O(log N) solutions.

## Similar Problems
*   Search in Rotated Sorted Array
*   Find Minimum in Rotated Sorted Array
*   Median of Two Sorted Arrays
*   First Bad Version

## Tags
`Array` `Binary Search` `Recursion`
