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
We solve this by finding the peak, then performing two binary searches: one on the increasing part and one on the decreasing part.

## Intuition
The key property of a mountain array is its single peak. This peak divides the array into two sorted subarrays: an increasing one and a decreasing one. Standard binary search works on sorted arrays. Therefore, we can adapt binary search to find the peak and then search within these two sorted segments. The "aha moment" is realizing that the problem can be decomposed into finding the peak and then applying binary search twice.

## Algorithm
1.  **Find the Peak Index**:
    *   Initialize `low = 0` and `high = arr.length() - 1`.
    *   While `low < high`:
        *   Calculate `mid = low + (high - low) / 2`.
        *   If `arr.get(mid) < arr.get(mid + 1)`, it means we are on the increasing slope, so the peak must be to the right. Set `low = mid + 1`.
        *   Otherwise (`arr.get(mid) >= arr.get(mid + 1)`), it means `mid` could be the peak or we are on the decreasing slope. The peak is at `mid` or to its left. Set `high = mid`.
    *   The loop terminates when `low == high`, which is the index of the peak.
2.  **Search in the Increasing Part**:
    *   Perform a standard binary search for `target` in the range `[0, peak - 1]`.
    *   If `target` is found, return its index.
3.  **Search in the Decreasing Part**:
    *   If `target` was not found in the increasing part, perform a modified binary search for `target` in the range `[peak, arr.length() - 1]`.
    *   This search needs to account for the decreasing order:
        *   If `target < arr.get(mid)`, it means `target` might be to the right (larger index) in the decreasing part. Set `low = mid + 1`.
        *   If `target > arr.get(mid)`, it means `target` might be to the left (smaller index) in the decreasing part. Set `high = mid - 1`.
        *   If `target == arr.get(mid)`, return `mid`.
    *   If `target` is not found in this part, return `-1`.

## Concept to Remember
*   **Binary Search**: Efficiently searching in sorted arrays by repeatedly dividing the search interval in half.
*   **Peak Finding in a Mountain Array**: Utilizing the unimodal property to locate the maximum element using binary search.
*   **Adapting Binary Search**: Modifying the comparison logic within binary search for non-standard sorted sequences (like decreasing order).
*   **Divide and Conquer**: Breaking down a complex problem into smaller, manageable subproblems.

## Common Mistakes
*   **Off-by-one errors in binary search**: Incorrectly adjusting `low` and `high` pointers, leading to missed elements or infinite loops.
*   **Incorrectly handling the decreasing part**: Forgetting to reverse the comparison logic for the decreasing segment of the mountain array.
*   **Not handling edge cases**: Forgetting to check if the peak is at the very beginning or end, or if the array has only one or two elements.
*   **Calling `arr.get()` too many times**: The `MountainArray` interface has a limited number of calls, so optimizing these calls is crucial. The provided solution is good in this regard.
*   **Assuming the array is always a "perfect" mountain**: While the problem statement guarantees it, sometimes interviewees might overthink edge cases that are already covered by the definition.

## Complexity Analysis
*   **Time**: O(log N) - The `getPeakIndex` function takes O(log N) time. The `findBetween` function is called twice, each taking O(log N) time. Thus, the total time complexity is O(log N) + O(log N) + O(log N) = O(log N).
*   **Space**: O(1) - The algorithm uses a constant amount of extra space for variables like `low`, `high`, `mid`, and `peak`.

## Commented Code
```java
/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index); // Method to get the element at a given index.
 *     public int length();      // Method to get the length of the array.
 * }
 */
 
class Solution {
    /**
     * Main function to find the target in a mountain array.
     * @param target The value to search for.
     * @param arr The MountainArray interface.
     * @return The index of the target if found, otherwise -1.
     */
    public int findInMountainArray(int target, MountainArray arr) {
        // First, find the index of the peak element in the mountain array.
        int peak = getPeakIndex(arr);
        
        // Perform binary search on the increasing part of the array (from index 0 to peak-1).
        // The 'isReversed' flag is false for the increasing part.
        int ans = findBetween(target, arr, 0, peak - 1, false);
        
        // If the target was found in the increasing part, return its index.
        if (ans != -1) {
            return ans;
        }
        
        // If not found in the increasing part, perform binary search on the decreasing part (from peak to arr.length()-1).
        // The 'isReversed' flag is true for the decreasing part.
        return findBetween(target, arr, peak, arr.length() - 1, true);
    }
    
    /**
     * Finds the index of the peak element in the mountain array.
     * The peak is the element that is greater than its neighbors.
     * @param arr The MountainArray interface.
     * @return The index of the peak element.
     */
    public int getPeakIndex(MountainArray arr){
        // Initialize binary search pointers for finding the peak.
        int low = 0;
        int high = arr.length() - 1;
        
        // Binary search loop to find the peak.
        // The loop continues as long as low is less than high.
        while(low < high){
            // Calculate the middle index to avoid potential integer overflow.
            int mid = low + (high - low) / 2;
            
            // Compare the middle element with its right neighbor.
            // If arr.get(mid) < arr.get(mid+1), it means we are on the increasing slope,
            // so the peak must be to the right of mid.
            if(arr.get(mid) < arr.get(mid + 1)) {
                low = mid + 1; // Move the low pointer to mid + 1.
            } else {
                // If arr.get(mid) >= arr.get(mid+1), it means mid could be the peak,
                // or we are on the decreasing slope. The peak is at mid or to its left.
                high = mid; // Move the high pointer to mid.
            }
        }
        // When the loop terminates, low (or high) will be the index of the peak element.
        return low;
    }
    
    /**
     * Performs binary search for a target value within a specified range of the MountainArray.
     * Handles both increasing and decreasing order based on the isReversed flag.
     * @param target The value to search for.
     * @param arr The MountainArray interface.
     * @param low The starting index of the search range.
     * @param high The ending index of the search range.
     * @param isReversed True if searching in a decreasing part, false for increasing.
     * @return The index of the target if found, otherwise -1.
     */
    public int findBetween(int target, MountainArray arr , int low , int high, boolean isReversed){
        // Standard binary search loop.
        while(low <= high){
            // Calculate the middle index.
            int mid = low + (high - low) / 2;
            
            // Check if we are searching in a reversed (decreasing) part of the array.
            if(isReversed){
               // In a decreasing array:
               // If target is smaller than the middle element, it might be to the right (larger index).
               if(target < arr.get(mid)) {
                   low = mid + 1; // Move low pointer to the right.
               } 
               // If target is larger than the middle element, it might be to the left (smaller index).
               else if(target > arr.get(mid)) {
                   high = mid - 1; // Move high pointer to the left.
               }   
               // If target is equal to the middle element, we found it.
               else {
                   return mid; // Return the index.
               }
            }
            // If not reversed, we are searching in an increasing part of the array.
            else {
                // In an increasing array:
                // If target is larger than the middle element, it might be to the right (larger index).
                if(target > arr.get(mid)) {
                    low = mid + 1; // Move low pointer to the right.
                } 
                // If target is smaller than the middle element, it might be to the left (smaller index).
                else if(target < arr.get(mid)) {
                    high = mid - 1; // Move high pointer to the left.
                }
                // If target is equal to the middle element, we found it.
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
*   **Clarify Constraints**: Ask about the size of the array and the range of values. Specifically, inquire about the `MountainArray` interface's call limit for `get()` and `length()`. This problem is designed to test awareness of such constraints.
*   **Explain the Two-Phase Approach**: Clearly articulate that the solution involves two main steps: finding the peak and then searching in the two sorted segments. This shows a structured thought process.
*   **Handle the Decreasing Search Correctly**: Emphasize how the comparison logic in the binary search is flipped for the decreasing portion of the array. This is a common point of error.
*   **Consider Edge Cases**: Discuss scenarios like the peak being at the beginning or end, or the array having only 3 elements. While the algorithm handles these, mentioning them shows thoroughness.
*   **Mention `get()` Call Optimization**: If the interviewer brings up the `get()` call limit, explain how your binary search approach minimizes these calls to O(log N), which is optimal.

## Revision Checklist
- [ ] Understand the definition of a mountain array.
- [ ] Implement binary search to find the peak index.
- [ ] Implement standard binary search for the increasing part.
- [ ] Implement modified binary search for the decreasing part.
- [ ] Handle the return values correctly (-1 if not found).
- [ ] Consider the `MountainArray` interface's `get()` call limit.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Find Peak Element
*   Binary Search
*   Search in Rotated Sorted Array
*   Median of Two Sorted Arrays

## Tags
`Array` `Binary Search`
