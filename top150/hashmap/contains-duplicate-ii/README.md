# Contains Duplicate Ii

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Sliding Window`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        int n = nums.length;
        for(int i =0; i<n; i++){
            if(hm.containsKey(nums[i])) if(i-hm.get(nums[i]) <= k) return true;
            hm.put(nums[i],i);
        }
        return false;
    }
}
```

---

---
## Quick Revision
Given an array of integers `nums` and an integer `k`, determine if there exist two distinct indices `i` and `j` in the array such that `nums[i] == nums[j]` and `abs(i - j) <= k`.
This problem can be solved efficiently using a hash map to store the most recent index of each number encountered.

## Intuition
The core idea is to keep track of the last seen index for each number. As we iterate through the array, if we encounter a number that we've seen before, we check the difference between its current index and its previously stored index. If this difference is less than or equal to `k`, it means we've found a duplicate within the specified distance, and we can immediately return `true`. If we iterate through the entire array without finding such a pair, then no such duplicate exists.

## Algorithm
1. Initialize an empty hash map (e.g., `HashMap<Integer, Integer>`) to store the number as the key and its most recent index as the value.
2. Iterate through the input array `nums` from index `0` to `n-1` (where `n` is the length of `nums`).
3. For each element `nums[i]` at index `i`:
    a. Check if the hash map already contains `nums[i]` as a key.
    b. If it does, retrieve the previously stored index `prevIndex` for `nums[i]` from the hash map.
    c. Calculate the absolute difference between the current index `i` and the `prevIndex`: `abs(i - prevIndex)`.
    d. If this difference is less than or equal to `k`, return `true` immediately, as we've found a duplicate within the allowed distance.
    e. If the hash map does not contain `nums[i]` or if the difference is greater than `k`, update (or insert) the entry in the hash map with `nums[i]` as the key and the current index `i` as the value. This ensures we always store the *most recent* index.
4. If the loop completes without returning `true`, it means no such duplicate pair was found, so return `false`.

## Concept to Remember
*   **Hash Maps (Dictionaries):** Efficient for key-value lookups, insertions, and deletions, allowing O(1) average time complexity for these operations.
*   **Sliding Window (Implicit):** While not a traditional fixed-size sliding window, the `k` parameter defines a "window" of indices to check for duplicates. The hash map helps manage this dynamic window.
*   **Time-Space Tradeoff:** Using a hash map increases space complexity but significantly improves time complexity compared to a brute-force approach.

## Common Mistakes
*   **Not updating the index:** Forgetting to update the hash map with the *current* index when a duplicate is found, leading to checking against an outdated index.
*   **Incorrectly calculating the difference:** Using `i - prevIndex` without considering the absolute difference, though in this specific problem, `i` will always be greater than `prevIndex` due to the iteration order.
*   **Handling edge cases:** Not considering empty arrays or `k=0` (though `k=0` would mean `i==j`, which is disallowed by "distinct indices").
*   **Brute-force approach:** Trying to solve it with nested loops, which would be O(n^2) and likely time out for larger inputs.

## Complexity Analysis
*   **Time:** O(n) - We iterate through the array once. Hash map operations (containsKey, get, put) take O(1) on average.
*   **Space:** O(n) - In the worst case, all elements in the array are distinct, and the hash map will store all `n` elements.

## Commented Code
```java
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // Initialize a HashMap to store the number and its most recent index.
        HashMap<Integer,Integer> hm = new HashMap<>();
        // Get the length of the input array.
        int n = nums.length;
        // Iterate through the array from the beginning to the end.
        for(int i =0; i<n; i++){
            // Check if the current number (nums[i]) is already present in the HashMap.
            if(hm.containsKey(nums[i])) {
                // If the number is present, check if the absolute difference between the current index (i)
                // and the previously stored index (hm.get(nums[i])) is less than or equal to k.
                if(i - hm.get(nums[i]) <= k) {
                    // If the difference is within k, we found a duplicate within the specified range.
                    return true;
                }
            }
            // If the number is not in the HashMap, or if the difference was greater than k,
            // put (or update) the current number and its current index into the HashMap.
            // This ensures we always have the most recent index for each number.
            hm.put(nums[i],i);
        }
        // If the loop finishes without finding any duplicates within distance k, return false.
        return false;
    }
}
```

## Interview Tips
*   **Explain your thought process:** Clearly articulate why a hash map is a good choice for this problem (fast lookups).
*   **Discuss the "window" concept:** Explain how `k` defines a dynamic window and how the hash map helps manage it.
*   **Consider edge cases:** Mention what happens with an empty array or `k=0` (though `k=0` is trivial here).
*   **Ask clarifying questions:** If unsure about constraints (e.g., range of numbers, size of `k`), ask the interviewer.

## Revision Checklist
- [ ] Understand the problem statement: find duplicates within distance `k`.
- [ ] Identify the need for efficient lookups of previous elements.
- [ ] Choose a hash map to store `number -> last_seen_index`.
- [ ] Implement the iteration and check for duplicates within `k`.
- [ ] Handle updating the hash map with the most recent index.
- [ ] Consider time and space complexity.
- [ ] Test with edge cases (empty array, `k=0`, all duplicates, no duplicates).

## Similar Problems
Contains Duplicate
Contains Duplicate III

## Tags
`Array` `Hash Map`
