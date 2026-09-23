# Find In Mountain Array

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Binary Search` `Interactive` `Ternary Search`  
**Time:** O(log N)  
**Space:** O(1)

---

## Solution (java)

```java
/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
 
class Solution {
    public int findInMountainArray(int target, MountainArray arr) {
        int peak = getPeakIndex(arr);
        int ans = findBetween(target, arr, 0, peak-1,false);
        if(ans != -1) return ans;
        return findBetween(target, arr, peak, arr.length()-1,true);
    }
    public int getPeakIndex(MountainArray arr){
        int low = 0;
        int high = arr.length()-1;
        while(low<high){//peak ke left aur right dono neighbours chhote honge.
            int mid = low + (high-low)/2;
            if(arr.get(mid) < arr.get(mid+1)) low = mid+1;
            else high = mid;
        }
        return low;
    }
    public int findBetween(int target, MountainArray arr , int low , int high, boolean isReversed){
        while(low<=high){
            int mid = low + (high-low)/2;
            if(isReversed){
               if(target < arr.get(mid)) low = mid+1;
               else if(target > arr.get(mid)) high = mid-1;   
               else return mid;
            }
            else {
                if(target > arr.get(mid)) low = mid+1;
                else if(target < arr.get(mid)) high = mid-1;
                else return mid;
            }
        }
        return -1;
    }
}
```

---

---
## Quick Revision
Given a mountain array, find the index of a target value. The array first strictly increases and then strictly decreases.
Solve by finding the peak, then performing two binary searches: one on the increasing part and one on the decreasing part.

## Intuition
A mountain array has a unique peak. This peak divides the array into two sorted subarrays: an increasing one and a decreasing one. Standard binary search works on sorted arrays. Therefore, we can adapt binary search to find the target in each of these sorted segments. The key is to first locate the peak efficiently.

## Algorithm
1.  **Find the Peak Index:**
    *   Initialize `low = 0` and `high = arr.length() - 1`.
    *   While `low < high`:
        *   Calculate `mid = low + (high - low) / 2`.
        *   If `arr.get(mid) < arr.get(mid + 1)`, it means `mid` is on the increasing slope, so the peak must be to its right. Set `low = mid + 1`.
        *   Otherwise (`arr.get(mid) >= arr.get(mid + 1)`), `mid` is either the peak or on the decreasing slope. The peak could be `mid` or to its left. Set `high = mid`.
    *   The loop terminates when `low == high`, which is the index of the peak.
2.  **Search in the Increasing Part:**
    *   Perform a standard binary search for `target` in the range `[0, peak - 1]`.
    *   If `target` is found, return its index.
3.  **Search in the Decreasing Part:**
    *   If `target` was not found in the increasing part, perform a modified binary search for `target` in the range `[peak, arr.length() - 1]`.
    *   For the decreasing part, the comparison logic in binary search needs to be reversed:
        *   If `target < arr.get(mid)`, the target might be to the right (larger index, smaller value). Set `low = mid + 1`.
        *   If `target > arr.get(mid)`, the target might be to the left (smaller index, larger value). Set `high = mid - 1`.
        *   If `target == arr.get(mid)`, return `mid`.
    *   If `target` is found, return its index.
4.  **Not Found:** If `target` is not found in either part, return `-1`.

## Concept to Remember
*   **Binary Search:** Efficiently searching in sorted data structures.
*   **Divide and Conquer:** Breaking a problem into smaller, similar subproblems.
*   **Array Properties:** Understanding how specific array structures (like mountain arrays) can be exploited.
*   **Edge Cases:** Handling boundaries and conditions where elements might not exist.

## Common Mistakes
*   **Incorrect Peak Finding:** Off-by-one errors or incorrect boundary conditions in the peak finding binary search.
*   **Reversed Binary Search Logic:** Forgetting to reverse the comparison operators when searching the decreasing portion of the mountain array.
*   **Not Handling Target at Peak:** The peak element itself needs to be considered in one of the searches. The current algorithm correctly includes the peak in the second search.
*   **Returning Incorrect Index:** Returning an index from the wrong search segment if the target appears in both (though the problem implies unique occurrences or first occurrence).
*   **Integer Overflow:** Using `mid = (low + high) / 2` instead of `mid = low + (high - low) / 2` can lead to overflow for very large `low` and `high`.

## Complexity Analysis
*   **Time:** O(log N) - The `getPeakIndex` function takes O(log N) time. The `findBetween` function is called twice, each taking O(log N) time. Therefore, the total time complexity is O(log N) + O(log N) + O(log N) = O(log N).
*   **Space:** O(1) - The algorithm uses a constant amount of extra space for variables like `low`, `high`, `mid`, and `ans`.

## Commented Code
```java
/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {} // Method to get the element at a specific index.
 *     public int length() {}      // Method to get the length of the array.
 * }
 */
 
class Solution {
    // Main function to find the target in the mountain array.
    public int findInMountainArray(int target, MountainArray arr) {
        // First, find the index of the peak element in the mountain array.
        int peak = getPeakIndex(arr);
        
        // Perform binary search on the increasing part of the array (from start to peak-1).
        // 'false' indicates it's not a reversed search.
        int ans = findBetween(target, arr, 0, peak - 1, false);
        
        // If the target was found in the increasing part, return its index.
        if (ans != -1) {
            return ans;
        }
        
        // If not found in the increasing part, perform binary search on the decreasing part (from peak to end).
        // 'true' indicates it's a reversed search (for a decreasing array).
        return findBetween(target, arr, peak, arr.length() - 1, true);
    }
    
    // Helper function to find the index of the peak element.
    public int getPeakIndex(MountainArray arr) {
        // Initialize low and high pointers for binary search.
        int low = 0;
        int high = arr.length() - 1;
        
        // Binary search loop to find the peak.
        // The loop continues as long as low is less than high.
        while (low < high) {
            // Calculate the middle index to avoid potential integer overflow.
            int mid = low + (high - low) / 2;
            
            // If the element at mid is less than the element at mid+1,
            // it means we are on the increasing slope, so the peak must be to the right.
            if (arr.get(mid) < arr.get(mid + 1)) {
                low = mid + 1; // Move low pointer to mid+1.
            } else {
                // If arr.get(mid) >= arr.get(mid+1), it means mid is either the peak or on the decreasing slope.
                // The peak could be at mid or to its left.
                high = mid; // Move high pointer to mid.
            }
        }
        // When the loop terminates, low (or high) will be the index of the peak element.
        return low;
    }
    
    // Helper function to perform binary search within a given range [low, high].
    // 'isReversed' flag determines if the search is on an increasing or decreasing subarray.
    public int findBetween(int target, MountainArray arr, int low, int high, boolean isReversed) {
        // Binary search loop. Continues as long as low is less than or equal to high.
        while (low <= high) {
            // Calculate the middle index.
            int mid = low + (high - low) / 2;
            
            // Check if the search is on a reversed (decreasing) part of the array.
            if (isReversed) {
                // For a decreasing array:
                // If target is smaller than the middle element, it must be to the right (larger index).
                if (target < arr.get(mid)) {
                    low = mid + 1; // Move low pointer.
                } 
                // If target is larger than the middle element, it must be to the left (smaller index).
                else if (target > arr.get(mid)) {
                    high = mid - 1; // Move high pointer.
                } 
                // If target equals the middle element, we found it.
                else {
                    return mid; // Return the index.
                }
            } 
            // If not reversed, it's an increasing part of the array.
            else {
                // For an increasing array:
                // If target is greater than the middle element, it must be to the right.
                if (target > arr.get(mid)) {
                    low = mid + 1; // Move low pointer.
                } 
                // If target is less than the middle element, it must be to the left.
                else if (target < arr.get(mid)) {
                    high = mid - 1; // Move high pointer.
                } 
                // If target equals the middle element, we found it.
                else {
                    return mid; // Return the index.
                }
            }
        }
        // If the loop finishes without finding the target, return -1.
        return -1;
    }
}
```

## Interview Tips
*   **Clarify Constraints:** Ask about the size of the array, the range of values, and whether the target can appear multiple times.
*   **Explain the Two-Phase Approach:** Clearly articulate why finding the peak first is necessary and how it enables binary search.
*   **Walk Through Binary Search Logic:** Be prepared to explain the conditions for moving `low` and `high` pointers in both the increasing and decreasing search phases.
*   **API Usage:** Emphasize that you are using the provided `MountainArray` interface methods (`get` and `length`) and not assuming direct array access.

## Revision Checklist
- [ ] Understand the definition of a mountain array.
- [ ] Implement binary search to find the peak index.
- [ ] Implement standard binary search for the increasing portion.
- [ ] Implement modified binary search for the decreasing portion.
- [ ] Handle edge cases where the target is not found.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Find Minimum in Rotated Sorted Array
*   Search in Rotated Sorted Array
*   Peak Index in a Mountain Array
*   Binary Search

## Tags
`Array` `Binary Search`
