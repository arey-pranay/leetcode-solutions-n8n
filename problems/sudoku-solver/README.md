# Two Sum
---
## Problem Summary
Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

## Approach and Intuition
The core idea is to efficiently find if a "complement" exists for each number in the array. The complement for a number `num` is `target - num`. If we can quickly check if this complement exists in the array and retrieve its index, we can solve the problem.

A brute-force approach would be to iterate through all possible pairs of numbers in the array and check if their sum equals the target. This would involve nested loops, leading to O(n^2) time complexity.

A more efficient approach uses a hash map (or dictionary in Python). We can iterate through the array once. For each number `nums[i]`, we calculate its complement: `complement = target - nums[i]`. Then, we check if this `complement` already exists as a key in our hash map.

*   If the `complement` is found in the hash map, it means we have found the two numbers that sum up to the target. The index of the `complement` is stored as the value associated with the `complement` key in the hash map, and the current index is `i`. We return these two indices.
*   If the `complement` is not found, we add the current number `nums[i]` as a key to the hash map, with its index `i` as the value. This way, if a future number needs `nums[i]` as its complement, we can quickly find it.

This approach ensures that we only iterate through the array once, and hash map lookups/insertions are, on average, O(1) operations.

## Complexity Analysis
- Time: O(n) - We iterate through the array `nums` once. For each element, we perform a hash map lookup and potentially a hash map insertion, both of which take O(1) time on average.
- Space: O(n) - In the worst case, we might store all `n` elements of the array in the hash map if the solution pair is found towards the end or if no solution exists (though the problem guarantees one solution).

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
                # If found, we have our pair. Return the index of the complement
                # (stored in num_map) and the current index 'i'.
                return [num_map[complement], i]

            # If the complement is not found, add the current number and its index
            # to the hash map for future lookups.
            num_map[num] = i

        # The problem guarantees exactly one solution, so this part should ideally
        # not be reached. However, for completeness or if the guarantee was
        # removed, one might return an empty list or raise an error.
        return []
```
1.  **`num_map = {}`**: An empty dictionary `num_map` is created. This dictionary will store numbers from `nums` as keys and their corresponding indices as values.
2.  **`for i, num in enumerate(nums):`**: The code iterates through the input list `nums`. `enumerate` provides both the index (`i`) and the value (`num`) for each element.
3.  **`complement = target - num`**: For the current number `num`, the `complement` is calculated. This is the value that, when added to `num`, would equal the `target`.
4.  **`if complement in num_map:`**: The code checks if this `complement` already exists as a key in the `num_map`.
    *   If `True`: This means we've previously encountered a number that is the complement of the current number. The index of that complement is stored as `num_map[complement]`. We have found our pair, so we return a list containing the stored index (`num_map[complement]`) and the current index (`i`).
    *   If `False`: The complement hasn't been seen yet.
5.  **`num_map[num] = i`**: If the complement was not found, the current number `num` and its index `i` are added to the `num_map`. This makes `num` available as a potential complement for future numbers in the iteration.
6.  **`return []`**: This line is technically unreachable given the problem statement's guarantee of exactly one solution. It's good practice to have a return statement outside the loop, though.

## Interview Tips
*   **Clarify Constraints**: Always ask about constraints: array size, range of numbers, whether duplicates are allowed, if a solution is guaranteed. The guarantee of "exactly one solution" is crucial here.
*   **Brute Force First**: Start by explaining the O(n^2) brute-force approach (nested loops). This shows you can think of a basic solution and sets the stage for optimization.
*   **Identify Bottleneck**: Explain why the brute-force is slow (nested loops, repeated checks).
*   **Hash Map Intuition**: Explain *why* a hash map is a good choice. It allows for O(1) average time lookups, which is exactly what we need to quickly check for the complement.
*   **One Pass vs. Two Pass**: Discuss the possibility of a two-pass hash map solution (first pass to build the map, second pass to find the complement). Then, explain how the provided one-pass solution is more efficient by combining these steps.
*   **Edge Cases**: While this problem guarantees a solution, in other problems, consider empty arrays, arrays with one element, or cases where no solution exists.
*   **Return Value**: Be precise about what the function should return (indices, values, etc.) and in what format.

## Optimization and Alternatives
*   **Two-Pass Hash Map**:
    *   **Approach**: First, iterate through the array and store all numbers and their indices in a hash map. Second, iterate through the array again. For each number `nums[i]`, calculate `complement = target - nums[i]`. Check if `complement` exists in the hash map and if its index is not `i`.
    *   **Complexity**: Time: O(n) (two passes), Space: O(n).
    *   **Benefit**: Conceptually simpler for some, but the one-pass is slightly more efficient in practice as it avoids a second full iteration.
*   **Sorting (Not Optimal for this problem)**:
    *   **Approach**: Sort the array first. Then use two pointers, one at the beginning and one at the end. If the sum is too small, move the left pointer right. If too large, move the right pointer left.
    *   **Complexity**: Time: O(n log n) due to sorting. Space: O(1) or O(n) depending on the sorting algorithm used and if modifying the original array is allowed.
    *   **Drawback**: This approach loses the original indices, so you'd need to store original indices before sorting or use a more complex mapping. For this specific problem where indices are required, the hash map approach is superior.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Consider brute-force solution and its complexity.
- [ ] Identify the need for efficient lookups.
- [ ] Choose an appropriate data structure (Hash Map).
- [ ] Implement the one-pass hash map solution.
- [ ] Analyze time and space complexity.
- [ ] Walk through the code with an example.
- [ ] Practice explaining the intuition and trade-offs.
- [ ] Be prepared to discuss alternative approaches.
- [ ] Consider edge cases (even if guaranteed not to occur).

## Similar Problems
*   3Sum
*   4Sum
*   Subarray Sum Equals K
*   Contains Duplicate II
*   Group Anagrams

## Tags
`Array` `Hash Map`