# Minimum Distance To The Target Element

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int n = nums.length;
        int i = 0;
        while(true){
            if(start-i >= 0) if(nums[start-i] == target) return i;
            if(start+i < n) if(nums[start+i] == target) return i;
            i++;
        }
    }
}
```

---

---
## Quick Revision
Find the minimum absolute difference between the `start` index and any index `i` where `nums[i]` equals `target`.
We can achieve this by expanding outwards from the `start` index in both directions.

## Intuition
The problem asks for the *minimum* distance. This suggests we should look for the `target` element as close to the `start` index as possible. The most efficient way to do this is to check indices that are progressively further away from `start`, one step at a time, in both the left and right directions. The first time we find the `target`, we've found the minimum distance because we are checking in increasing order of distance.

## Algorithm
1. Initialize a variable `distance` to 0. This will represent the current distance from the `start` index we are checking.
2. Enter an infinite loop.
3. Inside the loop, check the index `start - distance`. If this index is valid (i.e., non-negative) and the element at this index `nums[start - distance]` is equal to `target`, return `distance`.
4. Next, check the index `start + distance`. If this index is valid (i.e., less than the length of `nums`) and the element at this index `nums[start + distance]` is equal to `target`, return `distance`.
5. If the `target` was not found at either `start - distance` or `start + distance`, increment `distance` by 1.
6. The loop will eventually terminate because the problem guarantees that `target` exists in `nums`.

## Concept to Remember
*   **Linear Search:** Iterating through an array to find a specific element.
*   **Absolute Difference:** Calculating the distance between two indices, regardless of order.
*   **Two-Pointer/Expanding Window (Implicit):** While not a strict two-pointer, the approach of checking outwards from a central point is conceptually similar to expanding a window.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly handling boundary conditions for `start - distance` and `start + distance`.
*   **Not checking both directions:** Only searching in one direction (left or right) from `start`.
*   **Inefficient search:** Not realizing that checking outwards from `start` guarantees the minimum distance upon first find.
*   **Assuming `target` is always found:** While the problem guarantees it, robust code might consider this edge case if not explicitly stated.

## Complexity Analysis
- Time: O(N) - In the worst case, we might have to scan the entire array if the `target` is at one of the extremities and `start` is at the other.
- Space: O(1) - We are only using a few extra variables for `distance` and loop control, which does not depend on the input size.

## Commented Code
```java
class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int n = nums.length; // Get the length of the input array for boundary checks.
        int i = 0; // Initialize 'i' as the distance from the 'start' index we are currently checking.
        while(true){ // Start an infinite loop, which will break when the target is found.
            // Check the element to the left of 'start' at the current distance 'i'.
            // Ensure the index (start - i) is within the array bounds (>= 0).
            if(start-i >= 0) {
                // If the element at this left index matches the target, return the current distance 'i'.
                if(nums[start-i] == target) return i;
            }
            // Check the element to the right of 'start' at the current distance 'i'.
            // Ensure the index (start + i) is within the array bounds (< n).
            if(start+i < n) {
                // If the element at this right index matches the target, return the current distance 'i'.
                if(nums[start+i] == target) return i;
            }
            i++; // Increment the distance 'i' to check the next pair of indices further away from 'start'.
        }
    }
}
```

## Interview Tips
*   Clearly explain your strategy of expanding outwards from `start`.
*   Mention why this approach guarantees the minimum distance.
*   Be prepared to discuss edge cases like `start` being at the beginning or end of the array.
*   Walk through an example with the interviewer to demonstrate your understanding.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Identify the goal: minimum absolute difference.
- [ ] Devise a strategy to search outwards from `start`.
- [ ] Implement boundary checks correctly.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution clearly.

## Similar Problems
*   Find All Numbers Disappeared in an Array
*   Two Sum
*   Search Insert Position

## Tags
`Array` `Linear Search`
