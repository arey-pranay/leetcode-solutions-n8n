# Contains Duplicate Ii

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Sliding Window`  
**Time:** See complexity section  
**Space:** See complexity section

---

## Solution (java)

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            // Remove the element that is now outside the window
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            
            // Try to add the current element. If it fails, a duplicate exists.
            if (!set.add(nums[i])) {
                return true;
            }
        }
        
        return false;
    }
}
```

---

---
## Problem Summary
Given an integer array `nums` and an integer `k`, return `true` if there are two distinct indices `i` and `j` in the array such that `nums[i] == nums[j]` and `abs(i - j) <= k`.

## Approach and Intuition
The core idea is to maintain a "sliding window" of size `k+1` (or effectively, a window of elements within distance `k` of the current element). As we iterate through the array, we check if the current element `nums[i]` has already appeared within the current window.

A `HashSet` is an ideal data structure for this because it provides O(1) average time complexity for insertion and checking for the existence of an element.

We iterate through the array `nums` with index `i`. For each element `nums[i]`:
1. **Maintain the window:** If `i` is greater than `k`, it means the element at index `i - k - 1` is now outside our "look-back" window of size `k`. We remove `nums[i - k - 1]` from the `HashSet`. This ensures that the `HashSet` only contains elements within the current window of `k` preceding elements.
2. **Check for duplicates:** We attempt to add the current element `nums[i]` to the `HashSet`.
   - If `set.add(nums[i])` returns `false`, it means `nums[i]` was already present in the `HashSet`. Since the `HashSet` only contains elements within the `k` distance, this implies we've found a duplicate within the specified distance. We can immediately return `true`.
   - If `set.add(nums[i])` returns `true`, the element was successfully added, and no duplicate was found within the window for this element.

If we iterate through the entire array without finding such a duplicate, we return `false`.

## Complexity Analysis
- Time: O(n) - We iterate through the array `nums` once. For each element, the `HashSet` operations (add, remove, contains) take O(1) on average.
- Space: O(min(n, k)) - In the worst case, the `HashSet` will store up to `k+1` distinct elements. If `k` is larger than `n`, it will store at most `n` distinct elements. Therefore, the space complexity is bounded by the minimum of `n` and `k`.

## Code Walkthrough
```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // Initialize a HashSet to store elements within the current window.
        Set<Integer> set = new HashSet<>();
        
        // Iterate through the array with index 'i'.
        for (int i = 0; i < nums.length; i++) {
            // If the current index 'i' is greater than 'k', it means the element
            // at index 'i - k - 1' is now outside the sliding window of size k.
            // We remove it from the set to maintain the window.
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            
            // Attempt to add the current element nums[i] to the set.
            // The add() method returns false if the element is already present in the set.
            // If it returns false, it means we found a duplicate within the window of size k.
            if (!set.add(nums[i])) {
                // Duplicate found within distance k, return true.
                return true;
            }
        }
        
        // If the loop completes without finding any duplicates within distance k, return false.
        return false;
    }
}
```

## Interview Tips
1. **Clarify Constraints:** Always ask about the constraints on `nums.length`, `k`, and the range of values in `nums`. This helps in choosing the most efficient approach.
2. **Explain the Sliding Window:** Clearly articulate the concept of a sliding window and why a `HashSet` is suitable for checking duplicates within that window.
3. **Edge Cases:** Discuss edge cases like an empty array, `k=0`, or `k` being larger than the array length.
4. **Time and Space Complexity:** Be prepared to explain the time and space complexity of your solution and justify it.
5. **Alternative Approaches:** Briefly mention other potential approaches (like brute force) and why they are less efficient.
6. **Code Readability:** Write clean, well-commented code.

## Optimization and Alternatives
*   **Brute Force:** A naive approach would be to use nested loops. For each element `nums[i]`, iterate through `nums[j]` where `j` is from `i+1` to `min(i+k, nums.length-1)`. If `nums[i] == nums[j]`, return `true`. This has a time complexity of O(n*k) and space complexity of O(1). This is less efficient than the `HashSet` approach, especially for large `k`.
*   **HashMap for Indices:** Another approach using a `HashMap` could store the element as the key and its *last seen index* as the value. When iterating, if the current element is already in the map, check if the difference between the current index and the stored index is less than or equal to `k`. If so, return `true`. Otherwise, update the map with the current index. This also achieves O(n) time and O(n) space (or O(min(n, k)) if we prune old entries, which essentially leads back to the `HashSet` logic). The `HashSet` approach is slightly simpler for this specific problem as we only care about existence within the window, not the exact index.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Identify the core requirement: finding duplicates within a specific distance `k`.
- [ ] Choose an appropriate data structure (e.g., `HashSet`) for efficient lookups.
- [ ] Implement the sliding window logic correctly.
- [ ] Handle the window boundary (removing elements that fall out of the window).
- [ ] Test with various inputs, including edge cases.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Contains Duplicate (LeetCode 217)
*   Find All Duplicates in an Array (LeetCode 442)
*   Subarray Sum Equals K (LeetCode 560) - While different, it involves windowing and prefix sums/hash maps.

## Tags
`Array` `Hash Map`

## My Notes
comment area
