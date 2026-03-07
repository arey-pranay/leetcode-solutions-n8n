# Two Sum
---
## Problem Summary

Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

## Approach and Intuition

The most straightforward approach is to iterate through the array and for each element, check if there's another element that sums up to the target. A naive solution would involve nested loops, where the outer loop picks an element and the inner loop checks all subsequent elements. This would have a time complexity of O(n^2).

A more efficient approach utilizes a hash map (or dictionary in Python). The idea is to store elements we've seen so far along with their indices. For each number `num` in the input array `nums`, we calculate the `complement` needed to reach the `target` (i.e., `complement = target - num`). Then, we check if this `complement` already exists in our hash map.

*   If the `complement` is found in the hash map, it means we've found the two numbers that sum up to the target. We return the index of the `complement` (retrieved from the hash map) and the current index of `num`.
*   If the `complement` is not found, we add the current `num` and its index to the hash map. This way, if a future number needs the current `num` as its complement, we can find it.

This approach ensures that we only iterate through the array once.

## Complexity Analysis

*   **Time:** O(n) - We iterate through the array `nums` once. For each element, hash map lookups and insertions take, on average, O(1) time.
*   **Space:** O(n) - In the worst case, we might store all `n` elements of the array in the hash map.

## Code Walkthrough

```python
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        num_map = {}  # Initialize an empty hash map to store numbers and their indices

        # Iterate through the array with both index and value
        for i, num in enumerate(nums):
            complement = target - num  # Calculate the complement needed

            # Check if the complement is already in the hash map
            if complement in num_map:
                # If found, return the index of the complement and the current index
                return [num_map[complement], i]

            # If complement is not found, add the current number and its index to the map
            num_map[num] = i

        # This part should ideally not be reached given the problem constraints
        # (guaranteed one solution). However, for completeness or if constraints change,
        # one might consider returning an empty list or raising an error.
        return []
```

1.  **`num_map = {}`**: An empty dictionary `num_map` is created. This dictionary will store `number: index` pairs.
2.  **`for i, num in enumerate(nums):`**: The code iterates through the input list `nums`. `enumerate` provides both the index (`i`) and the value (`num`) for each element.
3.  **`complement = target - num`**: For the current number `num`, we calculate the value `complement` that, when added to `num`, would equal `target`.
4.  **`if complement in num_map:`**: We check if this calculated `complement` already exists as a key in our `num_map`.
    *   If `True`: This means we have previously encountered a number that is the complement of the current number. We have found our pair. We return a list containing the index of the `complement` (which is stored as the value associated with the `complement` key in `num_map`) and the current index `i`.
    *   If `False`: The `complement` has not been seen yet.
5.  **`num_map[num] = i`**: If the `complement` was not found, we add the current number `num` as a key to `num_map` and its index `i` as the corresponding value. This prepares the map for future iterations.
6.  **`return []`**: This line is a fallback. The problem statement guarantees exactly one solution, so this line should theoretically never be reached.

## Interview Tips

*   **Clarify Constraints:** Always ask about constraints: "Can the array be empty?", "Are there duplicate numbers?", "Is there always a solution?", "What's the range of numbers?". The problem statement here simplifies things by guaranteeing a solution and no duplicate usage.
*   **Start with Brute Force:** Even if you know a better solution, briefly mentioning the O(n^2) nested loop approach shows you can think through simpler solutions first.
*   **Explain the Hash Map Intuition:** Clearly articulate *why* a hash map is useful. It allows for O(1) average time lookups, which is crucial for optimizing from O(n^2) to O(n).
*   **Edge Cases:** While this problem guarantees a solution, in other problems, consider edge cases like empty arrays, single-element arrays, or targets that cannot be formed.
*   **Code Readability:** Use meaningful variable names (`num_map`, `complement`). Add comments where necessary, especially for complex logic.
*   **"What if there are multiple solutions?"**: If asked, you could modify the code to store all valid index pairs or return the first one found.
*   **"What if we can't use extra space?"**: This would lead you back to the O(n^2) brute-force approach or potentially sorting the array first (O(n log n) time, O(1) or O(n) space depending on sort implementation) and then using two pointers.

## Optimization and Alternatives

*   **Sorting + Two Pointers:**
    *   **Approach:** Sort the array first. Then, use two pointers, one at the beginning (`left`) and one at the end (`right`).
    *   **Logic:** If `nums[left] + nums[right] == target`, you've found the pair. If the sum is less than `target`, increment `left`. If the sum is greater than `target`, decrement `right`.
    *   **Challenge:** This approach finds the *values* that sum to the target, but you need to return the *original indices*. To do this, you'd need to store the original indices along with the values before sorting (e.g., as tuples `(value, original_index)`), or perform a second pass to find the original indices after identifying the values.
    *   **Complexity:**
        *   Time: O(n log n) due to sorting.
        *   Space: O(1) if sorting in-place, or O(n) if a copy is made or if storing original indices as tuples.
*   **Brute Force (Nested Loops):**
    *   **Approach:** Iterate through each element `nums[i]` and then iterate through the remaining elements `nums[j]` (where `j > i`).
    *   **Logic:** If `nums[i] + nums[j] == target`, return `[i, j]`.
    *   **Complexity:**
        *   Time: O(n^2)
        *   Space: O(1)

The hash map approach is generally preferred for this problem due to its optimal O(n) time complexity.

## Revision Checklist

*   [ ] Understand the problem statement thoroughly.
*   [ ] Consider brute-force solution and its complexity.
*   [ ] Identify data structures that can optimize the solution (hash map).
*   [ ] Walk through the hash map logic step-by-step.
*   [ ] Analyze time and space complexity of the chosen approach.
*   [ ] Write clean, readable code.
*   [ ] Consider edge cases and constraints.
*   [ ] Be prepared to discuss alternative approaches (sorting + two pointers).
*   [ ] Practice explaining the solution clearly and concisely.

## Similar Problems

*   3Sum
*   4Sum
*   Container With Most Water
*   Three Equal Sums

## Tags
`Array` `Hash Map`