# Find Missing Elements

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Sorting`  
**Time:** O(N + M)  
**Space:** O(M)

---

## Solution (java)

```java
class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        boolean[] vis = new boolean[101];
        int min = 101, max = -1;
        for(int i : nums){min = Math.min(min,i); max = Math.max(max,i); vis[i] = true;}
        List<Integer> ans = new ArrayList<>();
        for(int i=min;i<max;i++) if(!vis[i]) ans.add(i);
        return ans;
    }
}
```

---

---
## Quick Revision
Given an array of integers, find all numbers that are missing within the range defined by the minimum and maximum elements in the array.
This is solved by using a boolean array to mark seen numbers and then iterating through the range to find unmarked numbers.

## Intuition
The core idea is to efficiently check for the presence of each number within the observed range. If we know the minimum and maximum values in the input array, we only need to consider numbers between them. A boolean array (or a hash set) can act as a "presence tracker." We mark each number from the input array as "seen." Then, we iterate from the minimum to the maximum value and add any number that wasn't marked as "seen" to our result list.

## Algorithm
1. Initialize a boolean array `vis` of size 101 (assuming numbers are within 0-100, or adjust size based on constraints). This array will track which numbers have been seen.
2. Initialize `min` to a value larger than any possible input (e.g., 101) and `max` to a value smaller than any possible input (e.g., -1). These will store the minimum and maximum values found in the input array.
3. Iterate through the input array `nums`:
    a. For each number `i`, update `min = Math.min(min, i)` and `max = Math.max(max, i)`.
    b. Mark the number `i` as seen by setting `vis[i] = true`.
4. Initialize an empty `ArrayList` called `ans` to store the missing elements.
5. Iterate from `min` up to (but not including) `max`:
    a. For each number `i` in this range, check if `vis[i]` is `false`.
    b. If `vis[i]` is `false`, it means the number `i` was not present in the input array, so add `i` to the `ans` list.
6. Return the `ans` list.

## Concept to Remember
*   **Range Identification:** Determining the bounds (min and max) of the search space is crucial.
*   **Boolean Array/Hash Set for Presence Tracking:** Efficiently marking and checking for the existence of elements.
*   **Iterating Through a Range:** Systematically checking all potential candidates within the identified bounds.

## Common Mistakes
*   **Incorrect Array Size:** If the input numbers can exceed 100, the `vis` array will cause an `ArrayIndexOutOfBoundsException`. The size should be based on the problem's constraints.
*   **Off-by-One Errors in Range Iteration:** The loop for finding missing elements should correctly iterate from `min` up to `max-1` (inclusive of `min`, exclusive of `max`) if the problem implies finding missing numbers *between* the min and max. The current solution iterates `i < max`, which is correct for finding numbers *strictly between* min and max. If the problem statement implies including `max` in the check, the loop condition would need adjustment.
*   **Not Handling Empty Input:** If `nums` is empty, `min` and `max` might retain their initial sentinel values, leading to incorrect loop behavior or an empty result list, which might be acceptable depending on problem definition.
*   **Assuming Sorted Input:** The solution correctly handles unsorted input, but a common mistake is to assume the input is sorted and try to use a simpler approach that only works for sorted arrays.

## Complexity Analysis
- Time: O(N + M) - reason: We iterate through the input array `nums` once (O(N)) to find min/max and mark seen elements. Then, we iterate from `min` to `max` (at most O(M), where M is the range size, which is bounded by the maximum possible value if not specified, or by `max - min`). In this specific solution, the `vis` array size is fixed at 101, so the second loop is effectively O(101) which is O(1). Thus, the dominant factor is O(N).
- Space: O(M) - reason: We use a boolean array `vis` of size 101. This space is constant if the maximum possible value is fixed (like 100 here), making it O(1). If the maximum value could be arbitrarily large, it would be O(M) where M is the maximum possible value. The `ans` list can store up to M-2 elements in the worst case, also contributing O(M) space.

## Commented Code
```java
class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        // Initialize a boolean array to keep track of seen numbers.
        // The size 101 assumes numbers are in the range [0, 100]. Adjust if constraints differ.
        boolean[] vis = new boolean[101];
        
        // Initialize min and max to sentinel values.
        // min is set high, max is set low, so any number from nums will update them.
        int min = 101, max = -1;
        
        // Iterate through the input array to find the minimum and maximum values,
        // and to mark each number as seen in the 'vis' array.
        for(int i : nums){
            // Update the minimum value encountered so far.
            min = Math.min(min,i);
            // Update the maximum value encountered so far.
            max = Math.max(max,i);
            // Mark the current number 'i' as seen by setting its corresponding index in 'vis' to true.
            vis[i] = true;
        }
        
        // Initialize a list to store the missing elements.
        List<Integer> ans = new ArrayList<>();
        
        // Iterate through the range from the minimum value found up to (but not including) the maximum value.
        // This loop checks for numbers that should be present within the observed range but were not.
        for(int i=min; i < max; i++){
            // If the number 'i' was not marked as seen (i.e., vis[i] is false),
            if(!vis[i])
                // then it is a missing element, so add it to the result list.
                ans.add(i);
        }
        
        // Return the list of all missing elements found within the range [min, max).
        return ans;
    }
}
```

## Interview Tips
*   **Clarify Constraints:** Always ask about the range of numbers in the input array and the expected output range. This is critical for determining the size of the `vis` array and potential edge cases.
*   **Explain the Trade-offs:** Discuss why a boolean array is chosen over a `HashSet`. For a fixed, small range, a boolean array is more space and time efficient. For a very sparse, large range, a `HashSet` might be better.
*   **Handle Edge Cases:** Be prepared to discuss what happens with an empty input array, an array with only one element, or an array where all elements are consecutive.
*   **Walk Through an Example:** Use a small example like `[4, 3, 2, 7, 8, 2, 3, 1]` to trace the algorithm's execution, showing how `min`, `max`, `vis`, and `ans` change.

## Revision Checklist
- [ ] Understand the problem: find missing numbers within the min/max range of the input.
- [ ] Identify the range: correctly find the minimum and maximum values.
- [ ] Implement presence tracking: use a boolean array or hash set.
- [ ] Iterate through the range: check for unmarked numbers.
- [ ] Handle array bounds: ensure `vis` array size is appropriate.
- [ ] Consider edge cases: empty array, single element array, consecutive numbers.
- [ ] Analyze time and space complexity.

## Similar Problems
*   [448. Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/) (Similar, but range is 1 to N)
*   [287. Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/) (Related to finding missing/duplicate elements in a range)
*   [268. Missing Number](https://leetcode.com/problems/missing-number/) (Finds a single missing number in a range)

## Tags
`Array` `Boolean Array` `Hash Table`
