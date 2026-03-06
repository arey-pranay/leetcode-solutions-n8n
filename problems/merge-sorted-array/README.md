# Two Sum

---

## Problem Summary

Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

## Approach and Intuition

The core idea is to efficiently find if a "complement" exists for each number in the array. The complement for a number `num` is `target - num`. If we can quickly check if this complement exists in the array and get its index, we've found our pair.

A brute-force approach would be to iterate through all possible pairs of numbers in the array. For each number `nums[i]`, we'd iterate through the rest of the array `nums[j]` (where `j != i`) and check if `nums[i] + nums[j] == target`. This would have a time complexity of O(n^2).

To optimize this, we can use a hash map (or dictionary in Python). The hash map will store the numbers we've seen so far and their corresponding indices.

As we iterate through the array `nums`:
1. For each number `nums[i]`, calculate its `complement = target - nums[i]`.
2. Check if this `complement` already exists as a key in our hash map.
   - If it does, it means we've found the two numbers. The current index `i` and the index stored in the hash map for the `complement` are our answer. We can immediately return these indices.
   - If it doesn't, add the current number `nums[i]` and its index `i` to the hash map. This way, if a future number needs `nums[i]` as its complement, we'll find it.

This approach ensures that we only iterate through the array once. The hash map provides O(1) average time complexity for lookups and insertions.

## Complexity Analysis

- Time: O(n) - We iterate through the array `nums` exactly once. For each element, we perform a constant number of operations: calculating the complement, checking for its existence in the hash map (average O(1)), and inserting into the hash map (average O(1)).
- Space: O(n) - In the worst case, we might store all `n` elements of the array in the hash map if the solution pair is the last two elements or if no solution is found until the very end.

## Code Walkthrough

```python
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        num_map = {}  # Initialize an empty hash map to store number -> index mappings

        # Iterate through the input array 'nums' with both index and value
        for i, num in enumerate(nums):
            complement = target - num  # Calculate the complement needed for the current number

            # Check if the complement already exists as a key in our hash map
            if complement in num_map:
                # If it exists, we've found our pair.
                # Return the index of the complement (stored in the map) and the current index 'i'.
                return [num_map[complement], i]

            # If the complement is not found, add the current number and its index to the hash map.
            # This prepares for future iterations where the current number might be a complement.
            num_map[num] = i

        # According to the problem statement, there will always be exactly one solution.
        # This line would technically be unreachable if the problem constraints are met.
        # However, it's good practice to have a return statement outside the loop,
        # or to raise an error if no solution is found and the constraints were violated.
        return [] # Or raise an exception if no solution is guaranteed
```

1.  **`num_map = {}`**: An empty dictionary `num_map` is created. This dictionary will store `number: index` pairs.
2.  **`for i, num in enumerate(nums):`**: We loop through the `nums` array. `enumerate` provides both the index (`i`) and the value (`num`) for each element.
3.  **`complement = target - num`**: For the current number `num`, we calculate what value (`complement`) would be needed to reach the `target`.
4.  **`if complement in num_map:`**: We check if this `complement` has already been seen and stored in our `num_map`.
    *   If `True`: This means we've found the pair. The `complement` was encountered earlier, and its index is stored as `num_map[complement]`. The current number's index is `i`. We return these two indices.
    *   If `False`: The `complement` hasn't been seen yet.
5.  **`num_map[num] = i`**: We add the current number `num` and its index `i` to the `num_map`. This is crucial because if a future number in the array needs `num` as its complement, we'll be able to find it in the map.
6.  **`return []`**: This line is a fallback. The problem statement guarantees exactly one solution, so this line should ideally never be reached.

## Interview Tips

*   **Clarify Constraints**: Always ask about constraints. "Can the array be empty?", "Are there duplicates?", "What's the range of numbers?", "Is there always exactly one solution?". The problem statement here explicitly says "exactly one solution" and "may not use the same element twice," which simplifies things.
*   **Start with Brute Force**: Even if you know the optimal solution, briefly mentioning the brute-force O(n^2) approach shows you can analyze problems systematically.
*   **Explain the "Why"**: Clearly articulate *why* a hash map is a good choice. Emphasize the O(1) average time complexity for lookups and insertions, which is the key to optimizing from O(n^2) to O(n).
*   **Edge Cases**: Consider what happens if the solution involves the first or last elements, or if the target is very large or small. The hash map approach handles these naturally.
*   **"In-place" vs. "Extra Space"**: Be prepared to discuss the space complexity trade-off. The hash map uses extra space, but it significantly improves time complexity.
*   **Code Readability**: Use meaningful variable names (`num_map`, `complement`). Add comments where necessary, especially for the logic of adding to the map *after* checking for the complement.
*   **"What if there are multiple solutions?"**: If the problem allowed multiple solutions, how would you adapt? You might need to store all pairs or return the first one found.
*   **"What if there's no solution?"**: If a solution wasn't guaranteed, you'd need to handle the case where the loop finishes without finding a pair (e.g., return an empty list or raise an exception).

## Optimization and Alternatives

*   **Sorting**: Another approach is to sort the array first. Then, use two pointers, one starting at the beginning (`left`) and one at the end (`right`).
    *   If `nums[left] + nums[right] == target`, you've found the pair. However, you'd need to store the original indices before sorting, which adds complexity.
    *   If `nums[left] + nums[right] < target`, increment `left` to increase the sum.
    *   If `nums[left] + nums[right] > target`, decrement `right` to decrease the sum.
    *   **Complexity**: Sorting takes O(n log n) time. The two-pointer scan takes O(n) time. So, the total time complexity is O(n log n). The space complexity depends on the sorting algorithm used (e.g., O(log n) or O(n) for some in-place sorts, or O(n) if you need to store original indices separately).
    *   **Trade-off**: This approach uses less space (potentially O(1) if original indices are handled carefully or not needed) but is slower in terms of time complexity compared to the hash map approach. The hash map approach is generally preferred for this problem due to its O(n) time complexity.

## Revision Checklist

*   [ ] Understand the problem statement thoroughly.
*   [ ] Identify the core task: finding two numbers that sum to a target.
*   [ ] Consider brute-force solution and its complexity.
*   [ ] Think about data structures that can speed up lookups (Hash Map/Dictionary).
*   [ ] Develop the hash map approach: store seen numbers and their indices.
*   [ ] For each number, check if its complement is in the map.
*   [ ] If complement found, return indices.
*   [ ] If not found, add the current number and its index to the map.
*   [ ] Analyze time and space complexity of the hash map approach.
*   [ ] Walk through the code with an example.
*   [ ] Consider alternative approaches (like sorting + two pointers) and their trade-offs.
*   [ ] Practice explaining the solution clearly and concisely.

## Similar Problems

*   3Sum
*   4Sum
*   Two Sum II - Input Array Is Sorted
*   Container With Most Water (uses two pointers, but different logic)

## Tags
`Array` `Hash Map`