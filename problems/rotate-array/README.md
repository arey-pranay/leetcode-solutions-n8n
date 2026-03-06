# Two Sum

---

## Problem Summary

Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

## Approach and Intuition

The core idea is to efficiently find if a "complement" exists for each number in the array. The complement for a number `num` is `target - num`. If we can quickly check if this complement exists in the array and retrieve its index, we can solve the problem.

A brute-force approach would be to iterate through every possible pair of numbers in the array and check if their sum equals the target. This would involve nested loops, leading to O(n^2) time complexity.

A more efficient approach uses a hash map (or dictionary in Python). We can iterate through the array once. For each number `nums[i]`, we calculate its complement: `complement = target - nums[i]`. Then, we check if this `complement` already exists as a key in our hash map.

*   If the `complement` is found in the hash map, it means we have found the two numbers. The current index `i` and the index stored as the value for the `complement` in the hash map are our answer.
*   If the `complement` is not found, we add the current number `nums[i]` as a key to the hash map, with its index `i` as the value. This way, if a future number needs `nums[i]` as its complement, we can quickly find it.

This approach ensures that we only iterate through the array once.

## Complexity Analysis

*   **Time:** O(n) - We iterate through the array once. Hash map lookups and insertions are, on average, O(1) operations.
*   **Space:** O(n) - In the worst case, we might store all `n` elements of the array in the hash map if the solution pair is the last two elements.

## Code Walkthrough

```python
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        num_map = {}  # Initialize an empty hash map to store numbers and their indices

        # Iterate through the array with both index and value
        for i, num in enumerate(nums):
            complement = target - num  # Calculate the complement needed

            # Check if the complement is already in our hash map
            if complement in num_map:
                # If found, we have our pair. Return the current index 'i'
                # and the index of the complement stored in the map.
                return [num_map[complement], i]

            # If the complement is not found, add the current number and its index
            # to the hash map for future lookups.
            num_map[num] = i

        # This part should ideally not be reached given the problem statement
        # guarantees exactly one solution. However, it's good practice for
        # functions to have a return statement outside the loop.
        return []
```

1.  `num_map = {}`: An empty dictionary `num_map` is created. This will store `number: index` pairs.
2.  `for i, num in enumerate(nums):`: We loop through the `nums` array, getting both the index `i` and the value `num` for each element.
3.  `complement = target - num`: For the current number `num`, we calculate what value (`complement`) we need to find in the rest of the array to reach the `target`.
4.  `if complement in num_map:`: We check if this `complement` already exists as a key in our `num_map`. This means we've previously encountered a number that, when added to the current `num`, equals `target`.
5.  `return [num_map[complement], i]`: If the `complement` is found, we retrieve its index from `num_map` (which is `num_map[complement]`) and return it along with the current index `i`. This is our solution.
6.  `num_map[num] = i`: If the `complement` was *not* found, we add the current number `num` and its index `i` to the `num_map`. This makes `num` available as a potential complement for subsequent numbers in the array.
7.  `return []`: This line is a fallback. The problem statement guarantees a solution exists, so this line should theoretically never be executed.

## Interview Tips

*   **Clarify Constraints:** Always ask about constraints. "Can the array be empty?", "Are there duplicates?", "What's the range of numbers?", "Is there always exactly one solution?". The problem statement here simplifies things by guaranteeing one solution.
*   **Start with Brute Force:** Even if you know the optimal solution, briefly describe the brute-force approach (nested loops) and its complexity (O(n^2)). This shows you can think systematically.
*   **Explain the "Why" of the Hash Map:** Clearly articulate *why* a hash map is suitable. It provides O(1) average time complexity for lookups, which is crucial for optimizing from O(n^2) to O(n).
*   **Edge Cases:** Consider what happens if the solution involves the first or last elements, or if the target is very large or very small.
*   **"Cannot use the same element twice":** The hash map approach naturally handles this because when we check `if complement in num_map`, we are looking for a *previously seen* element. If the current element `num` is its own complement (e.g., `nums = [3, 3]`, `target = 6`), the first `3` will be added to the map. When the second `3` is processed, its complement is `6 - 3 = 3`. `3` will be found in the map, and we'll return the indices of the first `3` and the current `3`.
*   **Return Order:** The problem states "any order". The hash map solution returns `[index_of_complement, current_index]`.

## Optimization and Alternatives

*   **Sorting:** Another approach involves sorting the array first. If the array is sorted, you can use two pointers (one at the beginning, one at the end) to find the pair.
    *   Initialize `left = 0` and `right = n - 1`.
    *   While `left < right`:
        *   Calculate `current_sum = nums[left] + nums[right]`.
        *   If `current_sum == target`, you've found the pair. However, you need to return the *original indices*, which are lost after sorting. This makes this approach more complex if original indices are required. You'd need to store original indices along with values before sorting, or perform a secondary search for the original indices.
        *   If `current_sum < target`, increment `left` to increase the sum.
        *   If `current_sum > target`, decrement `right` to decrease the sum.
    *   **Complexity of Sorting Approach:**
        *   Time: O(n log n) due to sorting.
        *   Space: O(1) if sorting in-place, or O(n) if a new sorted array is created.
    *   **Comparison:** The hash map approach (O(n) time, O(n) space) is generally preferred for this problem because it's faster and directly provides the original indices.

## Revision Checklist

*   [ ] Understand the problem statement thoroughly.
*   [ ] Identify the core requirement: finding two numbers that sum to a target.
*   [ ] Consider brute-force (O(n^2)) and its limitations.
*   [ ] Think about data structures that allow fast lookups (Hash Map/Dictionary).
*   [ ] Develop the hash map approach: iterate, calculate complement, check map, add to map.
*   [ ] Analyze time and space complexity of the hash map approach.
*   [ ] Walk through the code step-by-step.
*   [ ] Practice explaining the solution and its trade-offs.
*   [ ] Consider alternative approaches (like sorting + two pointers) and their pros/cons.
*   [ ] Be ready to discuss edge cases and constraints.

## Similar Problems

*   3Sum
*   4Sum
*   Subarray Sum Equals K
*   Container With Most Water

## Tags
`Array` `Hash Map`