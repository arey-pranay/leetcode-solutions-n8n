# Search In Rotated Sorted Array

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Binary Search`  
**Time:** O(log n)  
**Space:** O(log n)

---

## Solution (java)

```java
class Solution {
    public int search(int[] nums, int target) {
        //binary search. find target. 
        int start = 0;
        int end = nums.length-1;
        if(nums.length==1 && nums[0]==target) return 0;
        while(start<end){
            int mid = start + (end-start)/2;
            if(nums[mid]==target) return mid;
            if(nums[start]==target) return start;
            if(nums[end]==target) return end;

            
            if(nums[start] <= nums[mid]){ //left side sorted
                if(target>= nums[start]  && target < nums[mid]) end = mid-1; // search target between s and m
                else start = mid+1; // eliminate left side
            } else{ //right side sorted
                if(target <= nums[end] && target > nums[mid]) start = mid+1; // search target between m and e
                else end = mid-1; // eliminate right side
            }
        }
        return -1;
    }
}
```

---

---

## Quick Revision
Search for a target element in a rotated sorted array.
Use binary search to find the target element efficiently.

## Intuition
The "aha moment" is realizing that we can use binary search on a rotated sorted array. This approach works because even though the array is rotated, it's still sorted, and we can take advantage of this property to eliminate half of the search space with each iteration.

## Algorithm
1. Initialize two pointers, `start` and `end`, to the start and end indices of the array.
2. If the array has only one element, check if it matches the target, and return its index if so.
3. While `start` is less than `end`:
   1. Calculate the midpoint `mid` using integer division (`start + (end-start)/2`).
   2. Check if `nums[mid]` matches the target. If so, return its index.
   3. Check if `nums[start]` or `nums[end]` matches the target. If so, return their indices.
   4. Determine which half of the array is sorted by comparing `nums[start]` and `nums[mid]`.
      * If left side is sorted:
         - If `target` is within the range `[nums[start], nums[mid]]`, update `end` to `mid-1`.
         - Otherwise, update `start` to `mid+1`.
      * If right side is sorted:
         - If `target` is within the range `[nums[mid], nums[end]]`, update `start` to `mid+1`.
         - Otherwise, update `end` to `mid-1`.
4. If the loop ends without finding the target, return `-1`.

## Concept to Remember
* Binary search can be used on partially sorted arrays.
* When searching in a rotated sorted array, focus on determining which half is sorted and eliminating the other half.

## Common Mistakes
* Not handling edge cases, such as an empty array or a single-element array with no match.
* Failing to update `start` and `end` correctly based on the comparison of `nums[start]` and `nums[mid]`.
* Not checking if `nums[mid]`, `nums[start]`, or `nums[end]` matches the target before eliminating half of the search space.

## Complexity Analysis
- Time: O(log n) - The binary search algorithm eliminates half of the search space with each iteration, resulting in a logarithmic time complexity.
- Space: O(1) - This solution uses only a constant amount of extra memory to store indices and variables.

## Commented Code
```java
class Solution {
    public int search(int[] nums, int target) {
        // Initialize two pointers, start and end, to the start and end indices of the array.
        int start = 0;
        int end = nums.length-1;

        // Handle edge case: if array has only one element, check if it matches the target.
        if(nums.length==1 && nums[0]==target) return 0;

        while(start<end){
            // Calculate the midpoint using integer division (start + (end-start)/2).
            int mid = start + (end-start)/2;

            // Check if nums[mid] matches the target. If so, return its index.
            if(nums[mid]==target) return mid;

            // Check if nums[start] or nums[end] matches the target. If so, return their indices.
            if(nums[start]==target) return start;
            if(nums[end]==target) return end;

            // Determine which half of the array is sorted by comparing nums[start] and nums[mid].
            if(nums[start] <= nums[mid]){ // Left side sorted
                // If target is within range [nums[start], nums[mid]], update end to mid-1.
                if(target>= nums[start]  && target < nums[mid]) end = mid-1; 
                // Otherwise, update start to mid+1 (eliminate left side).
                else start = mid+1;
            } else{ // Right side sorted
                // If target is within range [nums[mid], nums[end]], update start to mid+1.
                if(target <= nums[end] && target > nums[mid]) start = mid+1; 
                // Otherwise, update end to mid-1 (eliminate right side).
                else end = mid-1;
            }
        }

        // If the loop ends without finding the target, return -1.
        return -1;
    }
}
```

## Interview Tips
* Make sure to handle edge cases carefully, such as an empty array or a single-element array with no match.
* Practice explaining your thought process and reasoning for each step of the algorithm.
* Be prepared to discuss trade-offs between different approaches (e.g., binary search vs. linear search).

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Review edge cases, such as an empty array or a single-element array with no match.
- [ ] Practice explaining your thought process and reasoning for each step of the algorithm.
- [ ] Test the solution thoroughly to ensure it works correctly.

## Similar Problems
* `Find First and Last Position in Array`: Find the first and last positions of an element in a sorted array.
* `Search a 2D Grid`: Search for an element in a 2D grid, where each row is sorted in ascending order.
* `Search Insert Position`: Find the position to insert an element in a sorted array.

## Tags
`Array` `Hash Map` `Binary Search`
