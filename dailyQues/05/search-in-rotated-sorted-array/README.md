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
Search for an element in a rotated sorted array. We solve this problem by using binary search with a twist to handle the rotation.

## Intuition
The key insight is that when the array is rotated, the midpoint of the two halves can be either the start or end of the sorted subarray. We use this property to eliminate half of the search space in each iteration.

## Algorithm

1. Initialize `start` and `end` pointers to the beginning and end of the array.
2. If the array has only one element, return its index if it matches the target.
3. While `start` is less than `end`, calculate the midpoint `mid`.
4. Check if the target is present at the `mid` index or at the start or end of the array. If found, return the corresponding index.
5. Determine which half of the array is sorted based on the comparison between the first element and the midpoint.
6. If the target falls within this sorted half, update the `end` pointer to `mid-1`. Otherwise, update the `start` pointer to `mid+1`.

## Concept to Remember
* Binary search can be adapted for searching in rotated arrays by considering the rotation point.
* The key is to eliminate one of the two halves at each step based on the comparison between the first element and the midpoint.

## Common Mistakes

* Failing to handle the edge case where the array has only one element correctly.
* Not updating the pointers `start` and `end` correctly after finding the target in a sorted half.
* Not considering the possibility of the target being present at the start or end of the array.

## Complexity Analysis
- Time: O(log n) / The search space is reduced by half at each step, leading to logarithmic time complexity.
- Space: O(1) / Only constant space is used for storing indices and pointers.

## Commented Code

```java
class Solution {
    public int search(int[] nums, int target) {
        // Initialize start and end pointers to the beginning and end of the array
        int start = 0;
        int end = nums.length - 1;

        // Handle edge case where array has only one element
        if (nums.length == 1 && nums[0] == target) return 0;

        while (start < end) {
            // Calculate midpoint
            int mid = start + (end - start) / 2;

            // Check if target is present at the midpoint or start/end of array
            if (nums[mid] == target) return mid;
            if (nums[start] == target) return start;
            if (nums[end] == target) return end;

            // Determine which half is sorted based on comparison between first element and midpoint
            if (nums[start] <= nums[mid]) { 
                // Target falls within this sorted half, update end pointer to mid-1
                if (target >= nums[start] && target < nums[mid]) end = mid - 1; 
                // Eliminate left side
                else start = mid + 1; 
            } else { 
                // Right side is sorted
                if (target <= nums[end] && target > nums[mid]) start = mid + 1; 
                // Target falls within this sorted half, update start pointer to mid+1
                else end = mid - 1; 
            }
        }

        // If target not found, return -1
        return -1;
    }
}
```

## Interview Tips

* Make sure to handle edge cases correctly.
* Pay attention to the rotation point and how it affects the search space.
* Practice explaining your thought process and the logic behind your code.

## Revision Checklist
- [ ] Understand binary search and its adaptation for rotated arrays.
- [ ] Handle edge case where array has only one element.
- [ ] Update pointers correctly after finding target in a sorted half.
- [ ] Consider possibility of target being at start or end of array.

## Similar Problems

* 33. Search in Rotated Sorted Array II
* 81. Search in Rotated Sorted Array I
* 374. Guess Number Higher or Lower

## Tags
`Array`, `Binary Search`, `Rotated Sorted Array`
