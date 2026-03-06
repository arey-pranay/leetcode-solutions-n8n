# Two Sum

---

## Problem Summary

Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

## Approach and Intuition

The core idea is to efficiently find if a "complement" exists for each number in the array. The complement for a number `num` is `target - num`. If we can quickly check if this complement exists in the array and retrieve its index, we can solve the problem.

A brute-force approach would be to iterate through every possible pair of numbers in the array and check if their sum equals the target. This would involve nested loops, leading to O(n^2) time complexity.

To optimize this, we can use a hash map (or dictionary in Python). The hash map will store each number encountered so far along with its index. As we iterate through the array:

1. For each `num` at index `i`, calculate its `complement = target - num`.
2. Check if the `complement` already exists as a key in our hash map.
   - If it does, it means we've found the two numbers. The current index `i` and the index stored in the hash map for the `complement` are our answer.
   - If it doesn't, add the current `num` and its index `i` to the hash map. This way, if a future number's complement is `num`, we can find it.

This approach ensures that we only iterate through the array once.

## Complexity Analysis

- Time: O(n) - We iterate through the array once. For each element, hash map lookups and insertions take average O(1) time.
- Space: O(n) - In the worst case, we might store all `n` elements of the array in the hash map.

## Code Walkthrough

```python
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        num_map = {}  # Initialize an empty hash map to store number -> index
        
        # Iterate through the array with both index and value
        for i, num in enumerate(nums):
            complement = target - num  # Calculate the complement needed
            
            # Check if the complement is already in our map
            if complement in num_map:
                # If found, return the index of the complement and the current index
                return [num_map[complement], i]
            
            # If complement is not found, add the current number and its index to the map
            num_map[num] = i
            
        # This part should ideally not be reached given the problem constraints
        # (exactly one solution exists). However, for completeness, one might
        # return an empty list or raise an error if no solution is found.
        return [] 
```

1.  **`num_map = {}`**: An empty dictionary `num_map` is created. This dictionary will store `number: index` pairs.
2.  **`for i, num in enumerate(nums):`**: We iterate through the input list `nums`. `enumerate` provides both the index (`i`) and the value (`num`) for each element.
3.  **`complement = target - num`**: For the current number `num`, we calculate the value `complement` that, when added to `num`, would equal `target`.
4.  **`if complement in num_map:`**: We check if this `complement` value already exists as a key in our `num_map`.
    *   If `complement` is in `num_map`, it means we have previously encountered a number that is the complement of the current `num`. The value associated with `complement` in `num_map` is its index. We have found our pair! We return a list containing the index of the complement (`num_map[complement]`) and the current index (`i`).
5.  **`num_map[num] = i`**: If the `complement` was not found in the `num_map`, we add the current `num` and its index `i` to the `num_map`. This makes the current number available for future iterations to find its complement.
6.  **`return []`**: This line is a fallback. The problem statement guarantees exactly one solution, so this line should theoretically never be reached.

## Interview Tips

*   **Clarify Constraints**: Always ask about constraints: "Can the array be empty?", "Are there duplicates?", "What's the range of numbers?", "Is there always exactly one solution?". The problem statement here clarifies "exactly one solution" and "may not use the same element twice".
*   **Start with Brute Force**: Even if you know the optimal solution, briefly describe the brute-force approach (nested loops) and its complexity (O(n^2)). This shows your thought process and how you identify inefficiencies.
*   **Explain the Hash Map Rationale**: Clearly articulate *why* a hash map is suitable. Emphasize its O(1) average time complexity for lookups and insertions, which is key to achieving O(n) overall.
*   **Walk Through an Example**: Use a small example (e.g., `nums = [2, 7, 11, 15]`, `target = 9`) and trace the execution step-by-step, showing how the hash map is populated and how the solution is found.
*   **Edge Cases**: Consider edge cases, though this problem's constraints simplify them. If duplicates were allowed, or multiple solutions, how would your approach change?
*   **Code Readability**: Use meaningful variable names (`num_map`, `complement`). Add comments where necessary, especially for non-obvious logic.

## Optimization and Alternatives

*   **Sorting (Less Optimal for this specific problem)**: One could sort the array first (O(n log n)). Then, use two pointers (one from the beginning, one from the end) to find the pair. However, sorting loses the original indices, so you'd need to store original indices along with values before sorting, or perform a second search for indices, which complicates things and might not be more efficient overall if you need original indices. The hash map approach is generally preferred for its simplicity and O(n) time complexity.
*   **Two Pointers on Sorted Array (If indices weren't required)**: If the problem asked to *check if a pair exists* or *return the numbers themselves* (not indices), sorting and using two pointers would be a valid O(n log n) time, O(1) or O(n) space (depending on whether in-place sort is allowed/possible) solution.

## Revision Checklist

- [ ] Understand the problem statement thoroughly.
- [ ] Identify the core task: finding two numbers that sum to a target.
- [ ] Consider brute-force approach and its limitations.
- [ ] Recognize the need for efficient lookups.
- [ ] Choose a hash map (dictionary) for O(1) average lookups.
- [ ] Implement the hash map logic: store `number: index`.
- [ ] Iterate through the array, calculate complement, and check map.
- [ ] Handle the case where complement is found.
- [ ] Handle the case where complement is not found (add current number to map).
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution clearly and concisely.
- [ ] Be prepared to discuss alternative approaches.

## Similar Problems

*   3Sum
*   4Sum
*   Container With Most Water
*   Three Sum Closest

## Tags
`Array` `Hash Map`